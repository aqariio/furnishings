package aqario.furnishings.common.network.packet.s2c;

import aqario.furnishings.common.network.FurnishingsPacket;
import aqario.furnishings.common.network.listener.FurnishingsClientPlayPacketListener;
import net.minecraft.network.PacketByteBuf;

public class OpenPoseableStandScreenS2CPacket implements FurnishingsPacket {
    private final int syncId;
    private final int standId;

    public OpenPoseableStandScreenS2CPacket(int syncId, int standId) {
        this.syncId = syncId;
        this.standId = standId;
    }

    public OpenPoseableStandScreenS2CPacket(PacketByteBuf buf) {
        this.syncId = buf.readUnsignedByte();
        this.standId = buf.readInt();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeByte(this.syncId);
        buf.writeInt(this.standId);
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
}
