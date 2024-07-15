package aqario.furnishings.client.render;

import aqario.furnishings.common.block.entity.FluidContainerBlockEntity;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.resource.Material;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class FluidContainerBlockEntityRenderer implements BlockEntityRenderer<FluidContainerBlockEntity> {
    private static final Material WATER = new Material(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, new Identifier("block/water_still"));

    public FluidContainerBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(FluidContainerBlockEntity container, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (container.isEmpty()) {
            return;
        }

        Potion potion = container.getPotion();

        int color = PotionUtil.getColor(potion);
        int red = color >> 16 & 255;
        int green = color >> 8 & 255;
        int blue = color & 255;
        int alpha = 255;
        Sprite water = WATER.getSprite();

        matrices.push();
        matrices.translate(0, 0.5, 0);

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getTranslucentMovingBlock());
        Matrix4f matrix = matrices.peek().getModel();

        float scale = 0.375f;
        float maxU = (water.getMaxU() - water.getMinU()) * scale;
        float minU = (water.getMaxU() - water.getMinU()) * (1 - scale);
        float maxV = (water.getMaxV() - water.getMinV()) * scale;
        float minV = (water.getMaxV() - water.getMinV()) * (1 - scale);

        consumer.vertex(matrix, scale, 0, 1 - scale).color(red, green, blue, alpha).uv(water.getMinU() + minU, water.getMinV() + maxV).light(light).overlay(overlay).normal(1, 1, 1).next();
        consumer.vertex(matrix, 1 - scale, 0, 1 - scale).color(red, green, blue, alpha).uv(water.getMinU() + maxU, water.getMinV() + maxV).light(light).overlay(overlay).normal(1, 1, 1).next();
        consumer.vertex(matrix, 1 - scale, 0, scale).color(red, green, blue, alpha).uv(water.getMinU() + maxU, water.getMinV() + minV).light(light).overlay(overlay).normal(1, 1, 1).next();
        consumer.vertex(matrix, scale, 0, scale).color(red, green, blue, alpha).uv(water.getMinU() + minU, water.getMinV() + minV).light(light).overlay(overlay).normal(1, 1, 1).next();

        matrices.pop();
    }
}
