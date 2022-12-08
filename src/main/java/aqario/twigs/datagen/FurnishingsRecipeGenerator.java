package aqario.twigs.datagen;

import aqario.twigs.block.FurnishingsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class FurnishingsRecipeGenerator extends FabricRecipeProvider {
    public FurnishingsRecipeGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        offerCrackingRecipe(exporter, FurnishingsBlocks.CRACKED_BRICKS , Blocks.BRICKS);
        offerChiseledBlockRecipe(exporter, FurnishingsBlocks.CHISELED_BRICKS , Blocks.BRICK_SLAB);
        ShapelessRecipeJsonBuilder.create(FurnishingsBlocks.MOSSY_BRICKS).input(Blocks.BRICKS).input(Blocks.MOSS_BLOCK)
                .criterion(RecipeProvider.hasItem(Blocks.MOSS_BLOCK), RecipeProvider.conditionsFromItem(Items.VINE))
                .group("mossy_bricks").offerTo(exporter, "mossy_bricks_from_moss_block");
        ShapelessRecipeJsonBuilder.create(FurnishingsBlocks.MOSSY_BRICKS).input(Blocks.BRICKS).input(Items.VINE)
                .criterion(RecipeProvider.hasItem(Items.VINE), RecipeProvider.conditionsFromItem(Items.VINE))
                .group("mossy_bricks").offerTo(exporter, "mossy_bricks_from_vine");
        offerSlabRecipe(exporter, FurnishingsBlocks.CALCITE_SLAB , Blocks.CALCITE);
    }
}
