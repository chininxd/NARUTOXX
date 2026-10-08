package com.example.examplemod.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class PlayerDefenseHandler {

    @SubscribeEvent
    public static void aoReceberDano(LivingHurtEvent event) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        // Defesa do mod
        double defesa =
                stats.getDefesa();

        // Sharingan: Defesa x2
        if (stats.isSharinganAtivo()
                && stats.getSharinganTomoe() >= 1) {

            defesa *= 2.0;
        }

        // Cada ponto de Defesa = 1% de redução
        double reducao =
                defesa / 100.0;

        // Limite máximo de 80% de redução
        reducao =
                Math.min(reducao, 0.80);

        float danoOriginal =
                event.getAmount();

        float danoFinal =
                (float) (
                        danoOriginal *
                                (1.0 - reducao)
                );

        if (danoFinal < 0.0F) {
            danoFinal = 0.0F;
        }

        event.setAmount(danoFinal);
    }
}