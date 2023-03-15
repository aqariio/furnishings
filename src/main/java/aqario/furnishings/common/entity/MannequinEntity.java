package aqario.furnishings.common.entity;

import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MannequinEntity extends PoseableStandEntity {
	public MannequinEntity(EntityType<? extends PoseableStandEntity> entityType, World world) {
		super(entityType, world, Material.WOOD);
	}

	@Override
	public ItemStack getItem() {
		return null;
	}

	@Override
	public ParticleEffect getParticle() {
		return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.getDefaultState());
	}

	@Override
	public SoundEvent getPlaceSound() {
		return SoundEvents.BLOCK_WOOD_PLACE;
	}

	@Override
	public FallSounds getFallSounds() {
		return new LivingEntity.FallSounds(SoundEvents.BLOCK_WOOD_FALL, SoundEvents.BLOCK_WOOD_FALL);
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_WOOD_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_WOOD_BREAK;
	}
}
