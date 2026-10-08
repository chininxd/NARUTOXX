package com.example.examplemod.player;

import com.example.examplemod.network.CustomizationStatePacket;
import com.example.examplemod.network.ModNetwork;

import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class PlayerCustomizationSyncHandler {

    // =========================================================
    // JOGADOR ENTROU
    // =========================================================

    @SubscribeEvent
    public static void onPlayerLogin(
            PlayerEvent.PlayerLoggedInEvent event
    ) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        // =====================================================
        // MANDA A PERSONALIZAÇÃO SALVA PARA O CLIENTE
        // =====================================================

        ModNetwork.CHANNEL.send(
                PacketDistributor.PLAYER.with(
                        () -> player
                ),
                new CustomizationStatePacket(
                        stats.getCorPele(),
                        stats.getCorOlhos(),
                        stats.getCabeloSelecionado(),
                        stats.getCabeloR(),
                        stats.getCabeloG(),
                        stats.getCabeloB(),
                        stats.isCustomizacaoConcluida()
                )
        );

        System.out.println(
                "[NARUTOX] Enviando personalização para "
                        + player.getName().getString()
                        + " | Pele: "
                        + stats.getCorPele()
                        + " | Olhos: "
                        + stats.getCorOlhos()
                        + " | Concluída: "
                        + stats.isCustomizacaoConcluida()
        );
    }
}