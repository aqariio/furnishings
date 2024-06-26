package aqario.furnishings.common.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class FluidContainerBlockEntity extends BlockEntity {
    private boolean empty;
    private Potion potion;
    private String potionType;

    public FluidContainerBlockEntity(BlockPos pos, BlockState state) {
        super(FurnishingsBlockEntityType.FLUID_CONTAINER, pos, state);
        potion = Potions.EMPTY;
        potionType = Registries.ITEM.getId(Items.POTION).toString();
    }

//    @Override
//    public void markDirty() {
//        if (this.world == null) return;
//        int light = 10;
//        if (light != this.getCachedState().get(FluidContainerBlock.LUMINANCE)) {
//            this.world.setBlockState(this.pos, this.getCachedState().with(FluidContainerBlock.LUMINANCE, light), 2);
//        }
//        this.world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
//        super.markDirty();
//    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (potion != null) {
            Identifier potionId = Registries.POTION.getId(potion);
            String potionName = potionId.toString();

            nbt.putString("Potion", potionName);
            nbt.putString("PotionType", potionType);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        potion = Registries.POTION.get(Identifier.tryParse(nbt.getString("Potion")));
    }

    public @NotNull Potion getPotion() {
        return potion;
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
