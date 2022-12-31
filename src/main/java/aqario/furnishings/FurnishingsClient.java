package aqario.furnishings;

import aqario.furnishings.block.FurnishingsBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class FurnishingsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.PAPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.ALLIUM_PAPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.BLUE_ORCHID_PAPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.CRIMSON_ROOTS_PAPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.DANDELION_PAPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.RED_PAPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.FRAMED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.FRAMED_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.BRAZIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.SOUL_BRAZIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FurnishingsBlocks.IRON_SCAFFOLDING, RenderLayer.getCutout());
    }
}
