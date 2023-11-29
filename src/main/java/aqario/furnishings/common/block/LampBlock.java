package aqario.furnishings.common.block;

import aqario.furnishings.common.sound.FurnishingsSoundEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class LampBlock extends Block {
    public static final BooleanProperty LIT = Properties.LIT;

    public LampBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, true));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            boolean currentState = state.get(LIT);
            world.setBlockState(pos, state.with(LIT, !currentState), Block.NOTIFY_ALL);
            world.playSound(null, pos, currentState ? FurnishingsSoundEvents.BLOCK_LAMP_EXTINGUISH : FurnishingsSoundEvents.BLOCK_LAMP_LIGHT, SoundCategory.BLOCKS, 0.3f, 2.0f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
