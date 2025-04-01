package com.mmmmm.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public record ExamplePacket(String message) {

    public static void encode(ExamplePacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.message);
    }

    public static ExamplePacket decode(FriendlyByteBuf buf) {
        return new ExamplePacket(buf.readUtf());
    }

    public void handle(ServerPlayer player) {
        player.sendSystemMessage("Received: " + message);
    }
}
