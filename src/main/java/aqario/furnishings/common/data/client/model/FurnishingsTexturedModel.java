package aqario.furnishings.common.data.client.model;

import net.minecraft.data.client.model.Texture;
import net.minecraft.data.client.model.TexturedModel;

public class FurnishingsTexturedModel {
    public static final TexturedModel.Factory TEMPLATE_CUSHION = TexturedModel.makeFactory(Texture::sideAndTop, FurnishingsModels.TEMPLATE_CUSHION);
}
