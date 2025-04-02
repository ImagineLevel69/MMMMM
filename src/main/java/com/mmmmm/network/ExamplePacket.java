package com.mmmmm.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Represents a simple network packet containing a message.
 */
public record ExamplePacket(String message) {

    /**
     * Encodes the packet into a buffer.
     * @param packet The ExamplePacket instance.
     * @param buf The buffer to write to.
     */
    public static void encode(ExamplePacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.message != null ? packet.message : "");
    }

    /**
     * Decodes the packet from a buffer.
     * @param buf The buffer containing the packet data.
     * @return A new ExamplePacket instance.
     */
    public static ExamplePacket decode(FriendlyByteBuf buf) {
        return new ExamplePacket(buf.readUtf());
    }

    /**
     * Handles the received packet on the server side.
     * @param player The player who received the packet.
     */
    public void handle(ServerPlayer player) {
        if (player != null) {
            player.sendSystemMessage(Component.literal("Received: " + message));
        }
    }
}
