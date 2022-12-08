package aqario.twigs.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FurnishingsDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(FurnishingsLootTableGenerator::new);
        fabricDataGenerator.addProvider(FurnishingsRecipeGenerator::new);
        fabricDataGenerator.addProvider(FurnishingsModelProvider::new);
    }
}
