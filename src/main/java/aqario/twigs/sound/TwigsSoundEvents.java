package aqario.twigs.sound;

import aqario.twigs.Twigs;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class TwigsSoundEvents {

    public static SoundEvent BLOCK_LAMP_LIGHT = new SoundEvent(new Identifier(Twigs.MODID, "block.lamp.light"));
    public static SoundEvent BLOCK_LAMP_EXTINGUISH = new SoundEvent(new Identifier(Twigs.MODID, "block.lamp.extinguish"));

}
