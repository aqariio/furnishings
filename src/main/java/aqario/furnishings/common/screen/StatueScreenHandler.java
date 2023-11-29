package aqario.furnishings.common.screen;

import aqario.furnishings.common.entity.StatueEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class StatueScreenHandler extends PoseableStandScreenHandler {
    public StatueScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, StatueEntity statueEntity) {
        super(syncId, playerInventory, inventory, statueEntity);
    }

    @Override
    public void addStandSlots(Inventory inventory) {
        // Head
        this.addSlot(new Slot(inventory, 0, 44, 18) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return MobEntity.getPreferredEquipmentSlot(stack) == EquipmentSlot.HEAD;
            }

            @Override
            public Pair<Identifier, Identifier> getBackgroundSprite() {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_HELMET_SLOT_TEXTURE);
            }
        });

        // Chest
        this.addSlot(new Slot(inventory, 1, 62, 18) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return MobEntity.getPreferredEquipmentSlot(stack) == EquipmentSlot.CHEST;
            }

            @Override
            public Pair<Identifier, Identifier> getBackgroundSprite() {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_CHESTPLATE_SLOT_TEXTURE);
            }
        });

        // Legs
        this.addSlot(new Slot(inventory, 2, 44, 36) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return MobEntity.getPreferredEquipmentSlot(stack) == EquipmentSlot.LEGS;
            }

            @Override
            public Pair<Identifier, Identifier> getBackgroundSprite() {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_LEGGINGS_SLOT_TEXTURE);
            }
        });

        // Feet
        this.addSlot(new Slot(inventory, 3, 62, 36) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return MobEntity.getPreferredEquipmentSlot(stack) == EquipmentSlot.FEET;
            }

            @Override
            public Pair<Identifier, Identifier> getBackgroundSprite() {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_BOOTS_SLOT_TEXTURE);
            }
        });

        // Hand
        this.addSlot(new Slot(inventory, 4, 44, 54));

        // Offhand
        this.addSlot(new Slot(inventory, 5, 62, 54) {
            @Override
            public Pair<Identifier, Identifier> getBackgroundSprite() {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_OFFHAND_ARMOR_SLOT);
            }
        });
    }
}
