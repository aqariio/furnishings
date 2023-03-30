package aqario.furnishings.common.network.packet.s2c;

import aqario.furnishings.common.network.FurnishingsPacket;
import aqario.furnishings.common.network.listener.FurnishingsClientPlayPacketListener;
import net.minecraft.block.Material;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;

public class OpenPoseableStandScreenS2CPacket implements FurnishingsPacket{
	private final int syncId;
	private final int standId;
	private final boolean isStatue;

	public OpenPoseableStandScreenS2CPacket(int syncId, int standId, boolean isStatue) {
		this.syncId = syncId;
		this.standId = standId;
		this.isStatue = isStatue;
	}

	public OpenPoseableStandScreenS2CPacket(PacketByteBuf buf) {
		this.syncId = buf.readUnsignedByte();
		this.standId = buf.readInt();
		this.isStatue = buf.readBoolean();
	}

	@Override
	public void write(PacketByteBuf buf) {
		buf.writeByte(this.syncId);
		buf.writeInt(this.standId);
		buf.writeBoolean(this.isStatue);
	}

	@Override
	public void apply(FurnishingsClientPlayPacketListener clientPlayPacketListener) {
		clientPlayPacketListener.onOpenPoseableStandScreen(this);
	}

	public int getSyncId() {
		return this.syncId;
	}

	public int getStandId() {
		return this.standId;
	}

	public boolean isStatue() {
		return this.isStatue;
	}
}
