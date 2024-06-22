package aqario.furnishings.client.model;

import aqario.furnishings.common.Furnishings;
import com.google.common.collect.Sets;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Set;

public class FurnishingsEntityModelLayers {
    private static final String MAIN = "main";
    private static final Set<EntityModelLayer> LAYERS = Sets.newHashSet();
    public static final EntityModelLayer SCARECROW = registerMain("scarecrow");
    public static final EntityModelLayer STATUE = registerMain("statue");
    public static final EntityModelLayer SCARECROW_INNER_ARMOR = createInnerArmor("scarecrow");
    public static final EntityModelLayer SCARECROW_OUTER_ARMOR = createOuterArmor("scarecrow");
    public static final EntityModelLayer STATUE_INNER_ARMOR = createInnerArmor("statue");
    public static final EntityModelLayer STATUE_OUTER_ARMOR = createOuterArmor("statue");

    private static EntityModelLayer registerMain(String id) {
        return register(id, MAIN);
    }

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        }
        else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(new Identifier(Furnishings.ID, id), layer);
    }

    private static EntityModelLayer createInnerArmor(String id) {
        return register(id, "inner_armor");
    }

    private static EntityModelLayer createOuterArmor(String id) {
        return register(id, "outer_armor");
    }
}
