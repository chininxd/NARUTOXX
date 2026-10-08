package com.example.examplemod.player;

import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.WeakHashMap;

public class PlayerStatsManager {

    private static final Map<Player, PlayerStats> STATS =
            new WeakHashMap<>();

    public static PlayerStats get(Player player) {

        PlayerStats stats = STATS.get(player);

        if (stats == null) {

            stats = new PlayerStats();

            stats.load(player);

            STATS.put(player, stats);
        }

        return stats;
    }

    public static void save(Player player) {

        PlayerStats stats = STATS.get(player);

        if (stats != null) {
            stats.save(player);
        }
    }

    public static void remove(Player player) {

        STATS.remove(player);
    }
}
