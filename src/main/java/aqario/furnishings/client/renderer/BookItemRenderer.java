package aqario.furnishings.client.renderer;

import aqario.furnishings.client.model.item.BookItemModel;
import aqario.furnishings.common.Furnishings;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.qsl.resource.loader.api.reloader.IdentifiableResourceReloader;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BookItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, IdentifiableResourceReloader {
    private final Identifier rendererId;
    final Identifier bookId;
    private final Identifier texture;
    final EntityModelLayer modelLayer;
    ItemRenderer itemRenderer;
    Model itemModel;
    BakedModel inventoryModel;

    public BookItemRenderer(Identifier bookId, EntityModelLayer modelLayer) {
        this.rendererId = new Identifier(Furnishings.ID, bookId.getPath() + "_renderer");
        this.bookId = bookId;
        this.texture = new Identifier(Furnishings.ID, "textures/models/item/book.png");
        this.modelLayer = modelLayer;
    }

	@Override
	public @NotNull Identifier getQuiltId() {
		return this.rendererId;
	}

	@Override
	public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
		return synchronizer.whenPrepared(Unit.INSTANCE).thenRunAsync(() -> {
			applyProfiler.startTick();
			applyProfiler.push("listener");
			MinecraftClient client = MinecraftClient.getInstance();
			this.itemRenderer = client.getItemRenderer();
			this.inventoryModel = client.getBakedModelManager().getModel(new ModelIdentifier(bookId + "_gui", "inventory"));
			this.itemModel = new BookItemModel(client.getEntityModelLoader().getModelPart(this.modelLayer));
			applyProfiler.pop();
			applyProfiler.endTick();
		}, applyExecutor);
	}

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        assert this.itemModel != null;
//        if (mode == ModelTransformation.Mode.GUI || mode == ModelTransformation.Mode.GROUND || mode == ModelTransformation.Mode.FIXED) {
//            matrices.pop();
//            matrices.push();
//            itemRenderer.renderItem(stack, mode, false, matrices, vertexConsumers, light, overlay, this.inventoryModel);
//        } else {
//            matrices.push();
//            matrices.scale(1.0F, -1.0F, -1.0F);
//            VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.itemModel.getLayer(this.texture), false, false);
//            this.itemModel.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
//            matrices.pop();
//        }
		if (mode == ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND || mode == ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND) {
			matrices.push();
            matrices.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.itemModel.getLayer(this.texture), false, false);
            this.itemModel.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
		} else {
			matrices.pop();
            matrices.push();
            itemRenderer.renderItem(stack, mode, false, matrices, vertexConsumers, light, overlay, this.inventoryModel);
		}
    }
}
