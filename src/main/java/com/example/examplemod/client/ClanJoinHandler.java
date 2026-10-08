package com.example.examplemod.client;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ClanJoinHandler {

    // =========================================================
    // CONTROLE
    // =========================================================

    private static int ticksEsperando = 0;
    private static boolean verificou = false;

    // Espera 40 ticks = aproximadamente 2 segundos
    private static final int TEMPO_ESPERA = 40;

    // =========================================================
    // CLIENT TICK
    // =========================================================

    @SubscribeEvent
    public static void onClientTick(
            TickEvent.ClientTickEvent event
    ) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft minecraft =
                Minecraft.getInstance();

        LocalPlayer player =
                minecraft.player;

        // =====================================================
        // FORA DO MUNDO
        // =====================================================

        if (player == null) {

            ticksEsperando = 0;
            verificou = false;

            return;
        }

        // =====================================================
        // JÁ VERIFICOU NESSA ENTRADA
        // =====================================================

        if (verificou) {
            return;
        }

        // =====================================================
        // ESPERA SINCRONIZAÇÃO
        // =====================================================

        ticksEsperando++;

        if (ticksEsperando < TEMPO_ESPERA) {
            return;
        }

        verificou = true;

        // =====================================================
        // PEGA OS STATS
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return;
        }

        // =====================================================
        // PRIMEIRO: PERSONALIZAÇÃO
        // =====================================================

        if (!stats.isCustomizacaoConcluida()) {

            minecraft.setScreen(
                    new CustomizationScreen()
            );

            return;
        }

        // =====================================================
        // DEPOIS: CLÃ
        // =====================================================

        String cla =
                stats.getCla();

        if (
                cla == null ||
                        cla.isEmpty() ||
                        cla.equals("Nenhum")
        ) {

            minecraft.setScreen(
                    new ClanScreen()
            );
        }
    }
}