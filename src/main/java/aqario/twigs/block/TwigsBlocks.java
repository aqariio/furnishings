package aqario.twigs.block;

import aqario.twigs.Twigs;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.minecraft.block.Blocks.GLASS;

public class TwigsBlocks {

    /** Braziers */

    public static final Block BRAZIER = register("brazier", new BrazierBlock(true, 1, FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).strength(3.0f).sounds(BlockSoundGroup.METAL).luminance((state) -> state.get(BrazierBlock.LIT) ? 15 : 0).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
    public static final Block SOUL_BRAZIER = register("soul_brazier", new BrazierBlock(false, 2, FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).strength(3.0f).sounds(BlockSoundGroup.METAL).luminance((state) -> state.get(BrazierBlock.LIT) ? 10 : 0).nonOpaque()), ItemGroup.BUILDING_BLOCKS);

    /** Bricks */

    public static final Block CRACKED_BRICKS = register("cracked_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHISELED_BRICKS = register("chiseled_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICKS = register("mossy_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_SLAB = register("mossy_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_STAIRS = register("mossy_brick_stairs", new PublicStairsBlock(MOSSY_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_WALL = register("mossy_brick_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /* Calcite */

    public static final Block CALCITE_SLAB = register("calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CALCITE_STAIRS = register("calcite_stairs", new PublicStairsBlock(Blocks.CALCITE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CALCITE_WALL = register("calcite_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE = register("polished_calcite", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_SLAB = register("polished_calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_STAIRS = register("polished_calcite_stairs", new PublicStairsBlock(POLISHED_CALCITE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_WALL = register("polished_calcite_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICKS = register("polished_calcite_bricks", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICK_SLAB = register("polished_calcite_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICK_STAIRS = register("polished_calcite_brick_stairs", new PublicStairsBlock(POLISHED_CALCITE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICK_WALL = register("polished_calcite_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CRACKED_POLISHED_CALCITE_BRICKS = register("cracked_polished_calcite_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /** Cobblestone Bricks */

    public static final Block COBBLESTONE_BRICKS = register("cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CRACKED_COBBLESTONE_BRICKS = register("cracked_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICKS = register("mossy_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = register("mossy_cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = register("mossy_cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = register("mossy_cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /** Dirt */

    public static final Block ROCKY_DIRT = register("rocky_dirt", new Block(FabricBlockSettings.copyOf(Blocks.DIRT).strength(1.0F).sounds(BlockSoundGroup.TUFF).requiresTool()), ItemGroup.BUILDING_BLOCKS);

    /** Framed Glass */

    public static final Block FRAMED_GLASS = register("framed_glass", new GlassBlock(FabricBlockSettings.of(Material.GLASS).mapColor(MapColor.LIGHT_GRAY).strength(1.0f, 1.0f).requiresTool().sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
    public static final Block FRAMED_GLASS_PANE = register("framed_glass_pane", new PaneBlock(FabricBlockSettings.of(Material.GLASS).mapColor(MapColor.LIGHT_GRAY).strength(1.0f, 1.0f).requiresTool().sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroup.BUILDING_BLOCKS);

    /** Lamps */

    public static final Block LAMP = register("lamp", new LampBlock(FabricBlockSettings.of(Material.METAL).luminance((state) -> state.get(LampBlock.LIT) ? 15 : 0).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block SOUL_LAMP = register("soul_lamp", new LampBlock(FabricBlockSettings.of(Material.METAL).luminance((state) -> state.get(LampBlock.LIT) ? 10 : 0).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroup.BUILDING_BLOCKS);

    /** Paper Lanterns */

    public static final Block PAPER_LANTERN = register("paper_lantern", new PaperLanternBlock(FabricBlockSettings.of(Material.WOOL).breakInstantly().sounds(BlockSoundGroup.BAMBOO).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(10).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
    public static final Block ALLIUM_PAPER_LANTERN = register("allium_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_ORCHID_PAPER_LANTERN = register("blue_orchid_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CRIMSON_ROOTS_PAPER_LANTERN = register("crimson_roots_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DANDELION_PAPER_LANTERN = register("dandelion_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block RED_PAPER_LANTERN = register("red_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.BUILDING_BLOCKS);

    /* Tuff */

    public static final Block TUFF_SLAB = register("tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block TUFF_STAIRS = register("tuff_stairs", new PublicStairsBlock(Blocks.TUFF.getDefaultState(), FabricBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block TUFF_WALL = register("tuff_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF = register("polished_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_SLAB = register("polished_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_STAIRS = register("polished_tuff_stairs", new PublicStairsBlock(POLISHED_TUFF.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_WALL = register("polished_tuff_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICKS = register("polished_tuff_bricks", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICK_SLAB = register("polished_tuff_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICK_STAIRS = register("polished_tuff_brick_stairs", new PublicStairsBlock(POLISHED_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICK_WALL = register("polished_tuff_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CRACKED_POLISHED_TUFF_BRICKS = register("cracked_polished_tuff_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /** Registration and Initialization */

    private static Block register(String id, Block block, ItemGroup group) {
        registerBlockItem(id, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Twigs.MODID, id), block);
    }

    private static Item registerBlockItem(String id, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(Twigs.MODID, id), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void init() {
    }
}
