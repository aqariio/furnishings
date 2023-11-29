package aqario.furnishings.common.block;

import aqario.furnishings.common.block.entity.FluidContainerBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public abstract class FluidContainerBlock extends BlockWithEntity implements Waterloggable {
    public FluidContainerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FluidContainerBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
