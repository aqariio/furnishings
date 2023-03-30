package aqario.furnishings.common.network.listener;

import aqario.furnishings.common.network.packet.s2c.OpenPoseableStandScreenS2CPacket;

public interface FurnishingsClientPlayPacketListener {

	void onOpenPoseableStandScreen(OpenPoseableStandScreenS2CPacket packet);

//	void handleAttackMannequin(ClientboundAttackMannequin packet, PollinatedPacketContext ctx);
}
