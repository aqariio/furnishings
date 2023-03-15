package aqario.furnishings.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SconceBlock extends Block implements Waterloggable {
	protected static final VoxelShape STANDING_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 11.0, 10.0);
	protected static final Map<Direction, VoxelShape> WALL_SHAPES = Maps.newEnumMap(
			ImmutableMap.of(
					Direction.NORTH,
					Block.createCuboidShape(6.0, 2.0, 10.0, 10.0, 13.0, 16.0),
					Direction.SOUTH,
					Block.createCuboidShape(6.0, 2.0, 0.0, 10.0, 13.0, 6.0),
					Direction.WEST,
					Block.createCuboidShape(10.0, 2.0, 6.0, 16.0, 13.0, 10.0),
					Direction.EAST,
					Block.createCuboidShape(0.0, 2.0, 6.0, 6.0, 13.0, 10.0)
			)
	);
	public static final BooleanProperty LIT = Properties.LIT;
	public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP);
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	protected final ParticleEffect particle;

	public SconceBlock(Settings settings, ParticleEffect particle) {
		super(settings);
		this.particle = particle;
		this.setDefaultState(this.stateManager.getDefaultState().with(LIT, true).with(FACING, Direction.UP).with(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return state.get(FACING).equals(Direction.UP) ? STANDING_SHAPE : WALL_SHAPES.get(state.get(FACING));
	}

	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		World worldAccess = ctx.getWorld();
		boolean bl = worldAccess.getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;
		BlockState blockState = this.getDefaultState();
		WorldView worldView = ctx.getWorld();
		BlockPos blockPos = ctx.getBlockPos();
		Direction[] directions = ctx.getPlacementDirections();

		for(Direction direction : directions) {
			if (direction.getAxis().isHorizontal()) {
				Direction direction2 = direction.getOpposite();
				blockState = blockState.with(FACING, direction2).with(WATERLOGGED, bl).with(LIT, !bl);
				if (blockState.canPlaceAt(worldView, blockPos)) {
					return blockState;
				}
			}
			return blockState.with(FACING, Direction.UP).with(WATERLOGGED, bl).with(LIT, !bl);
		}
		return null;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return direction.equals(Direction.UP) ? sideCoversSmallSquare(world, pos.down(), Direction.UP) : blockState.isSideSolidFullSquare(world, blockPos, direction);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
		if (!state.get(LIT)) {
			return;
		}
		if (state.get(FACING).equals(Direction.UP)) {
			double d = (double)pos.getX() + 0.5;
			double e = (double)pos.getY() + 0.7;
			double f = (double)pos.getZ() + 0.5;
			world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
			world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
		} else {
			Direction direction = state.get(FACING);
			double d = (double)pos.getX() + 0.5;
			double e = (double)pos.getY() + 0.7;
			double f = (double)pos.getZ() + 0.5;
			double g = 0.22;
			double h = 0.27;
			Direction direction2 = direction.getOpposite();
			world.addParticle(ParticleTypes.SMOKE, d + 0.27 * (double)direction2.getOffsetX(), e + 0.22, f + 0.27 * (double)direction2.getOffsetZ(), 0.0, 0.0, 0.0);
			world.addParticle(this.particle, d + 0.27 * (double)direction2.getOffsetX(), e + 0.22, f + 0.27 * (double)direction2.getOffsetZ(), 0.0, 0.0, 0.0);
		}
	}

	@Override
	public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
		if (!state.get(Properties.WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
			boolean bl = state.get(LIT);
			if (bl) {
				if (!world.isClient()) {
					world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0f, 1.0f);
				}
				BrazierBlock.extinguish(null, world, pos, state);
			}
			world.setBlockState(pos, state.with(WATERLOGGED, true).with(LIT, false), Block.NOTIFY_ALL);
			world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
			return true;
		}
		return false;
	}

	@Override
	public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		BlockPos blockPos = hit.getBlockPos();
		if (!world.isClient && projectile.isOnFire() && projectile.canModifyAt(world, blockPos) && !state.get(LIT) && !state.get(WATERLOGGED)) {
			world.setBlockState(blockPos, state.with(Properties.LIT, true), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
		}
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
		builder.add(LIT, FACING, WATERLOGGED);
	}
}
