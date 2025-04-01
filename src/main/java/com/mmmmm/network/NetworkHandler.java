package com.mmmmm.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.NetworkChannel;

public class NetworkHandler {
    private static final String PROTOCOL = "1";

    // Use NetworkChannel instead of SimpleChannel
    public static final NetworkChannel CHANNEL = NetworkChannel(
            new ResourceLocation("modid", "channel")
    );

    public static void register() {
        CHANNEL.registerMessage(
                ExamplePacket.class,
                ExamplePacket::encode,
                ExamplePacket::decode,
                (packet, context) -> packet.handle(context.getSender())
        );
    }

    public static void sendToServer(String message) {
        CHANNEL.sendToServer(new ExamplePacket(message));
    }

    public static void sendToPlayer(ServerPlayer player, String message) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new ExamplePacket(message));
    }
}
