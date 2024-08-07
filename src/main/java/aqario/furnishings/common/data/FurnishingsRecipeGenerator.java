package aqario.furnishings.common.data;

import aqario.furnishings.common.block.BrazierBlock;
import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.tags.FurnishingsItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Consumer;

public class FurnishingsRecipeGenerator extends FabricRecipeProvider {
    public FurnishingsRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        // Braziers

        offerBrazierRecipe(exporter, FurnishingsBlocks.BRAZIER);
        offerBrazierRecipe(exporter, FurnishingsBlocks.SOUL_BRAZIER);

        // Bricks

        offerCrackingRecipe(exporter, FurnishingsBlocks.CRACKED_BRICKS, Blocks.BRICKS);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CHISELED_BRICKS, Blocks.BRICK_SLAB);
        offerMossyRecipe(exporter, FurnishingsBlocks.MOSSY_BRICKS, Blocks.BRICKS);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_BRICK_SLAB, FurnishingsBlocks.MOSSY_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_BRICK_SLAB, FurnishingsBlocks.MOSSY_BRICKS, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.MOSSY_BRICK_STAIRS, FurnishingsBlocks.MOSSY_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_BRICK_STAIRS, FurnishingsBlocks.MOSSY_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_BRICK_WALL, FurnishingsBlocks.MOSSY_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_BRICK_WALL, FurnishingsBlocks.MOSSY_BRICKS);

        // Calcite

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_SLAB, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_SLAB, Blocks.CALCITE, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_WALL, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_WALL, Blocks.CALCITE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_CALCITE, Blocks.CALCITE);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_CALCITE_SLAB, FurnishingsBlocks.POLISHED_CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_CALCITE_SLAB, FurnishingsBlocks.POLISHED_CALCITE, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.POLISHED_CALCITE_STAIRS, FurnishingsBlocks.POLISHED_CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_CALCITE_STAIRS, FurnishingsBlocks.POLISHED_CALCITE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_CALCITE_WALL, FurnishingsBlocks.POLISHED_CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_CALCITE_WALL, FurnishingsBlocks.POLISHED_CALCITE);
        offerPolishedBricksRecipe(exporter, FurnishingsBlocks.CALCITE_BRICKS, FurnishingsBlocks.POLISHED_CALCITE);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_BRICK_SLAB, FurnishingsBlocks.CALCITE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_BRICK_SLAB, FurnishingsBlocks.CALCITE_BRICKS, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.CALCITE_BRICK_STAIRS, FurnishingsBlocks.CALCITE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_BRICK_STAIRS, FurnishingsBlocks.CALCITE_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_BRICK_WALL, FurnishingsBlocks.CALCITE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CALCITE_BRICK_WALL, FurnishingsBlocks.CALCITE_BRICKS);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CHISELED_CALCITE_BRICKS, FurnishingsBlocks.CALCITE_BRICKS);

        // Candelabras

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

        // Carpets

        offerGildedCarpetRecipe(exporter, FurnishingsBlocks.GILDED_CARPET);

        // Cobblestone Bricks

        offerPolishedBricksRecipe(exporter, FurnishingsBlocks.COBBLESTONE_BRICKS, Blocks.COBBLESTONE);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.COBBLESTONE_BRICK_SLAB, FurnishingsBlocks.COBBLESTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.COBBLESTONE_BRICK_SLAB, FurnishingsBlocks.COBBLESTONE_BRICKS, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.COBBLESTONE_BRICK_STAIRS, FurnishingsBlocks.COBBLESTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.COBBLESTONE_BRICK_STAIRS, FurnishingsBlocks.COBBLESTONE_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.COBBLESTONE_BRICK_WALL, FurnishingsBlocks.COBBLESTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.COBBLESTONE_BRICK_WALL, FurnishingsBlocks.COBBLESTONE_BRICKS);
        offerMossyRecipe(exporter, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS, FurnishingsBlocks.COBBLESTONE_BRICKS);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_SLAB, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_SLAB, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_WALL, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_WALL, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        offerCrackingRecipe(exporter, FurnishingsBlocks.CRACKED_COBBLESTONE_BRICKS, FurnishingsBlocks.COBBLESTONE_BRICKS);

        // Cushions

        offerCushionRecipe(exporter, FurnishingsBlocks.WHITE_CUSHION, Blocks.WHITE_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.LIGHT_GRAY_CUSHION, Blocks.LIGHT_GRAY_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.GRAY_CUSHION, Blocks.GRAY_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.BLACK_CUSHION, Blocks.BLACK_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.BROWN_CUSHION, Blocks.BROWN_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.RED_CUSHION, Blocks.RED_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.ORANGE_CUSHION, Blocks.ORANGE_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.YELLOW_CUSHION, Blocks.YELLOW_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.LIME_CUSHION, Blocks.LIME_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.GREEN_CUSHION, Blocks.GREEN_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.CYAN_CUSHION, Blocks.CYAN_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.LIGHT_BLUE_CUSHION, Blocks.LIGHT_BLUE_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.BLUE_CUSHION, Blocks.BLUE_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.PURPLE_CUSHION, Blocks.PURPLE_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.MAGENTA_CUSHION, Blocks.MAGENTA_WOOL);
        offerCushionRecipe(exporter, FurnishingsBlocks.PINK_CUSHION, Blocks.PINK_WOOL);
        // Dyeing
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.WHITE_CUSHION, Items.WHITE_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.LIGHT_GRAY_CUSHION, Items.LIGHT_GRAY_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.GRAY_CUSHION, Items.GRAY_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.BLACK_CUSHION, Items.BLACK_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.BROWN_CUSHION, Items.BROWN_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.RED_CUSHION, Items.RED_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.ORANGE_CUSHION, Items.ORANGE_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.YELLOW_CUSHION, Items.YELLOW_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.LIME_CUSHION, Items.LIME_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.GREEN_CUSHION, Items.GREEN_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.CYAN_CUSHION, Items.CYAN_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.LIGHT_BLUE_CUSHION, Items.LIGHT_BLUE_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.BLUE_CUSHION, Items.BLUE_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.PURPLE_CUSHION, Items.PURPLE_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.MAGENTA_CUSHION, Items.MAGENTA_DYE);
        offerCushionDyeingRecipe(exporter, FurnishingsBlocks.PINK_CUSHION, Items.PINK_DYE);

        // Decorations

        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, FurnishingsBlocks.MUG, 4)
            .ingredient('#', Blocks.SPRUCE_PLANKS)
            .pattern("# #")
            .pattern("# #")
            .pattern(" # ")
            .criterion(hasItem(Blocks.SPRUCE_PLANKS), conditionsFromItem(Blocks.SPRUCE_PLANKS))
            .offerTo(exporter);
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, FurnishingsBlocks.CHALICE, 2)
            .ingredient('#', Items.IRON_NUGGET)
            .ingredient('I', Items.IRON_INGOT)
            .pattern("# #")
            .pattern("#I#")
            .pattern(" # ")
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter);
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, FurnishingsBlocks.RED_PAPER_LANTERN)
            .ingredient('#', Blocks.TORCH)
            .ingredient('P', Items.PAPER)
            .ingredient('G', Items.GOLD_NUGGET)
            .pattern("PGP")
            .pattern("P#P")
            .pattern("PGP")
            .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
            .offerTo(exporter);

        // Glass

        offerGlassFramingRecipe(exporter, FurnishingsBlocks.FRAMED_GLASS, Blocks.GLASS);
        offerGlassPaneFramingRecipe(exporter, FurnishingsBlocks.FRAMED_GLASS_PANE, Blocks.GLASS_PANE);
        offerFramedGlassPaneRecipe(exporter, FurnishingsBlocks.FRAMED_GLASS_PANE, FurnishingsBlocks.FRAMED_GLASS);
        offerGlassPanelRecipe(exporter, FurnishingsBlocks.GLASS_PANEL, Blocks.GLASS_PANE);

        // Iron

        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, FurnishingsBlocks.IRON_GRATE, 4)
            .ingredient('I', Items.IRON_INGOT)
            .pattern("III")
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter);
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, FurnishingsBlocks.IRON_SCAFFOLDING, 6)
            .ingredient('~', Items.IRON_NUGGET)
            .ingredient('I', Items.IRON_INGOT)
            .pattern("I~I")
            .pattern("I I")
            .pattern("I I")
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter);

        // Lamps

        offerLampRecipe(exporter, FurnishingsBlocks.LAMP, Blocks.TORCH);
        offerLampRecipe(exporter, FurnishingsBlocks.SOUL_LAMP, Blocks.SOUL_TORCH);

        // Nature

        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.ROCKY_DIRT, 4)
            .ingredient('D', Blocks.DIRT)
            .ingredient('C', Blocks.COBBLESTONE)
            .pattern("DC")
            .pattern("CD")
            .criterion(hasItem(Blocks.DIRT), conditionsFromItem(Blocks.DIRT))
            .offerTo(exporter);

        // Pedestals

        offerPedestalRecipe(exporter, FurnishingsBlocks.QUARTZ_PEDESTAL, Blocks.QUARTZ_PILLAR, Blocks.QUARTZ_SLAB);
        offerPedestalRecipe(exporter, FurnishingsBlocks.STONE_PEDESTAL, Blocks.STONE_BRICKS, Blocks.STONE_BRICK_SLAB);

        // Sconces

        offerSconceRecipe(exporter, FurnishingsBlocks.SCONCE, ItemTags.COALS);
        offerSconceRecipe(exporter, FurnishingsBlocks.SOUL_SCONCE, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        offerLeverSconceRecipe(exporter, FurnishingsBlocks.LEVER_SCONCE, FurnishingsBlocks.SCONCE);
        offerLeverSconceRecipe(exporter, FurnishingsBlocks.LEVER_SOUL_SCONCE, FurnishingsBlocks.SOUL_SCONCE);

        // Tuff

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_SLAB, Blocks.TUFF);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_SLAB, Blocks.TUFF, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.TUFF_STAIRS, Blocks.TUFF);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_STAIRS, Blocks.TUFF);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_WALL, Blocks.TUFF);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_WALL, Blocks.TUFF);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_TUFF, Blocks.TUFF);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_TUFF_SLAB, FurnishingsBlocks.POLISHED_TUFF);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_TUFF_SLAB, FurnishingsBlocks.POLISHED_TUFF, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.POLISHED_TUFF_STAIRS, FurnishingsBlocks.POLISHED_TUFF);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_TUFF_STAIRS, FurnishingsBlocks.POLISHED_TUFF);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_TUFF_WALL, FurnishingsBlocks.POLISHED_TUFF);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.POLISHED_TUFF_WALL, FurnishingsBlocks.POLISHED_TUFF);
        offerPolishedBricksRecipe(exporter, FurnishingsBlocks.TUFF_BRICKS, FurnishingsBlocks.POLISHED_TUFF);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_BRICK_SLAB, FurnishingsBlocks.TUFF_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_BRICK_SLAB, FurnishingsBlocks.TUFF_BRICKS, 2);
        offerStairsRecipe(exporter, FurnishingsBlocks.TUFF_BRICK_STAIRS, FurnishingsBlocks.TUFF_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_BRICK_STAIRS, FurnishingsBlocks.TUFF_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_BRICK_WALL, FurnishingsBlocks.TUFF_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.TUFF_BRICK_WALL, FurnishingsBlocks.TUFF_BRICKS);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FurnishingsBlocks.CHISELED_TUFF_BRICKS, FurnishingsBlocks.TUFF_BRICKS);
    }

    public static void offerBrazierRecipe(Consumer<RecipeJsonProvider> exporter, Block brazier) {
        TagKey<Item> baseItemTag = ((BrazierBlock) brazier).getBaseItemTag();
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, brazier)
            .ingredient('#', baseItemTag)
            .ingredient('I', Items.IRON_INGOT)
            .ingredient('B', Items.IRON_BARS)
            .pattern("B#B")
            .pattern("III")
            .group("brazier")
            .criterion(hasTag(baseItemTag), conditionsFromItemTag(baseItemTag))
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter);
    }

    public static void offerGildedCarpetRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 3)
            .ingredient('G', Items.GOLD_NUGGET)
            .ingredient('C', Items.RED_CARPET)
            .pattern("GGG")
            .pattern("GCG")
            .pattern("GGG")
            .group("trimmed_carpet")
            .criterion(hasItem(Items.RED_CARPET), conditionsFromItem(Items.RED_CARPET))
            .offerTo(exporter);
    }

    public static void offerGlassFramingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 1)
            .ingredient(input)
            .ingredient(Items.IRON_BARS)
            .group("framed_glass")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter);
    }

    public static void offerGlassPaneFramingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 1)
            .ingredient(input)
            .ingredient(Items.IRON_BARS)
            .group("framed_glass_pane")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter, output.asItem().toString() + "_from_iron_bars");
    }

    public static void offerFramedGlassPaneRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 16)
            .ingredient('#', input)
            .pattern("###")
            .pattern("###")
            .group("framed_glass_pane")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter, output.asItem().toString() + "_from_" + input.asItem().toString());
    }

    public static void offerGlassPanelRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 2)
            .ingredient(input)
            .group("glass_panel")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter);
    }

    public static void offerLampRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output)
            .ingredient('#', input)
            .ingredient('I', Items.IRON_INGOT)
            .pattern("III")
            .pattern("###")
            .pattern("III")
            .group("lamp")
            .criterion(hasItem(input), conditionsFromItem(input))
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter);
    }

    public static void offerPedestalRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible middle, ItemConvertible slab) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 2)
            .ingredient('#', middle)
            .ingredient('S', slab)
            .pattern("S")
            .pattern("#")
            .pattern("S")
            .criterion(hasItem(middle), conditionsFromItem(middle))
            .offerTo(exporter);
    }

    public static void offerSconceRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, TagKey<Item> input) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 4)
            .ingredient('#', input)
            .ingredient('I', Items.IRON_INGOT)
            .pattern("#")
            .pattern("I")
            .group("sconce")
            .criterion(hasTag(input), conditionsFromItemTag(input))
            .offerTo(exporter);
    }

    public static void offerLeverSconceRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.REDSTONE, output)
            .ingredient(input)
            .ingredient(Items.REDSTONE)
            .group("lever_sconce")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter);
    }

    public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createStairsRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerCandelabraDyeingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible dye) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output)
            .ingredient(FurnishingsItemTags.CANDELABRAS)
            .ingredient(dye)
            .group("candelabra")
            .criterion("has_candelabra", conditionsFromItemTag(FurnishingsItemTags.CANDELABRAS))
            .offerTo(exporter, dye(output));
    }

    public static void offerCandelabraRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output)
            .ingredient('#', input)
            .ingredient('I', Items.IRON_INGOT)
            .pattern("#")
            .pattern("I")
            .group("candelabra")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter);
    }

    public static void offerCushionDyeingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible dye) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output)
            .ingredient(FurnishingsItemTags.CUSHIONS)
            .ingredient(dye)
            .group("cushion")
            .criterion(hasTag(FurnishingsItemTags.CUSHIONS), conditionsFromItemTag(FurnishingsItemTags.CUSHIONS))
            .offerTo(exporter, dye(output));
    }

    public static void offerCushionRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output)
            .ingredient('#', input)
            .ingredient('X', Ingredient.ofTag(ItemTags.PLANKS))
            .pattern("##")
            .pattern("XX")
            .group("cushion")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter);
    }

    public static void offerPolishedBricksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
            .ingredient('#', input)
            .pattern("##")
            .pattern("##")
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter);
    }

    public static void offerMossyRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output).ingredient(input).ingredient(Blocks.MOSS_BLOCK)
            .criterion(hasItem(Blocks.MOSS_BLOCK), conditionsFromItem(Items.MOSS_BLOCK))
            .group(output.asItem().toString()).offerTo(exporter, output.asItem().toString() + "_from_moss_block");
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output).ingredient(input).ingredient(Items.VINE)
            .criterion(hasItem(Items.VINE), conditionsFromItem(Items.VINE))
            .group(output.asItem().toString()).offerTo(exporter, output.asItem().toString() + "_from_vine");
    }

    public static String dye(ItemConvertible item) {
        return "dye_" + getItemPath(item);
    }

    public static String convertBetween(ItemConvertible from, TagKey<Item> to) {
        return getItemPath(from) + "_from_" + getTagPath(to);
    }

    public static String hasTag(TagKey<Item> tagKey) {
        return "has_" + getTagPath(tagKey);
    }

    public static String getTagPath(TagKey<Item> tagKey) {
        return tagKey.id().getPath();
    }
}
