package com.mmmmm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

/**
 * Screen displayed to confirm the download URL for mods.
 */
public class DownloadConfirmScreen extends Screen {
    private final String url;

    public DownloadConfirmScreen(String url) {
        super(Component.literal("Download Confirmation"));
        this.url = url;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(Button.builder(Component.literal("Download Mods"), button -> {
            // Start the download process
            ClientEventHandlers.startModDownload(url);
            Minecraft.getInstance().setScreen(null); // Close this screen
        }).bounds(this.width / 2 - 100, this.height / 2 + 20, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.drawCenteredString(this.font, "Download URL: " + url, this.width / 2, this.height / 2 - 10, 0xFFFFFF);
    }
}