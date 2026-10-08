package com.example.examplemod.command;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class PTCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("tpnarutox")

                        // /pt
                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource().getPlayerOrException();

                            adicionarPontos(player, 1);

                            return 1;
                        })

                        // /pt <quantidade>
                        .then(
                                Commands.argument(
                                                "quantidade",
                                                IntegerArgumentType.integer(1)
                                        )

                                        .executes(context -> {

                                            ServerPlayer player =
                                                    context.getSource()
                                                            .getPlayerOrException();

                                            int quantidade =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "quantidade"
                                                    );

                                            adicionarPontos(
                                                    player,
                                                    quantidade
                                            );

                                            return 1;
                                        })
                        )
        );
    }

    // =========================================================
    // ADICIONAR PT
    // =========================================================

    private static void adicionarPontos(
            ServerPlayer player,
            int quantidade
    ) {

        PlayerStats stats =
                PlayerStatsManager.get(player);

        int pontosAtuais =
                stats.getPontos();

        int novosPontos =
                pontosAtuais + quantidade;

        stats.setPontos(novosPontos);

        PlayerStatsManager.save(player);

        // Mensagem no chat
        player.sendSystemMessage(
                Component.literal(
                        "§a+" + quantidade +
                                " PT! §7Total: §b" +
                                novosPontos
                )
        );
    }
}