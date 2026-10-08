package com.example.examplemod.network;

import com.example.examplemod.player.DojutsuConfig;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsEffects;
import com.example.examplemod.player.PlayerStatsManager;
import com.example.examplemod.player.PlayerTechniques;
import com.example.examplemod.player.PlayerTechniquesManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class BuyDojutsuPacket {

    // =========================================================
    // AÇÕES
    //
    // 2 = Sharingan 2 Tomoe
    // 3 = Sharingan 3 Tomoe
    // 4 = Mangekyō
    // 5 = Byakugan Despertado
    // 6 = Susanoo Estágio 2
    // 7 = Susanoo Estágio 3
    // 8 = Susanoo Estágio 4
    // 9 = Susanoo Estágio 5 / Perfeito
    // =========================================================

    private final int tomoe;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public BuyDojutsuPacket(
            int tomoe
    ) {

        this.tomoe =
                tomoe;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            BuyDojutsuPacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeInt(
                packet.tomoe
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static BuyDojutsuPacket decode(
            FriendlyByteBuf buffer
    ) {

        return new BuyDojutsuPacket(
                buffer.readInt()
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            BuyDojutsuPacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {

        NetworkEvent.Context context =
                contextSupplier.get();


        context.enqueueWork(() -> {

            ServerPlayer player =
                    context.getSender();

            if (
                    player == null
            ) {

                return;
            }


            PlayerStats stats =
                    PlayerStatsManager.get(
                            player
                    );

            if (
                    stats == null
            ) {

                return;
            }


            // =================================================
            // 2 TOMOE
            // =================================================

            if (
                    packet.tomoe == 2
            ) {

                if (
                        stats.getSharinganTomoe()
                                >= 2
                ) {

                    return;
                }


                if (
                        stats.getSharinganTomoe()
                                < 1
                ) {

                    return;
                }


                int custoTP =
                        DojutsuConfig.CUSTO_2_TOMOE;

                int requisitoMente =
                        DojutsuConfig.MENTE_2_TOMOE;


                if (
                        stats.getMente()
                                < requisitoMente
                ) {

                    return;
                }


                if (
                        stats.getPontos()
                                < custoTP
                ) {

                    return;
                }


                stats.setPontos(
                        stats.getPontos()
                                - custoTP
                );


                stats.setSharinganTomoe(
                        2
                );


                finalizarCompra(
                        player,
                        stats
                );

                return;
            }


            // =================================================
            // 3 TOMOE
            // =================================================

            if (
                    packet.tomoe == 3
            ) {

                if (
                        stats.getSharinganTomoe()
                                >= 3
                ) {

                    return;
                }


                if (
                        stats.getSharinganTomoe()
                                < 2
                ) {

                    return;
                }


                int custoTP =
                        DojutsuConfig.CUSTO_3_TOMOE;

                int requisitoMente =
                        DojutsuConfig.MENTE_3_TOMOE;


                if (
                        stats.getMente()
                                < requisitoMente
                ) {

                    return;
                }


                if (
                        stats.getPontos()
                                < custoTP
                ) {

                    return;
                }


                stats.setPontos(
                        stats.getPontos()
                                - custoTP
                );


                stats.setSharinganTomoe(
                        3
                );


                finalizarCompra(
                        player,
                        stats
                );

                return;
            }


            // =================================================
            // MANGEKYŌ
            // =================================================

            if (
                    packet.tomoe == 4
            ) {

                if (
                        stats.getSharinganTomoe()
                                < 3
                ) {

                    return;
                }


                // =============================================
                // JÁ POSSUI
                //
                // TAMBÉM CONSERTA SAVE ANTIGO.
                // =============================================

                if (
                        stats.isMangekyouDesbloqueado()
                ) {

                    if (
                            stats.getMangekyouTipo()
                                    == 0
                    ) {

                        sortearMangekyou(
                                player,
                                stats
                        );
                    }


                    if (
                            stats.getMangekyouTipo() >= 1
                                    && stats.getMangekyouTipo() <= 3
                                    && stats.getSusanooEstagio() <= 0
                    ) {

                        stats.setSusanooEstagio(
                                1
                        );
                    }


                    finalizarCompra(
                            player,
                            stats
                    );

                    return;
                }


                int custoTP =
                        DojutsuConfig.CUSTO_MANGEKYOU;

                int requisitoMente =
                        DojutsuConfig.MENTE_MANGEKYOU;


                if (
                        stats.getMente()
                                < requisitoMente
                ) {

                    return;
                }


                if (
                        stats.getPontos()
                                < custoTP
                ) {

                    return;
                }


                // =============================================
                // PAGAR
                // =============================================

                stats.setPontos(
                        stats.getPontos()
                                - custoTP
                );


                // =============================================
                // DESBLOQUEAR MANGEKYŌ
                // =============================================

                stats.setMangekyouDesbloqueado(
                        true
                );


                // =============================================
                // SORTEAR TIPO
                // =============================================

                if (
                        stats.getMangekyouTipo()
                                == 0
                ) {

                    sortearMangekyou(
                            player,
                            stats
                    );
                }


                // =============================================
                // SUSANOO 1 AUTOMÁTICO
                // =============================================

                if (
                        stats.getMangekyouTipo() >= 1
                                && stats.getMangekyouTipo() <= 3
                ) {

                    stats.setSusanooEstagio(
                            1
                    );
                }


                // =============================================
                // SALVA + REGISTRA SUSANOO NO X
                // =============================================

                finalizarCompra(
                        player,
                        stats
                );

                return;
            }


            // =================================================
            // BYAKUGAN DESPERTADO
            // =================================================

            if (
                    packet.tomoe == 5
            ) {

                if (
                        !"Hyuga".equalsIgnoreCase(
                                stats.getCla()
                        )
                ) {

                    return;
                }


                if (
                        !stats.isByakuganDesbloqueado()
                ) {

                    return;
                }


                if (
                        stats.isByakuganDespertado()
                ) {

                    return;
                }


                int custoTP =
                        DojutsuConfig
                                .CUSTO_BYAKUGAN_DESPERTADO;

                int requisitoMente =
                        DojutsuConfig
                                .MENTE_BYAKUGAN_DESPERTADO;


                if (
                        stats.getMente()
                                < requisitoMente
                ) {

                    return;
                }


                if (
                        stats.getPontos()
                                < custoTP
                ) {

                    return;
                }


                stats.setPontos(
                        stats.getPontos()
                                - custoTP
                );


                stats.setByakuganDespertado(
                        true
                );


                finalizarCompra(
                        player,
                        stats
                );

                return;
            }


            // =================================================
            // SUSANOO ESTÁGIO 2
            // =================================================

            if (
                    packet.tomoe == 6
            ) {

                comprarSusanoo(
                        player,
                        stats,
                        2,
                        DojutsuConfig.CUSTO_SUSANOO_ESTAGIO_2,
                        DojutsuConfig.MENTE_SUSANOO_ESTAGIO_2
                );

                return;
            }


            // =================================================
            // SUSANOO ESTÁGIO 3
            // =================================================

            if (
                    packet.tomoe == 7
            ) {

                comprarSusanoo(
                        player,
                        stats,
                        3,
                        DojutsuConfig.CUSTO_SUSANOO_ESTAGIO_3,
                        DojutsuConfig.MENTE_SUSANOO_ESTAGIO_3
                );

                return;
            }


            // =================================================
            // SUSANOO ESTÁGIO 4
            // =================================================

            if (
                    packet.tomoe == 8
            ) {

                comprarSusanoo(
                        player,
                        stats,
                        4,
                        DojutsuConfig.CUSTO_SUSANOO_ESTAGIO_4,
                        DojutsuConfig.MENTE_SUSANOO_ESTAGIO_4
                );

                return;
            }


            // =================================================
            // SUSANOO ESTÁGIO 5
            // =================================================

            if (
                    packet.tomoe == 9
            ) {

                comprarSusanoo(
                        player,
                        stats,
                        5,
                        DojutsuConfig.CUSTO_SUSANOO_ESTAGIO_5,
                        DojutsuConfig.MENTE_SUSANOO_ESTAGIO_5
                );
            }
        });


        context.setPacketHandled(
                true
        );
    }


    // =========================================================
    // COMPRAR ESTÁGIO DO SUSANOO
    // =========================================================

    private static void comprarSusanoo(
            ServerPlayer player,
            PlayerStats stats,
            int novoEstagio,
            int custoTP,
            int requisitoMente
    ) {

        if (
                !"Uchiha".equalsIgnoreCase(
                        stats.getCla()
                )
        ) {

            return;
        }


        if (
                !stats.isMangekyouDesbloqueado()
        ) {

            return;
        }


        if (
                stats.getMangekyouTipo() < 1
                        || stats.getMangekyouTipo() > 3
        ) {

            return;
        }


        // =====================================================
        // JÁ TEM
        // =====================================================

        if (
                stats.getSusanooEstagio()
                        >= novoEstagio
        ) {

            return;
        }


        // =====================================================
        // PRECISA COMPRAR EM ORDEM
        // =====================================================

        if (
                novoEstagio
                        != stats.getSusanooEstagio() + 1
        ) {

            return;
        }


        // =====================================================
        // ESTÁGIO 5 EXIGE MANGEKYŌ ETERNO
        // =====================================================

        if (
                novoEstagio == 5
                        && !stats.isMangekyouEternoDesbloqueado()
        ) {

            return;
        }


        // =====================================================
        // MENTE É REQUISITO
        //
        // NÃO GASTA MENTE.
        // =====================================================

        if (
                stats.getMente()
                        < requisitoMente
        ) {

            return;
        }


        if (
                stats.getPontos()
                        < custoTP
        ) {

            return;
        }


        // =====================================================
        // PAGAR
        // =====================================================

        stats.setPontos(
                stats.getPontos()
                        - custoTP
        );


        // =====================================================
        // DESBLOQUEAR
        // =====================================================

        stats.setSusanooEstagio(
                novoEstagio
        );


        // =====================================================
        // SEGURANÇA
        // =====================================================

        if (
                stats.getSusanooEstagio()
                        != novoEstagio
        ) {

            stats.setPontos(
                    stats.getPontos()
                            + custoTP
            );

            return;
        }


        finalizarCompra(
                player,
                stats
        );
    }


    // =========================================================
    // FINALIZAR COMPRA
    // =========================================================

    private static void finalizarCompra(
            ServerPlayer player,
            PlayerStats stats
    ) {

        // =====================================================
        // REGISTRAR SUSANOO AUTOMATICAMENTE NO X
        // =====================================================

        registrarSusanooNoMenuX(
                player,
                stats
        );


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
        // SYNC DOJUTSU
        // =====================================================

        sincronizarDojutsu(
                player,
                stats
        );


        // =====================================================
        // SYNC MENU X
        // =====================================================

        sincronizarTecnicas(
                player,
                stats
        );
    }


    // =========================================================
    // REGISTRAR SUSANOO NO MENU X
    // =========================================================

    private static void registrarSusanooNoMenuX(
            ServerPlayer player,
            PlayerStats stats
    ) {

        if (
                player == null
                        || stats == null
        ) {

            return;
        }


        if (
                !stats.isSusanooDesbloqueado()
        ) {

            return;
        }


        PlayerTechniques techniques =
                PlayerTechniquesManager.get(
                        player
                );


        if (
                techniques == null
        ) {

            return;
        }


        boolean adicionou =
                techniques.garantirSusanooRegistrado();


        if (
                adicionou
        ) {

            PlayerTechniquesManager.save(
                    player
            );


            System.out.println(
                    "[NARUTOX] "
                            + player.getGameProfile()
                            .getName()
                            + " recebeu Susanoo no menu X."
            );
        }
    }


    // =========================================================
    // SORTEAR MANGEKYŌ
    //
    // 1 = Kakashi
    // 2 = Sasuke
    // 3 = Madara
    // =========================================================

    private static void sortearMangekyou(
            ServerPlayer player,
            PlayerStats stats
    ) {

        if (
                stats.getMangekyouTipo()
                        != 0
        ) {

            return;
        }


        int tipoSorteado =
                player
                        .getRandom()
                        .nextInt(
                                3
                        )
                        + 1;


        stats.setMangekyouTipo(
                tipoSorteado
        );


        String nomeMangekyou;


        switch (
                tipoSorteado
        ) {

            case 1:

                nomeMangekyou =
                        "Kakashi";

                break;


            case 2:

                nomeMangekyou =
                        "Sasuke";

                break;


            case 3:

                nomeMangekyou =
                        "Madara";

                break;


            default:

                nomeMangekyou =
                        "Desconhecido";

                break;
        }


        System.out.println(
                "[NARUTOX] "
                        + player.getGameProfile()
                        .getName()
                        + " recebeu Mangekyou tipo "
                        + tipoSorteado
                        + " ("
                        + nomeMangekyou
                        + ")"
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
                        stats.getSharinganTomoe(),
                        stats.isSharinganAtivo(),

                        stats.isMangekyouDesbloqueado(),
                        stats.isMangekyouAtivo(),
                        stats.getMangekyouTipo(),

                        stats.isByakuganDesbloqueado(),
                        stats.isByakuganAtivo(),

                        stats.isByakuganDespertado(),
                        stats.isByakuganDespertadoAtivo()
                )
        );
    }


    // =========================================================
    // SINCRONIZAR MENU X
    // =========================================================

    private static void sincronizarTecnicas(
            ServerPlayer player,
            PlayerStats stats
    ) {

        PlayerTechniques techniques =
                PlayerTechniquesManager.get(
                        player
                );


        // =====================================================
        // MARCA
        // =====================================================

        boolean possuiMarca =
                false;

        boolean marcaAtiva =
                false;


        if (
                techniques != null
        ) {

            PlayerTechniques.Tecnica marca =
                    techniques.getTecnica(
                            PlayerTechniques
                                    .TECNICA_MARCA_DA_MALDICAO
                    );


            if (
                    marca == null
            ) {

                marca =
                        techniques.getTecnica(
                                PlayerTechniques
                                        .TECNICA_MARCA_DA_MALDICAO_SEM_ACENTO
                        );
            }


            if (
                    marca != null
            ) {

                possuiMarca =
                        true;

                marcaAtiva =
                        marca.isAtivada();
            }
        }


        // =====================================================
        // SUSANOO
        // =====================================================

        boolean possuiSusanoo =
                stats.isSusanooDesbloqueado()
                        && techniques != null
                        && techniques.possuiTecnica(
                        PlayerTechniques
                                .TECNICA_SUSANOO
                );


        boolean susanooAtivo =
                possuiSusanoo
                        && stats.isSusanooAtivo();


        int susanooTipo =
                possuiSusanoo
                        ? stats.getSusanooTipo()
                        : 0;


        int susanooEstagio =
                possuiSusanoo
                        ? stats.getSusanooEstagio()
                        : 0;


        // =====================================================
        // ENVIAR
        // =====================================================

        ModNetwork.CHANNEL.send(
                PacketDistributor.PLAYER.with(
                        () -> player
                ),

                new TechniqueStatePacket(
                        possuiMarca,
                        marcaAtiva,

                        possuiSusanoo,
                        susanooAtivo,

                        susanooTipo,
                        susanooEstagio
                )
        );
    }
}