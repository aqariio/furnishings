package aqario.furnishings.tags;

import aqario.furnishings.Furnishings;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class FurnishingsBlockTags {
    public static final TagKey<Block> COBBLESTONE_BRICKS = FurnishingsBlockTags.of("cobblestone_bricks");

    private FurnishingsBlockTags() {
    }

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(Furnishings.MODID, id));
    }
}
