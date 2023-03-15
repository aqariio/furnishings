package aqario.furnishings.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public abstract class PoseableStandEntity extends LivingEntity {
	private static final EulerAngle DEFAULT_HEAD_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
	private static final EulerAngle DEFAULT_BODY_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
	private static final EulerAngle DEFAULT_LEFT_ARM_ROTATION = new EulerAngle(-10.0F, 0.0F, -10.0F);
	private static final EulerAngle DEFAULT_RIGHT_ARM_ROTATION = new EulerAngle(-10.0F, 0.0F, 10.0F);
	private static final EulerAngle DEFAULT_LEFT_LEG_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
	private static final EulerAngle DEFAULT_RIGHT_LEG_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
	public static final TrackedData<EulerAngle> TRACKER_HEAD_ROTATION = DataTracker.registerData(PoseableStandEntity.class, TrackedDataHandlerRegistry.ROTATION);
	public static final TrackedData<EulerAngle> TRACKER_BODY_ROTATION = DataTracker.registerData(PoseableStandEntity.class, TrackedDataHandlerRegistry.ROTATION);
	public static final TrackedData<EulerAngle> TRACKER_LEFT_ARM_ROTATION = DataTracker.registerData(PoseableStandEntity.class, TrackedDataHandlerRegistry.ROTATION);
	public static final TrackedData<EulerAngle> TRACKER_RIGHT_ARM_ROTATION = DataTracker.registerData(PoseableStandEntity.class, TrackedDataHandlerRegistry.ROTATION);
	public static final TrackedData<EulerAngle> TRACKER_LEFT_LEG_ROTATION = DataTracker.registerData(PoseableStandEntity.class, TrackedDataHandlerRegistry.ROTATION);
	public static final TrackedData<EulerAngle> TRACKER_RIGHT_LEG_ROTATION = DataTracker.registerData(PoseableStandEntity.class, TrackedDataHandlerRegistry.ROTATION);
	private EulerAngle headRotation = DEFAULT_HEAD_ROTATION;
	private EulerAngle bodyRotation = DEFAULT_BODY_ROTATION;
	private EulerAngle leftArmRotation = DEFAULT_LEFT_ARM_ROTATION;
	private EulerAngle rightArmRotation = DEFAULT_RIGHT_ARM_ROTATION;
	private EulerAngle leftLegRotation = DEFAULT_LEFT_LEG_ROTATION;
	private EulerAngle rightLegRotation = DEFAULT_RIGHT_LEG_ROTATION;
	private final DefaultedList<ItemStack> heldItems = DefaultedList.ofSize(2, ItemStack.EMPTY);
	private final DefaultedList<ItemStack> armorItems = DefaultedList.ofSize(4, ItemStack.EMPTY);
	private static final Predicate<Entity> RIDEABLE_MINECART_PREDICATE = entity -> entity instanceof AbstractMinecartEntity
			&& ((AbstractMinecartEntity)entity).getMinecartType() == AbstractMinecartEntity.Type.RIDEABLE;
	private final Material material;
	private int disabledSlots;

	public PoseableStandEntity(EntityType<? extends PoseableStandEntity> entityType, World world, Material material) {
		super(entityType, world);
		this.stepHeight = 0.0F;
		this.material = material;
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(TRACKER_HEAD_ROTATION, DEFAULT_HEAD_ROTATION);
		this.dataTracker.startTracking(TRACKER_BODY_ROTATION, DEFAULT_BODY_ROTATION);
		this.dataTracker.startTracking(TRACKER_LEFT_ARM_ROTATION, DEFAULT_LEFT_ARM_ROTATION);
		this.dataTracker.startTracking(TRACKER_RIGHT_ARM_ROTATION, DEFAULT_RIGHT_ARM_ROTATION);
		this.dataTracker.startTracking(TRACKER_LEFT_LEG_ROTATION, DEFAULT_LEFT_LEG_ROTATION);
		this.dataTracker.startTracking(TRACKER_RIGHT_LEG_ROTATION, DEFAULT_RIGHT_LEG_ROTATION);
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return 1.72F;
	}

	@Override
	public Iterable<ItemStack> getItemsHand() {
		return this.heldItems;
	}

	@Override
	public Iterable<ItemStack> getArmorItems() {
		return this.armorItems;
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return switch (slot.getType()) {
			case HAND -> this.heldItems.get(slot.getEntitySlotId());
			case ARMOR -> this.armorItems.get(slot.getEntitySlotId());
		};
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {
		this.processEquippedStack(stack);
		switch (slot.getType()) {
			case HAND -> this.m_nutvedxs(slot, this.heldItems.set(slot.getEntitySlotId(), stack), stack);
			case ARMOR -> this.m_nutvedxs(slot, this.armorItems.set(slot.getEntitySlotId(), stack), stack);
		}
	}

	@Override
	public boolean canEquip(ItemStack stack) {
		EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stack);
		return this.getEquippedStack(equipmentSlot).isEmpty() && !this.isSlotDisabled(equipmentSlot);
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		NbtList nbtList = new NbtList();

		for(ItemStack itemStack : this.armorItems) {
			NbtCompound nbtCompound = new NbtCompound();
			if (!itemStack.isEmpty()) {
				itemStack.writeNbt(nbtCompound);
			}

			nbtList.add(nbtCompound);
		}

		nbt.put("ArmorItems", nbtList);
		NbtList nbtList2 = new NbtList();

		for(ItemStack itemStack2 : this.heldItems) {
			NbtCompound nbtCompound2 = new NbtCompound();
			if (!itemStack2.isEmpty()) {
				itemStack2.writeNbt(nbtCompound2);
			}

			nbtList2.add(nbtCompound2);
		}

		nbt.put("HandItems", nbtList2);
		nbt.putInt("DisabledSlots", this.disabledSlots);
		nbt.put("Pose", this.poseToNbt());
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		if (nbt.contains("ArmorItems", NbtElement.LIST_TYPE)) {
			NbtList nbtList = nbt.getList("ArmorItems", NbtElement.COMPOUND_TYPE);

			for(int i = 0; i < this.armorItems.size(); ++i) {
				this.armorItems.set(i, ItemStack.fromNbt(nbtList.getCompound(i)));
			}
		}

		if (nbt.contains("HandItems", NbtElement.LIST_TYPE)) {
			NbtList nbtList = nbt.getList("HandItems", NbtElement.COMPOUND_TYPE);

			for(int i = 0; i < this.heldItems.size(); ++i) {
				this.heldItems.set(i, ItemStack.fromNbt(nbtList.getCompound(i)));
			}
		}
		NbtCompound nbtCompound = nbt.getCompound("Pose");
		this.disabledSlots = nbt.getInt("DisabledSlots");
		this.readPoseNbt(nbtCompound);
	}

	private void readPoseNbt(NbtCompound nbt) {
		NbtList nbtList = nbt.getList("Head", NbtElement.FLOAT_TYPE);
		this.setHeadRotation(nbtList.isEmpty() ? DEFAULT_HEAD_ROTATION : new EulerAngle(nbtList));
		NbtList nbtList2 = nbt.getList("Body", NbtElement.FLOAT_TYPE);
		this.setBodyRotation(nbtList2.isEmpty() ? DEFAULT_BODY_ROTATION : new EulerAngle(nbtList2));
		NbtList nbtList3 = nbt.getList("LeftArm", NbtElement.FLOAT_TYPE);
		this.setLeftArmRotation(nbtList3.isEmpty() ? DEFAULT_LEFT_ARM_ROTATION : new EulerAngle(nbtList3));
		NbtList nbtList4 = nbt.getList("RightArm", NbtElement.FLOAT_TYPE);
		this.setRightArmRotation(nbtList4.isEmpty() ? DEFAULT_RIGHT_ARM_ROTATION : new EulerAngle(nbtList4));
		NbtList nbtList5 = nbt.getList("LeftLeg", NbtElement.FLOAT_TYPE);
		this.setLeftLegRotation(nbtList5.isEmpty() ? DEFAULT_LEFT_LEG_ROTATION : new EulerAngle(nbtList5));
		NbtList nbtList6 = nbt.getList("RightLeg", NbtElement.FLOAT_TYPE);
		this.setRightLegRotation(nbtList6.isEmpty() ? DEFAULT_RIGHT_LEG_ROTATION : new EulerAngle(nbtList6));
	}

	private NbtCompound poseToNbt() {
		NbtCompound nbtCompound = new NbtCompound();
		if (!DEFAULT_HEAD_ROTATION.equals(this.headRotation)) {
			nbtCompound.put("Head", this.headRotation.toNbt());
		}

		if (!DEFAULT_BODY_ROTATION.equals(this.bodyRotation)) {
			nbtCompound.put("Body", this.bodyRotation.toNbt());
		}

		if (!DEFAULT_LEFT_ARM_ROTATION.equals(this.leftArmRotation)) {
			nbtCompound.put("LeftArm", this.leftArmRotation.toNbt());
		}

		if (!DEFAULT_RIGHT_ARM_ROTATION.equals(this.rightArmRotation)) {
			nbtCompound.put("RightArm", this.rightArmRotation.toNbt());
		}

		if (!DEFAULT_LEFT_LEG_ROTATION.equals(this.leftLegRotation)) {
			nbtCompound.put("LeftLeg", this.leftLegRotation.toNbt());
		}

		if (!DEFAULT_RIGHT_LEG_ROTATION.equals(this.rightLegRotation)) {
			nbtCompound.put("RightLeg", this.rightLegRotation.toNbt());
		}

		return nbtCompound;
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		if (!player.getStackInHand(hand).isEmpty() && !player.shouldCancelInteraction()) {
			return ActionResult.PASS;
		}

		ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
		if (serverPlayerEntity.currentScreenHandler != serverPlayerEntity.playerScreenHandler) {
			serverPlayerEntity.closeScreenHandler();
		}

		return super.interact(player, hand);
	}

	@Override
	public void tick() {
		super.tick();
		EulerAngle eulerAngle = this.dataTracker.get(TRACKER_HEAD_ROTATION);
		if (!this.headRotation.equals(eulerAngle)) {
			this.setHeadRotation(eulerAngle);
		}

		EulerAngle eulerAngle2 = this.dataTracker.get(TRACKER_BODY_ROTATION);
		if (!this.bodyRotation.equals(eulerAngle2)) {
			this.setBodyRotation(eulerAngle2);
		}

		EulerAngle eulerAngle3 = this.dataTracker.get(TRACKER_LEFT_ARM_ROTATION);
		if (!this.leftArmRotation.equals(eulerAngle3)) {
			this.setLeftArmRotation(eulerAngle3);
		}

		EulerAngle eulerAngle4 = this.dataTracker.get(TRACKER_RIGHT_ARM_ROTATION);
		if (!this.rightArmRotation.equals(eulerAngle4)) {
			this.setRightArmRotation(eulerAngle4);
		}

		EulerAngle eulerAngle5 = this.dataTracker.get(TRACKER_LEFT_LEG_ROTATION);
		if (!this.leftLegRotation.equals(eulerAngle5)) {
			this.setLeftLegRotation(eulerAngle5);
		}

		EulerAngle eulerAngle6 = this.dataTracker.get(TRACKER_RIGHT_LEG_ROTATION);
		if (!this.rightLegRotation.equals(eulerAngle6)) {
			this.setRightLegRotation(eulerAngle6);
		}
	}

	public void setHeadRotation(EulerAngle angle) {
		this.headRotation = angle;
		this.dataTracker.set(TRACKER_HEAD_ROTATION, angle);
	}

	public void setBodyRotation(EulerAngle angle) {
		this.bodyRotation = angle;
		this.dataTracker.set(TRACKER_BODY_ROTATION, angle);
	}

	public void setLeftArmRotation(EulerAngle angle) {
		this.leftArmRotation = angle;
		this.dataTracker.set(TRACKER_LEFT_ARM_ROTATION, angle);
	}

	public void setRightArmRotation(EulerAngle angle) {
		this.rightArmRotation = angle;
		this.dataTracker.set(TRACKER_RIGHT_ARM_ROTATION, angle);
	}

	public void setLeftLegRotation(EulerAngle angle) {
		this.leftLegRotation = angle;
		this.dataTracker.set(TRACKER_LEFT_LEG_ROTATION, angle);
	}

	public void setRightLegRotation(EulerAngle angle) {
		this.rightLegRotation = angle;
		this.dataTracker.set(TRACKER_RIGHT_LEG_ROTATION, angle);
	}

	public EulerAngle getHeadRotation() {
		return this.headRotation;
	}

	public EulerAngle getBodyRotation() {
		return this.bodyRotation;
	}

	public EulerAngle getLeftArmRotation() {
		return this.leftArmRotation;
	}

	public EulerAngle getRightArmRotation() {
		return this.rightArmRotation;
	}

	public EulerAngle getLeftLegRotation() {
		return this.leftLegRotation;
	}

	public EulerAngle getRightLegRotation() {
		return this.rightLegRotation;
	}

	private boolean isSlotDisabled(EquipmentSlot equipmentSlot) {
		return false;
	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void pushAway(Entity entity) {
	}

	@Override
	public boolean collides() {
		return !this.isRemoved();
	}

	@Override
	protected void tickCramming() {
		List<Entity> list = this.world.getOtherEntities(this, this.getBoundingBox(), RIDEABLE_MINECART_PREDICATE);
		for (Entity entity : list) {
			if (this.squaredDistanceTo(entity) <= 0.2) {
				entity.pushAwayFrom(this);
			}
		}
	}

	@Override
	public boolean handleAttack(Entity attacker) {
		return super.handleAttack(attacker);
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		if (this.world.isClient || this.isRemoved()) {
			return false;
		}
		if (DamageSource.OUT_OF_WORLD.equals(source)) {
			this.kill();
			return false;
		}
		if (this.isInvulnerableTo(source)) {
			return false;
		}
		if (source.isExplosive()) {
			this.onBreak(source);
			this.kill();
			return false;
		}
		if (DamageSource.IN_FIRE.equals(source) && material == Material.WOOD) {
			if (this.isOnFire()) {
				this.updateHealth(source, 0.15F);
			} else {
				this.setOnFireFor(5);
			}
			return false;
		} else if (DamageSource.ON_FIRE.equals(source) && this.getHealth() > 0.5F && material == Material.WOOD) {
			this.updateHealth(source, 4.0F);
			return false;
		} else {
			if (source.getSource() instanceof PersistentProjectileEntity) {
				return false;
			}
			if (source.getAttacker() instanceof PlayerEntity && !((PlayerEntity)source.getAttacker()).getAbilities().allowModifyWorld) {
				return false;
			}
			if (source.getAttacker() instanceof PlayerEntity && !source.getAttacker().isSneaking()) {
				this.world.sendEntityStatus(this, EntityStatuses.HIT_ARMOR_STAND);
				this.emitGameEvent(GameEvent.ENTITY_DAMAGE, source.getAttacker());
			} else {
				if (source.isSourceCreativePlayer()) {
					this.playBreakSound();
					this.spawnBreakParticles();
					this.kill();
					return false;
				}
				this.breakAndDropThis(source);
				this.spawnBreakParticles();
				this.kill();
			}
			return true;
		}
	}

	@Override
	public boolean isInvulnerableTo(DamageSource damageSource) {
		return damageSource.isFromFalling();
	}

	@Override
	public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
	}

	@Override
	public void kill() {
		this.remove(Entity.RemovalReason.KILLED);
		this.emitGameEvent(GameEvent.ENTITY_DIE);
	}

	private void spawnBreakParticles() {
		if (this.world instanceof ServerWorld) {
			((ServerWorld)this.world)
				.spawnParticles(
					this.getParticle(),
					this.getX(),
					this.getBodyY(0.6666666666666666),
					this.getZ(),
					10,
					this.getWidth() / 4.0F,
					this.getHeight() / 4.0F,
					this.getWidth() / 4.0F,
					0.05
				);
		}
	}

	private void updateHealth(DamageSource damageSource, float amount) {
		float f = this.getHealth();
		f -= amount;
		if (f <= 0.5F) {
			this.onBreak(damageSource);
			this.kill();
		} else {
			this.setHealth(f);
			this.emitGameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getAttacker());
		}
	}

	private void breakAndDropThis(DamageSource damageSource) {
		Block.dropStack(this.world, this.getBlockPos(), this.getItem());
		this.onBreak(damageSource);
	}

	private void onBreak(DamageSource damageSource) {
		this.playBreakSound();
		this.drop(damageSource);

		for(int i = 0; i < this.heldItems.size(); ++i) {
			ItemStack itemStack = this.heldItems.get(i);
			if (!itemStack.isEmpty()) {
				Block.dropStack(this.world, this.getBlockPos().up(), itemStack);
				this.heldItems.set(i, ItemStack.EMPTY);
			}
		}

		for(int i = 0; i < this.armorItems.size(); ++i) {
			ItemStack itemStack = this.armorItems.get(i);
			if (!itemStack.isEmpty()) {
				Block.dropStack(this.world, this.getBlockPos().up(), itemStack);
				this.armorItems.set(i, ItemStack.EMPTY);
			}
		}
	}

	private void playBreakSound() {
		this.world.playSound(null, this.getX(), this.getY(), this.getZ(), this.getDeathSound(), this.getSoundCategory(), 1.0F, 1.0F);
	}

	@Override
	public Arm getMainArm() {
		return Arm.RIGHT;
	}

	public abstract ItemStack getItem();

	public abstract ParticleEffect getParticle();

	public abstract SoundEvent getPlaceSound();

	@Override
	public abstract LivingEntity.FallSounds getFallSounds();

	@Nullable
	@Override
	protected abstract SoundEvent getHurtSound(DamageSource source);

	@Nullable
	@Override
	protected abstract SoundEvent getDeathSound();

	@Override
	public boolean isAffectedBySplashPotions() {
		return false;
	}

	@Override
	public boolean isMobOrPlayer() {
		return false;
	}

	@Nullable
	@Override
	public ItemStack getPickBlockStack() {
		return getItem();
	}
}
