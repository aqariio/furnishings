package aqario.furnishings.common.data;

import aqario.furnishings.common.block.BookBlock;
import aqario.furnishings.common.block.FurnishingsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

import java.util.List;

public class FurnishingsLootTableGenerator extends FabricBlockLootTableProvider {
    public FurnishingsLootTableGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {

        // Braziers

        addDrop(FurnishingsBlocks.BRAZIER);
        addDrop(FurnishingsBlocks.SOUL_BRAZIER);

        // Bricks

        addDrop(FurnishingsBlocks.CRACKED_BRICKS);
        addDrop(FurnishingsBlocks.CHISELED_BRICKS);
        addDrop(FurnishingsBlocks.MOSSY_BRICKS);
        addSlabDrops(FurnishingsBlocks.MOSSY_BRICK_SLAB);
        addDrop(FurnishingsBlocks.MOSSY_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.MOSSY_BRICK_WALL);

        // Calcite

        addSlabDrops(FurnishingsBlocks.CALCITE_SLAB);
        addDrop(FurnishingsBlocks.CALCITE_STAIRS);
        addDrop(FurnishingsBlocks.CALCITE_WALL);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE);
        addSlabDrops(FurnishingsBlocks.POLISHED_CALCITE_SLAB);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE_STAIRS);
        addDrop(FurnishingsBlocks.POLISHED_CALCITE_WALL);
        addDrop(FurnishingsBlocks.CALCITE_BRICKS);
        addSlabDrops(FurnishingsBlocks.CALCITE_BRICK_SLAB);
        addDrop(FurnishingsBlocks.CALCITE_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.CALCITE_BRICK_WALL);
        addDrop(FurnishingsBlocks.CHISELED_CALCITE_BRICKS);

        // Candelabras

        addCandleDrops(FurnishingsBlocks.CANDELABRA);
        addCandleDrops(FurnishingsBlocks.WHITE_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.ORANGE_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.MAGENTA_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.LIGHT_BLUE_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.YELLOW_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.LIME_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.PINK_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.GRAY_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.LIGHT_GRAY_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.CYAN_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.PURPLE_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.BLUE_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.BROWN_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.GREEN_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.RED_CANDELABRA);
        addCandleDrops(FurnishingsBlocks.BLACK_CANDELABRA);

        // Carpets

        addDrop(FurnishingsBlocks.GILDED_CARPET);

        // Cobblestone Bricks

        addDrop(FurnishingsBlocks.COBBLESTONE_BRICKS);
        addSlabDrops(FurnishingsBlocks.COBBLESTONE_BRICK_SLAB);
        addDrop(FurnishingsBlocks.COBBLESTONE_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.COBBLESTONE_BRICK_WALL);
        addDrop(FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        addSlabDrops(FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_SLAB);
        addDrop(FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_WALL);
        addDrop(FurnishingsBlocks.CRACKED_COBBLESTONE_BRICKS);

        // Cushions

        addDrop(FurnishingsBlocks.WHITE_CUSHION);
        addDrop(FurnishingsBlocks.ORANGE_CUSHION);
        addDrop(FurnishingsBlocks.MAGENTA_CUSHION);
        addDrop(FurnishingsBlocks.LIGHT_BLUE_CUSHION);
        addDrop(FurnishingsBlocks.YELLOW_CUSHION);
        addDrop(FurnishingsBlocks.LIME_CUSHION);
        addDrop(FurnishingsBlocks.PINK_CUSHION);
        addDrop(FurnishingsBlocks.GRAY_CUSHION);
        addDrop(FurnishingsBlocks.LIGHT_GRAY_CUSHION);
        addDrop(FurnishingsBlocks.CYAN_CUSHION);
        addDrop(FurnishingsBlocks.PURPLE_CUSHION);
        addDrop(FurnishingsBlocks.BLUE_CUSHION);
        addDrop(FurnishingsBlocks.BROWN_CUSHION);
        addDrop(FurnishingsBlocks.GREEN_CUSHION);
        addDrop(FurnishingsBlocks.RED_CUSHION);
        addDrop(FurnishingsBlocks.BLACK_CUSHION);

        // Decorations

        addDrop(FurnishingsBlocks.MUG);
        addDrop(FurnishingsBlocks.CHALICE);
        addBookDrops(FurnishingsBlocks.BOOK);
        addDrop(FurnishingsBlocks.RED_PAPER_LANTERN);

        // Glass

        addDrop(FurnishingsBlocks.FRAMED_GLASS);
        addDrop(FurnishingsBlocks.FRAMED_GLASS_PANE);
        addDropWithSilkTouch(FurnishingsBlocks.GLASS_PANEL);

        // Iron

        addDrop(FurnishingsBlocks.IRON_GRATE);
        addDrop(FurnishingsBlocks.IRON_SCAFFOLDING);

        // Lamps

        addDrop(FurnishingsBlocks.LAMP);
        addDrop(FurnishingsBlocks.SOUL_LAMP);

        // Nature

        addDrop(FurnishingsBlocks.ROCKY_DIRT);

        // Pedestals

        addDrop(FurnishingsBlocks.QUARTZ_PEDESTAL);
        addDrop(FurnishingsBlocks.STONE_PEDESTAL);

        // Sconces

        addDrop(FurnishingsBlocks.SCONCE);
        addDrop(FurnishingsBlocks.SOUL_SCONCE);
        addDrop(FurnishingsBlocks.LEVER_SCONCE);
        addDrop(FurnishingsBlocks.LEVER_SOUL_SCONCE);

        // Tuff

        addSlabDrops(FurnishingsBlocks.TUFF_SLAB);
        addDrop(FurnishingsBlocks.TUFF_STAIRS);
        addDrop(FurnishingsBlocks.TUFF_WALL);
        addDrop(FurnishingsBlocks.POLISHED_TUFF);
        addSlabDrops(FurnishingsBlocks.POLISHED_TUFF_SLAB);
        addDrop(FurnishingsBlocks.POLISHED_TUFF_STAIRS);
        addDrop(FurnishingsBlocks.POLISHED_TUFF_WALL);
        addDrop(FurnishingsBlocks.TUFF_BRICKS);
        addSlabDrops(FurnishingsBlocks.TUFF_BRICK_SLAB);
        addDrop(FurnishingsBlocks.TUFF_BRICK_STAIRS);
        addDrop(FurnishingsBlocks.TUFF_BRICK_WALL);
        addDrop(FurnishingsBlocks.CHISELED_TUFF_BRICKS);
    }

    public void addBookDrops(Block block) {
        this.add(block, bookDrops(block));
    }

    public void addCandleDrops(Block block) {
        this.add(block, candleDrops(block));
    }

    public void addSlabDrops(Block block) {
        this.add(block, slabDrops(block));
    }

    public LootTable.Builder bookDrops(Block drop) {
        return LootTable.builder()
            .pool(
                LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(
                        this.applyExplosionDecay(
                            drop,
                            ItemEntry.builder(drop)
                                .apply(
                                    List.of(2, 3, 4),
                                    count -> SetCountLootFunction.builder(ConstantLootNumberProvider.create((float) count))
                                        .conditionally(BlockStatePropertyLootCondition.builder(drop).properties(StatePredicate.Builder.create().exactMatch(BookBlock.BOOKS, count)))
                                )
                        )
                    )
            );
    }
}
