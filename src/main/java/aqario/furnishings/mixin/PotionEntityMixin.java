package aqario.furnishings.mixin;

import aqario.furnishings.common.block.Extinguishable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionEntity.class)
public abstract class PotionEntityMixin extends ThrownItemEntity implements FlyingItemEntity {
	public PotionEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "extinguishFire", at = @At("TAIL"))
	private void furnishings$extinguishExtinguishable(BlockPos pos, CallbackInfo ci) {
		BlockState state = this.world.getBlockState(pos);
		Block block = state.getBlock();
		if (block instanceof Extinguishable && ((Extinguishable) block).isLit(state)) {
			((Extinguishable) block).extinguish(this.getOwner(), state, this.world, pos);
		}
	}
}
