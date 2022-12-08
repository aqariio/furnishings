package aqario.twigs.datagen;

import aqario.twigs.Furnishings;
import aqario.twigs.block.FurnishingsBlocks;
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
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.MODID, "blocks/cracked_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.CRACKED_BRICKS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.MODID, "blocks/chiseled_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.CHISELED_BRICKS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.MODID, "blocks/mossy_bricks"), BlockLootTableGenerator.drops(FurnishingsBlocks.MOSSY_BRICKS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.MODID, "blocks/mossy_brick_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.MOSSY_BRICK_SLAB));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.MODID, "blocks/mossy_brick_stairs"), BlockLootTableGenerator.drops(FurnishingsBlocks.MOSSY_BRICK_STAIRS));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.MODID, "blocks/mossy_brick_wall"), BlockLootTableGenerator.drops(FurnishingsBlocks.MOSSY_BRICK_WALL));
        identifierBuilderBiConsumer.accept(new Identifier(Furnishings.MODID, "blocks/calcite_slab"), BlockLootTableGenerator.slabDrops(FurnishingsBlocks.CALCITE_SLAB));
    }
}
