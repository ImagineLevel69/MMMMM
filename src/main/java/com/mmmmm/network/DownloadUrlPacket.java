package com.mmmmm.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import java.util.function.Supplier;
import net.neoforged.neoforge.common.NetworkEvent;

import com.mmmmm.client.DownloadConfirmScreen;

/**
 * Packet sent from the server to the client containing the download URL for mods.
 */
public class DownloadUrlPacket {
    private final String url;

    public DownloadUrlPacket(String url) {
        this.url = url;
    }

    public static void encode(DownloadUrlPacket msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.url);
    }

    public static DownloadUrlPacket decode(FriendlyByteBuf buffer) {
        return new DownloadUrlPacket(buffer.readUtf());
        }

        public static void handle(DownloadUrlPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Handle the received URL on the client side
            Minecraft.getInstance().setScreen(new DownloadConfirmScreen(msg.url));
        });
        ctx.get().setPacketHandled(true);
    }
}