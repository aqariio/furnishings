package aqario.twigs.sound;

import aqario.twigs.Twigs;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TwigsSoundEvents {

    public static final SoundEvent BLOCK_LAMP_LIGHT = new SoundEvent(new Identifier(Twigs.MODID, "block.lamp.light"));
    public static final SoundEvent BLOCK_LAMP_EXTINGUISH = new SoundEvent(new Identifier(Twigs.MODID, "block.lamp.extinguish"));
//            TwigsSoundEvents.register("block.lamp.extinguish");

//    private static SoundEvent register(String id) {
//        Identifier identifier = new Identifier(Twigs.MODID, id);
//        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
//    }
}
