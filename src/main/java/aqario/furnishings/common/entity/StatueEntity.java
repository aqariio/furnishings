package aqario.furnishings.common.entity;

import aqario.furnishings.common.item.FurnishingsItems;
import aqario.furnishings.common.screen.StatueScreenHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StatueEntity extends PoseableStandEntity {
    private static final EulerAngle DEFAULT_HEAD_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    private static final EulerAngle DEFAULT_BODY_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    private static final EulerAngle DEFAULT_LEFT_ARM_ROTATION = new EulerAngle(-10.0F, 0.0F, -10.0F);
    private static final EulerAngle DEFAULT_RIGHT_ARM_ROTATION = new EulerAngle(-10.0F, 0.0F, 10.0F);
    private static final EulerAngle DEFAULT_LEFT_LEG_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    private static final EulerAngle DEFAULT_RIGHT_LEG_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    public static final TrackedData<EulerAngle> TRACKER_HEAD_ROTATION = DataTracker.registerData(StatueEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_BODY_ROTATION = DataTracker.registerData(StatueEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_LEFT_ARM_ROTATION = DataTracker.registerData(StatueEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_RIGHT_ARM_ROTATION = DataTracker.registerData(StatueEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_LEFT_LEG_ROTATION = DataTracker.registerData(StatueEntity.class, TrackedDataHandlerRegistry.ROTATION);
    public static final TrackedData<EulerAngle> TRACKER_RIGHT_LEG_ROTATION = DataTracker.registerData(StatueEntity.class, TrackedDataHandlerRegistry.ROTATION);
    private EulerAngle headRotation = DEFAULT_HEAD_ROTATION;
    private EulerAngle bodyRotation = DEFAULT_BODY_ROTATION;
    private EulerAngle leftArmRotation = DEFAULT_LEFT_ARM_ROTATION;
    private EulerAngle rightArmRotation = DEFAULT_RIGHT_ARM_ROTATION;
    private EulerAngle leftLegRotation = DEFAULT_LEFT_LEG_ROTATION;
    private EulerAngle rightLegRotation = DEFAULT_RIGHT_LEG_ROTATION;

    public StatueEntity(EntityType<? extends PoseableStandEntity> entityType, World world) {
        super(entityType, world, StandType.STATUE);
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
        return new StatueScreenHandler(syncId, playerInventory, this.inventory, this);
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

    @Override
    public ItemStack getItem() {
        return new ItemStack(FurnishingsItems.STATUE);
    }

    @Override
    public ParticleEffect getParticle() {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState());
    }

    @Override
    protected TagKey<Item> getTool() {
        return ItemTags.PICKAXES;
    }

    @Override
    public SoundEvent getPlaceSound() {
        return SoundEvents.BLOCK_STONE_PLACE;
    }

    @Override
    public FallSounds getFallSounds() {
        return new FallSounds(SoundEvents.BLOCK_STONE_FALL, SoundEvents.BLOCK_STONE_FALL);
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
}
