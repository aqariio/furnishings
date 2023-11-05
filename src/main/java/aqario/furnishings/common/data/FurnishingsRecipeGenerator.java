package aqario.furnishings.common.data;

import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.tags.FurnishingsItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;

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

		/* Candelabra */

		offerCandelabraRecipe(exporter, FurnishingsBlocks.CANDELABRA, Blocks.CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.WHITE_CANDELABRA, Blocks.WHITE_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.LIGHT_GRAY_CANDELABRA, Blocks.LIGHT_GRAY_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.GRAY_CANDELABRA, Blocks.GRAY_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.BLACK_CANDELABRA, Blocks.BLACK_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.BROWN_CANDELABRA, Blocks.BROWN_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.RED_CANDELABRA, Blocks.RED_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.ORANGE_CANDELABRA, Blocks.ORANGE_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.YELLOW_CANDELABRA, Blocks.YELLOW_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.LIME_CANDELABRA, Blocks.LIME_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.GREEN_CANDELABRA, Blocks.GREEN_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.CYAN_CANDELABRA, Blocks.CYAN_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.LIGHT_BLUE_CANDELABRA, Blocks.LIGHT_BLUE_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.BLUE_CANDELABRA, Blocks.BLUE_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.PURPLE_CANDELABRA, Blocks.PURPLE_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.MAGENTA_CANDELABRA, Blocks.MAGENTA_CANDLE);
		offerCandelabraRecipe(exporter, FurnishingsBlocks.PINK_CANDELABRA, Blocks.PINK_CANDLE);
		// Dyeing
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.WHITE_CANDELABRA, Items.WHITE_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.LIGHT_GRAY_CANDELABRA, Items.LIGHT_GRAY_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.GRAY_CANDELABRA, Items.GRAY_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.BLACK_CANDELABRA, Items.BLACK_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.BROWN_CANDELABRA, Items.BROWN_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.RED_CANDELABRA, Items.RED_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.ORANGE_CANDELABRA, Items.ORANGE_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.YELLOW_CANDELABRA, Items.YELLOW_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.LIME_CANDELABRA, Items.LIME_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.GREEN_CANDELABRA, Items.GREEN_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.CYAN_CANDELABRA, Items.CYAN_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.LIGHT_BLUE_CANDELABRA, Items.LIGHT_BLUE_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.BLUE_CANDELABRA, Items.BLUE_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.PURPLE_CANDELABRA, Items.PURPLE_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.MAGENTA_CANDELABRA, Items.MAGENTA_DYE);
		offerCandelabraDyeingRecipe(exporter, FurnishingsBlocks.PINK_CANDELABRA, Items.PINK_DYE);

		/* Tuff */

		offerSlabRecipe(exporter, FurnishingsBlocks.TUFF_SLAB, Blocks.TUFF);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.TUFF_SLAB, Blocks.TUFF, 2);
		offerStairsRecipe(exporter, FurnishingsBlocks.TUFF_STAIRS, Blocks.TUFF);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.TUFF_STAIRS, Blocks.TUFF);
		offerWallRecipe(exporter, FurnishingsBlocks.TUFF_WALL, Blocks.TUFF);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.TUFF_WALL, Blocks.TUFF);
		offerPolishedStoneRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF, Blocks.TUFF);
		offerSlabRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_SLAB, FurnishingsBlocks.POLISHED_TUFF);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_SLAB, FurnishingsBlocks.POLISHED_TUFF, 2);
		offerStairsRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_STAIRS, FurnishingsBlocks.POLISHED_TUFF);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_STAIRS, FurnishingsBlocks.POLISHED_TUFF);
		offerWallRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_WALL, FurnishingsBlocks.POLISHED_TUFF);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_WALL, FurnishingsBlocks.POLISHED_TUFF);
		offerPolishedBricksRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_BRICKS, FurnishingsBlocks.POLISHED_TUFF);
		offerSlabRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_BRICK_SLAB, FurnishingsBlocks.POLISHED_TUFF_BRICKS);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_BRICK_SLAB, FurnishingsBlocks.POLISHED_TUFF_BRICKS, 2);
		offerStairsRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_BRICK_STAIRS, FurnishingsBlocks.POLISHED_TUFF_BRICKS);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_BRICK_STAIRS, FurnishingsBlocks.POLISHED_TUFF_BRICKS);
		offerWallRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_BRICK_WALL, FurnishingsBlocks.POLISHED_TUFF_BRICKS);
		offerStonecuttingRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_BRICK_WALL, FurnishingsBlocks.POLISHED_TUFF_BRICKS);
		offerCrackingRecipe(exporter, FurnishingsBlocks.CRACKED_POLISHED_TUFF_BRICKS, FurnishingsBlocks.POLISHED_TUFF_BRICKS);
    }

    public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
		RecipesProvider.createStairsRecipe(output, Ingredient.ofItems(input)).criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input)).offerTo(exporter);
    }

	public static void offerCandelabraDyeingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible dye) {
		ShapelessRecipeJsonFactory.create(output)
			.m_jrksubfg(FurnishingsItemTags.CANDELABRAS)
			.input(dye)
			.group("candelabra")
			.criterion("has_candelabra", RecipesProvider.conditionsFromItem(FurnishingsBlocks.CANDELABRA))
			.offerTo(exporter, dye(output));
	}

	public static void offerCandelabraRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
		ShapedRecipeJsonFactory.create(output)
			.input('#', input)
			.input('I', Items.IRON_INGOT)
			.pattern("#")
			.pattern("I")
			.group("candelabra")
			.criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
			.offerTo(exporter);
	}

    public static void offerPolishedBricksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
		ShapedRecipeJsonFactory.create(output, 4)
			.input('#', input)
			.pattern("##")
			.pattern("##")
			.criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
			.offerTo(exporter);
    }

	public static InventoryChangedCriterion.Conditions conditionsFromItemTag(TagKey<Item> tagKey) {
		return conditionsFromItemPredicates(ItemPredicate.Builder.create().m_nknvndsh(tagKey).build());
	}

	public static String dye(ItemConvertible item) {
		return "dye_" + getItemPath(item);
	}

	public static String convertBetween(ItemConvertible from, TagKey<Item> to) {
		return getItemPath(from) + "_from_" + getTagPath(to);
	}

	public static String getTagPath(TagKey<Item> tagKey) {
		return tagKey.id().getPath();
	}
}
