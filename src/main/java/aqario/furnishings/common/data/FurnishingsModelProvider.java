package aqario.furnishings.common.data;

import aqario.furnishings.common.block.CandelabraBlock;
import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.block.enums.NoCeilingWallMountLocation;
import aqario.furnishings.common.data.client.model.FurnishingsModels;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class FurnishingsModelProvider extends FabricModelProvider {
    public FurnishingsModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
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
    }

	@Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
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

		blockStateModelGenerator.blockStateCollector
			.accept(
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
}
