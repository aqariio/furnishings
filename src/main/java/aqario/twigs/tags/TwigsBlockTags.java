package aqario.twigs.tags;

import aqario.twigs.Twigs;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class TwigsBlockTags {
    public static final TagKey<Block> COBBLESTONE_BRICKS = TwigsBlockTags.of("cobblestone_bricks");

    private TwigsBlockTags() {
    }

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(Twigs.MODID, id));
    }
}
