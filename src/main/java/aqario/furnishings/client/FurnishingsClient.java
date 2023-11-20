package aqario.furnishings.client;

import aqario.furnishings.client.model.*;
import aqario.furnishings.client.render.EmptyRenderer;
import aqario.furnishings.client.render.ScarecrowEntityRenderer;
import aqario.furnishings.client.render.StatueEntityRenderer;
import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.entity.FurnishingsEntityType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.RenderLayer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class FurnishingsClient implements ClientModInitializer {
	private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
	private static final Dilation HAT_DILATION = new Dilation(0.5F);

    @Override
    public void onInitializeClient(ModContainer mod) {
//        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.PAPER_LANTERN);
//        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.ALLIUM_PAPER_LANTERN);
//        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BLUE_ORCHID_PAPER_LANTERN);
//        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.CRIMSON_ROOTS_PAPER_LANTERN);
//        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.DANDELION_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.RED_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), FurnishingsBlocks.FRAMED_GLASS);
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), FurnishingsBlocks.FRAMED_GLASS_PANE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.GLASS_PANEL);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BRAZIER);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.SOUL_BRAZIER);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.IRON_GRATE);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.IRON_SCAFFOLDING);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.SOUL_SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.LEVER_SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.LEVER_SOUL_SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.MOSS);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.WHITE_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.ORANGE_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.MAGENTA_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.LIGHT_BLUE_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.YELLOW_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.LIME_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.PINK_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.GRAY_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.LIGHT_GRAY_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.CYAN_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.PURPLE_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BLUE_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BROWN_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.GREEN_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.RED_CANDELABRA);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BLACK_CANDELABRA);

		EntityRendererRegistry.register(FurnishingsEntityType.SEAT, EmptyRenderer::new);

		EntityRendererRegistry.register(FurnishingsEntityType.SCARECROW, ScarecrowEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.SCARECROW, ScarecrowEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(FurnishingsEntityType.STATUE, StatueEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.STATUE, StatueEntityModel::getTexturedModelData);

		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.SCARECROW_INNER_ARMOR, () -> ScarecrowArmorEntityModel.getTexturedModelData(HAT_DILATION));
		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.SCARECROW_OUTER_ARMOR, () -> ScarecrowArmorEntityModel.getTexturedModelData(ARMOR_DILATION));

		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.STATUE_INNER_ARMOR, () -> StatueArmorEntityModel.getTexturedModelData(HAT_DILATION));
		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.STATUE_OUTER_ARMOR, () -> StatueArmorEntityModel.getTexturedModelData(ARMOR_DILATION));
    }
}
