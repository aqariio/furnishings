package aqario.furnishings.common.entity;

import aqario.furnishings.common.item.FurnishingsItems;
import aqario.furnishings.common.screen.ScarecrowScreenHandler;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ScarecrowEntity extends PoseableStandEntity {
    private static final EulerAngle DEFAULT_HEAD_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    private static final EulerAngle DEFAULT_BODY_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    private static final EulerAngle DEFAULT_LEFT_ARM_ROTATION = new EulerAngle(0.0F, 0.0F, -90.0F);
    private static final EulerAngle DEFAULT_RIGHT_ARM_ROTATION = new EulerAngle(0.0F, 0.0F, 90.0F);
    public static final TrackedData<EulerAngle> TRACKER_HEAD_ROTATION = DataTracker.registerData(ScarecrowEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_BODY_ROTATION = DataTracker.registerData(ScarecrowEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_LEFT_ARM_ROTATION = DataTracker.registerData(ScarecrowEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_RIGHT_ARM_ROTATION = DataTracker.registerData(ScarecrowEntity.class, TrackedDataHandlerRegistry.ROTATION);
    private EulerAngle headRotation = DEFAULT_HEAD_ROTATION;
    private EulerAngle bodyRotation = DEFAULT_BODY_ROTATION;
    private EulerAngle leftArmRotation = DEFAULT_LEFT_ARM_ROTATION;
    private EulerAngle rightArmRotation = DEFAULT_RIGHT_ARM_ROTATION;

    public ScarecrowEntity(EntityType<? extends PoseableStandEntity> entityType, World world) {
        super(entityType, world, StandType.SCARECROW);
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
        if (source.getSource() instanceof FireworkRocketEntity) {
            return false;
        }
        if (source.isExplosive()) {
            this.onBreak(source);
            this.kill();
            return false;
        }
        if (source.getSource() instanceof PersistentProjectileEntity) {
            return true;
        }
        if (DamageSource.LAVA.equals(source)) {
            this.updateHealth(source, 1.0F);
            return false;
        }
        if (source.isFire()) {
            this.updateHealth(source, 0.05F);
            return false;
        }
        if (source.getAttacker() instanceof PlayerEntity) {
            if (!((PlayerEntity)source.getAttacker()).getAbilities().allowModifyWorld) {
                return false;
            }
            if (source.getAttacker().isSneaking()) {
                if (source.isSourceCreativePlayer() ) {
                    this.onBreak(source);
                    this.spawnBreakParticles();
                    this.kill();
                    return false;
                }
                if (source.getAttacker() instanceof PlayerEntity && (((PlayerEntity) source.getAttacker()).getMainHandStack().isIn(ConventionalItemTags.AXES) || ((PlayerEntity) source.getAttacker()).getMainHandStack().getItem() instanceof AxeItem)) {
                    this.breakAndDropThis(source);
                    this.spawnBreakParticles();
                    this.kill();
                }
            } else {
                this.emitGameEvent(GameEvent.ENTITY_DAMAGE, source.getAttacker());
            }
        }
        return true;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TRACKER_HEAD_ROTATION, DEFAULT_HEAD_ROTATION);
        this.dataTracker.startTracking(TRACKER_BODY_ROTATION, DEFAULT_BODY_ROTATION);
        this.dataTracker.startTracking(TRACKER_LEFT_ARM_ROTATION, DEFAULT_LEFT_ARM_ROTATION);
        this.dataTracker.startTracking(TRACKER_RIGHT_ARM_ROTATION, DEFAULT_RIGHT_ARM_ROTATION);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Pose", this.poseToNbt());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        NbtCompound nbtCompound = nbt.getCompound("Pose");
        this.readPoseNbt(nbtCompound);
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ScarecrowScreenHandler(syncId, playerInventory, this.inventory, this);
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

        return nbtCompound;
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

    @Override
    public EulerAngle getLeftLegRotation() {
        return new EulerAngle(0.0F, 0.0F, 0.0F);
    }

    @Override
    public EulerAngle getRightLegRotation() {
        return new EulerAngle(0.0F, 0.0F, 0.0F);
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

    @Override
    public void setLeftLegRotation(EulerAngle angle) {
    }

    @Override
    public void setRightLegRotation(EulerAngle angle) {
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(FurnishingsItems.SCARECROW);
    }

    @Override
    public ParticleEffect getParticle() {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.HAY_BLOCK.getDefaultState());
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
