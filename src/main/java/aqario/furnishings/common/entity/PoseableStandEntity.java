package aqario.furnishings.common.entity;

import aqario.furnishings.common.network.packet.s2c.OpenPoseableStandScreenS2CPacket;
import aqario.furnishings.mixin.ServerPlayerEntityAccessor;
import aqario.furnishings.server.network.FurnishingsServerPlayNetworkHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public abstract class PoseableStandEntity extends LivingEntity implements InventoryChangedListener, NamedScreenHandlerFactory {
    private static final Predicate<Entity> RIDEABLE_MINECART_PREDICATE = entity -> entity instanceof AbstractMinecartEntity
        && ((AbstractMinecartEntity) entity).getMinecartType() == AbstractMinecartEntity.Type.RIDEABLE;
    private final StandType standType;
    private int disabledSlots;
    protected SimpleInventory inventory;

    public PoseableStandEntity(EntityType<? extends PoseableStandEntity> entityType, World world, StandType type) {
        super(entityType, world);
        this.setStepHeight(0.0F);
        this.standType = type;
        this.inventory = new SimpleInventory(6);
        this.inventory.addListener(this);
    }

    @Override
    public void onInventoryChanged(Inventory sender) {
    }

    @Override
    protected void dropInventory() {
        super.dropInventory();

        if (this.inventory != null) {
            for (int i = 0; i < this.inventory.size(); ++i) {
                ItemStack itemStack = this.inventory.getStack(i);
                if (itemStack.isEmpty()) {
                    continue;
                }
                this.dropStack(itemStack);
            }
        }
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.72F;
    }

    @Override
    public Iterable<ItemStack> getItemsHand() {
        return ImmutableList.of(this.inventory.getStack(4), this.inventory.getStack(5));
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return ImmutableList.of(this.inventory.getStack(0), this.inventory.getStack(1), this.inventory.getStack(2), this.inventory.getStack(3));
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD -> this.inventory.getStack(0);
            case CHEST -> this.inventory.getStack(1);
            case LEGS -> this.inventory.getStack(2);
            case FEET -> this.inventory.getStack(3);
            case MAINHAND -> this.inventory.getStack(4);
            case OFFHAND -> this.inventory.getStack(5);
        };
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        this.processEquippedStack(stack);
        switch (slot) {
            case HEAD -> this.inventory.setStack(0, stack);
            case CHEST -> this.inventory.setStack(1, stack);
            case LEGS -> this.inventory.setStack(2, stack);
            case FEET -> this.inventory.setStack(3, stack);
            case MAINHAND -> this.inventory.setStack(4, stack);
            case OFFHAND -> this.inventory.setStack(5, stack);
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

        for (int i = 0; i < this.inventory.size(); ++i) {
            ItemStack itemStack = this.inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte) i);
                itemStack.writeNbt(nbtCompound);
                nbtList.add(nbtCompound);
            }
        }

        nbt.put("Items", nbtList);
        nbt.putInt("DisabledSlots", this.disabledSlots);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        NbtList nbtList = nbt.getList("Items", NbtElement.COMPOUND_TYPE);

        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 255;
            if (j < this.inventory.size()) {
                this.inventory.setStack(j, ItemStack.fromNbt(nbtCompound));
            }
        }
        this.disabledSlots = nbt.getInt("DisabledSlots");
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!player.getWorld().isClient && player.getStackInHand(hand).isEmpty() && !player.shouldCancelInteraction()) {
            openInventory((ServerPlayerEntity) player);
            return ActionResult.CONSUME;
        }
        return super.interact(player, hand);
    }

    public void openInventory(ServerPlayerEntity player) {
        ServerPlayerEntityAccessor playerAccessor = (ServerPlayerEntityAccessor) player;
        ScreenHandler screenHandler = createMenu(playerAccessor.getScreenHandlerSyncId() % 100 + 1, player.getInventory(), player);
        if (screenHandler != null) {
            playerAccessor.callIncrementScreenHandlerSyncId();
            FurnishingsServerPlayNetworkHandler.sendPacket(player, new OpenPoseableStandScreenS2CPacket(playerAccessor.getScreenHandlerSyncId(), this.getId()));
            player.currentScreenHandler = screenHandler;
            playerAccessor.callOnSpawn(player.currentScreenHandler);
        }
    }

    @Nullable
    @Override
    public abstract ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity playerEntity);

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
    protected void tickCramming() {
        List<Entity> list = this.getWorld().getOtherEntities(this, this.getBoundingBox(), RIDEABLE_MINECART_PREDICATE);
        for (Entity entity : list) {
            if (this.squaredDistanceTo(entity) <= 0.2) {
                entity.pushAwayFrom(this);
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.getWorld().isClient() || this.isRemoved()) {
            return false;
        }
        if (source.isTypeIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            this.kill();
            return false;
        }
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if (source.getSource() instanceof FireworkRocketEntity) {
            return false;
        }
        if (source.isTypeIn(DamageTypeTags.IS_EXPLOSION)) {
            this.onBreak(source);
            this.kill();
            return false;
        }
        if (source.isType(DamageTypes.LAVA)) {
            if (this.isOnFire()) {
                this.updateHealth(source, 1.0F);
            }
            else {
                this.setOnFireFor(10);
            }
            return false;
        }
        if (source.isTypeIn(DamageTypeTags.IS_FIRE)) {
            if (this.isOnFire()) {
                this.updateHealth(source, 0.05F);
            }
            else {
                this.setOnFireFor(5);
            }
            return false;
        }
        if (source.getSource() instanceof PersistentProjectileEntity) {
            return true;
        }
        if (source.getAttacker() instanceof PlayerEntity && !((PlayerEntity) source.getAttacker()).getAbilities().allowModifyWorld) {
            return false;
        }
        if (source.getAttacker() instanceof PlayerEntity && !source.getAttacker().isSneaking()) {
            this.emitGameEvent(GameEvent.ENTITY_DAMAGE, source.getAttacker());
        }
        else {
            if (source.isSourceCreativePlayer()) {
                this.onBreak(source);
                this.spawnBreakParticles();
                this.kill();
                return false;
            }
            if (source.getAttacker() instanceof PlayerEntity && ((PlayerEntity) source.getAttacker()).getMainHandStack().isIn(this.getTool())) {
                this.breakAndDropThis(source);
                this.spawnBreakParticles();
                this.kill();
            }
        }
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return this.getStandType().equals(StandType.STATUE) ? damageSource.isTypeIn(DamageTypeTags.IS_FALL) || damageSource.isTypeIn(DamageTypeTags.IS_FIRE) : damageSource.isTypeIn(DamageTypeTags.IS_FALL);
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
    }

    @Override
    public void kill() {
        this.remove(Entity.RemovalReason.KILLED);
        this.emitGameEvent(GameEvent.ENTITY_DIE);
    }

    protected void spawnBreakParticles() {
        if (this.getWorld() instanceof ServerWorld) {
            ((ServerWorld) this.getWorld())
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

    void updateHealth(DamageSource damageSource, float amount) {
        float f = this.getHealth();
        f -= amount;
        if (f <= 0.5F) {
            this.onBreak(damageSource);
            this.kill();
        }
        else {
            this.setHealth(f);
            this.emitGameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getAttacker());
        }
    }

    void breakAndDropThis(DamageSource damageSource) {
        Block.dropStack(this.getWorld(), this.getBlockPos(), this.getItem());
        this.onBreak(damageSource);
    }

    void onBreak(DamageSource damageSource) {
        this.playBreakSound();
        this.drop(damageSource);
    }

    void playBreakSound() {
        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), this.getDeathSound(), this.getSoundCategory(), 1.0F, 1.0F);
    }

    public abstract EulerAngle getHeadRotation();

    public abstract EulerAngle getBodyRotation();

    public abstract EulerAngle getLeftArmRotation();

    public abstract EulerAngle getRightArmRotation();

    public abstract EulerAngle getLeftLegRotation();

    public abstract EulerAngle getRightLegRotation();

    public abstract void setHeadRotation(EulerAngle angle);

    public abstract void setBodyRotation(EulerAngle angle);

    public abstract void setLeftArmRotation(EulerAngle angle);

    public abstract void setRightArmRotation(EulerAngle angle);

    public abstract void setLeftLegRotation(EulerAngle angle);

    public abstract void setRightLegRotation(EulerAngle angle);

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    public StandType getStandType() {
        return this.standType;
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

    protected abstract TagKey<Item> getTool();

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

    public enum StandType {
        SCARECROW,
        STATUE
    }
}
