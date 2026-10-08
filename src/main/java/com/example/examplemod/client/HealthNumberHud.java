package com.example.examplemod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class HealthNumberHud {

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();

        int vida = (int) Math.ceil(
                minecraft.player.getHealth() / 2.0F
        );

        int x = guiGraphics.guiWidth() / 2 - 91;
        int y = guiGraphics.guiHeight() - 39;

        guiGraphics.drawString(
                minecraft.font,
                String.valueOf(vida),
                x,
                y,
                0xFFFFFF
        );
    }
}
