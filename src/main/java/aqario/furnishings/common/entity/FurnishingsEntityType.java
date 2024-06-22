package aqario.furnishings.common.entity;

import aqario.furnishings.common.Furnishings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class FurnishingsEntityType {
    public static final EntityType<SeatEntity> SEAT = register("seat",
        QuiltEntityTypeBuilder.create()
            .entityFactory(SeatEntity::new)
            .spawnGroup(SpawnGroup.MISC)
            .setDimensions(EntityDimensions.fixed(0.0F, 0.0F))
    );

    public static final EntityType<ScarecrowEntity> SCARECROW = register("scarecrow",
        QuiltEntityTypeBuilder.createLiving()
            .entityFactory(ScarecrowEntity::new)
            .spawnGroup(SpawnGroup.MISC)
            .defaultAttributes(StatueEntity.createAttributes().add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0F))
            .setDimensions(EntityDimensions.fixed(0.6F, 2.0F))
    );

    public static final EntityType<StatueEntity> STATUE = register("statue",
        QuiltEntityTypeBuilder.createLiving()
            .entityFactory(StatueEntity::new)
            .spawnGroup(SpawnGroup.MISC)
            .defaultAttributes(StatueEntity.createAttributes().add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0F))
            .setDimensions(EntityDimensions.fixed(0.6F, 2.0F))
    );

    private static <T extends Entity> EntityType<T> register(String id, QuiltEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(Furnishings.ID, id), builder.build());
    }

    public static void init() {
    }
}
