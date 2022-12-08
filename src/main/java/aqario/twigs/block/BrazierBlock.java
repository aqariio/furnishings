package aqario.twigs.block;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class BrazierBlock extends Block implements Waterloggable {
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(2, 7, 2, 14, 15, 14);
    protected static final VoxelShape BASE_SHAPE = Block.createCuboidShape(3, 5, 3, 13, 7, 13);
    protected static final VoxelShape TOP_LEG_SHAPE = Block.createCuboidShape(2, 2, 2, 14, 5, 14);
    protected static final VoxelShape BOTTOM_LEG_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 3, 16);
    protected static final VoxelShape HEAD = VoxelShapes.combineAndSimplify(TOP_SHAPE, BASE_SHAPE, BooleanBiFunction.OR);
    protected static final VoxelShape BASE = VoxelShapes.combineAndSimplify(TOP_LEG_SHAPE, BOTTOM_LEG_SHAPE, BooleanBiFunction.OR);
    protected static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(HEAD, BASE, BooleanBiFunction.OR);
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(2, 7, 2, 14, 16, 14);
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape SMOKEY_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    private final boolean emitsParticles;
    private final int fireDamage;

    public BrazierBlock(boolean emitsParticles, int fireDamage, AbstractBlock.Settings settings) {
        super(settings);
        this.emitsParticles = emitsParticles;
        this.fireDamage = fireDamage;
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(LIT, true)).with(WATERLOGGED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        ItemUsageContext context = new ItemUsageContext(player, hand, hit);
        if (state.get(LIT)) {
            if (itemStack.getItem() instanceof ShovelItem) {
                if (!world.isClient()) {
                    world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, pos, 0);
                }
                BrazierBlock.extinguish(context.getPlayer(), world, pos, state);
                context.getStack().damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
                world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                return ActionResult.SUCCESS;
            }

        }
        if (!state.get(WATERLOGGED) && !state.get(LIT)) {
            if (itemStack.getItem() == Items.FLINT_AND_STEEL) {;
                world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0f, world.getRandom().nextFloat() * 0.4f + 0.8f);
                world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
                world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                context.getStack().damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
                return ActionResult.SUCCESS;
            }
            if (itemStack.getItem() == Items.FIRE_CHARGE) {
                Random random = (Random) world.getRandom();
                world.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f);
                world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
                world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                if (!player.isCreative()) {
                    context.getStack().decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!entity.isFireImmune() && state.get(LIT) && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            if (entity.getY() >= state.getCollisionShape(world, pos).getMax(Direction.Axis.Y) + pos.getY() - 0.1f) {
                entity.damage(DamageSource.IN_FIRE, this.fireDamage);
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos;
        World worldAccess = ctx.getWorld();
        boolean bl = worldAccess.getFluidState(blockPos = ctx.getBlockPos()).getFluid() == Fluids.WATER;
        return ((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, bl)).with(LIT, !bl));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
            world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }
        if (random.nextInt(10) == 0) {
            world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5f + random.nextFloat(), random.nextFloat() * 0.7f + 0.6f, false);
        }
        if (this.emitsParticles && random.nextInt(5) == 0) {
            for (int i = 0; i < random.nextInt(1) + 1; ++i) {
                world.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5, (double)pos.getY() + 1.0, (double)pos.getZ() + 0.5, random.nextFloat() / 2.0f, 5.0E-5, random.nextFloat() / 2.0f);
            }
        }
    }

    public static void extinguish(@Nullable Entity entity, WorldAccess world, BlockPos pos, BlockState state) {
        world.emitGameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
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
            world.setBlockState(pos, (BlockState)((BlockState)state.with(WATERLOGGED, true)).with(LIT, false), Block.NOTIFY_ALL);
            world.createAndScheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            return true;
        }
        return false;
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos blockPos = hit.getBlockPos();
        if (!world.isClient && projectile.isOnFire() && projectile.canModifyAt(world, blockPos) && !state.get(LIT) && !state.get(WATERLOGGED)) {
            world.setBlockState(blockPos, (BlockState)state.with(Properties.LIT, true), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
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
        builder.add(LIT, WATERLOGGED);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public static boolean canBeLit(BlockState state2) {
        return state2.isIn(BlockTags.CAMPFIRES, state -> state.contains(WATERLOGGED) && state.contains(LIT)) && !state2.get(WATERLOGGED) && !state2.get(LIT);
    }
}
