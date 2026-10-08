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
public class StatsBarHud {

    private static final ResourceLocation HEALTH_BAR =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/health_bar.png"
            );

    private static final ResourceLocation CHAKARA_BAR =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/bar_chakara.png"
            );

    private static final ResourceLocation MOLDURA_BAR =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/muldora_bar.png"
            );

    @SubscribeEvent
    public static void render(RenderGuiEvent.Post event) {

        Minecraft minecraft =
                Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        GuiGraphics gui =
                event.getGuiGraphics();

        // =========================================
        // TAMANHO DA BARRA DE VIDA
        // =========================================

        int larguraVidaMaxima = 123;
        int alturaVida = 10;

        // =========================================
        // TAMANHO DA BARRA DE CHAKARA
        // =========================================

        int larguraChakaraMaxima = 154;
        int alturaChakara = 64;

        // =========================================
        // TAMANHO DA MOLDURA
        // =========================================

        int molduraLargura = 160;
        int molduraAltura = 60;

        // =========================================
        // POSIÇÃO DA MOLDURA
        // MUDE X E Y PARA MOVER
        // =========================================

        int molduraX = -15;
        int molduraY = 10;

        // =========================================
        // POSIÇÃO DA VIDA
        // MUDE X E Y PARA MOVER
        // =========================================

        int vidaX = 19;
        int vidaY = 27;

        // =========================================
        // POSIÇÃO DO CHAKARA
        // MUDE X E Y PARA MOVER
        // =========================================

        int chakaraX = -14;
        int chakaraY = 8;

        // =========================================
        // MOLDURA
        // =========================================

        gui.blit(
                MOLDURA_BAR,
                molduraX,
                molduraY,
                0,
                0,
                molduraLargura,
                molduraAltura,
                molduraLargura,
                molduraAltura
        );

        // =========================================
        // VIDA
        // =========================================

        float vidaAtual =
                minecraft.player.getHealth();

        float vidaMaxima =
                minecraft.player.getMaxHealth();

        float porcentagemVida =
                vidaAtual / vidaMaxima;

        porcentagemVida =
                Math.max(
                        0.0F,
                        Math.min(
                                1.0F,
                                porcentagemVida
                        )
                );

        int larguraVida =
                (int) (
                        larguraVidaMaxima
                                * porcentagemVida
                );

        if (larguraVida > 0) {

            gui.blit(
                    HEALTH_BAR,
                    vidaX,
                    vidaY,
                    0,
                    0,
                    larguraVida,
                    alturaVida,
                    larguraVidaMaxima,
                    alturaVida
            );
        }

        // =========================================
        // CHAKARA
        // =========================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        minecraft.player
                );

        int chakraAtual =
                stats.getChakraAtual();

        int chakraMaximo =
                stats.getChakara();

        if (chakraMaximo > 0) {

            float porcentagemChakara =
                    (float) chakraAtual
                            / chakraMaximo;

            porcentagemChakara =
                    Math.max(
                            0.0F,
                            Math.min(
                                    1.0F,
                                    porcentagemChakara
                            )
                    );

            int larguraChakara =
                    (int) (
                            larguraChakaraMaxima
                                    * porcentagemChakara
                    );

            if (larguraChakara > 0) {

                gui.blit(
                        CHAKARA_BAR,
                        chakaraX,
                        chakaraY,
                        0,
                        0,
                        larguraChakara,
                        alturaChakara,
                        larguraChakaraMaxima,
                        alturaChakara
                );
            }
        }
    }
}