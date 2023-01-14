package aqario.furnishings.datagen;

import aqario.furnishings.block.FurnishingsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonFactory;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

import java.util.function.Consumer;

public class FurnishingsRecipeGenerator extends FabricRecipeProvider {
    public FurnishingsRecipeGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        /* Bricks */

        offerCrackingRecipe(exporter, FurnishingsBlocks.CRACKED_BRICKS, Blocks.BRICKS);
        offerChiseledBlockRecipe(exporter, FurnishingsBlocks.CHISELED_BRICKS, Blocks.BRICK_SLAB);
        ShapelessRecipeJsonFactory.create(FurnishingsBlocks.MOSSY_BRICKS).input(Blocks.BRICKS).input(Blocks.MOSS_BLOCK)
                .criterion(RecipesProvider.hasItem(Blocks.MOSS_BLOCK), RecipesProvider.conditionsFromItem(Items.VINE))
                .group("mossy_bricks").offerTo(exporter, "mossy_bricks_from_moss_block");
		ShapelessRecipeJsonFactory.create(FurnishingsBlocks.MOSSY_BRICKS).input(Blocks.BRICKS).input(Items.VINE)
                .criterion(RecipesProvider.hasItem(Items.VINE), RecipesProvider.conditionsFromItem(Items.VINE))
                .group("mossy_bricks").offerTo(exporter, "mossy_bricks_from_vine");
        offerSlabRecipe(exporter, FurnishingsBlocks.MOSSY_BRICK_SLAB, FurnishingsBlocks.MOSSY_BRICKS);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.MOSSY_BRICK_SLAB, FurnishingsBlocks.MOSSY_BRICKS, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.MOSSY_BRICK_STAIRS, FurnishingsBlocks.MOSSY_BRICKS);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.MOSSY_BRICK_STAIRS, FurnishingsBlocks.MOSSY_BRICKS);
        offerWallRecipe(exporter, FurnishingsBlocks.MOSSY_BRICK_WALL, FurnishingsBlocks.MOSSY_BRICKS);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.MOSSY_BRICK_WALL, FurnishingsBlocks.MOSSY_BRICKS);

        /* Calcite */

        offerSlabRecipe(exporter, FurnishingsBlocks.CALCITE_SLAB, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.CALCITE_SLAB, Blocks.CALCITE, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        offerWallRecipe(exporter, FurnishingsBlocks.CALCITE_WALL, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.CALCITE_WALL, Blocks.CALCITE);
        offerPolishedStoneRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE, Blocks.CALCITE);
        offerSlabRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_SLAB, FurnishingsBlocks.POLISHED_CALCITE);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_SLAB, FurnishingsBlocks.POLISHED_CALCITE, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_STAIRS, FurnishingsBlocks.POLISHED_CALCITE);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_STAIRS, FurnishingsBlocks.POLISHED_CALCITE);
        offerWallRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_WALL, FurnishingsBlocks.POLISHED_CALCITE);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_WALL, FurnishingsBlocks.POLISHED_CALCITE);
        offerPolishedBricksRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_BRICKS, FurnishingsBlocks.POLISHED_CALCITE);
        offerSlabRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_BRICK_SLAB, FurnishingsBlocks.POLISHED_CALCITE_BRICKS);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_BRICK_SLAB, FurnishingsBlocks.POLISHED_CALCITE_BRICKS, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_BRICK_STAIRS, FurnishingsBlocks.POLISHED_CALCITE_BRICKS);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_BRICK_STAIRS, FurnishingsBlocks.POLISHED_CALCITE_BRICKS);
        offerWallRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_BRICK_WALL, FurnishingsBlocks.POLISHED_CALCITE_BRICKS);
        offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_BRICK_WALL, FurnishingsBlocks.POLISHED_CALCITE_BRICKS);
        offerCrackingRecipe(exporter, FurnishingsBlocks.CRACKED_POLISHED_CALCITE_BRICKS, FurnishingsBlocks.POLISHED_CALCITE_BRICKS);
    }

    public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
		RecipesProvider.createStairsRecipe(output, Ingredient.ofItems(input)).criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerPolishedBricksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createPolishedBricksRecipe(output, Ingredient.ofItems(input)).criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input)).offerTo(exporter);
    }

    public static CraftingRecipeJsonFactory createPolishedBricksRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonFactory.create(output, 4).input('#', input).pattern("##").pattern("##");
    }

}
