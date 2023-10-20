package aqario.furnishings.common.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class FluidContainerBlockEntity extends BlockEntity {
	public FluidContainerBlockEntity(BlockPos pos, BlockState state) {
		super(FurnishingsBlockEntityType.FLUID_CONTAINER, pos, state);
	}
}
