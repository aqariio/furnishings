package aqario.furnishings.common.item;

import aqario.furnishings.common.block.IronScaffoldingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IronScaffoldingItem extends BlockItem {
    public IronScaffoldingItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    @Nullable
    public ItemPlacementContext getPlacementContext(ItemPlacementContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(this.getBlock())) {
            Direction direction = context.shouldCancelInteraction() ? (context.hitsInsideBlock() ? context.getSide().getOpposite() : context.getSide()) : (context.getSide() == Direction.UP ? context.getPlayerFacing() : Direction.UP);
            int i = 0;
            BlockPos.Mutable mutable = blockPos.mutableCopy().move(direction);
            while (i < IronScaffoldingBlock.MAX_DISTANCE) {
                if (!world.isClient && !world.isInBuildLimit(mutable)) {
                    PlayerEntity playerEntity = context.getPlayer();
                    int j = world.getTopY();
                    if (!(playerEntity instanceof ServerPlayerEntity) || mutable.getY() < j) {
                        break;
                    }
                    playerEntity.sendMessage(Text.translatable("build.tooHigh", j - 1).formatted(Formatting.RED), true);
                    break;
                }
                blockState = world.getBlockState(mutable);
                if (!blockState.isOf(this.getBlock())) {
                    if (!blockState.canReplace(context)) {
                        break;
                    }
                    return ItemPlacementContext.offset(context, mutable, direction);
                }
                mutable.move(direction);
                if (!direction.getAxis().isHorizontal()) {
                    continue;
                }
                ++i;
            }
            return null;
        }
        if (IronScaffoldingBlock.calculateDistance(world, blockPos) == IronScaffoldingBlock.MAX_DISTANCE) {
            return null;
        }
        return context;
    }

    @Override
    protected boolean checkStatePlacement() {
        return false;
    }
}
