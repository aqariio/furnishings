package aqario.twigs;

import aqario.twigs.block.TwigsBlocks;
import aqario.twigs.sound.TwigsSoundEvents;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Twigs implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "twigs";

	@Override
	public void onInitialize() {
		TwigsBlocks.init();
		TwigsSoundEvents.init();
	}
}