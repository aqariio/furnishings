package aqario.furnishings.common.item;

import aqario.furnishings.common.Furnishings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class FurnishingsItems {
    public static final Item SCARECROW = register("scarecrow", new ScarecrowItem(new QuiltItemSettings().maxCount(16)), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Item STATUE = register("statue", new StatueItem(new QuiltItemSettings().maxCount(16)), ItemGroups.FUNCTIONAL_BLOCKS);

    private static Item register(String id, Item item, RegistryKey<ItemGroup> group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addItem(item, ItemGroup.Visibility.PARENT_AND_SEARCH_TABS));
        return Registry.register(Registries.ITEM, new Identifier(Furnishings.ID, id), item);
    }

    public static void init() {
    }
}
