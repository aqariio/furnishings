package aqario.furnishings.common.item;

import aqario.furnishings.common.Furnishings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class FurnishingsItems {
	public static final Item STATUE = register("statue", new StatueItem(new QuiltItemSettings().maxCount(16).group(ItemGroup.DECORATIONS)));

	private static Item register(String id, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(Furnishings.ID, id), item);
	}

	public static void init() {
	}
}
