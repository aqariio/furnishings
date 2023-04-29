package aqario.furnishings.common.datagen;

import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.block.FurnishingsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class FurnishingsLootTableGenerator extends SimpleFabricLootTableProvider {
    public FurnishingsLootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {

        /* Bricks */

        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/cracked_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.CRACKED_BRICKS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/chiseled_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.CHISELED_BRICKS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/mossy_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.MOSSY_BRICKS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/mossy_brick_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.MOSSY_BRICK_SLAB));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/mossy_brick_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.MOSSY_BRICK_STAIRS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/mossy_brick_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.MOSSY_BRICK_WALL));

        /* Calcite */

        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/calcite_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.CALCITE_SLAB));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/calcite_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.CALCITE_STAIRS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/calcite_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.CALCITE_WALL));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_CALCITE));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.POLISHED_CALCITE_SLAB));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_CALCITE_STAIRS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_CALCITE_WALL));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_CALCITE_BRICKS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite_brick_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.POLISHED_CALCITE_BRICK_SLAB));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite_brick_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_CALCITE_BRICK_STAIRS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_calcite_brick_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_CALCITE_BRICK_WALL));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/cracked_polished_calcite_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.CRACKED_POLISHED_CALCITE_BRICKS));

		/* Tuff */

		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/tuff_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.TUFF_SLAB));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/tuff_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.TUFF_STAIRS));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/tuff_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.TUFF_WALL));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_TUFF));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.POLISHED_TUFF_SLAB));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_TUFF_STAIRS));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_TUFF_WALL));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_TUFF_BRICKS));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff_brick_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.POLISHED_TUFF_BRICK_SLAB));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff_brick_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_TUFF_BRICK_STAIRS));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/polished_tuff_brick_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.POLISHED_TUFF_BRICK_WALL));
		identifierBuilderBiConsumer.accept(new Identifier(Furnishings.ID, "blocks/cracked_polished_tuff_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.CRACKED_POLISHED_TUFF_BRICKS));
    }
}
