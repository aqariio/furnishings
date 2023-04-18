package aqario.furnishings.common.entity;

import aqario.furnishings.client.gui.screen.PoseableStandScreen;
import aqario.furnishings.client.gui.screen.StatueScreen;
import aqario.furnishings.common.item.FurnishingsItems;
import aqario.furnishings.common.screen.PoseableStandScreenHandler;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StatueEntity extends PoseableStandEntity {
	public StatueEntity(EntityType<? extends PoseableStandEntity> entityType, World world) {
		super(entityType, world, Material.STONE);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(FurnishingsItems.STATUE);
	}

	@Override
	public ParticleEffect getParticle() {
		return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState());
	}

	@Override
	public SoundEvent getPlaceSound() {
		return SoundEvents.BLOCK_STONE_PLACE;
	}

	@Override
	public LivingEntity.FallSounds getFallSounds() {
		return new LivingEntity.FallSounds(SoundEvents.BLOCK_STONE_FALL, SoundEvents.BLOCK_STONE_FALL);
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_STONE_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_STONE_BREAK;
	}

	@Override
	public PoseableStandScreen getScreen(PoseableStandScreenHandler screenHandler, PlayerInventory inventory) {
		return new StatueScreen(screenHandler, inventory, this);
	}
}
