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
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class PoseableStandScreenHandler extends ScreenHandler {
	private final PoseableStandEntity poseableStand;
	protected final ScreenHandlerContext context;

	protected PoseableStandScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PoseableStandEntity poseableStand, ScreenHandlerContext context) {
		super(null, syncId);
		this.poseableStand = poseableStand;
		this.context = context;

		this.addSlot(new Slot(inventory, 0, 8, 18) {
			@Override
			public int getMaxItemCount() {
				return 1;
			}

			@Override
			public boolean canInsert(ItemStack stack) {
				return MobEntity.getPreferredEquipmentSlot(stack) == EquipmentSlot.HEAD;
			}

			@Nullable
			@Override
			public Pair<Identifier, Identifier> getBackgroundSprite() {
				return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_HELMET_SLOT_TEXTURE);
			}
		});
		this.addSlot(new Slot(inventory, 1, 8, 36) {
			@Override
			public int getMaxItemCount() {
				return 1;
			}

			@Override
			public boolean canInsert(ItemStack stack) {
				return MobEntity.getPreferredEquipmentSlot(stack) == EquipmentSlot.CHEST;
			}

			@Nullable
			@Override
			public Pair<Identifier, Identifier> getBackgroundSprite() {
				return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_CHESTPLATE_SLOT_TEXTURE);
			}
		});
		this.addSlot(new Slot(inventory, 2, 8, 54) {
		});
		this.addSlot(new Slot(inventory, 3, 8, 72) {
			@Nullable
			@Override
			public Pair<Identifier, Identifier> getBackgroundSprite() {
				return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_OFFHAND_ARMOR_SLOT);
			}
		});

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack quickTransfer(PlayerEntity player, int fromIndex) {
		return null;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.context
				.get(
						(world, pos) -> poseableStand.isAlive() && player.squaredDistanceTo((double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5) <= 64.0,
						true
				);
	}
}
