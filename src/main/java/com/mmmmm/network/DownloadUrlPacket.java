package com.mmmmm.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import com.mmmmm.client.DownloadConfirmScreen;
import net.neoforged.neoforge.network.registration.NetworkChannel;

/**
 * Packet sent from the server to the client containing the download URL for mods.
 */
public class DownloadUrlPacket {
    private final String url;

    // Constructor
    public DownloadUrlPacket(String url) {
        this.url = url;
    }

    // Encode the packet
    public static void encode(DownloadUrlPacket packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.url); // Write the URL as a string
    }

    // Decode the packet
    public static DownloadUrlPacket decode(FriendlyByteBuf buffer) {
        return new DownloadUrlPacket(buffer.readUtf()); // Read the URL from the buffer
    }

    // Handle the packet on the client side
    public static void handle(DownloadUrlPacket packet, NetworkChannel context) {
        // This is called when the packet is received by the client
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            // Display the custom screen with the URL
            minecraft.setScreen(new DownloadConfirmScreen(packet.url)); // Pass the URL to the screen
        });

        context.setPacketHandled(true); // Mark the packet as processed
    }

    // Getter for the URL
    public String getUrl() {
        return url;
    }
}
