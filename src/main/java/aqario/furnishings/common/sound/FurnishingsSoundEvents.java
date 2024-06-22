package aqario.furnishings.common.sound;

import aqario.furnishings.common.Furnishings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class FurnishingsSoundEvents {
    public static final SoundEvent BLOCK_LAMP_LIGHT = register("block.lamp.light");
    public static final SoundEvent BLOCK_LAMP_EXTINGUISH = register("block.lamp.extinguish");
    public static final SoundEvent BLOCK_CUSHION_SIT = register("block.cushion.sit");

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(Furnishings.ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void init() {
    }

    public static class FurnishingsBlockSoundGroup {

        public static final BlockSoundGroup BOOK = register("book");
        public static final BlockSoundGroup CUSHION = register("cushion");

        private static BlockSoundGroup register(String id) {
            return new BlockSoundGroup(
                1.0F,
                1.0F,
                FurnishingsSoundEvents.register("block." + id + ".break"),
                FurnishingsSoundEvents.register("block." + id + ".step"),
                FurnishingsSoundEvents.register("block." + id + ".place"),
                FurnishingsSoundEvents.register("block." + id + ".hit"),
                FurnishingsSoundEvents.register("block." + id + ".fall")
            );
        }
    }

}
