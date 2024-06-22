package aqario.furnishings.common.tags;

import aqario.furnishings.common.Furnishings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class FurnishingsItemTags {
    public static final TagKey<Item> CANDELABRAS = register("candelabras");
    public static final TagKey<Item> COBBLESTONE_BRICKS = register("cobblestone_bricks");
    public static final TagKey<Item> CUSHIONS = register("cushions");

    private static TagKey<Item> register(String id) {
        return TagKey.of(Registries.ITEM.getKey(), new Identifier(Furnishings.ID, id));
    }
}

