package com.example.examplemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

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
public class ChakaraNumberHud {

    @SubscribeEvent
    public static void renderChakra(RenderGuiEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(minecraft.player);

        int chakraAtual = stats.getChakraAtual();
        int chakraMaximo = stats.getChakara();

        GuiGraphics guiGraphics = event.getGuiGraphics();

        String texto =
                chakraAtual + " / " + chakraMaximo;



        guiGraphics.drawString(
                minecraft.font,
                texto,
                (int) (40 / 0.7F),
                (int) (45 / 0.7F),
                0xFFFFFF
        );


    }
}