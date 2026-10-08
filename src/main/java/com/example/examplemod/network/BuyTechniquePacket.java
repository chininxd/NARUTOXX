package com.example.examplemod.network;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;
import com.example.examplemod.player.PlayerTechniques;
import com.example.examplemod.player.PlayerTechniquesManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class BuyTechniquePacket {

    // =========================================================
    // TÉCNICAS
    // =========================================================

    public static final int MARCA_DA_MALDICAO = 1;


    // =========================================================
    // CUSTOS
    // =========================================================

    private static final int CUSTO_MARCA_TP = 1_000_000;
    private static final int CUSTO_MARCA_MENTE = 50;


    // =========================================================
    // AÇÃO
    // =========================================================

    private final int tecnica;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public BuyTechniquePacket(
            int tecnica
    ) {

        this.tecnica =
                tecnica;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            BuyTechniquePacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeInt(
                packet.tecnica
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static BuyTechniquePacket decode(
            FriendlyByteBuf buffer
    ) {

        return new BuyTechniquePacket(
                buffer.readInt()
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            BuyTechniquePacket packet,
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


            // =================================================
            // MARCA DA MALDIÇÃO
            // =================================================

            if (
                    packet.tecnica == MARCA_DA_MALDICAO
            ) {

                comprarMarcaDaMaldicao(
                        player
                );
            }
        });


        context.setPacketHandled(
                true
        );
    }


    // =========================================================
    // COMPRAR MARCA DA MALDIÇÃO
    // =========================================================

    private static void comprarMarcaDaMaldicao(
            ServerPlayer player
    ) {

        // =====================================================
        // PEGA OS DADOS DO JOGADOR
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );


        PlayerTechniques techniques =
                PlayerTechniquesManager.get(
                        player
                );


        if (
                stats == null
                        || techniques == null
        ) {

            return;
        }


        // =====================================================
        // JÁ POSSUI
        // =====================================================

        if (
                techniques.possuiTecnica(
                        "Marca da Maldição"
                )
        ) {

            player.sendSystemMessage(
                    Component.literal(
                            "§eVoce ja possui a Marca da Maldicao."
                    )
            );


            sincronizarEstado(
                    player,
                    techniques,
                    stats
            );


            return;
        }


        // =====================================================
        // VERIFICA SLOT LIVRE
        // =====================================================

        if (
                techniques.slotsCheios()
        ) {

            player.sendSystemMessage(
                    Component.literal(
                            "§cSeus slots de tecnicas estao cheios."
                    )
            );


            return;
        }


        // =====================================================
        // VERIFICA MENTE
        // =====================================================

        if (
                stats.getMente()
                        < CUSTO_MARCA_MENTE
        ) {

            player.sendSystemMessage(
                    Component.literal(
                            "§cVoce precisa de 50 de Mente."
                    )
            );


            return;
        }


        // =====================================================
        // VERIFICA TP
        // =====================================================

        if (
                stats.getPontos()
                        < CUSTO_MARCA_TP
        ) {

            player.sendSystemMessage(
                    Component.literal(
                            "§cVoce precisa de 1.000.000 TP."
                    )
            );


            return;
        }


        // =====================================================
        // DESCONTA TP
        // =====================================================

        stats.setPontos(
                stats.getPontos()
                        - CUSTO_MARCA_TP
        );


        // =====================================================
        // DESCONTA MENTE
        // =====================================================

        stats.setMente(
                stats.getMente()
                        - CUSTO_MARCA_MENTE
        );


        // =====================================================
        // ADICIONA TÉCNICA
        // =====================================================

        boolean adicionada =
                techniques.adicionarTecnica(
                        "Marca da Maldição"
                );


        // =====================================================
        // SEGURANÇA
        // =====================================================

        if (
                !adicionada
        ) {

            // DEVOLVE TP
            stats.setPontos(
                    stats.getPontos()
                            + CUSTO_MARCA_TP
            );


            // DEVOLVE MENTE
            stats.setMente(
                    stats.getMente()
                            + CUSTO_MARCA_MENTE
            );


            return;
        }


        // =====================================================
        // SALVA STATS
        // =====================================================

        stats.save(
                player
        );


        // =====================================================
        // SALVA TÉCNICAS
        // =====================================================

        PlayerTechniquesManager.save(
                player
        );


        // =====================================================
        // SINCRONIZA CLIENTE
        // =====================================================

        sincronizarEstado(
                player,
                techniques,
                stats
        );


        // =====================================================
        // MENSAGEM
        // =====================================================

        player.sendSystemMessage(
                Component.literal(
                        "§aVoce aprendeu a Marca da Maldicao!"
                )
        );


        // =====================================================
        // DEBUG
        // =====================================================

        System.out.println(
                "[NARUTOX] "
                        + player.getName().getString()
                        + " aprendeu Marca da Maldição."
        );
    }


    // =========================================================
    // SINCRONIZAR TÉCNICAS COM O CLIENTE
    //
    // AGORA SINCRONIZA:
    //
    // - Marca possui
    // - Marca ativa
    // - Susanoo possui
    // - Susanoo ativo
    // - Susanoo tipo
    // - Susanoo estágio
    // =========================================================

    private static void sincronizarEstado(
            ServerPlayer player,
            PlayerTechniques techniques,
            PlayerStats stats
    ) {

        // =====================================================
        // MARCA DA MALDIÇÃO
        // =====================================================

        boolean possuiMarca =
                false;


        boolean marcaAtiva =
                false;


        // =====================================================
        // TENTA COM ACENTO
        // =====================================================

        PlayerTechniques.Tecnica marca =
                techniques.getTecnica(
                        "Marca da Maldição"
                );


        // =====================================================
        // SAVE ANTIGO SEM ACENTO
        // =====================================================

        if (
                marca == null
        ) {

            marca =
                    techniques.getTecnica(
                            "Marca da Maldicao"
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


        // =====================================================
        // SUSANOO
        // =====================================================

        boolean possuiSusanoo =
                false;


        boolean susanooAtivo =
                false;


        int susanooTipo =
                0;


        int susanooEstagio =
                0;


        if (
                stats != null
        ) {

            possuiSusanoo =
                    stats.isSusanooDesbloqueado();


            susanooAtivo =
                    stats.isSusanooAtivo();


            susanooTipo =
                    stats.getSusanooTipo();


            susanooEstagio =
                    stats.getSusanooEstagio();


            // =================================================
            // SEGURANÇA
            // =================================================

            if (
                    !possuiSusanoo
            ) {

                susanooAtivo =
                        false;


                susanooTipo =
                        0;


                susanooEstagio =
                        0;
            }
        }


        // =====================================================
        // ENVIA PARA O CLIENTE
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