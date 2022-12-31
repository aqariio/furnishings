package aqario.furnishings;

import aqario.furnishings.block.FurnishingsBlocks;
import aqario.furnishings.sound.FurnishingsSoundEvents;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Furnishings implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "furnishings";

	@Override
	public void onInitialize() {
		FurnishingsBlocks.init();
		FurnishingsSoundEvents.init();
	}
}