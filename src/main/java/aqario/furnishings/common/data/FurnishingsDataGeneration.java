package aqario.furnishings.common.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FurnishingsDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(FurnishingsLootTableGenerator::new);
        pack.addProvider(FurnishingsRecipeGenerator::new);
        pack.addProvider(FurnishingsModelProvider::new);
    }
}
