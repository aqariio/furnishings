package aqario.furnishings.common.network;

import aqario.furnishings.common.network.listener.FurnishingsClientPlayPacketListener;
import net.minecraft.network.PacketByteBuf;

import java.io.*;

public interface FurnishingsPacket extends Serializable {
    static FurnishingsPacket decode(PacketByteBuf buf) {
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (FurnishingsPacket)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("SneakyThrows", e);
        }
    }

    default void encode(PacketByteBuf buf) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException("SneakyThrows", e);
        }

        buf.writeBytes(baos.toByteArray());
    }

    void write(PacketByteBuf buf);

    void apply(FurnishingsClientPlayPacketListener clientPlayPacketListener);
}
