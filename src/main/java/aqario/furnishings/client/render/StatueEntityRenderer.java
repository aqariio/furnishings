package aqario.furnishings.client.render;

import aqario.furnishings.client.model.FurnishingsEntityModelLayers;
import aqario.furnishings.client.model.StatueArmorEntityModel;
import aqario.furnishings.client.model.StatueEntityModel;
import aqario.furnishings.client.render.feature.StuckArrowsFeatureRenderer;
import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.entity.StatueEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class StatueEntityRenderer extends LivingEntityRenderer<StatueEntity, StatueEntityModel> {
    public static final Identifier TEXTURE = new Identifier(Furnishings.ID, "textures/entity/statue/statue.png");

    public StatueEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new StatueEntityModel(ctx.getPart(FurnishingsEntityModelLayers.STATUE)), 0.0F);
        this.addFeature(
            new ArmorFeatureRenderer<>(
                this,
                new StatueArmorEntityModel(ctx.getPart(FurnishingsEntityModelLayers.STATUE_INNER_ARMOR)),
                new StatueArmorEntityModel(ctx.getPart(FurnishingsEntityModelLayers.STATUE_OUTER_ARMOR)),
                ctx.getModelManager()
            )
        );
        this.addFeature(new StuckArrowsFeatureRenderer<>(ctx, this));
        this.addFeature(new HeldItemFeatureRenderer<>(this, ctx.getHeldItemRenderer()));
        this.addFeature(new ElytraFeatureRenderer<>(this, ctx.getModelLoader()));
        this.addFeature(new HeadFeatureRenderer<>(this, ctx.getModelLoader(), ctx.getHeldItemRenderer()));
    }

    @Override
    public void render(StatueEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.push();
        matrices.pop();
    }

    @Override
    protected void scale(StatueEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(0.9375F, 0.9375F, 0.9375F);
    }

    @Override
    protected boolean hasLabel(StatueEntity livingEntity) {
        return false;
    }

    @Override
    public Identifier getTexture(StatueEntity entity) {
        return TEXTURE;
    }
}
