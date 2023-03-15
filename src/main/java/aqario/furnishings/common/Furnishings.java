package aqario.furnishings.common;

import aqario.furnishings.common.block.FurnishingsBlocks;
import aqario.furnishings.common.entity.FurnishingsEntityType;
import aqario.furnishings.common.item.FurnishingsItems;
import aqario.furnishings.common.sound.FurnishingsSoundEvents;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Furnishings implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("Furnishings");
	public static final String ID = "furnishings";

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Loading {}", mod.metadata().name());
		FurnishingsBlocks.init();
		FurnishingsEntityType.init();
		FurnishingsItems.init();
		FurnishingsSoundEvents.init();
	}
}