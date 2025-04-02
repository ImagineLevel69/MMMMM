package com.mmmmm.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.NetworkChannel;

/**
 * Handles network packet registration and sending in NeoForge.
 */
public class NetworkHandler {
    private static final ResourceLocation CHANNEL_ID = ResourceLocation("mmmmm", "channel"); // Use the actual mod ID

    public static final NetworkChannel CHANNEL = new NetworkChannel(CHANNEL_ID);

    /**
     * Registers network messages.
     */
    public static void register() {
        CHANNEL.registerMessage(
                ExamplePacket.class,
                ExamplePacket::encode,
                ExamplePacket::decode,
                (packet, context) -> {
                    ServerPlayer sender = context.getSender();
                    if (sender != null) {
                        packet.handle(sender);
                    }
                }
        );
    }

    /**
     * Sends a message to the server.
     * @param message The message to send.
     */
    public static void sendToServer(String message) {
        CHANNEL.sendToServer(new ExamplePacket(message));
    }

    /**
     * Sends a message to a specific player.
     * @param player The target player.
     * @param message The message to send.
     */
    public static void sendToPlayer(ServerPlayer player, String message) {
        if (player != null) {
            CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new ExamplePacket(message));
        }
    }
}