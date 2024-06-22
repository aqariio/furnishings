package aqario.furnishings.common.block.entity;

import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.block.FurnishingsBlocks;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class FurnishingsBlockEntityType {
    public static final BlockEntityType<FluidContainerBlockEntity> FLUID_CONTAINER = register(
        "fluid_container",
        QuiltBlockEntityTypeBuilder.create(
            FluidContainerBlockEntity::new,
            FurnishingsBlocks.MUG,
            FurnishingsBlocks.CHALICE
        )
    );

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, QuiltBlockEntityTypeBuilder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Furnishings.ID, id), builder.build(type));
    }

    public static void init() {
    }
}
