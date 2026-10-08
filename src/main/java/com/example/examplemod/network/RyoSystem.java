package com.example.examplemod.economy;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.server.level.ServerPlayer;

public class RyoSystem {

    public static void adicionarRyo(
            ServerPlayer player,
            int quantidade
    ) {

        if (player == null) {
            return;
        }

        if (quantidade <= 0) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        stats.adicionarRyo(quantidade);

        PlayerStatsManager.save(player);
    }

    public static boolean gastarRyo(
            ServerPlayer player,
            int quantidade
    ) {

        if (player == null) {
            return false;
        }

        if (quantidade <= 0) {
            return false;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        boolean conseguiu =
                stats.gastarRyo(quantidade);

        if (conseguiu) {
            PlayerStatsManager.save(player);
        }

        return conseguiu;
    }

    public static int getRyo(
            ServerPlayer player
    ) {

        if (player == null) {
            return 0;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        return stats.getRyo();
    }
}
