package aqario.furnishings.client;

import aqario.furnishings.client.model.StatueEntityModel;
import aqario.furnishings.client.renderer.StatueEntityRenderer;
import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.entity.FurnishingsEntityType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class FurnishingsClient implements ClientModInitializer {
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

		EntityRendererRegistry.register(FurnishingsEntityType.STATUE, StatueEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(STATUE, StatueEntityModel::getTexturedModelData);
    }
}
