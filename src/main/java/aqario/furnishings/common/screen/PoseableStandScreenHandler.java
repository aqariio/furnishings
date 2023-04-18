package aqario.furnishings.common.screen;

import aqario.furnishings.common.entity.PoseableStandEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.EulerAngle;

public class PoseableStandScreenHandler extends ScreenHandler {
	public final PoseableStandEntity poseableStand;
	private final Inventory inventory;

	public PoseableStandScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PoseableStandEntity poseableStand) {
		super(null, syncId);
		checkSize(inventory, 6);
		this.poseableStand = poseableStand;
		this.inventory = inventory;
		inventory.onOpen(playerInventory.player);

		// Head
		this.addSlot(new Slot(inventory, 0, 8, 18) {
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
		this.addSlot(new Slot(inventory, 1, 8, 36) {
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
		this.addSlot(new Slot(inventory, 2, 8, 54) {
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
		this.addSlot(new Slot(inventory, 3, 8, 72) {
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
		this.addSlot(new Slot(inventory, 4, 77, 54));

		// Offhand
		this.addSlot(new Slot(inventory, 5, 77, 72) {
			@Override
			public Pair<Identifier, Identifier> getBackgroundSprite() {
				return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_OFFHAND_ARMOR_SLOT);
			}
		});

		// Player Inventory
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 103 + y * 18));
			}
		}

		// Player Hotbar
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 161));
		}
	}

	public void setPose(EulerAngle headAngle, EulerAngle bodyAngle, EulerAngle leftArmAngle, EulerAngle rightArmAngle) {
		this.poseableStand.setHeadRotation(headAngle);
		this.poseableStand.setBodyRotation(bodyAngle);
		this.poseableStand.setLeftArmRotation(leftArmAngle);
		this.poseableStand.setRightArmRotation(rightArmAngle);
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
