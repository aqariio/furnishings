package aqario.furnishings.common.tags;

import aqario.furnishings.common.Furnishings;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class FurnishingsBlockTags {
    public static final TagKey<Block> COBBLESTONE_BRICKS = of("cobblestone_bricks");
	public static final TagKey<Block> CUSHIONS = of("cushions");
	public static final TagKey<Block> CANDELABRAS = of("candelabras");

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(Furnishings.ID, id));
    }
}
