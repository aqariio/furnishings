package aqario.furnishings.common.data.client.model;

import net.minecraft.block.Block;
import net.minecraft.data.client.model.Texture;
import net.minecraft.data.client.model.TextureKey;
import net.minecraft.data.client.model.TexturedModel;

public class FurnishingsTexturedModel {
    public static final TexturedModel.Factory TEMPLATE_BRAZIER = TexturedModel.makeFactory(FurnishingsTexturedModel::brazier, FurnishingsModels.TEMPLATE_BRAZIER);
    public static final TexturedModel.Factory TEMPLATE_BRAZIER_HANGING = TexturedModel.makeFactory(FurnishingsTexturedModel::brazier, FurnishingsModels.TEMPLATE_BRAZIER_HANGING);
    public static final TexturedModel.Factory TEMPLATE_BRAZIER_OFF = TexturedModel.makeFactory(FurnishingsTexturedModel::brazierOff, FurnishingsModels.TEMPLATE_BRAZIER_OFF);
    public static final TexturedModel.Factory TEMPLATE_BRAZIER_OFF_HANGING = TexturedModel.makeFactory(FurnishingsTexturedModel::brazierOff, FurnishingsModels.TEMPLATE_BRAZIER_OFF_HANGING);
    public static final TexturedModel.Factory TEMPLATE_CUSHION = TexturedModel.makeFactory(Texture::sideAndTop, FurnishingsModels.TEMPLATE_CUSHION);

    public static Texture brazier(Block block) {
        return new Texture().put(TextureKey.FIRE, Texture.getSubId(block, "_fire")).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom_lit"));
    }

    public static Texture brazierOff(Block block) {
        return new Texture().put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }
}
