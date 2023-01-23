package aqario.furnishings;

import aqario.furnishings.block.FurnishingsBlocks;
import aqario.furnishings.sound.FurnishingsSoundEvents;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Furnishings implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("Furnishings");
	public static final String MODID = "furnishings";

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Loading {}", mod.metadata().name());
		FurnishingsBlocks.init();
		FurnishingsSoundEvents.init();
	}
}
