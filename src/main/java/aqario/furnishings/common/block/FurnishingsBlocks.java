package aqario.furnishings.common.block;

import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.item.IronScaffoldingItem;
import aqario.furnishings.common.sound.FurnishingsSoundEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
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

	/* Candelabras */

	public static final Block CANDELABRA = register("candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.PALE_YELLOW).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block WHITE_CANDELABRA = register("white_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.WHITE_GRAY).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block ORANGE_CANDELABRA = register("orange_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.ORANGE).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block MAGENTA_CANDELABRA = register("magenta_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.MAGENTA).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block LIGHT_BLUE_CANDELABRA = register("light_blue_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.LIGHT_BLUE).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block YELLOW_CANDELABRA = register("yellow_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.YELLOW).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block LIME_CANDELABRA = register("lime_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.LIME).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block PINK_CANDELABRA = register("pink_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.PINK).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block GRAY_CANDELABRA = register("gray_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.GRAY).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block LIGHT_GRAY_CANDELABRA = register("light_gray_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.LIGHT_GRAY).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block CYAN_CANDELABRA = register("cyan_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.CYAN).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block PURPLE_CANDELABRA = register("purple_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.PURPLE).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block BLUE_CANDELABRA = register("blue_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.BLUE).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block BROWN_CANDELABRA = register("brown_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.BROWN).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block GREEN_CANDELABRA = register("green_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.GREEN).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block RED_CANDELABRA = register("red_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.RED).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);
	public static final Block BLACK_CANDELABRA = register("black_candelabra",
		new CandelabraBlock(QuiltBlockSettings.of(Material.DECORATION, MapColor.BLACK).nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE)), ItemGroup.DECORATIONS
	);

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

	/** Cushions */

	public static final Block WHITE_CUSHION = register(
		"white_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.WHITE).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block ORANGE_CUSHION = register(
		"orange_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.ORANGE).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block MAGENTA_CUSHION = register(
		"magenta_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.MAGENTA).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block LIGHT_BLUE_CUSHION = register(
		"light_blue_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.LIGHT_BLUE).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block YELLOW_CUSHION = register(
		"yellow_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.YELLOW).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block LIME_CUSHION = register(
		"lime_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.LIME).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block PINK_CUSHION = register(
		"pink_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.PINK).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block GRAY_CUSHION = register(
		"gray_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.GRAY).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block LIGHT_GRAY_CUSHION = register(
		"light_gray_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.LIGHT_GRAY).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block CYAN_CUSHION = register(
		"cyan_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.CYAN).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block PURPLE_CUSHION = register(
		"purple_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.PURPLE).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block BLUE_CUSHION = register(
		"blue_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.BLUE).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block BROWN_CUSHION = register(
		"brown_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.BROWN).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block GREEN_CUSHION = register(
		"green_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.GREEN).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block RED_CUSHION = register(
		"red_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.RED).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);
	public static final Block BLACK_CUSHION = register(
		"black_cushion", new CushionBlock(QuiltBlockSettings.of(Material.WOOL, MapColor.BLACK).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION)), ItemGroup.DECORATIONS
	);

	/** Dinnerware */

	public static final Block MUG = register("mug", new MugBlock(QuiltBlockSettings.of(Material.DECORATION).breakInstantly().sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);
	public static final Block CHALICE = register("chalice", new ChaliceBlock(QuiltBlockSettings.of(Material.DECORATION).breakInstantly().sounds(BlockSoundGroup.LANTERN)), ItemGroup.DECORATIONS);

    /** Dirt */

    public static final Block ROCKY_DIRT = register("rocky_dirt", new Block(QuiltBlockSettings.copyOf(Blocks.DIRT).strength(1.0F).sounds(BlockSoundGroup.TUFF).requiresTool()), ItemGroup.BUILDING_BLOCKS);

    /** Gilded Carpet */

	public static final Block GILDED_CARPET = register("gilded_carpet", new GildedCarpetBlock(QuiltBlockSettings.copyOf(Blocks.RED_CARPET)), ItemGroup.DECORATIONS);

	/** Glass */

	public static final Block FRAMED_GLASS = register("framed_glass", new GlassBlock(QuiltBlockSettings.of(Material.GLASS).mapColor(MapColor.LIGHT_GRAY).strength(2.0f, 5.0f).sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroup.DECORATIONS);
	public static final Block FRAMED_GLASS_PANE = register("framed_glass_pane", new PaneBlock(QuiltBlockSettings.of(Material.GLASS).mapColor(MapColor.LIGHT_GRAY).strength(2.0f, 5.0f).sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroup.DECORATIONS);
	public static final Block GLASS_PANEL = register("glass_panel", new PanelBlock(QuiltBlockSettings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroup.DECORATIONS);

	/** Iron */

    public static final Block IRON_SCAFFOLDING = registerScaffolding("iron_scaffolding", new IronScaffoldingBlock(QuiltBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN).noCollision().dynamicBounds()));
	public static final Block IRON_GRATE = registerScaffolding("iron_grate", new GrateBlock(QuiltBlockSettings.of(Material.METAL).mapColor(MapColor.CLEAR).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.COPPER).nonOpaque()));

    /** Lamps */

    public static final Block LAMP = register("lamp", new Block(QuiltBlockSettings.of(Material.METAL).luminance(state -> 15).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroup.DECORATIONS);
    public static final Block SOUL_LAMP = register("soul_lamp", new Block(QuiltBlockSettings.of(Material.METAL).luminance(state -> 10).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroup.DECORATIONS);

	/* Misc */

	public static final Block MOSS = register("moss", new GlowLichenBlock(QuiltBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.GREEN).noCollision().strength(0.1F).sounds(BlockSoundGroup.MOSS_CARPET)), ItemGroup.DECORATIONS);
	public static final Block BOOK = registerWithoutItem("book", new BookBlock(QuiltBlockSettings.of(Material.DECORATION).mapColor(MapColor.BROWN).nonOpaque().strength(0.1F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.BOOK)));


	/** Paper Lanterns */

    public static final Block PAPER_LANTERN = register("paper_lantern", new PaperLanternBlock(QuiltBlockSettings.of(Material.WOOL).breakInstantly().sounds(BlockSoundGroup.BAMBOO).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(10).nonOpaque()), ItemGroup.DECORATIONS);
    public static final Block ALLIUM_PAPER_LANTERN = register("allium_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block BLUE_ORCHID_PAPER_LANTERN = register("blue_orchid_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block CRIMSON_ROOTS_PAPER_LANTERN = register("crimson_roots_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block DANDELION_PAPER_LANTERN = register("dandelion_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);
    public static final Block RED_PAPER_LANTERN = register("red_paper_lantern", new RedPaperLanternBlock(QuiltBlockSettings.copyOf(PAPER_LANTERN)), ItemGroup.DECORATIONS);

	/** Pedestal */

	public static final Block STONE_PEDESTAL = register("stone_pedestal", new PedestalBlock(QuiltBlockSettings.of(Material.STONE).mapColor(MapColor.STONE_GRAY).requiresTool().strength(1.5F, 6.0F)), ItemGroup.DECORATIONS);

	/** Sconce */

	public static final Block SCONCE = register("sconce", new SconceBlock(QuiltBlockSettings.of(Material.DECORATION).strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 15 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.FLAME), ItemGroup.DECORATIONS);
	public static final Block SOUL_SCONCE = register("soul_sconce", new SconceBlock(QuiltBlockSettings.of(Material.DECORATION).strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 10 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.SOUL_FIRE_FLAME), ItemGroup.DECORATIONS);
	public static final Block LEVER_SCONCE = register("lever_sconce", new LeverSconceBlock(QuiltBlockSettings.of(Material.DECORATION).strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 15 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.FLAME), ItemGroup.REDSTONE);
	public static final Block LEVER_SOUL_SCONCE = register("lever_soul_sconce", new LeverSconceBlock(QuiltBlockSettings.of(Material.DECORATION).strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 10 : 0).sounds(BlockSoundGroup.LANTERN), ParticleTypes.SOUL_FIRE_FLAME), ItemGroup.REDSTONE);

    /** Tuff */

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

	private static Block registerWithoutItem(String id, Block block) {
		return Registry.register(Registry.BLOCK, new Identifier(Furnishings.ID, id), block);
	}

    private static Block registerScaffolding(String id, Block block) {
        registerScaffoldingBlockItem(id, block);
        return Registry.register(Registry.BLOCK, new Identifier(Furnishings.ID, id), block);
    }

    private static void registerBlockItem(String id, Block block, ItemGroup group) {
		Registry.register(Registry.ITEM, new Identifier(Furnishings.ID, id), new BlockItem(block, new QuiltItemSettings().group(group)));
	}

    private static void registerScaffoldingBlockItem(String id, Block block) {
		Registry.register(Registry.ITEM, new Identifier(Furnishings.ID, id), new IronScaffoldingItem(block, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));
	}

    public static void init() {
    }
}
