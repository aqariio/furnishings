package aqario.furnishings.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public interface Extinguishable {
    boolean isLit(BlockState state);

    void extinguish(@Nullable Entity entity, BlockState state, WorldAccess world, BlockPos pos);
}
