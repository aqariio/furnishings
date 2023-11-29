package aqario.furnishings.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("deprecation")
public class BookBlock extends Block implements Waterloggable {
    protected static final Map<Integer, VoxelShape> SHAPES = Maps.newHashMap(
        ImmutableMap.of(
            1,
            createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D),
            2,
            createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
            3,
            createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
            4,
            createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D)
        )
    );
    public static final IntProperty BOOKS = IntProperty.of("books", 1, 4);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public BookBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(BOOKS, 1));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(BOOKS));
    }

    @Override
    public Item asItem() {
        return Items.BOOK;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.asItem());
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return ((!context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && state.get(BOOKS) < 4) || super.canReplace(state, context));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (state.isOf(this)) {
            return state.cycle(BOOKS);
        }
        return this.getDefaultState().with(WATERLOGGED, state.getFluidState().getFluid() == Fluids.WATER);
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
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BOOKS, WATERLOGGED);
    }

    public static ActionResult placeBookStack(PlayerEntity player, World world, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.BOOK)) {
            Block block = FurnishingsBlocks.BOOK;
            ItemPlacementContext ctx = new ItemPlacementContext(world, player, hand, stack, hit);
            BlockState state = block.getPlacementState(ctx);
            BlockPos pos = ctx.getBlockPos();
            if (state == null || !block.canPlaceAt(state, world, pos) || !world.getBlockState(pos).canReplace(ctx)) {
                return ActionResult.PASS;
            }
            world.setBlockState(pos, state, 11);
            BlockState placedState = world.getBlockState(pos);
            if (placedState.isOf(state.getBlock())) {
                placedState.getBlock().onPlaced(world, pos, placedState, player, stack);
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger(serverPlayerEntity, pos, stack);
                }
            }
            BlockSoundGroup blockSoundGroup = placedState.getSoundGroup();
            world.playSound(player, pos, placedState.getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
            world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.create(player, placedState));
            if (!(player.getAbilities()).creativeMode)
                stack.decrement(1);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
