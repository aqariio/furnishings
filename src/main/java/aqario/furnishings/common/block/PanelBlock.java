package aqario.furnishings.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Map;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class PanelBlock extends Block implements Waterloggable {
    protected static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
        ImmutableMap.of(
            Direction.NORTH,
            Block.createCuboidShape(0, 0, 15, 16, 16, 16),
            Direction.SOUTH,
            Block.createCuboidShape(0, 0, 0, 16, 16, 1),
            Direction.WEST,
            Block.createCuboidShape(15, 0, 0, 16, 16, 16),
            Direction.EAST,
            Block.createCuboidShape(0, 0, 0, 1, 16, 16),
            Direction.UP,
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),
            Direction.DOWN,
            Block.createCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0, 16.0)
        )
    );
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public PanelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            if (state.get(FACING) == stateFrom.get(FACING)) {
                return true;
            }
            if (direction == stateFrom.get(FACING)) {
                return true;
            }
        }

        return super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(FACING));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World worldAccess = ctx.getWorld();
        boolean waterlogged = worldAccess.getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;
        if (Objects.requireNonNull(ctx.getPlayer()).isSneaking()) {
            if (ctx.getSide().getAxis().isHorizontal()) {
                return this.getDefaultState().with(FACING, ctx.getHitPos().y - (double) ctx.getBlockPos().getY() > 0.5 ? Direction.DOWN : Direction.UP).with(WATERLOGGED, waterlogged);
            }

            return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(WATERLOGGED, waterlogged);
        }

        return this.getDefaultState().with(FACING, ctx.getSide()).with(WATERLOGGED, waterlogged);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }
}
