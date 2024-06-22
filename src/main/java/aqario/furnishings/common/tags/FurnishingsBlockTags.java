package aqario.furnishings.common.tags;

import aqario.furnishings.common.Furnishings;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class FurnishingsBlockTags {
    public static final TagKey<Block> CANDELABRAS = register("candelabras");
    public static final TagKey<Block> COBBLESTONE_BRICKS = register("cobblestone_bricks");
    public static final TagKey<Block> CUSHIONS = register("cushions");
    public static final TagKey<Block> SCONCES = register("sconces");

    private static TagKey<Block> register(String id) {
        return TagKey.of(Registries.BLOCK.getKey(), new Identifier(Furnishings.ID, id));
    }
}
