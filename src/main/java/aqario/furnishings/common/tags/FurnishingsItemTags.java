package aqario.furnishings.common.tags;

import aqario.furnishings.common.Furnishings;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class FurnishingsItemTags {
    public static final TagKey<Item> COBBLESTONE_BRICKS = FurnishingsItemTags.of("cobblestone_bricks");

    private FurnishingsItemTags() {
    }

    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(Furnishings.ID, id));
    }
}

