package aqario.furnishings.client;

import aqario.furnishings.client.model.*;
import aqario.furnishings.client.model.item.BookItemModel;
import aqario.furnishings.client.renderer.BookItemRenderer;
import aqario.furnishings.client.renderer.EmptyRenderer;
import aqario.furnishings.client.renderer.ScarecrowEntityRenderer;
import aqario.furnishings.client.renderer.StatueEntityRenderer;
import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.entity.FurnishingsEntityType;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Items;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;

public class FurnishingsClient implements ClientModInitializer {
	private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
	private static final Dilation HAT_DILATION = new Dilation(0.5F);
	private static final EntityModelLayer WRITABLE_BOOK = new EntityModelLayer(new Identifier(Furnishings.ID, "writable"), "main");
	private static final EntityModelLayer WRITTEN_BOOK = new EntityModelLayer(new Identifier(Furnishings.ID, "written"), "main");

    @Override
    public void onInitializeClient(ModContainer mod) {
		Identifier writableBookId = Registry.ITEM.getId(Items.WRITABLE_BOOK);

		EntityModelLayerRegistry.registerModelLayer(WRITABLE_BOOK, BookItemModel::getTexturedModelData);
		BookItemRenderer writableBookItemRenderer = new BookItemRenderer(writableBookId, WRITABLE_BOOK);
		ResourceLoader.get(ResourceType.CLIENT_RESOURCES).registerReloader(writableBookItemRenderer);
		BuiltinItemRendererRegistry.INSTANCE.register(Items.WRITABLE_BOOK, writableBookItemRenderer);
		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(new ModelIdentifier(writableBookId + "_gui", "inventory")));


		Identifier writtenBookId = Registry.ITEM.getId(Items.WRITTEN_BOOK);

		EntityModelLayerRegistry.registerModelLayer(WRITTEN_BOOK, BookItemModel::getTexturedModelData);
		BookItemRenderer writtenBookItemRenderer = new BookItemRenderer(writtenBookId, WRITTEN_BOOK);
		ResourceLoader.get(ResourceType.CLIENT_RESOURCES).registerReloader(writtenBookItemRenderer);
		BuiltinItemRendererRegistry.INSTANCE.register(Items.WRITTEN_BOOK, writtenBookItemRenderer);
		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(new ModelIdentifier(writtenBookId + "_gui", "inventory")));

        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.ALLIUM_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.BLUE_ORCHID_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.CRIMSON_ROOTS_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.DANDELION_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), FurnishingsBlocks.RED_PAPER_LANTERN);
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), FurnishingsBlocks.FRAMED_GLASS);
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), FurnishingsBlocks.FRAMED_GLASS_PANE);
		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), FurnishingsBlocks.GLASS_PANEL);
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
