package aqario.furnishings.common.tags;

import aqario.furnishings.common.Furnishings;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class FurnishingsBlockTags {
	public static final TagKey<Block> CANDELABRAS = register("candelabras");
    public static final TagKey<Block> COBBLESTONE_BRICKS = register("cobblestone_bricks");
	public static final TagKey<Block> CUSHIONS = register("cushions");
	public static final TagKey<Block> SCONCES = register("sconces");

    private static TagKey<Block> register(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(Furnishings.ID, id));
    }
}
