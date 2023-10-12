package aqario.furnishings.client.model.item;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;

public class BookItemModel extends Model {
	private final ModelPart book;
	public BookItemModel(ModelPart root) {
		super(RenderLayer::getEntitySolid);
		this.book = root.getChild("book");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData book = modelPartData.addChild("book", ModelPartBuilder.create().uv(12, 0).cuboid(-1.0F, -10.0F, 0.0F, 2.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		book.addChild("flip_right", ModelPartBuilder.create().uv(26, 0).cuboid(0.0F, -4.0F, -0.025F, 5.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, -1.075F, 0.0F, 0.48F, 0.0F));

		book.addChild("flip_left", ModelPartBuilder.create().uv(16, 0).cuboid(-5.0F, -4.0F, -0.025F, 5.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, -1.075F, 0.0F, -0.48F, 0.0F));

		book.addChild("page_right", ModelPartBuilder.create().uv(12, 10).cuboid(0.0F, -4.0F, -0.025F, 5.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, -1.075F, 0.0F, 0.3927F, 0.0F));

		book.addChild("page_left", ModelPartBuilder.create().uv(0, 10).cuboid(-5.0F, -4.0F, -0.025F, 5.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, -1.075F, 0.0F, -0.3927F, 0.0F));

		book.addChild("cover_right", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(0.0F, -5.0F, 0.0F, 6.0F, 10.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, -5.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		book.addChild("cover_left", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -5.0F, 0.0F, 6.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -5.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		book.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
