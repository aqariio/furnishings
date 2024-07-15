package aqario.furnishings.common.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FluidContainerBlockEntity extends BlockEntity {
    private Potion potion;
    private String potionType;

    public FluidContainerBlockEntity(BlockPos pos, BlockState state) {
        super(FurnishingsBlockEntityType.FLUID_CONTAINER, pos, state);
        this.potion = Potions.EMPTY;
        this.potionType = Registries.ITEM.getId(Items.POTION).toString();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (this.potion != null) {
            Identifier potionId = Registries.POTION.getId(this.potion);
            String potionName = potionId.toString();

            nbt.putString("Potion", potionName);
            nbt.putString("PotionType", potionType);
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.of(this);
    }

    @Override
    public NbtCompound toSyncedNbt() {
        return this.toIdentifiedLocatedNbt();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.potion = Registries.POTION.get(Identifier.tryParse(nbt.getString("Potion")));
    }

    public @NotNull Potion getPotion() {
        return this.potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public @NotNull String getPotionType() {
        return potionType;
    }

    public void setPotionType(String potionType) {
        this.potionType = potionType;
    }

    public boolean isEmpty() {
        return this.getPotion() == Potions.EMPTY;
    }
}
