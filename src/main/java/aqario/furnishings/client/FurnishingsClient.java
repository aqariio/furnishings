package aqario.furnishings.client;

import aqario.furnishings.client.model.*;
import aqario.furnishings.client.renderer.ScarecrowEntityRenderer;
import aqario.furnishings.client.renderer.StatueEntityRenderer;
import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.entity.FurnishingsEntityType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class FurnishingsClient implements ClientModInitializer {
	private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
	private static final Dilation HAT_DILATION = new Dilation(0.5F);

	public static final EntityModelLayer SCARECROW = new EntityModelLayer(new Identifier(Furnishings.ID, "scarecrow"), "main");
	public static final EntityModelLayer STATUE = new EntityModelLayer(new Identifier(Furnishings.ID, "statue"), "main");

    @Override
    public void onInitializeClient(ModContainer mod) {
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.ALLIUM_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BLUE_ORCHID_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.CRIMSON_ROOTS_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.DANDELION_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.RED_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), FurnishingsBlocks.FRAMED_GLASS);
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), FurnishingsBlocks.FRAMED_GLASS_PANE);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BRAZIER);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.SOUL_BRAZIER);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.IRON_SCAFFOLDING);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.SOUL_SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.LEVER_SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.LEVER_SOUL_SCONCE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.MOSS);

		EntityRendererRegistry.register(FurnishingsEntityType.SCARECROW, ScarecrowEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(SCARECROW, ScarecrowEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(FurnishingsEntityType.STATUE, StatueEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(STATUE, StatueEntityModel::getTexturedModelData);

//		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.SCARECROW_INNER_ARMOR, () -> ScarecrowArmorEntityModel.getTexturedModelData(HAT_DILATION));
//		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.SCARECROW_OUTER_ARMOR, () -> ScarecrowArmorEntityModel.getTexturedModelData(ARMOR_DILATION));

		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.STATUE_INNER_ARMOR, () -> StatueArmorEntityModel.getTexturedModelData(HAT_DILATION));
		EntityModelLayerRegistry.registerModelLayer(FurnishingsEntityModelLayers.STATUE_OUTER_ARMOR, () -> StatueArmorEntityModel.getTexturedModelData(ARMOR_DILATION));
    }
}
