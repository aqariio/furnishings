package aqario.twigs.tags;

import aqario.twigs.Twigs;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class TwigsItemTags {
    public static final TagKey<Item> COBBLESTONE_BRICKS = TwigsItemTags.of("cobblestone_bricks");

    private TwigsItemTags() {
    }

    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(Twigs.MODID, id));
    }
}

