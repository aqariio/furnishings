package aqario.furnishings.common.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum NoCeilingWallMountLocation implements StringIdentifiable {
	FLOOR("floor"),
	WALL("wall");

	private final String name;

	NoCeilingWallMountLocation(String name) {
		this.name = name;
	}

	@Override
	public String asString() {
		return this.name;
	}
}
