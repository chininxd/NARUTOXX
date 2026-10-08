package com.example.examplemod.network;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsEffects;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class ToggleDojutsuPacket {

    private final int slot;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public ToggleDojutsuPacket(int slot) {

        this.slot = slot;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            ToggleDojutsuPacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeInt(
                packet.slot
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static ToggleDojutsuPacket decode(
            FriendlyByteBuf buffer
    ) {

        return new ToggleDojutsuPacket(
                buffer.readInt()
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            ToggleDojutsuPacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {

        NetworkEvent.Context context =
                contextSupplier.get();

        context.enqueueWork(() -> {

            // =================================================
            // PLAYER
            // =================================================

            ServerPlayer player =
                    context.getSender();

            if (player == null) {

                System.out.println(
                        "[NARUTOX] ERRO: PLAYER NULL!"
                );

                return;
            }


            System.out.println(
                    "[NARUTOX] PACOTE RECEBIDO! SLOT: "
                            + packet.slot
            );


            // =================================================
            // VALIDAR SLOT
            // =================================================

            if (
                    packet.slot < 0
                            || packet.slot > 3
            ) {

                System.out.println(
                        "[NARUTOX] SLOT INVALIDO!"
                );

                return;
            }


            // =================================================
            // STATS
            // =================================================

            PlayerStats stats =
                    PlayerStatsManager.get(
                            player
                    );

            if (stats == null) {

                System.out.println(
                        "[NARUTOX] ERRO: STATS NULL!"
                );

                return;
            }


            // =================================================
            // BYAKUGAN NORMAL
            // SLOT 0
            // =================================================

            if (
                    packet.slot == 0
                            && stats.isByakuganDesbloqueado()
            ) {

                boolean estavaAtivo =
                        stats.isByakuganAtivo();


                // =================================================
                // DESATIVAR
                // =================================================

                if (estavaAtivo) {

                    stats.setByakuganAtivo(
                            false
                    );

                    System.out.println(
                            "[NARUTOX] BYAKUGAN DESATIVADO!"
                    );

                } else {

                    // =============================================
                    // DESATIVAR BYAKUGAN DESPERTADO
                    // =============================================

                    if (
                            stats.isByakuganDespertadoAtivo()
                    ) {

                        stats.setByakuganDespertadoAtivo(
                                false
                        );

                        System.out.println(
                                "[NARUTOX] BYAKUGAN DESPERTADO DESATIVADO PARA USAR O BYAKUGAN NORMAL!"
                        );
                    }


                    // =============================================
                    // DESATIVAR SHARINGAN
                    // =============================================

                    if (
                            stats.isSharinganAtivo()
                    ) {

                        stats.setSharinganAtivo(
                                false
                        );

                        stats.setSharinganTomoeAtivo(
                                0
                        );

                        System.out.println(
                                "[NARUTOX] SHARINGAN DESATIVADO PARA USAR O BYAKUGAN!"
                        );
                    }


                    // =============================================
                    // DESATIVAR MANGEKYOU
                    // =============================================

                    if (
                            stats.isMangekyouAtivo()
                    ) {

                        stats.setMangekyouAtivo(
                                false
                        );

                        System.out.println(
                                "[NARUTOX] MANGEKYOU DESATIVADO PARA USAR O BYAKUGAN!"
                        );
                    }


                    // =============================================
                    // ATIVAR BYAKUGAN
                    // =============================================

                    stats.setByakuganAtivo(
                            true
                    );

                    System.out.println(
                            "[NARUTOX] BYAKUGAN ATIVADO!"
                    );
                }


                // =================================================
                // ATUALIZAR
                // =================================================

                atualizarSalvarSincronizar(
                        player,
                        stats
                );


                System.out.println(
                        "[NARUTOX] BYAKUGAN ATIVO: "
                                + stats.isByakuganAtivo()
                );

                return;
            }


            // =================================================
            // BYAKUGAN DESPERTADO
            // SLOT 1
            // =================================================

            if (
                    packet.slot == 1
                            && stats.isByakuganDespertado()
            ) {

                boolean estavaAtivo =
                        stats.isByakuganDespertadoAtivo();


                // =================================================
                // DESATIVAR
                // =================================================

                if (estavaAtivo) {

                    stats.setByakuganDespertadoAtivo(
                            false
                    );

                    System.out.println(
                            "[NARUTOX] BYAKUGAN DESPERTADO DESATIVADO!"
                    );

                } else {

                    // =============================================
                    // DESATIVAR BYAKUGAN NORMAL
                    // =============================================

                    if (
                            stats.isByakuganAtivo()
                    ) {

                        stats.setByakuganAtivo(
                                false
                        );

                        System.out.println(
                                "[NARUTOX] BYAKUGAN NORMAL DESATIVADO PARA USAR O BYAKUGAN DESPERTADO!"
                        );
                    }


                    // =============================================
                    // DESATIVAR SHARINGAN
                    // =============================================

                    if (
                            stats.isSharinganAtivo()
                    ) {

                        stats.setSharinganAtivo(
                                false
                        );

                        stats.setSharinganTomoeAtivo(
                                0
                        );

                        System.out.println(
                                "[NARUTOX] SHARINGAN DESATIVADO PARA USAR O BYAKUGAN DESPERTADO!"
                        );
                    }


                    // =============================================
                    // DESATIVAR MANGEKYOU
                    // =============================================

                    if (
                            stats.isMangekyouAtivo()
                    ) {

                        stats.setMangekyouAtivo(
                                false
                        );

                        System.out.println(
                                "[NARUTOX] MANGEKYOU DESATIVADO PARA USAR O BYAKUGAN DESPERTADO!"
                        );
                    }


                    // =============================================
                    // ATIVAR BYAKUGAN DESPERTADO
                    // =============================================

                    stats.setByakuganDespertadoAtivo(
                            true
                    );

                    System.out.println(
                            "[NARUTOX] BYAKUGAN DESPERTADO ATIVADO!"
                    );
                }


                // =================================================
                // ATUALIZAR
                // =================================================

                atualizarSalvarSincronizar(
                        player,
                        stats
                );


                System.out.println(
                        "[NARUTOX] BYAKUGAN DESPERTADO ATIVO: "
                                + stats.isByakuganDespertadoAtivo()
                );

                return;
            }


            // =================================================
            // MANGEKYOU
            // SLOT 3
            // =================================================

            if (
                    packet.slot == 3
            ) {

                // -------------------------------------------------
                // NÃO DESBLOQUEADO
                // -------------------------------------------------

                if (
                        !stats.isMangekyouDesbloqueado()
                ) {

                    System.out.println(
                            "[NARUTOX] MANGEKYOU NAO DESBLOQUEADO!"
                    );

                    return;
                }


                // -------------------------------------------------
                // SEGURANÇA
                // PRECISA TER UM TIPO SORTEADO
                // -------------------------------------------------

                if (
                        stats.getMangekyouTipo() <= 0
                ) {

                    System.out.println(
                            "[NARUTOX] ERRO: MANGEKYOU DESBLOQUEADO SEM TIPO SORTEADO!"
                    );

                    return;
                }


                boolean estavaAtivo =
                        stats.isMangekyouAtivo();


                // =================================================
                // DESATIVAR
                // =================================================

                if (estavaAtivo) {

                    stats.setMangekyouAtivo(
                            false
                    );

                    System.out.println(
                            "[NARUTOX] MANGEKYOU DESATIVADO!"
                    );

                } else {

                    // =============================================
                    // DESATIVAR BYAKUGAN NORMAL
                    // =============================================

                    if (
                            stats.isByakuganAtivo()
                    ) {

                        stats.setByakuganAtivo(
                                false
                        );

                        System.out.println(
                                "[NARUTOX] BYAKUGAN DESATIVADO PARA USAR O MANGEKYOU!"
                        );
                    }


                    // =============================================
                    // DESATIVAR BYAKUGAN DESPERTADO
                    // =============================================

                    if (
                            stats.isByakuganDespertadoAtivo()
                    ) {

                        stats.setByakuganDespertadoAtivo(
                                false
                        );

                        System.out.println(
                                "[NARUTOX] BYAKUGAN DESPERTADO DESATIVADO PARA USAR O MANGEKYOU!"
                        );
                    }


                    // =============================================
                    // DESATIVAR SHARINGAN
                    // =============================================

                    if (
                            stats.isSharinganAtivo()
                    ) {

                        stats.setSharinganAtivo(
                                false
                        );

                        stats.setSharinganTomoeAtivo(
                                0
                        );

                        System.out.println(
                                "[NARUTOX] SHARINGAN DESATIVADO PARA USAR O MANGEKYOU!"
                        );
                    }


                    // =============================================
                    // ATIVAR MANGEKYOU
                    // =============================================

                    stats.setMangekyouAtivo(
                            true
                    );


                    System.out.println(
                            "[NARUTOX] MANGEKYOU ATIVADO!"
                    );


                    System.out.println(
                            "[NARUTOX] TIPO DO MANGEKYOU: "
                                    + stats.getMangekyouTipo()
                    );
                }


                // =================================================
                // ATUALIZAR
                // =================================================

                atualizarSalvarSincronizar(
                        player,
                        stats
                );


                System.out.println(
                        "[NARUTOX] MANGEKYOU ATIVO: "
                                + stats.isMangekyouAtivo()
                );

                return;
            }


            // =================================================
            // SHARINGAN
            //
            // SLOT 0 = 1 TOMOE
            // SLOT 1 = 2 TOMOE
            // SLOT 2 = 3 TOMOE
            //
            // PARA HYUGA, OS IFs DE CIMA CAPTURAM:
            //
            // SLOT 0 = BYAKUGAN
            // SLOT 1 = BYAKUGAN DESPERTADO
            // =================================================

            int tomoePossuidas =
                    stats.getSharinganTomoe();


            int tomoeSelecionada =
                    packet.slot + 1;


            System.out.println(
                    "[NARUTOX] TOMOE POSSUIDAS: "
                            + tomoePossuidas
            );


            System.out.println(
                    "[NARUTOX] TOMOE SELECIONADA: "
                            + tomoeSelecionada
            );


            // =================================================
            // VERIFICAR SE POSSUI
            // =================================================

            if (
                    tomoePossuidas <
                            tomoeSelecionada
            ) {

                System.out.println(
                        "[NARUTOX] TOMOE NAO DESBLOQUEADA!"
                );

                return;
            }


            // =================================================
            // DESATIVAR BYAKUGAN NORMAL
            // =================================================

            if (
                    stats.isByakuganAtivo()
            ) {

                stats.setByakuganAtivo(
                        false
                );

                System.out.println(
                        "[NARUTOX] BYAKUGAN DESATIVADO PARA USAR O SHARINGAN!"
                );
            }


            // =================================================
            // DESATIVAR BYAKUGAN DESPERTADO
            // =================================================

            if (
                    stats.isByakuganDespertadoAtivo()
            ) {

                stats.setByakuganDespertadoAtivo(
                        false
                );

                System.out.println(
                        "[NARUTOX] BYAKUGAN DESPERTADO DESATIVADO PARA USAR O SHARINGAN!"
                );
            }


            // =================================================
            // DESATIVAR MANGEKYOU
            // =================================================

            if (
                    stats.isMangekyouAtivo()
            ) {

                stats.setMangekyouAtivo(
                        false
                );

                System.out.println(
                        "[NARUTOX] MANGEKYOU DESATIVADO PARA USAR O SHARINGAN!"
                );
            }


            // =================================================
            // TOMOE ATUAL
            // =================================================

            int tomoeAtivo =
                    stats.getSharinganTomoeAtivo();


            System.out.println(
                    "[NARUTOX] TOMOE ATIVO ANTES: "
                            + tomoeAtivo
            );


            // =================================================
            // DESATIVAR TOMOE
            // =================================================

            if (
                    tomoeAtivo ==
                            tomoeSelecionada
            ) {

                stats.setSharinganTomoeAtivo(
                        0
                );

                System.out.println(
                        "[NARUTOX] TOMOE "
                                + tomoeSelecionada
                                + " DESATIVADO!"
                );

            } else {

                // =================================================
                // ATIVAR TOMOE
                // =================================================

                stats.setSharinganTomoeAtivo(
                        tomoeSelecionada
                );

                System.out.println(
                        "[NARUTOX] TOMOE "
                                + tomoeSelecionada
                                + " ATIVADO!"
                );
            }


            // =================================================
            // ATUALIZAR
            // =================================================

            atualizarSalvarSincronizar(
                    player,
                    stats
            );


            System.out.println(
                    "[NARUTOX] TOMOE ATIVO DEPOIS: "
                            + stats.getSharinganTomoeAtivo()
            );


            System.out.println(
                    "[NARUTOX] SHARINGAN ATIVO: "
                            + stats.isSharinganAtivo()
            );
        });


        context.setPacketHandled(
                true
        );
    }


    // =========================================================
    // ATUALIZAR + SALVAR + SINCRONIZAR
    // =========================================================

    private static void atualizarSalvarSincronizar(
            ServerPlayer player,
            PlayerStats stats
    ) {

        // =====================================================
        // EFEITOS
        // =====================================================

        PlayerStatsEffects.atualizar(
                player
        );


        // =====================================================
        // SAVE
        // =====================================================

        PlayerStatsManager.save(
                player
        );


        // =====================================================
        // SYNC
        // =====================================================

        sincronizarDojutsu(
                player,
                stats
        );
    }


    // =========================================================
    // SINCRONIZAR DOJUTSU
    // =========================================================

    private static void sincronizarDojutsu(
            ServerPlayer player,
            PlayerStats stats
    ) {

        ModNetwork.CHANNEL.send(
                PacketDistributor.PLAYER.with(
                        () -> player
                ),
                new DojutsuStatePacket(

                        // =========================================
                        // SHARINGAN
                        // =========================================

                        stats.getSharinganTomoe(),

                        stats.isSharinganAtivo(),


                        // =========================================
                        // MANGEKYOU
                        // =========================================

                        stats.isMangekyouDesbloqueado(),

                        stats.isMangekyouAtivo(),

                        // TIPO:
                        // 0 = Nenhum
                        // 1 = Kakashi
                        // 2 = Sasuke
                        // 3 = Madara
                        stats.getMangekyouTipo(),


                        // =========================================
                        // BYAKUGAN NORMAL
                        // =========================================

                        stats.isByakuganDesbloqueado(),

                        stats.isByakuganAtivo(),


                        // =========================================
                        // BYAKUGAN DESPERTADO
                        // =========================================

                        stats.isByakuganDespertado(),

                        stats.isByakuganDespertadoAtivo()
                )
        );


        System.out.println(
                "[NARUTOX] ESTADO DOJUTSU ENVIADO!"
        );
    }
}