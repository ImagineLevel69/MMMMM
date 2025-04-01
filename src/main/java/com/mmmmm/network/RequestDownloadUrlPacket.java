package com.mmmmm.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import java.util.function.Supplier;

/**
 * Packet sent from the client to request the download URL for mods.
 */
public class RequestDownloadUrlPacket {
    public RequestDownloadUrlPacket() {}

    public static void encode(RequestDownloadUrlPacket msg, FriendlyByteBuf buffer) {
        // No data to encode for this packet
    }

    public static RequestDownloadUrlPacket decode(FriendlyByteBuf buffer) {
        return new RequestDownloadUrlPacket();
    }

    public static void handle(RequestDownloadUrlPacket msg, Supplier<NetworkChannel.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                String downloadUrl = "https://example.com/mods.zip"; // Replace with real URL
                ModNetworkHandler.sendToClient(new DownloadUrlPacket(downloadUrl), player);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}