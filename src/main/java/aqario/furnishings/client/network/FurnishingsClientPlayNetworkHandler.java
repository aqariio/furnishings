package aqario.furnishings.client.network;

import aqario.furnishings.client.gui.screen.PoseableStandScreen;
import aqario.furnishings.client.gui.screen.ScarecrowScreen;
import aqario.furnishings.client.gui.screen.StatueScreen;
import aqario.furnishings.common.entity.PoseableStandEntity;
import aqario.furnishings.common.entity.ScarecrowEntity;
import aqario.furnishings.common.entity.StatueEntity;
import aqario.furnishings.common.network.listener.FurnishingsClientPlayPacketListener;
import aqario.furnishings.common.network.packet.s2c.OpenPoseableStandScreenS2CPacket;
import aqario.furnishings.common.screen.PoseableStandScreenHandler;
import net.minecraft.client.MinecraftClient;

public class FurnishingsClientPlayNetworkHandler implements FurnishingsClientPlayPacketListener {
    private final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onOpenPoseableStandScreen(OpenPoseableStandScreenS2CPacket packet) {
        if (client.world != null && client.player != null) {
            PoseableStandEntity poseableStand = (PoseableStandEntity) client.world.getEntityById(packet.getStandId());
            if (poseableStand != null) {
                PoseableStandScreenHandler screenHandler = (PoseableStandScreenHandler) poseableStand.createMenu(packet.getSyncId(), client.player.getInventory(), client.player);
                PoseableStandScreen screen = switch (poseableStand.getStandType()) {
                    case STATUE ->
                        new StatueScreen(screenHandler, client.player.getInventory(), (StatueEntity) poseableStand);
                    case SCARECROW ->
                        new ScarecrowScreen(screenHandler, client.player.getInventory(), (ScarecrowEntity) poseableStand);
                };
                client.player.currentScreenHandler = screen.getScreenHandler();
                client.setScreen(screen);
            }
        }
    }
}
