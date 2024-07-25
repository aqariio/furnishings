package aqario.furnishings.common.data;

import aqario.furnishings.common.block.CandelabraBlock;
import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.block.enums.NoCeilingWallMountLocation;
import aqario.furnishings.common.data.client.model.FurnishingsModels;
import aqario.furnishings.common.data.client.model.FurnishingsTexturedModel;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class FurnishingsModelProvider extends FabricModelProvider {
    public FurnishingsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        // Braziers



        // Bricks

        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.MOSSY_BRICKS);
        registerSlab(blockStateModelGenerator, FurnishingsBlocks.MOSSY_BRICK_SLAB, FurnishingsBlocks.MOSSY_BRICKS);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.MOSSY_BRICK_STAIRS, FurnishingsBlocks.MOSSY_BRICKS);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.MOSSY_BRICK_WALL, FurnishingsBlocks.MOSSY_BRICKS);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.CRACKED_BRICKS);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.CHISELED_BRICKS);

        // Calcite

        registerSlab(blockStateModelGenerator, FurnishingsBlocks.CALCITE_SLAB, Blocks.CALCITE);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.CALCITE_WALL, Blocks.CALCITE);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.POLISHED_CALCITE);
        registerSlab(blockStateModelGenerator, FurnishingsBlocks.POLISHED_CALCITE_SLAB, FurnishingsBlocks.POLISHED_CALCITE);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.POLISHED_CALCITE_STAIRS, FurnishingsBlocks.POLISHED_CALCITE);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.POLISHED_CALCITE_WALL, FurnishingsBlocks.POLISHED_CALCITE);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.CALCITE_BRICKS);
        registerSlab(blockStateModelGenerator, FurnishingsBlocks.CALCITE_BRICK_SLAB, FurnishingsBlocks.CALCITE_BRICKS);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.CALCITE_BRICK_STAIRS, FurnishingsBlocks.CALCITE_BRICKS);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.CALCITE_BRICK_WALL, FurnishingsBlocks.CALCITE_BRICKS);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.CHISELED_CALCITE_BRICKS);

        // Candelabras

        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.CANDELABRA, Blocks.CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.WHITE_CANDELABRA, Blocks.WHITE_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.LIGHT_GRAY_CANDELABRA, Blocks.LIGHT_GRAY_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.GRAY_CANDELABRA, Blocks.GRAY_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.BLACK_CANDELABRA, Blocks.BLACK_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.BROWN_CANDELABRA, Blocks.BROWN_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.RED_CANDELABRA, Blocks.RED_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.ORANGE_CANDELABRA, Blocks.ORANGE_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.YELLOW_CANDELABRA, Blocks.YELLOW_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.LIME_CANDELABRA, Blocks.LIME_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.GREEN_CANDELABRA, Blocks.GREEN_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.CYAN_CANDELABRA, Blocks.CYAN_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.LIGHT_BLUE_CANDELABRA, Blocks.LIGHT_BLUE_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.BLUE_CANDELABRA, Blocks.BLUE_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.PURPLE_CANDELABRA, Blocks.PURPLE_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.MAGENTA_CANDELABRA, Blocks.MAGENTA_CANDLE);
        registerCandelabra(blockStateModelGenerator, FurnishingsBlocks.PINK_CANDELABRA, Blocks.PINK_CANDLE);

        // Carpets



        // Cobblestone Bricks

        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.COBBLESTONE_BRICKS);
        registerSlab(blockStateModelGenerator, FurnishingsBlocks.COBBLESTONE_BRICK_SLAB, FurnishingsBlocks.COBBLESTONE_BRICKS);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.COBBLESTONE_BRICK_STAIRS, FurnishingsBlocks.COBBLESTONE_BRICKS);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.COBBLESTONE_BRICK_WALL, FurnishingsBlocks.COBBLESTONE_BRICKS);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        registerSlab(blockStateModelGenerator, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_SLAB, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICK_WALL, FurnishingsBlocks.MOSSY_COBBLESTONE_BRICKS);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.CRACKED_COBBLESTONE_BRICKS);

        // Cushions

        registerCushion(blockStateModelGenerator, FurnishingsBlocks.WHITE_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.LIGHT_GRAY_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.GRAY_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.BLACK_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.BROWN_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.RED_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.ORANGE_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.YELLOW_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.LIME_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.GREEN_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.CYAN_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.LIGHT_BLUE_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.BLUE_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.PURPLE_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.MAGENTA_CUSHION);
        registerCushion(blockStateModelGenerator, FurnishingsBlocks.PINK_CUSHION);

        // Decorations



        // Glass



        // Iron



        // Lamps

        registerLamp(blockStateModelGenerator, FurnishingsBlocks.LAMP);
        registerLamp(blockStateModelGenerator, FurnishingsBlocks.SOUL_LAMP);

        // Nature



        // Pedestals



        // Sconces



        // Tuff

        registerSlab(blockStateModelGenerator, FurnishingsBlocks.TUFF_SLAB, Blocks.TUFF);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.TUFF_STAIRS, Blocks.TUFF);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.TUFF_WALL, Blocks.TUFF);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.POLISHED_TUFF);
        registerSlab(blockStateModelGenerator, FurnishingsBlocks.POLISHED_TUFF_SLAB, FurnishingsBlocks.POLISHED_TUFF);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.POLISHED_TUFF_STAIRS, FurnishingsBlocks.POLISHED_TUFF);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.POLISHED_TUFF_WALL, FurnishingsBlocks.POLISHED_TUFF);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.TUFF_BRICKS);
        registerSlab(blockStateModelGenerator, FurnishingsBlocks.TUFF_BRICK_SLAB, FurnishingsBlocks.TUFF_BRICKS);
        registerStairs(blockStateModelGenerator, FurnishingsBlocks.TUFF_BRICK_STAIRS, FurnishingsBlocks.TUFF_BRICKS);
        registerWall(blockStateModelGenerator, FurnishingsBlocks.TUFF_BRICK_WALL, FurnishingsBlocks.TUFF_BRICKS);
        registerCubeAll(blockStateModelGenerator, FurnishingsBlocks.CHISELED_TUFF_BRICKS);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }

    public final void registerCubeAll(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        blockStateModelGenerator.registerSimpleCubeAll(block);
    }

    public final void registerSlab(BlockStateModelGenerator blockStateModelGenerator, Block slab, Block block) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
        Identifier slabBottomModel = Models.SLAB.upload(slab, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        Identifier slabTopModel = Models.SLAB_TOP.upload(slab, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        Identifier fullModel = ModelIds.getBlockModelId(block);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, slabBottomModel, slabTopModel, fullModel));
    }

    public final void registerStairs(BlockStateModelGenerator blockStateModelGenerator, Block stairs, Block block) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
        Identifier innerStairsModel = Models.INNER_STAIRS.upload(stairs, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        Identifier stairsModel = Models.STAIRS.upload(stairs, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        Identifier outerStairsModel = Models.OUTER_STAIRS.upload(stairs, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs, innerStairsModel, stairsModel, outerStairsModel));
    }

    public final void registerWall(BlockStateModelGenerator blockStateModelGenerator, Block wall, Block block) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
        Identifier postModel = Models.TEMPLATE_WALL_POST.upload(wall, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        Identifier lowSideModel = Models.TEMPLATE_WALL_SIDE.upload(wall, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        Identifier tallSideModel = Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wall, postModel, lowSideModel, tallSideModel));
        Identifier inventoryModel = Models.WALL_INVENTORY.upload(wall, texturedModel.getTexture(), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(wall, inventoryModel);
    }

    public final void registerCandelabra(BlockStateModelGenerator blockStateModelGenerator, Block candelabra, Block candle) {
        blockStateModelGenerator.registerItemModel(candelabra.asItem());
        Texture texture = candle(candle, false);
        Texture litTexture = candle(candle, true);
        Identifier floor1 = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_1.upload(candelabra, "_floor_1", texture, blockStateModelGenerator.modelCollector);
        Identifier floor2 = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_2.upload(candelabra, "_floor_2", texture, blockStateModelGenerator.modelCollector);
        Identifier floor3 = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_3.upload(candelabra, "_floor_3", texture, blockStateModelGenerator.modelCollector);
        Identifier floor4 = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_4.upload(candelabra, "_floor_4", texture, blockStateModelGenerator.modelCollector);
        Identifier floor1Lit = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_1.upload(candelabra, "_floor_1_lit", litTexture, blockStateModelGenerator.modelCollector);
        Identifier floor2Lit = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_2.upload(candelabra, "_floor_2_lit", litTexture, blockStateModelGenerator.modelCollector);
        Identifier floor3Lit = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_3.upload(candelabra, "_floor_3_lit", litTexture, blockStateModelGenerator.modelCollector);
        Identifier floor4Lit = FurnishingsModels.TEMPLATE_CANDELABRA_FLOOR_4.upload(candelabra, "_floor_4_lit", litTexture, blockStateModelGenerator.modelCollector);

        Identifier wall1 = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_1.upload(candelabra, "_wall_1", texture, blockStateModelGenerator.modelCollector);
        Identifier wall2 = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_2.upload(candelabra, "_wall_2", texture, blockStateModelGenerator.modelCollector);
        Identifier wall3 = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_3.upload(candelabra, "_wall_3", texture, blockStateModelGenerator.modelCollector);
        Identifier wall4 = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_4.upload(candelabra, "_wall_4", texture, blockStateModelGenerator.modelCollector);
        Identifier wall1Lit = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_1.upload(candelabra, "_wall_1_lit", litTexture, blockStateModelGenerator.modelCollector);
        Identifier wall2Lit = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_2.upload(candelabra, "_wall_2_lit", litTexture, blockStateModelGenerator.modelCollector);
        Identifier wall3Lit = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_3.upload(candelabra, "_wall_3_lit", litTexture, blockStateModelGenerator.modelCollector);
        Identifier wall4Lit = FurnishingsModels.TEMPLATE_CANDELABRA_WALL_4.upload(candelabra, "_wall_4_lit", litTexture, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(candelabra)
                .coordinate(
                    BlockStateVariantMap.create(Properties.HORIZONTAL_FACING)
                        .register(
                            Direction.NORTH,
                            BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0)
                        )
                        .register(
                            Direction.EAST,
                            BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90)
                        )
                        .register(
                            Direction.SOUTH,
                            BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180)
                        )
                        .register(
                            Direction.WEST,
                            BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270)
                        )
                )
                .coordinate(
                    BlockStateVariantMap.create(Properties.CANDLES, Properties.LIT, CandelabraBlock.FACE)
                        .register(1, false, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor1))
                        .register(2, false, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor2))
                        .register(3, false, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor3))
                        .register(4, false, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor4))
                        .register(1, true, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor1Lit))
                        .register(2, true, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor2Lit))
                        .register(3, true, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor3Lit))
                        .register(4, true, NoCeilingWallMountLocation.FLOOR, BlockStateVariant.create().put(VariantSettings.MODEL, floor4Lit))
                        .register(1, false, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall1))
                        .register(2, false, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall2))
                        .register(3, false, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall3))
                        .register(4, false, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall4))
                        .register(1, true, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall1Lit))
                        .register(2, true, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall2Lit))
                        .register(3, true, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall3Lit))
                        .register(4, true, NoCeilingWallMountLocation.WALL, BlockStateVariant.create().put(VariantSettings.MODEL, wall4Lit))
                )
        );
    }

    public static Texture candle(Block block, boolean lit) {
        return new Texture()
            .put(TextureKey.CANDLE, Texture.getSubId(block, lit ? "_lit" : ""));
    }

    public final void registerConnectingCarpet(BlockStateModelGenerator blockStateModelGenerator, Block carpet) {

    }

    public final void registerCushion(BlockStateModelGenerator blockStateModelGenerator, Block cushion) {
        blockStateModelGenerator.registerSingleton(cushion, FurnishingsTexturedModel.TEMPLATE_CUSHION);
    }

    public final void registerLamp(BlockStateModelGenerator blockStateModelGenerator, Block lamp) {
        blockStateModelGenerator.registerSingleton(lamp, TexturedModel.CUBE_BOTTOM_TOP);
    }
}
