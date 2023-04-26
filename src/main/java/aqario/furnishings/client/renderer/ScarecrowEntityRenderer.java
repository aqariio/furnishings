package aqario.furnishings.client.renderer;

import aqario.furnishings.client.model.FurnishingsEntityModelLayers;
import aqario.furnishings.client.model.ScarecrowArmorEntityModel;
import aqario.furnishings.client.model.ScarecrowEntityModel;
import aqario.furnishings.client.renderer.feature.StuckArrowsFeatureRenderer;
import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.entity.ScarecrowEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ScarecrowEntityRenderer extends LivingEntityRenderer<ScarecrowEntity, ScarecrowEntityModel> {
	public static final Identifier TEXTURE = new Identifier(Furnishings.ID, "textures/entity/scarecrow/scarecrow.png");

	public ScarecrowEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new ScarecrowEntityModel(ctx.getPart(FurnishingsEntityModelLayers.SCARECROW)), 0.0F);
		this.addFeature(
				new ArmorFeatureRenderer<>(
						this,
						new ScarecrowArmorEntityModel(ctx.getPart(FurnishingsEntityModelLayers.SCARECROW_INNER_ARMOR)),
						new ScarecrowArmorEntityModel(ctx.getPart(FurnishingsEntityModelLayers.SCARECROW_OUTER_ARMOR))
				)
		);
		this.addFeature(new StuckArrowsFeatureRenderer<>(ctx, this));
		this.addFeature(new HeldItemFeatureRenderer<>(this, ctx.getHeldItemRenderer()));
		this.addFeature(new ElytraFeatureRenderer<>(this, ctx.getModelLoader()));
		this.addFeature(new HeadFeatureRenderer<>(this, ctx.getModelLoader(), ctx.getHeldItemRenderer()));
	}

	@Override
	public void render(ScarecrowEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
		matrices.push();
		matrices.pop();
	}

	@Override
	protected void scale(ScarecrowEntity entity, MatrixStack matrices, float amount) {
		matrices.scale(0.9375F, 0.9375F, 0.9375F);
	}

	@Override
	protected boolean hasLabel(ScarecrowEntity livingEntity) {
		return false;
	}

	@Override
	public Identifier getTexture(ScarecrowEntity entity) {
		return TEXTURE;
	}
}
