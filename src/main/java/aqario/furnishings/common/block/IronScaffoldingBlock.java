package aqario.furnishings.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class IronScaffoldingBlock extends Block implements Waterloggable {
    private static final VoxelShape NORMAL_OUTLINE_SHAPE;
    private static final VoxelShape BOTTOM_OUTLINE_SHAPE;
    private static final VoxelShape BOTTOM_COLLISION_SHAPE;
    private static final VoxelShape OUTLINE_SHAPE;
    public static final int MAX_DISTANCE = 31;
    public static final IntProperty DISTANCE = IntProperty.of("distance", 0, MAX_DISTANCE);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty BOTTOM = Properties.BOTTOM;

    public IronScaffoldingBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(DISTANCE, MAX_DISTANCE).with(WATERLOGGED, false).with(BOTTOM, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, WATERLOGGED, BOTTOM);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!context.isHolding(state.getBlock().asItem())) {
            return state.get(BOTTOM) ? BOTTOM_OUTLINE_SHAPE : NORMAL_OUTLINE_SHAPE;
        }
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getStack().isOf(this.asItem());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        int distance = calculateDistance(world, blockPos);
        return this.getDefaultState()
            .with(WATERLOGGED, world.getFluidState(blockPos).getFluid() == Fluids.WATER)
            .with(DISTANCE, distance)
            .with(BOTTOM, this.shouldBeBottom(world, blockPos, distance));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, 1);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(
        BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (!world.isClient()) {
            world.scheduleBlockTick(pos, this, 1);
        }

        return state;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        int distance = calculateDistance(world, pos);
        BlockState blockState = state.with(DISTANCE, distance).with(BOTTOM, this.shouldBeBottom(world, pos, distance));
        if (blockState.get(DISTANCE) == MAX_DISTANCE) {
            if (state.get(DISTANCE) == MAX_DISTANCE) {
                FallingBlockEntity.fall(world, pos, blockState);
            } else {
                world.breakBlock(pos, true);
            }
        } else if (state != blockState) {
            world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return calculateDistance(world, pos) < MAX_DISTANCE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isAbove(VoxelShapes.fullCube(), pos, true) && !context.isDescending()) {
            return NORMAL_OUTLINE_SHAPE;
        } else {
            return state.get(DISTANCE) != 0 && state.get(BOTTOM) && context.isAbove(OUTLINE_SHAPE, pos, true) ? BOTTOM_COLLISION_SHAPE : VoxelShapes.empty();
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    private boolean shouldBeBottom(BlockView world, BlockPos pos, int distance) {
        return distance > 0 && !world.getBlockState(pos.down()).isOf(this);
    }

    public static int calculateDistance(BlockView world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy().move(Direction.DOWN);
        BlockState blockState = world.getBlockState(mutable);
        int distance = MAX_DISTANCE;
        if (blockState.isOf(FurnishingsBlocks.IRON_SCAFFOLDING)) {
            distance = blockState.get(DISTANCE);
        } else if (blockState.isSideSolidFullSquare(world, mutable, Direction.UP)) {
            return 0;
        }

        for(Direction direction : Direction.Type.HORIZONTAL) {
            BlockState blockState2 = world.getBlockState(mutable.set(pos, direction));
            if (blockState2.isOf(FurnishingsBlocks.IRON_SCAFFOLDING)) {
                distance = Math.min(distance, blockState2.get(DISTANCE) + 1);
                if (distance == 1) {
                    break;
                }
            }
        }

        return distance;
    }

    static {
        BOTTOM_COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);
        OUTLINE_SHAPE = VoxelShapes.fullCube().offset(0.0, -1.0, 0.0);
        VoxelShape voxelShape = Block.createCuboidShape(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
        VoxelShape voxelShape2 = Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 2.0);
        VoxelShape voxelShape3 = Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 2.0);
        VoxelShape voxelShape4 = Block.createCuboidShape(0.0, 0.0, 14.0, 2.0, 16.0, 16.0);
        VoxelShape voxelShape5 = Block.createCuboidShape(14.0, 0.0, 14.0, 16.0, 16.0, 16.0);
        VoxelShape voxelShape6 = Block.createCuboidShape(1.0, 1.0, 1.0, 15.0, 13.0, 15.0);
        NORMAL_OUTLINE_SHAPE = VoxelShapes.union(voxelShape, voxelShape2, voxelShape3, voxelShape4, voxelShape5, voxelShape6);
        VoxelShape voxelShape7 = Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 2.0, 16.0);
        VoxelShape voxelShape8 = Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 2.0, 16.0);
        VoxelShape voxelShape9 = Block.createCuboidShape(0.0, 0.0, 14.0, 16.0, 2.0, 16.0);
        VoxelShape voxelShape10 = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 2.0);
        BOTTOM_OUTLINE_SHAPE = VoxelShapes.union(BOTTOM_COLLISION_SHAPE, NORMAL_OUTLINE_SHAPE, voxelShape7, voxelShape8, voxelShape9, voxelShape10);
    }
}
