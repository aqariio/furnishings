package aqario.furnishings.common.data;

import aqario.furnishings.common.block.FurnishingsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class FurnishingsLootTableGenerator extends FabricBlockLootTableProvider {
    public FurnishingsLootTableGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        /* Bricks */

        addDrop(FurnishingsBlocks.CRACKED_BRICKS);
        addDrop(FurnishingsBlocks.CHISELED_BRICKS);
        addDrop(FurnishingsBlocks.MOSSY_BRICKS);
        slabDrops(FurnishingsBlocks.MOSSY_BRICK_SLAB);
        addDrop(FurnishingsBlocks.MOSSY_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.MOSSY_BRICK_WALL);

        /* Calcite */

        slabDrops(FurnishingsBlocks.CALCITE_SLAB);
        addDrop(FurnishingsBlocks.CALCITE_STAIRS);
        addDrop(FurnishingsBlocks.CALCITE_WALL);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE);
        slabDrops(FurnishingsBlocks.POLISHED_CALCITE_SLAB);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE_STAIRS);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE_WALL);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE_BRICKS);
        slabDrops(FurnishingsBlocks.POLISHED_CALCITE_BRICK_SLAB);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE_BRICK_WALL);
        addDrop(FurnishingsBlocks.CRACKED_POLISHED_CALCITE_BRICKS);

        /* Tuff */

        slabDrops(FurnishingsBlocks.TUFF_SLAB);
        addDrop(FurnishingsBlocks.TUFF_STAIRS, FurnishingsBlocks.TUFF_STAIRS);
        addDrop(FurnishingsBlocks.TUFF_WALL, FurnishingsBlocks.TUFF_WALL);
        addDrop(FurnishingsBlocks.POLISHED_TUFF);
        slabDrops(FurnishingsBlocks.POLISHED_TUFF_SLAB);
        addDrop(FurnishingsBlocks.POLISHED_TUFF_STAIRS);
        addDrop(FurnishingsBlocks.POLISHED_TUFF_WALL);
        addDrop(FurnishingsBlocks.POLISHED_TUFF_BRICKS);
        slabDrops(FurnishingsBlocks.POLISHED_TUFF_BRICK_SLAB);
        addDrop(FurnishingsBlocks.POLISHED_TUFF_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.POLISHED_TUFF_BRICK_WALL);
        addDrop(FurnishingsBlocks.CRACKED_POLISHED_TUFF_BRICKS);

        /* Candelabra */

        candleDrops(FurnishingsBlocks.CANDELABRA);
        candleDrops(FurnishingsBlocks.WHITE_CANDELABRA);
        candleDrops(FurnishingsBlocks.ORANGE_CANDELABRA);
        candleDrops(FurnishingsBlocks.MAGENTA_CANDELABRA);
        candleDrops(FurnishingsBlocks.LIGHT_BLUE_CANDELABRA);
        candleDrops(FurnishingsBlocks.YELLOW_CANDELABRA);
        candleDrops(FurnishingsBlocks.LIME_CANDELABRA);
        candleDrops(FurnishingsBlocks.PINK_CANDELABRA);
        candleDrops(FurnishingsBlocks.GRAY_CANDELABRA);
        candleDrops(FurnishingsBlocks.LIGHT_GRAY_CANDELABRA);
        candleDrops(FurnishingsBlocks.CYAN_CANDELABRA);
        candleDrops(FurnishingsBlocks.PURPLE_CANDELABRA);
        candleDrops(FurnishingsBlocks.BLUE_CANDELABRA);
        candleDrops(FurnishingsBlocks.BROWN_CANDELABRA);
        candleDrops(FurnishingsBlocks.GREEN_CANDELABRA);
        candleDrops(FurnishingsBlocks.RED_CANDELABRA);
        candleDrops(FurnishingsBlocks.BLACK_CANDELABRA);
    }
}
