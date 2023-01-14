package aqario.furnishings;

import aqario.furnishings.block.FurnishingsBlocks;
import aqario.furnishings.sound.FurnishingsSoundEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class Furnishings implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "furnishings";

	@Override
	public void onInitialize(ModContainer mod) {
		FurnishingsBlocks.init();
		FurnishingsSoundEvents.init();
	}
}
