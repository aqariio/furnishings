package aqario.furnishings.common.data.client.model;

import aqario.furnishings.common.Furnishings;
import net.minecraft.data.client.model.Model;
import net.minecraft.data.client.model.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class FurnishingsModels {
	public static final Model TEMPLATE_CANDELABRA_FLOOR_1 = block("template_candelabra_floor_1", TextureKey.CANDLE);
	public static final Model TEMPLATE_CANDELABRA_FLOOR_2 = block("template_candelabra_floor_2", TextureKey.CANDLE);
	public static final Model TEMPLATE_CANDELABRA_FLOOR_3 = block("template_candelabra_floor_3", TextureKey.CANDLE);
	public static final Model TEMPLATE_CANDELABRA_FLOOR_4 = block("template_candelabra_floor_4", TextureKey.CANDLE);
	public static final Model TEMPLATE_CANDELABRA_WALL_1 = block("template_candelabra_wall_1", TextureKey.CANDLE);
	public static final Model TEMPLATE_CANDELABRA_WALL_2 = block("template_candelabra_wall_2", TextureKey.CANDLE);
	public static final Model TEMPLATE_CANDELABRA_WALL_3 = block("template_candelabra_wall_3", TextureKey.CANDLE);
	public static final Model TEMPLATE_CANDELABRA_WALL_4 = block("template_candelabra_wall_4", TextureKey.CANDLE);

	private static Model block(String parent, TextureKey... requiredTextures) {
		return new Model(Optional.of(new Identifier(Furnishings.ID, "block/" + parent)), Optional.empty(), requiredTextures);
	}
}
