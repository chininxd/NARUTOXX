package com.example.examplemod.client;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class RyoHud {

    private static final ResourceLocation RYO_TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/money/ryo.png"
            );

    @SubscribeEvent
    public static void renderRyo(RenderGuiEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(minecraft.player);

        int ryo = stats.getRyo();

        GuiGraphics guiGraphics =
                event.getGuiGraphics();

        String texto = String.valueOf(ryo);

        int tamanho = 16;

        int espaco = 4;

        int larguraTexto =
                minecraft.font.width(texto);

        int margemDireita = 10;
        int margemTopo = 10;

        int xImagem =
                guiGraphics.guiWidth()
                        - margemDireita
                        - tamanho;

        int xTexto =
                xImagem
                        - espaco
                        - larguraTexto;

        int y = margemTopo;

        // Número do Ryo
        guiGraphics.drawString(
                minecraft.font,
                texto,
                xTexto,
                y + 4,
                0xFFFFFF
        );

        // Ícone do Ryo
        guiGraphics.blit(
                RYO_TEXTURE,
                xImagem,
                y,
                0,
                0,
                tamanho,
                tamanho,
                tamanho,
                tamanho
        );
    }
}