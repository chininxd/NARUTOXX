package com.example.examplemod.command;

import com.example.examplemod.world.OrochimaruHideoutGenerator;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class NarutoXCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("narutox")

                        // Qualquer jogador pode usar
                        .requires(source -> true)

                        .then(
                                Commands.literal("cord")

                                        .then(
                                                Commands.literal("orochimaru")

                                                        // Qualquer jogador pode usar
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

                                                            source.sendSuccess(
                                                                    () -> Component.literal(
                                                                            "§6[NarutoX] §fCoordenadas do esconderijo do Orochimaru:"
                                                                    ),
                                                                    false
                                                            );

                                                            source.sendSuccess(
                                                                    () -> Component.literal(
                                                                            "§7X: §e"
                                                                                    + pos.getX()
                                                                                    + " §7Y: §e"
                                                                                    + pos.getY()
                                                                                    + " §7Z: §e"
                                                                                    + pos.getZ()
                                                                    ),
                                                                    false
                                                            );

                                                            return 1;
                                                        })
                                        )
                        )
        );
    }
}