package aqario.furnishings.client.gui.screen;

import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.entity.FurnishingsEntityType;
import aqario.furnishings.common.entity.PoseableStandEntity;
import aqario.furnishings.common.entity.StatueEntity;
import aqario.furnishings.common.screen.PoseableStandScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class StatueScreen extends PoseableStandScreen {
	public static final Identifier TEXTURE = new Identifier(Furnishings.ID, "textures/gui/container/statue.png");

	public StatueScreen(PoseableStandScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory);
	}

	@Override
	public Identifier getTexture() {
		return TEXTURE;
	}
}
