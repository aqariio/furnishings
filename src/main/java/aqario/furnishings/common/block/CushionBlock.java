package aqario.furnishings.common.block;

import aqario.furnishings.common.entity.FurnishingsEntityType;
import aqario.furnishings.common.entity.SeatEntity;
import aqario.furnishings.common.sound.FurnishingsSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CushionBlock extends Block implements Waterloggable {
	public static final BooleanProperty OCCUPIED = Properties.OCCUPIED;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);

	public CushionBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(OCCUPIED, false).with(WATERLOGGED, false));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		Item item = player.getStackInHand(hand).getItem();
		if (item instanceof BlockItem blockItem) {
            if (blockItem.getBlock() instanceof CushionBlock) {
				return ActionResult.PASS;
			}
		}
		if (!hit.getSide().equals(Direction.DOWN)) {
			if (unseat(state, world, pos)) {
				return ActionResult.success(world.isClient);
			}
			if (seat(player, world, pos)) {
				return ActionResult.success(world.isClient);
			}
		}
		return ActionResult.PASS;
	}



	public boolean unseat(BlockState state, World world, BlockPos pos) {
		if (state.get(OCCUPIED)) {
			List<SeatEntity> list = world.getEntitiesByClass(SeatEntity.class, new Box(pos), entity -> true);
			boolean unseated = false;
			for (SeatEntity entity : list) {
				if (!(entity.getFirstPassenger() instanceof PlayerEntity) &&
					entity.getFirstPassenger() != null) {
					entity.delete();
					unseated = true;
				}
			}
			return unseated;
		}
		return false;
	}

	public boolean seat(LivingEntity livingEntity, World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (!(state.getBlock() instanceof CushionBlock)) {
			return false;
		}
		if (livingEntity.getVehicle() != null && !(livingEntity instanceof PlayerEntity)) {
			return false;
		}
		if (livingEntity.getVehicle() != null) {
			Entity entity = livingEntity.getVehicle();
			if (entity instanceof SeatEntity seatEntity) {
				if (seatEntity.getCushionPos() != null && seatEntity.getCushionPos().equals(pos)) {
					return false;
				}
			}
		}
		if (state.get(OCCUPIED)) {
			if (livingEntity instanceof PlayerEntity player) {
				player.sendMessage(Text.translatable("block.furnishings.cushion.occupied"), true);
			}
			return false;
		}
		Vec3d seatPos = new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.35D, pos.getZ() + 0.5D);
		if (!state.get(WATERLOGGED) && livingEntity.getPos().distanceTo(seatPos) < 4.0D && !livingEntity.isSneaking()) {
			SeatEntity seatEntity = FurnishingsEntityType.SEAT.create(world);
			if (seatEntity == null) {
				return false;
			}
			seatEntity.setCushionPos(new BlockPos(pos));
			seatEntity.setPosition(seatPos);
			seatEntity.setYaw(livingEntity.getYaw());
			world.spawnEntity(seatEntity);
			if (!world.isClient) {
				boolean riding = livingEntity.startRiding(seatEntity);
				if (!riding) {
					seatEntity.discard();
					return false;
				}
			}
			world.playSound(null, pos, FurnishingsSoundEvents.BLOCK_CUSHION_SIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return true;
		}
		return false;
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}

	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		super.onLandedUpon(world, state, pos, entity, fallDistance * 0.5F);
	}

	private void bounceEntity(Entity entity) {
		Vec3d vec3d = entity.getVelocity();
		if (vec3d.y < 0.0) {
			double d = entity instanceof LivingEntity ? 1.0 : 0.8;
			entity.setVelocity(vec3d.x, -vec3d.y * 0.66F * d, vec3d.z);
		}
	}

	@Override
	public void onEntityLand(BlockView world, Entity entity) {
		if (entity.bypassesLandingEffects()) {
			super.onEntityLand(world, entity);
		} else {
			this.bounceEntity(entity);
		}
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(OCCUPIED, WATERLOGGED);
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

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
	}
}
