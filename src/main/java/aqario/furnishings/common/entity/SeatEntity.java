package aqario.furnishings.common.entity;

import aqario.furnishings.common.block.CushionBlock;
import aqario.furnishings.common.sound.FurnishingsSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SeatEntity extends Entity {
    private static final TrackedData<Optional<BlockPos>> CUSHION_POS = DataTracker.registerData(SeatEntity.class, TrackedDataHandlerRegistry.OPTIONAL_BLOCK_POS);

    public SeatEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        this.discard();
        this.getWorld().playSound(null, this.getBlockPos(), FurnishingsSoundEvents.BLOCK_CUSHION_SIT, SoundCategory.BLOCKS, 1.0F, 0.9F);
        BlockPos cushionPos = getCushionPos();
        if (cushionPos != null) {
            BlockState cushionState = this.getWorld().getBlockState(cushionPos);
            if (cushionState.getBlock() instanceof CushionBlock) {
                this.getWorld().setBlockState(cushionPos, cushionState.with(Properties.OCCUPIED, false), 2);
            }
        }
    }

    @Override
    public boolean isInvisible() {
        return true;
    }

    @Override
    public PistonBehavior getPistonBehavior() {
        return PistonBehavior.IGNORE;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(CUSHION_POS, Optional.empty());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("cushion_pos")) {
            this.setCushionPos(NbtHelper.toBlockPos(nbt.getCompound("cushion_pos")));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        BlockPos cushionPos = getCushionPos();
        if (cushionPos != null) {
            nbt.put("cushion_pos", NbtHelper.fromBlockPos(cushionPos));
        }
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < this.getMaxPassengers();
    }

    protected int getMaxPassengers() {
        return 1;
    }

    @Override
    public void tick() {
        if (this.getFirstPassenger() != null && !(this.getWorld()).isClient) {
            Entity entity = this.getFirstPassenger();
            if (entity instanceof MobEntity mobEntity && mobEntity.getTarget() != null) {
                delete();
                return;
            }
        }
        BlockPos cushionPos = this.getCushionPos();
        if (cushionPos == null) {
            delete();
        }
        BlockState cushionState = this.getWorld().getBlockState(cushionPos);
        if (!(cushionState.getBlock() instanceof CushionBlock)) {
            delete();
        } else if (!((Boolean)cushionState.get(Properties.OCCUPIED))) {
            this.getWorld().setBlockState(cushionPos, cushionState.with(Properties.OCCUPIED, true), 2);
        }
    }

    public void delete() {
        if (!(this.getWorld()).isClient) {
            this.removeAllPassengers();
        }
        this.discard();
    }

    @Nullable
    public BlockPos getCushionPos() {
        return this.dataTracker.get(CUSHION_POS).orElse(null);
    }

    public void setCushionPos(BlockPos pos) {
        this.dataTracker.set(CUSHION_POS, Optional.of(pos));
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
