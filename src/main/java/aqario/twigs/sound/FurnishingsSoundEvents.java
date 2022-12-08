package aqario.twigs.sound;

import aqario.twigs.Furnishings;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FurnishingsSoundEvents {

    public static final SoundEvent BLOCK_LAMP_LIGHT = register("block.lamp.light");
    public static final SoundEvent BLOCK_LAMP_EXTINGUISH = register("block.lamp.extinguish");

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(Furnishings.MODID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    public static void init() {
    }
}
