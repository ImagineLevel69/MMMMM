package com.mmmmm.network;

import com.mmmmm.MMMMM;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NetworkChannel;
import net.neoforged.neoforge.common.PlayNetworkDirection;

/**
 * Handles the networking for the mod, including packet registration and communication.
 */
public class ModNetworkHandler {

    // Define the network channel for communication
    public static final NetworkChannel CHANNEL = NetworkChannel.Builder.named(new ResourceLocation(MMMMM.MODID, "network_channel"))
            .networkProtocolVersion(1) // Set the protocol version
            .clientAcceptedVersions(version -> version == 1) // Accept client version 1
            .serverAcceptedVersions(version -> version == 1) // Accept server version 1
            .build();

    /**
     * Registers the packets used for communication between client and server.
     */
    public static void registerPackets() {
        // Register the RequestDownloadUrlPacket for server-bound communication
        CHANNEL.registerPacket(
                RequestDownloadUrlPacket.class,
                RequestDownloadUrlPacket::encode,
                RequestDownloadUrlPacket::decode,
                RequestDownloadUrlPacket::handle,
                PlayNetworkDirection.SERVERBOUND
        );

        // Register the DownloadUrlPacket for client-bound communication
        CHANNEL.registerPacket(
                DownloadUrlPacket.class,
                DownloadUrlPacket::encode,
                DownloadUrlPacket::decode,
                DownloadUrlPacket::handle,
                PlayNetworkDirection.CLIENTBOUND
        );
    }

    /**
     * Sends a DownloadUrlPacket to the specified player.
     *
     * @param packet The packet containing the download URL.
     * @param player The player to send the packet to.
     */
    public static void sendToClient(DownloadUrlPacket packet, ServerPlayer player) {
        CHANNEL.sendToPlayer(player, packet);
    }

    /**
     * Sends a RequestDownloadUrlPacket to the server.
     *
     * @param packet The packet requesting the download URL.
     */
    public static void sendToServer(RequestDownloadUrlPacket packet) {
        CHANNEL.sendToServer(packet);
    }
}