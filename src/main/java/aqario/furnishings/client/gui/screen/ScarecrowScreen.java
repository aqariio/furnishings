package aqario.furnishings.client.gui.screen;

import aqario.furnishings.common.Furnishings;
import aqario.furnishings.common.entity.ScarecrowEntity;
import aqario.furnishings.common.screen.PoseableStandScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;

public class ScarecrowScreen extends PoseableStandScreen {
    public static final Identifier TEXTURE = new Identifier(Furnishings.ID, "textures/gui/container/scarecrow.png");

    public ScarecrowScreen(PoseableStandScreenHandler handler, PlayerInventory inventory, ScarecrowEntity scarecrow) {
        super(handler, inventory, scarecrow);
    }

    @Override
    public Identifier getTexture() {
        return TEXTURE;
    }
}
