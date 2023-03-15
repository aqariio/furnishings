package aqario.furnishings.common.block;

import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.item.IronScaffoldingItem;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class FurnishingsBlocks {

    /** Braziers */

    public static final Block BRAZIER = register("brazier", new BrazierBlock(true, 1, QuiltBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).strength(3.0f).sounds(BlockSoundGroup.METAL).requiresTool().luminance((state) -> state.get(BrazierBlock.LIT) ? 15 : 0).nonOpaque()), ItemGroup.DECORATIONS);
    public static final Block SOUL_BRAZIER = register("soul_brazier", new BrazierBlock(false, 2, QuiltBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).strength(3.0f).sounds(BlockSoundGroup.METAL).requiresTool().luminance((state) -> state.get(BrazierBlock.LIT) ? 10 : 0).nonOpaque()), ItemGroup.DECORATIONS);

    /** Bricks */

    public static final Block CRACKED_BRICKS = register("cracked_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHISELED_BRICKS = register("chiseled_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICKS = register("mossy_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_SLAB = register("mossy_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_STAIRS = register("mossy_brick_stairs", new PublicStairsBlock(MOSSY_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_WALL = register("mossy_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /** Calcite */

    public static final Block CALCITE_SLAB = register("calcite_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CALCITE_STAIRS = register("calcite_stairs", new PublicStairsBlock(Blocks.CALCITE.getDefaultState(), QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CALCITE_WALL = register("calcite_wall", new WallBlock(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE = register("polished_calcite", new Block(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_SLAB = register("polished_calcite_slab", new SlabBlock(QuiltBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_STAIRS = register("polished_calcite_stairs", new PublicStairsBlock(POLISHED_CALCITE.getDefaultState(), QuiltBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_WALL = register("polished_calcite_wall", new WallBlock(QuiltBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICKS = register("polished_calcite_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICK_SLAB = register("polished_calcite_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICK_STAIRS = register("polished_calcite_brick_stairs", new PublicStairsBlock(POLISHED_CALCITE_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_BRICK_WALL = register("polished_calcite_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CRACKED_POLISHED_CALCITE_BRICKS = register("cracked_polished_calcite_bricks", new Block(QuiltBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /** Cobblestone Bricks */

    public static final Block COBBLESTONE_BRICKS = register("cobblestone_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.COBBLESTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CRACKED_COBBLESTONE_BRICKS = register("cracked_cobblestone_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.COBBLESTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICKS = register("mossy_cobblestone_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.COBBLESTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = register("mossy_cobblestone_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = register("mossy_cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = register("mossy_cobblestone_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /** Dirt */

    public static final Block ROCKY_DIRT = register("rocky_dirt", new Block(QuiltBlockSettings.copyOf(Blocks.DIRT).strength(1.0F).sounds(BlockSoundGroup.TUFF).requiresTool()), ItemGroup.BUILDING_BLOCKS);

    /** Embossed Carpet */

    public static final Block EMBOSSED_CARPET = register("embossed_carpet", new EmbossedCarpetBlock(QuiltBlockSettings.copyOf(Blocks.RED_CARPET)), ItemGroup.DECORATIONS);

    /** Framed Glass */

    public static final Block FRAMED_GLASS = register("framed_glass", new GlassBlock(QuiltBlockSettings.of(Material.GLASS).mapColor(MapColor.LIGHT_GRAY).strength(1.0f, 1.0f).requiresTool().sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
    public static final Block FRAMED_GLASS_PANE = register("framed_glass_pane", new PaneBlock(QuiltBlockSettings.of(Material.GLASS).mapColor(MapColor.LIGHT_GRAY).strength(1.0f, 1.0f).requiresTool().sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroup.BUILDING_BLOCKS);

    /** Iron Scaffolding */

    public static final Block IRON_SCAFFOLDING = registerScaffolding("iron_scaffolding", new IronScaffoldingBlock(QuiltBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN).noCollision().dynamicBounds()));

    /** Lamps */

    public static final Block LAMP = register("lamp", new Block(QuiltBlockSettings.of(Material.METAL).luminance(state -> 15).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroup.DECORATIONS);
    public static final Block SOUL_LAMP = register("soul_lamp", new Block(QuiltBlockSettings.of(Material.METAL).luminance(state -> 10).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroup.DECORATIONS);

	/** Mug */

	public static final Block SPRUCE_MUG = register("spruce_mug", new MugBlock(QuiltBlockSettings.of(Material.WOOD).breakInstantly().sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);

    /** Paper Lanterns */

    public static final Block PAPER_LANTERN = register("paper_lantern", new PaperLanternBlock(QuiltBlockSettings.of(Material.WOOL).breakInstantly().sounds(BlockSoundGroup.BAMBOO).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(10).nonOpaque()), ItemGroup.DECORATIONS);
    public static final Block ALLIUM_PAPER_LANTERN = register("allium_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block BLUE_ORCHID_PAPER_LANTERN = register("blue_orchid_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block CRIMSON_ROOTS_PAPER_LANTERN = register("crimson_roots_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block DANDELION_PAPER_LANTERN = register("dandelion_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block RED_PAPER_LANTERN = register("red_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);

	/** Pedestal */

	public static final Block STONE_PEDESTAL = register("stone_pedestal", new PedestalBlock(QuiltBlockSettings.of(Material.STONE).mapColor(MapColor.STONE_GRAY).requiresTool().strength(1.5F, 6.0F)), ItemGroup.DECORATIONS);

	/* Sconce */

	public static final Block SCONCE = register("sconce", new SconceBlock(QuiltBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> state.get(SconceBlock.LIT) ? 15 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.FLAME), ItemGroup.DECORATIONS);
	public static final Block SOUL_SCONCE = register("soul_sconce", new SconceBlock(QuiltBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> state.get(SconceBlock.LIT) ? 10 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.SOUL_FIRE_FLAME), ItemGroup.DECORATIONS);
	public static final Block LEVER_SCONCE = register("lever_sconce", new LeverSconceBlock(QuiltBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> state.get(SconceBlock.LIT) ? 15 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.FLAME), ItemGroup.REDSTONE);
	public static final Block LEVER_SOUL_SCONCE = register("lever_soul_sconce", new LeverSconceBlock(QuiltBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> state.get(SconceBlock.LIT) ? 10 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.SOUL_FIRE_FLAME), ItemGroup.REDSTONE);

    /* Tuff */

    public static final Block TUFF_SLAB = register("tuff_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block TUFF_STAIRS = register("tuff_stairs", new PublicStairsBlock(Blocks.TUFF.getDefaultState(), QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block TUFF_WALL = register("tuff_wall", new WallBlock(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF = register("polished_tuff", new Block(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_SLAB = register("polished_tuff_slab", new SlabBlock(QuiltBlockSettings.copyOf(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_STAIRS = register("polished_tuff_stairs", new PublicStairsBlock(POLISHED_TUFF.getDefaultState(), QuiltBlockSettings.copyOf(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_WALL = register("polished_tuff_wall", new WallBlock(QuiltBlockSettings.copyOf(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICKS = register("polished_tuff_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICK_SLAB = register("polished_tuff_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICK_STAIRS = register("polished_tuff_brick_stairs", new PublicStairsBlock(POLISHED_TUFF_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_BRICK_WALL = register("polished_tuff_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CRACKED_POLISHED_TUFF_BRICKS = register("cracked_polished_tuff_bricks", new Block(QuiltBlockSettings.copyOf(POLISHED_TUFF_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    /** Registration and Initialization */

    private static Block register(String id, Block block, ItemGroup group) {
        registerBlockItem(id, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Furnishings.ID, id), block);
    }

    private static Block registerScaffolding(String id, Block block) {
        registerScaffoldingBlockItem(id, block);
        return Registry.register(Registry.BLOCK, new Identifier(Furnishings.ID, id), block);
    }

    private static Item registerBlockItem(String id, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(Furnishings.ID, id), new BlockItem(block, new QuiltItemSettings().group(group)));
    }

    private static Item registerScaffoldingBlockItem(String id, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(Furnishings.ID, id), new IronScaffoldingItem(block, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));
    }

    public static void init() {
    }
}
