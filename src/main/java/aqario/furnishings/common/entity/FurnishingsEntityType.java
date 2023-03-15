package aqario.furnishings.common.entity;

import aqario.furnishings.common.Furnishings;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class FurnishingsEntityType {
	public static final EntityType<StatueEntity> STATUE = Registry.register(
			Registry.ENTITY_TYPE, new Identifier(Furnishings.ID, "statue"),
			QuiltEntityTypeBuilder.createLiving()
					.entityFactory(StatueEntity::new)
					.spawnGroup(SpawnGroup.MISC)
					.defaultAttributes(StatueEntity.createLivingAttributes().add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0F))
					.setDimensions(EntityDimensions.fixed(0.6F, 2.0F))
					.build());

	public static void init() {
	}
}
