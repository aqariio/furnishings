package aqario.furnishings.common.screen;

import aqario.furnishings.common.entity.PoseableStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.EulerAngle;

public abstract class PoseableStandScreenHandler extends ScreenHandler {
    public final PoseableStandEntity poseableStand;
    private final Inventory inventory;

    public PoseableStandScreenHandler(int syncId, PlayerInventory playerInventory, Inventory standInventory, PoseableStandEntity poseableStand) {
        super(null, syncId);
        checkSize(standInventory, 6);
        this.poseableStand = poseableStand;
        this.inventory = standInventory;
        standInventory.onOpen(playerInventory.player);

        addStandSlots(standInventory);

        // Player Inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Player Hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public abstract void addStandSlots(Inventory inventory);

    public void setPose(EulerAngle headAngle, EulerAngle bodyAngle, EulerAngle leftArmAngle, EulerAngle rightArmAngle, EulerAngle leftLegAngle, EulerAngle rightLegAngle) {
        this.poseableStand.setHeadRotation(headAngle);
        this.poseableStand.setBodyRotation(bodyAngle);
        this.poseableStand.setLeftArmRotation(leftArmAngle);
        this.poseableStand.setRightArmRotation(rightArmAngle);
        this.poseableStand.setLeftLegRotation(leftLegAngle);
        this.poseableStand.setRightLegRotation(rightLegAngle);
    }

    @Override
    public ItemStack quickTransfer(PlayerEntity player, int fromIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(fromIndex);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (fromIndex < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.poseableStand.isAlive() && player.squaredDistanceTo(this.poseableStand) <= 64;
    }
}
