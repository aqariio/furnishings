package aqario.furnishings.client.render;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class EmptyRenderer extends EntityRenderer<Entity> {
	public EmptyRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
	}

	@Override
	public Identifier getTexture(Entity entity) {
		return null;
	}
}
