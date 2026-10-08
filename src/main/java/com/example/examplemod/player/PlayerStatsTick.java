package com.example.examplemod.player;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod"
)
public class PlayerStatsTick {

    @SubscribeEvent
    public static void onPlayerTick(
            TickEvent.PlayerTickEvent event
    ) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Player player = event.player;

        // Só executa no servidor
        if (player.level().isClientSide()) {
            return;
        }

        PlayerStatsEffects.atualizar(player);
    }
}