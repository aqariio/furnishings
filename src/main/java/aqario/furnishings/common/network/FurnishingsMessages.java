package aqario.furnishings.common.network;

import aqario.furnishings.common.network.packet.s2c.OpenPoseableStandScreenS2CPacket;
import aqario.furnishings.server.network.FurnishingsServerPlayNetworkHandler;

public class FurnishingsMessages {

    public static void init() {
        FurnishingsServerPlayNetworkHandler.registerMessage(OpenPoseableStandScreenS2CPacket.class, "open_stand");
    }
}
