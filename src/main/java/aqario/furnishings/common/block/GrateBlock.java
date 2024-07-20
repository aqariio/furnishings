package aqario.furnishings.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

@SuppressWarnings("deprecation")
public class GrateBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
    protected static final VoxelShape BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);

    public GrateBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, BlockHalf.BOTTOM).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) && state.get(HALF) == stateFrom.get(HALF) || super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction direction = ctx.getSide();
        BlockState blockState = this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        if (!ctx.canReplaceExisting() && direction.getAxis().isHorizontal()) {
            return blockState.with(HALF, ctx.getHitPos().y - (double) ctx.getBlockPos().getY() > 0.5 ? BlockHalf.TOP : BlockHalf.BOTTOM);
        }
        if (ctx.getPlayer() != null && ctx.getPlayer().isSneaking()) {
            return blockState.with(HALF, direction == Direction.UP ? BlockHalf.TOP : BlockHalf.BOTTOM);
        }
        return blockState.with(HALF, direction == Direction.UP ? BlockHalf.BOTTOM : BlockHalf.TOP);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALF) == BlockHalf.TOP ? TOP_SHAPE : BOTTOM_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }
}
