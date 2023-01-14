package aqario.furnishings;

import aqario.furnishings.block.FurnishingsBlocks;
import net.minecraft.client.render.RenderLayer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class FurnishingsClient implements ClientModInitializer {
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
    }
}
