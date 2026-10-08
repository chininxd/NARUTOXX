package com.example.examplemod.command;

import com.example.examplemod.world.OrochimaruHideoutGenerator;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class OrochimaruCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("orochimaru")

                        // QUALQUER JOGADOR PODE USAR
                        .requires(source -> true)

                        // /orochimaru
                        .executes(context -> {

                            CommandSourceStack source =
                                    context.getSource();

                            BlockPos pos =
                                    OrochimaruHideoutGenerator
                                            .getPosicaoEstrutura();

                            if (pos == null) {

                                source.sendFailure(
                                        Component.literal(
                                                "§c[NarutoX] O esconderijo do Orochimaru ainda não foi gerado."
                                        )
                                );

                                return 0;
                            }

                            source.sendSuccess(
                                    () -> Component.literal(
                                            "§6[NarutoX] §fEsconderijo do Orochimaru"
                                    ),
                                    false
                            );

                            source.sendSuccess(
                                    () -> Component.literal(
                                            "§7Coordenadas: §e"
                                                    + pos.getX()
                                                    + " "
                                                    + pos.getY()
                                                    + " "
                                                    + pos.getZ()
                                    ),
                                    false
                            );

                            source.sendSuccess(
                                    () -> Component.literal(
                                            "§7Teleportar: §e/orochimaru tp"
                                    ),
                                    false
                            );

                            return 1;
                        })

                        // /orochimaru tp
                        .then(
                                Commands.literal("tp")

                                        // QUALQUER JOGADOR PODE USAR
                                        .requires(source -> true)

                                        .executes(context -> {

                                            CommandSourceStack source =
                                                    context.getSource();

                                            BlockPos pos =
                                                    OrochimaruHideoutGenerator
                                                            .getPosicaoEstrutura();

                                            if (pos == null) {

                                                source.sendFailure(
                                                        Component.literal(
                                                                "§c[NarutoX] O esconderijo do Orochimaru ainda não foi gerado."
                                                        )
                                                );

                                                return 0;
                                            }

                                            ServerPlayer player =
                                                    source.getPlayer();

                                            if (player == null) {
                                                return 0;
                                            }

                                            player.teleportTo(
                                                    pos.getX() + 0.5D,
                                                    pos.getY() + 1.0D,
                                                    pos.getZ() + 0.5D
                                            );

                                            source.sendSuccess(
                                                    () -> Component.literal(
                                                            "§6[NarutoX] §fVocê foi teleportado para o esconderijo do Orochimaru."
                                                    ),
                                                    false
                                            );

                                            return 1;
                                        })
                        )
        );
    }
}
