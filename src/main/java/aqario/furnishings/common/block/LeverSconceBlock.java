package aqario.furnishings.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("deprecation")
public class LeverSconceBlock extends Block implements Waterloggable, Extinguishable {
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
	public static final BooleanProperty POWERED = Properties.POWERED;
	public static final BooleanProperty LIT = Properties.LIT;
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public LeverSconceBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false).with(FACING, Direction.NORTH).with(LIT, true).with(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return WALL_SHAPES.get(state.get(FACING));
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
		return blockState.isSideSolidFullSquare(world, blockPos, direction);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		ItemUsageContext context = new ItemUsageContext(player, hand, hit);
		if (!state.get(WATERLOGGED) && !state.get(LIT)) {
			if (itemStack.getItem() == Items.FLINT_AND_STEEL) {
				world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0f, world.getRandom().nextFloat() * 0.4f + 0.8f);
				world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
				setLit(world, state, pos, true);
				context.getStack().damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
				return ActionResult.SUCCESS;
			}
			if (itemStack.getItem() == Items.FIRE_CHARGE) {
				RandomGenerator random = world.getRandom();
				world.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f);
				world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
				setLit(world, state, pos, true);
				if (!player.isCreative()) {
					context.getStack().decrement(1);
				}
				return ActionResult.SUCCESS;
			}
		}
		if (world.isClient) {
			BlockState blockState = state.cycle(POWERED);
			if (blockState.get(POWERED)) {
				spawnRedstoneParticles(blockState, world, pos, 1.0F);
			}

			return ActionResult.SUCCESS;
		} else {
			BlockState blockState = this.togglePower(state, world, pos);
			float f = blockState.get(POWERED) ? 0.6F : 0.5F;
			world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
			world.emitGameEvent(player, blockState.get(POWERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
			return ActionResult.CONSUME;
		}
	}

	public BlockState togglePower(BlockState state, World world, BlockPos pos) {
		state = state.cycle(POWERED);
		world.setBlockState(pos, state, Block.NOTIFY_ALL);
		this.updateNeighbors(state, world, pos);
		return state;
	}

	private static void spawnRedstoneParticles(BlockState state, WorldAccess world, BlockPos pos, float alpha) {
		Direction direction = state.get(FACING).getOpposite();
		Direction direction2 = state.get(FACING).getOpposite();
		double d = (double)pos.getX() + 0.5 + 0.1 * (double)direction.getOffsetX() + 0.2 * (double)direction2.getOffsetX();
		double e = (double)pos.getY() + 0.5 + 0.1 * (double)direction.getOffsetY() + 0.2 * (double)direction2.getOffsetY();
		double f = (double)pos.getZ() + 0.5 + 0.1 * (double)direction.getOffsetZ() + 0.2 * (double)direction2.getOffsetZ();
		world.addParticle(new DustParticleEffect(DustParticleEffect.RED, alpha), d, e, f, 0.0, 0.0, 0.0);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
		if (!state.get(LIT)) {
			return;
		}
		if (state.get(POWERED)) {
			if (random.nextFloat() < 0.25F) {
				spawnRedstoneParticles(state, world, pos, 0.5F);
			}
			Direction direction = state.get(FACING);
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.65D;
			double z = pos.getZ() + 0.5D;
			Direction direction1 = direction.getOpposite();
			world.addParticle(ParticleTypes.SMOKE, x + 0.125D * direction1.getOffsetX(), y + 0.15D, z + 0.125D * direction1.getOffsetZ(), 0.0D, 0.0D, 0.0D);
		} else {
			Direction direction = state.get(FACING);
			double x = (double)pos.getX() + 0.5;
			double y = (double)pos.getY() + 0.7;
			double z = (double)pos.getZ() + 0.5;
			Direction direction2 = direction.getOpposite();
			world.addParticle(ParticleTypes.SMOKE, x + 0.27 * direction2.getOffsetX(), y + 0.22, z + 0.27 * direction2.getOffsetZ(), 0.0, 0.0, 0.0);
		}
	}

	@Override
	public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return state.get(POWERED) ? 15 : 0;
	}

	@Override
	public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return state.get(POWERED) && state.get(FACING) == direction ? 15 : 0;
	}

	@Override
	public boolean emitsRedstonePower(BlockState state) {
		return true;
	}

	private void updateNeighbors(BlockState state, World world, BlockPos pos) {
		world.updateNeighborsAlways(pos, this);
		world.updateNeighborsAlways(pos.offset(state.get(FACING).getOpposite()), this);
	}

	@Override
	public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
		if (!state.get(Properties.WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
			boolean bl = state.get(LIT);
			if (bl) {
				if (!world.isClient()) {
					world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0f, 1.0f);
				}
				extinguish(null, state, world, pos);
			}
			world.setBlockState(pos, state.with(WATERLOGGED, true).with(LIT, false), Block.NOTIFY_ALL);
			world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
			return true;
		}
		return false;
	}

	@Override
	public boolean isLit(BlockState state) {
		return !state.get(WATERLOGGED) && state.get(LIT);
	}

	private static void setLit(WorldAccess world, BlockState state, BlockPos pos, boolean lit) {
		world.setBlockState(pos, state.with(LIT, lit), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
	}

	@Override
	public void extinguish(@Nullable Entity entity, BlockState state, WorldAccess world, BlockPos pos) {
		setLit(world, state, pos, false);
		world.emitGameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
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
		builder.add(POWERED, LIT, FACING, WATERLOGGED);
	}
}
