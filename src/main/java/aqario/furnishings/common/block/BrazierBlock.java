package aqario.furnishings.common.block;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class BrazierBlock extends Block implements Waterloggable, Extinguishable {
    protected static final VoxelShape CHAIN_SHAPE = Block.createCuboidShape(6.5, 7, 6.5, 9.5, 16, 9.5);
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(2, 7, 2, 14, 15, 14);
    protected static final VoxelShape BASE_SHAPE = Block.createCuboidShape(3, 5, 3, 13, 7, 13);
    protected static final VoxelShape LEG_SHAPE = Stream.of(
        Block.createCuboidShape(7, 2, 2, 9, 5, 4),
        Block.createCuboidShape(12, 2, 7, 14, 5, 9),
        Block.createCuboidShape(7, 2, 12, 9, 5, 14),
        Block.createCuboidShape(2, 2, 7, 4, 5, 9),
        Block.createCuboidShape(7, 0, 0, 9, 3, 2),
        Block.createCuboidShape(14, 0, 7, 16, 3, 9),
        Block.createCuboidShape(7, 0, 14, 9, 3, 16),
        Block.createCuboidShape(0, 0, 7, 2, 3, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    protected static final VoxelShape STANDING_SHAPE = VoxelShapes.union(TOP_SHAPE, BASE_SHAPE, LEG_SHAPE);
    protected static final VoxelShape HANGING_SHAPE = VoxelShapes.union(CHAIN_SHAPE, TOP_SHAPE, BASE_SHAPE);
    protected static final Box FIRE_SHAPE = new Box(0.125, 0.4375, 0.125, 0.875, 1.0, 0.875);
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final boolean emitsParticles;
    private final int fireDamage;
    private final TagKey<Item> baseItemTag;

    public BrazierBlock(BrazierType type, AbstractBlock.Settings settings) {
        super(settings);
        this.emitsParticles = type.emitsParticles;
        this.fireDamage = type.fireDamage;
        this.baseItemTag = type.baseItemTag;
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, true).with(HANGING, false).with(WATERLOGGED, false));
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
                this.extinguish(context.getPlayer(), state, world, pos);
                context.getStack().damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
                return ActionResult.SUCCESS;
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
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!entity.isFireImmune() && state.get(LIT) && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            Box fire = FIRE_SHAPE.offset(pos);
            if (fire.intersects(entity.getBoundingBox())) {
                entity.damage(entity.getDamageSources().inFire(), this.fireDamage);
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        BlockPos blockPos = pos.offset(direction);
        return direction == Direction.DOWN ?
            world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction.getOpposite()) :
            Block.sideCoversSmallSquare(world, blockPos, direction.getOpposite());
    }

    protected static Direction attachedDirection(BlockState state) {
        return state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World worldAccess = ctx.getWorld();
        boolean bl = worldAccess.getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;
        for (Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState;
            if (direction.getAxis() != Direction.Axis.Y || !(blockState = this.getDefaultState().with(HANGING, direction == Direction.UP)).canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                continue;
            }
            return blockState.with(WATERLOGGED, bl).with(LIT, !bl);
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
        if (!state.get(LIT)) {
            return;
        }
        if (random.nextInt(10) == 0) {
            world.playSound((double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5f + random.nextFloat(), random.nextFloat() * 0.7f + 0.6f, false);
        }
        if (this.emitsParticles && random.nextInt(5) == 0) {
            for (int i = 0; i < random.nextInt(1) + 1; ++i) {
                world.addParticle(ParticleTypes.LAVA, (double) pos.getX() + 0.5, (double) pos.getY() + 1.0, (double) pos.getZ() + 0.5, random.nextFloat() / 2.0f, 5.0E-5, random.nextFloat() / 2.0f);
            }
        }
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
        builder.add(LIT, HANGING, WATERLOGGED);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public TagKey<Item> getBaseItemTag() {
        return this.baseItemTag;
    }

    public enum BrazierType {
        NORMAL(true, 1, ItemTags.COALS),
        SOUL(false, 2, ItemTags.SOUL_FIRE_BASE_BLOCKS);

        private final boolean emitsParticles;
        private final int fireDamage;
        private final TagKey<Item> baseItemTag;

        BrazierType(boolean emitsParticles, int fireDamage, TagKey<Item> baseItemTag) {
            this.emitsParticles = emitsParticles;
            this.fireDamage = fireDamage;
            this.baseItemTag = baseItemTag;
        }
    }
}
