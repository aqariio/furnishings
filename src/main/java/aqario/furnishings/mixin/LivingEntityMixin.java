package aqario.furnishings.mixin;

import aqario.furnishings.common.block.FurnishingsBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "applyClimbingSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isHoldingOntoLadder()Z", shift = At.Shift.AFTER), cancellable = true)
	private void furnishings$stopHoldingOntoScaffolding(Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
		if (this.getBlockStateAtPos().isOf(FurnishingsBlocks.IRON_SCAFFOLDING)) {
			double x = MathHelper.clamp(motion.x, -0.15F, 0.15F);
			double z = MathHelper.clamp(motion.z, -0.15F, 0.15F);
			double y = Math.max(motion.y, -0.15F);
			cir.setReturnValue(new Vec3d(x, y, z));
		}
	}
}
