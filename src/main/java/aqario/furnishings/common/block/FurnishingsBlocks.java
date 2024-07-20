package aqario.furnishings.common.block;

import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.item.IronScaffoldingItem;
import aqario.furnishings.common.sound.FurnishingsSoundEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class FurnishingsBlocks {
    /**
     * Braziers
     */
    public static final Block BRAZIER = register("brazier", new BrazierBlock(BrazierBlock.BrazierType.NORMAL, QuiltBlockSettings.create().strength(3.0f).sounds(BlockSoundGroup.METAL).requiresTool().luminance((state) -> state.get(BrazierBlock.LIT) ? 15 : 0).nonOpaque()), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Block SOUL_BRAZIER = register("soul_brazier", new BrazierBlock(BrazierBlock.BrazierType.SOUL, QuiltBlockSettings.create().strength(3.0f).sounds(BlockSoundGroup.METAL).requiresTool().luminance((state) -> state.get(BrazierBlock.LIT) ? 10 : 0).nonOpaque()), ItemGroups.FUNCTIONAL_BLOCKS);

    /**
     * Bricks
     */
    public static final Block MOSSY_BRICKS = register("mossy_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_SLAB = register("mossy_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_STAIRS = register("mossy_brick_stairs", new StairsBlock(MOSSY_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block MOSSY_BRICK_WALL = register("mossy_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(MOSSY_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CRACKED_BRICKS = register("cracked_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CHISELED_BRICKS = register("chiseled_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS)), ItemGroups.BUILDING_BLOCKS);

    /**
     * Calcite
     */
    public static final Block CALCITE_SLAB = register("calcite_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CALCITE_STAIRS = register("calcite_stairs", new StairsBlock(Blocks.CALCITE.getDefaultState(), QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CALCITE_WALL = register("calcite_wall", new WallBlock(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE = register("polished_calcite", new Block(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_SLAB = register("polished_calcite_slab", new SlabBlock(QuiltBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_STAIRS = register("polished_calcite_stairs", new StairsBlock(POLISHED_CALCITE.getDefaultState(), QuiltBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_WALL = register("polished_calcite_wall", new WallBlock(QuiltBlockSettings.copyOf(POLISHED_CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CALCITE_BRICKS = register("calcite_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.CALCITE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CALCITE_BRICK_SLAB = register("calcite_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(CALCITE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CALCITE_BRICK_STAIRS = register("calcite_brick_stairs", new StairsBlock(CALCITE_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(CALCITE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CALCITE_BRICK_WALL = register("calcite_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(CALCITE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CHISELED_CALCITE_BRICKS = register("chiseled_calcite_bricks", new Block(QuiltBlockSettings.copyOf(CALCITE_BRICKS)), ItemGroups.BUILDING_BLOCKS);

    /**
     * Candelabras
     */
    public static final Block CANDELABRA = register(
        "candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.SAND)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block WHITE_CANDELABRA = register(
        "white_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.WOOL)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block LIGHT_GRAY_CANDELABRA = register(
        "light_gray_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.LIGHT_GRAY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block GRAY_CANDELABRA = register(
        "gray_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.GRAY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block BLACK_CANDELABRA = register(
        "black_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.BLACK)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block BROWN_CANDELABRA = register(
        "brown_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.BROWN)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block RED_CANDELABRA = register(
        "red_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.RED)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block ORANGE_CANDELABRA = register(
        "orange_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.ORANGE)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block YELLOW_CANDELABRA = register(
        "yellow_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.YELLOW)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block LIME_CANDELABRA = register(
        "lime_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.LIME)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block GREEN_CANDELABRA = register(
        "green_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.GREEN)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block CYAN_CANDELABRA = register(
        "cyan_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.CYAN)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block LIGHT_BLUE_CANDELABRA = register(
        "light_blue_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.LIGHT_BLUE)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block BLUE_CANDELABRA = register(
        "blue_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.BLUE)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block PURPLE_CANDELABRA = register(
        "purple_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.PURPLE)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block MAGENTA_CANDELABRA = register(
        "magenta_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.MAGENTA)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block PINK_CANDELABRA = register(
        "pink_candelabra",
        new CandelabraBlock(QuiltBlockSettings.create().nonOpaque().strength(2.0F).sounds(BlockSoundGroup.LANTERN).luminance(CandelabraBlock.STATE_TO_LUMINANCE).mapColor(MapColor.PINK)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );

    /**
     * Carpets
     */
    public static final Block GILDED_CARPET = register("gilded_carpet", new ConnectingCarpetBlock(QuiltBlockSettings.copyOf(Blocks.RED_CARPET)), ItemGroups.FUNCTIONAL_BLOCKS);

    /**
     * Cobblestone Bricks
     */
    public static final Block COBBLESTONE_BRICKS = register("cobblestone_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.COBBLESTONE).sounds(BlockSoundGroup.DEEPSLATE)), ItemGroups.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs", new StairsBlock(COBBLESTONE_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICKS = register("mossy_cobblestone_bricks", new Block(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = register("mossy_cobblestone_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = register("mossy_cobblestone_brick_stairs", new StairsBlock(COBBLESTONE_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = register("mossy_cobblestone_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CRACKED_COBBLESTONE_BRICKS = register("cracked_cobblestone_bricks", new Block(QuiltBlockSettings.copyOf(COBBLESTONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_TILES)), ItemGroups.BUILDING_BLOCKS);

    /**
     * Cushions
     */
    public static final Block WHITE_CUSHION = register(
        "white_cushion",
        new CushionBlock(QuiltBlockSettings.create().mapColor(MapColor.WOOL).strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block LIGHT_GRAY_CUSHION = register(
        "light_gray_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block GRAY_CUSHION = register(
        "gray_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block BLACK_CUSHION = register(
        "black_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block BROWN_CUSHION = register(
        "brown_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block RED_CUSHION = register(
        "red_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block ORANGE_CUSHION = register(
        "orange_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block YELLOW_CUSHION = register(
        "yellow_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block LIME_CUSHION = register(
        "lime_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block GREEN_CUSHION = register(
        "green_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block CYAN_CUSHION = register(
        "cyan_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block LIGHT_BLUE_CUSHION = register(
        "light_blue_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block BLUE_CUSHION = register(
        "blue_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block PURPLE_CUSHION = register(
        "purple_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block MAGENTA_CUSHION = register(
        "magenta_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );
    public static final Block PINK_CUSHION = register(
        "pink_cushion",
        new CushionBlock(QuiltBlockSettings.create().strength(0.2F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.CUSHION).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS, ItemGroups.COLORED_BLOCKS
    );

    /**
     * Decorations
     */
    public static final Block MUG = register("mug", new MugBlock(QuiltBlockSettings.create().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Block CHALICE = register("chalice", new ChaliceBlock(QuiltBlockSettings.create().breakInstantly().sounds(BlockSoundGroup.LANTERN).pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Block BOOK = registerWithoutItem("book", new BookBlock(QuiltBlockSettings.create().mapColor(MapColor.BROWN).nonOpaque().strength(0.1F).sounds(FurnishingsSoundEvents.FurnishingsBlockSoundGroup.BOOK).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block RED_PAPER_LANTERN = register("red_paper_lantern", new PaperLanternBlock(QuiltBlockSettings.create().breakInstantly().sounds(BlockSoundGroup.BAMBOO).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(10).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), ItemGroups.FUNCTIONAL_BLOCKS);

    /**
     * Glass
     */
    public static final Block FRAMED_GLASS = register("framed_glass", new GlassBlock(QuiltBlockSettings.create().mapColor(MapColor.LIGHT_GRAY).strength(3.0f, 6.0f).requiresTool().sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Block FRAMED_GLASS_PANE = register("framed_glass_pane", new PaneBlock(QuiltBlockSettings.create().mapColor(MapColor.LIGHT_GRAY).strength(3.0f, 6.0f).requiresTool().sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Block GLASS_PANEL = register("glass_panel", new PanelBlock(QuiltBlockSettings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()), ItemGroups.FUNCTIONAL_BLOCKS);

    /**
     * Iron
     */
    public static final Block IRON_GRATE = register("iron_grate", new GrateBlock(QuiltBlockSettings.create().mapColor(MapColor.NONE).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.COPPER).nonOpaque()), ItemGroups.BUILDING_BLOCKS);
    public static final Block IRON_SCAFFOLDING = registerScaffolding("iron_scaffolding", new IronScaffoldingBlock(QuiltBlockSettings.create().mapColor(MapColor.METAL).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN).noCollision().dynamicBounds()));

    /**
     * Lamps
     */
    public static final Block LAMP = register("lamp", new Block(QuiltBlockSettings.create().luminance(state -> 15).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Block SOUL_LAMP = register("soul_lamp", new Block(QuiltBlockSettings.create().luminance(state -> 10).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)), ItemGroups.FUNCTIONAL_BLOCKS);

    /* Nature */
    public static final Block ROCKY_DIRT = register("rocky_dirt", new Block(QuiltBlockSettings.copyOf(Blocks.DIRT).strength(1.0F).sounds(BlockSoundGroup.TUFF).requiresTool()), ItemGroups.NATURAL_BLOCKS);
    public static final Block MOSS = register("moss", new GlowLichenBlock(QuiltBlockSettings.create().noCollision().strength(0.1F).sounds(BlockSoundGroup.MOSS_CARPET)), ItemGroups.NATURAL_BLOCKS);

    /**
     * Pedestals
     */
    public static final Block QUARTZ_PEDESTAL = register("quartz_pedestal", new PedestalBlock(QuiltBlockSettings.create().mapColor(MapColor.QUARTZ).requiresTool().strength(0.8F)), ItemGroups.FUNCTIONAL_BLOCKS);
    public static final Block STONE_PEDESTAL = register("stone_pedestal", new PedestalBlock(QuiltBlockSettings.create().mapColor(MapColor.STONE).requiresTool().strength(1.5F, 6.0F)), ItemGroups.FUNCTIONAL_BLOCKS);

    /**
     * Sconces
     */
    public static final Block SCONCE = register(
        "sconce",
        new SconceBlock(QuiltBlockSettings.create().strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 15 : 0).sounds(BlockSoundGroup.LANTERN)), ItemGroups.FUNCTIONAL_BLOCKS
    );
    public static final Block SOUL_SCONCE = register(
        "soul_sconce",
        new SconceBlock(QuiltBlockSettings.create().strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 10 : 0).sounds(BlockSoundGroup.LANTERN)), ItemGroups.FUNCTIONAL_BLOCKS
    );
    public static final Block LEVER_SCONCE = register(
        "lever_sconce",
        new LeverSconceBlock(QuiltBlockSettings.create().strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 15 : 0).sounds(BlockSoundGroup.LANTERN)), ItemGroups.REDSTONE_BLOCKS
    );
    public static final Block LEVER_SOUL_SCONCE = register(
        "lever_soul_sconce",
        new LeverSconceBlock(QuiltBlockSettings.create().strength(2.0F).requiresTool().luminance((state) -> state.get(SconceBlock.LIT) ? 10 : 0).sounds(BlockSoundGroup.LANTERN)), ItemGroups.REDSTONE_BLOCKS
    );

    /**
     * Tuff
     */
    public static final Block TUFF_SLAB = register("tuff_slab", new SlabBlock(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block TUFF_STAIRS = register("tuff_stairs", new StairsBlock(Blocks.TUFF.getDefaultState(), QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block TUFF_WALL = register("tuff_wall", new WallBlock(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF = register("polished_tuff", new Block(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_SLAB = register("polished_tuff_slab", new SlabBlock(QuiltBlockSettings.copyOf(POLISHED_TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_STAIRS = register("polished_tuff_stairs", new StairsBlock(POLISHED_TUFF.getDefaultState(), QuiltBlockSettings.copyOf(POLISHED_TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_WALL = register("polished_tuff_wall", new WallBlock(QuiltBlockSettings.copyOf(POLISHED_TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block TUFF_BRICKS = register("tuff_bricks", new Block(QuiltBlockSettings.copyOf(Blocks.TUFF)), ItemGroups.BUILDING_BLOCKS);
    public static final Block TUFF_BRICK_SLAB = register("tuff_brick_slab", new SlabBlock(QuiltBlockSettings.copyOf(TUFF_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block TUFF_BRICK_STAIRS = register("tuff_brick_stairs", new StairsBlock(TUFF_BRICKS.getDefaultState(), QuiltBlockSettings.copyOf(TUFF_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block TUFF_BRICK_WALL = register("tuff_brick_wall", new WallBlock(QuiltBlockSettings.copyOf(TUFF_BRICKS)), ItemGroups.BUILDING_BLOCKS);
    public static final Block CHISELED_TUFF_BRICKS = register("chiseled_tuff_bricks", new Block(QuiltBlockSettings.copyOf(TUFF_BRICKS)), ItemGroups.BUILDING_BLOCKS);

    /**
     * Registration and Initialization
     */
    @SafeVarargs
    private static Block register(String id, Block block, RegistryKey<ItemGroup>... groups) {
        registerBlockItem(id, block, groups);
        return Registry.register(Registries.BLOCK, new Identifier(Furnishings.ID, id), block);
    }

    private static Block registerWithoutItem(String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Furnishings.ID, id), block);
    }

    private static Block registerScaffolding(String id, Block block) {
        registerScaffoldingBlockItem(id, block);
        return Registry.register(Registries.BLOCK, new Identifier(Furnishings.ID, id), block);
    }

    @SafeVarargs
    private static void registerBlockItem(String id, Block block, RegistryKey<ItemGroup>... groups) {
        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addItem(block));
        }
        Registry.register(Registries.ITEM, new Identifier(Furnishings.ID, id), new BlockItem(block, new QuiltItemSettings()));
    }

    private static void registerScaffoldingBlockItem(String id, Block block) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(Items.SCAFFOLDING, block));
        Registry.register(Registries.ITEM, new Identifier(Furnishings.ID, id), new IronScaffoldingItem(block, new QuiltItemSettings()));
    }

    public static void init() {
    }
}