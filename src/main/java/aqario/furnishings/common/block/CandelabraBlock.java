package aqario.furnishings.common.block;

import aqario.furnishings.common.block.enums.NoCeilingWallMountLocation;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.ToIntFunction;

public class CandelabraBlock extends HorizontalFacingBlock implements Waterloggable, Extinguishable {
	protected static final VoxelShape STANDING_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 14.0, 11.0);
	protected static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
		ImmutableMap.of(
		Direction.NORTH,
			Block.createCuboidShape(5.0, 0.0, 11.0, 11.0, 14.0, 16.0),
		Direction.SOUTH,
			Block.createCuboidShape(5.0, 0.0, 0.0, 11.0, 14.0, 5.0),
		Direction.WEST,
			Block.createCuboidShape(11.0, 0.0, 5.0, 16.0, 14.0, 11.0),
		Direction.EAST,
			Block.createCuboidShape(0.0, 0.0, 5.0, 5.0, 14.0, 11.0)
		)
	);
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final EnumProperty<NoCeilingWallMountLocation> FACE = EnumProperty.of("face", NoCeilingWallMountLocation.class);
	public static final IntProperty CANDLES = Properties.CANDLES;
	public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.get(LIT) ? 3 * state.get(CANDLES) : 0;
	private static final EnumMap<Direction, EnumMap<NoCeilingWallMountLocation, Int2ObjectMap<List<Vec3d>>>> PARTICLE_OFFSETS;

	public CandelabraBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false).with(CANDLES, 1).with(FACING, Direction.NORTH).with(FACE, NoCeilingWallMountLocation.FLOOR).with(WATERLOGGED, false));
	}

	@Override
	public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		if (!world.isClient && projectile.isOnFire() && !this.isLit(state)) {
			setLit(world, state, hit.getBlockPos(), true);
		}
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
		if (state.get(LIT)) {
			this.getParticleOffsets(state)
				.forEach(offset -> spawnCandleParticles(world, offset.add(pos.getX(), pos.getY(), pos.getZ()), random));
		}
	}

	private static void spawnCandleParticles(World world, Vec3d vec3d, RandomGenerator random) {
		float f = random.nextFloat();
		if (f < 0.3F) {
			world.addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
			if (f < 0.17F) {
				world.playSound(
					vec3d.x + 0.5,
					vec3d.y + 0.5,
					vec3d.z + 0.5,
					SoundEvents.BLOCK_CANDLE_AMBIENT,
					SoundCategory.BLOCKS,
					1.0F + random.nextFloat(),
					random.nextFloat() * 0.7F + 0.3F,
					false
				);
			}
		}

		world.addParticle(ParticleTypes.SMALL_FLAME, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return state.get(FACE).equals(NoCeilingWallMountLocation.FLOOR) ? STANDING_SHAPE : SHAPES.get(state.get(FACING));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		ItemUsageContext context = new ItemUsageContext(player, hand, hit);
		if (state.get(LIT)) {
			if (player.getAbilities().allowModifyWorld && player.getStackInHand(hand).isEmpty()) {
				extinguish(player, state, world, pos);
				return ActionResult.success(world.isClient);
			}
		}
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
		return ActionResult.PASS;
	}

	@Override
	public void extinguish(@Nullable Entity entity, BlockState state, WorldAccess world, BlockPos pos) {
		setLit(world, state, pos, false);
		if (state.getBlock() instanceof CandelabraBlock) {
			((CandelabraBlock)state.getBlock())
				.getParticleOffsets(state)
				.forEach(
					offset -> world.addParticle(
						ParticleTypes.SMOKE, (double)pos.getX() + offset.getX(), (double)pos.getY() + offset.getY(), (double)pos.getZ() + offset.getZ(), 0.0, 0.1F, 0.0
					)
				);
		}

		world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
		world.emitGameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
	}

	@Override
	public boolean isLit(BlockState state) {
		return !state.get(WATERLOGGED) && state.get(LIT);
	}

	private static void setLit(WorldAccess world, BlockState state, BlockPos pos, boolean lit) {
		world.setBlockState(pos, state.with(LIT, lit), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
	}

	private Iterable<Vec3d> getParticleOffsets(BlockState state) {
		Direction direction = state.get(FACING);
		NoCeilingWallMountLocation face = state.get(FACE);
		int candles = state.get(CANDLES);
        return PARTICLE_OFFSETS.get(direction).get(face).get(candles);
	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && state.get(CANDLES) < 4 || super.canReplace(state, context);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState replacedState = ctx.getWorld().getBlockState(ctx.getBlockPos());
		if (replacedState.isOf(this)) {
			return replacedState.cycle(CANDLES);
		}
		for (Direction direction : ctx.getPlacementDirections()) {
			BlockState blockState = this.getDefaultState();
			if (direction == Direction.UP) {
				continue;
			}
			if (direction.getAxis().isHorizontal()) {
				blockState = blockState.with(FACE, NoCeilingWallMountLocation.WALL).with(FACING, direction.getOpposite());
			} else {
				blockState = blockState.with(FACE, NoCeilingWallMountLocation.FLOOR).with(FACING, ctx.getPlayerFacing());
			}
			if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
				return blockState.with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
			}
		}
		return null;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(CANDLES, LIT, FACING, FACE, WATERLOGGED);
	}

	@Override
	public BlockState getStateForNeighborUpdate(
		BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
	) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return getDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos)
			? Blocks.AIR.getDefaultState()
			: super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
		if (!state.get(WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
			BlockState blockState = state.with(WATERLOGGED, Boolean.TRUE);
			if (state.get(LIT)) {
				extinguish(null, blockState, world, pos);
			} else {
				world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
			}

			world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockPos blockPos = pos.offset(getDirection(state).getOpposite());
		return state.get(FACE).equals(NoCeilingWallMountLocation.FLOOR) ? sideCoversSmallSquare(world, pos.down(), Direction.UP) : world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, getDirection(state));
	}

	protected static Direction getDirection(BlockState state) {
        if (Objects.requireNonNull(state.get(FACE)) == NoCeilingWallMountLocation.FLOOR) {
            return Direction.UP;
        }
        return state.get(FACING);
    }

	public static Vec3d rotateVec3d(Vec3d vec, Direction dir) {
		double cos = 1;
		double sin = 0;
		switch (dir) {
			case SOUTH -> {
				cos = -1;
				sin = 0;
			}
			case WEST -> {
				cos = 0;
				sin = 1;
			}
			case EAST -> {
				cos = 0;
				sin = -1;
			}
		}
		double d0 = vec.x * cos + vec.z * sin;
		double d1 = vec.y;
		double d2 = vec.z * cos - vec.x * sin;
		return new Vec3d(d0, d1, d2);
	}

	static {
		PARTICLE_OFFSETS = new EnumMap<>(Direction.class);
		EnumMap<NoCeilingWallMountLocation, Int2ObjectMap<List<Vec3d>>> temp = new EnumMap<>(NoCeilingWallMountLocation.class);
		{
			Int2ObjectMap<List<Vec3d>> int2ObjectMap = new Int2ObjectOpenHashMap<>();
			int2ObjectMap.put(1, List.of(new Vec3d(0.5, 0.6875, 0.5)));
			int2ObjectMap.put(2, List.of(new Vec3d(0.3125, 0.875, 0.5), new Vec3d(0.6875, 0.875, 0.5)));
			int2ObjectMap.put(3, List.of(new Vec3d(0.1875, 0.9375, 0.5), new Vec3d(0.5, 0.9375, 0.5), new Vec3d(0.8125, 0.9375, 0.5)));
			int2ObjectMap.put(4, List.of(new Vec3d(0.1875, 1, 0.5), new Vec3d(0.8125, 1, 0.5), new Vec3d(0.5, 0.9375, 0.25), new Vec3d(0.5, 0.9375, 0.75)));
			temp.put(NoCeilingWallMountLocation.FLOOR, Int2ObjectMaps.unmodifiable(int2ObjectMap));
		}
		{
			Int2ObjectMap<List<Vec3d>> int2ObjectMap = new Int2ObjectOpenHashMap<>();
			int2ObjectMap.put(1, List.of(new Vec3d(0.5, 0.9375, 0.1875)));
			int2ObjectMap.put(2, List.of(new Vec3d(0.3125, 0.9375, 0.1875), new Vec3d(0.6875, 0.9375, 0.1875)));
			int2ObjectMap.put(3, List.of(new Vec3d(0.8125, 0.9375, 0.1875), new Vec3d(0.1875, 0.9375, 0.1875), new Vec3d(0.5, 0.9375, 0.25)));
			int2ObjectMap.put(4, List.of(new Vec3d(0.1875, 1, 0.1875), new Vec3d(0.8125, 1, 0.1875), new Vec3d(0.3125, 0.875, 0.3125), new Vec3d(0.6875, 0.875, 0.3125)));
			temp.put(NoCeilingWallMountLocation.WALL, Int2ObjectMaps.unmodifiable(int2ObjectMap));
		}
		for (Direction direction : Direction.values()) {
			EnumMap<NoCeilingWallMountLocation, Int2ObjectMap<List<Vec3d>>> newFaceMap = new EnumMap<>(NoCeilingWallMountLocation.class);
			for (var faceList : temp.entrySet()) {
				Int2ObjectMap<List<Vec3d>> newCandleList = new Int2ObjectOpenHashMap<>();
				newCandleList.defaultReturnValue(List.of());
				int c = 1;
				var oldVec = faceList.getValue();
				for (int i = 1; i < 5; i++) {
					ArrayList<Vec3d> vectorsList = new ArrayList<>();
					for (var vec : oldVec.get(i)) {
						vectorsList.add(rotateVec3d(vec.subtract(0.5, 0.5, 0.5), direction.getOpposite())
							.add(0.5, 0.5, 0.5));
					}
					newCandleList.put(c++, ImmutableList.copyOf(vectorsList));
				}
				newFaceMap.put(faceList.getKey(), Int2ObjectMaps.unmodifiable(newCandleList));
			}
			PARTICLE_OFFSETS.put(direction, newFaceMap);
		}
	}
}
