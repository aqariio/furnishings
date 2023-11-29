package aqario.furnishings.common.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class PedestalBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty UP = Properties.UP;
    public static final BooleanProperty DOWN = Properties.DOWN;
    protected static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES.entrySet().stream().filter(entry -> entry.getKey().getAxis().isVertical()).collect(Util.toMap());
    protected static final VoxelShape SHAPE = Stream.of(
        Block.createCuboidShape(0, 14, 0, 16, 16, 16),
        Block.createCuboidShape(2, 2, 2, 14, 14, 14),
        Block.createCuboidShape(0, 0, 0, 16, 2, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    protected static final VoxelShape BOTTOM_SHAPE = Stream.of(
        Block.createCuboidShape(2, 2, 2, 14, 16, 14),
        Block.createCuboidShape(0, 0, 0, 16, 2, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    protected static final VoxelShape TOP_SHAPE = Stream.of(
        Block.createCuboidShape(0, 14, 0, 16, 16, 16),
        Block.createCuboidShape(2, 0, 2, 14, 14, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    protected static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(2, 0, 2, 14, 16, 14);

    public PedestalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(UP, false).with(DOWN, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockPos upPos = blockPos.up();
        BlockPos downPos = blockPos.down();
        BlockState upState = worldAccess.getBlockState(upPos);
        BlockState downState = worldAccess.getBlockState(downPos);
        boolean bl = worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(UP, this.canConnect(upState)).with(DOWN, this.canConnect(downState)).with(WATERLOGGED, bl);
    }

    public boolean canConnect(BlockState state) {
        return !cannotConnect(state) && state.getBlock() instanceof PedestalBlock;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction.getAxis().isVertical()) {
            return state.with(FACING_PROPERTIES.get(direction), this.canConnect(neighborState));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(UP) && state.get(DOWN)) {
            return MIDDLE_SHAPE;
        }
        if (state.get(DOWN)) {
            return TOP_SHAPE;
        }
        if (state.get(UP)) {
            return BOTTOM_SHAPE;
        }
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, UP, DOWN);
    }
}
