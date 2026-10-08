package com.example.examplemod.network;

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

public class ToggleTechniquePacket {

    // =========================================================
    // SLOTS
    //
    // 0 = Marca da Maldição
    // 1 = Susanoo
    // 2+ = outras técnicas
    // =========================================================

    private static final int SLOT_SUSANOO = 1;


    // =========================================================
    // SLOT DA TÉCNICA
    // =========================================================

    private final int slot;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public ToggleTechniquePacket(
            int slot
    ) {

        this.slot =
                slot;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            ToggleTechniquePacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeInt(
                packet.slot
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static ToggleTechniquePacket decode(
            FriendlyByteBuf buffer
    ) {

        return new ToggleTechniquePacket(
                buffer.readInt()
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            ToggleTechniquePacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {

        NetworkEvent.Context context =
                contextSupplier.get();


        context.enqueueWork(() -> {

            // =================================================
            // JOGADOR QUE ENVIOU O PACOTE
            // =================================================

            ServerPlayer player =
                    context.getSender();


            if (
                    player == null
            ) {

                return;
            }


            // =================================================
            // SEGURANÇA DO SLOT
            // =================================================

            if (
                    packet.slot < 0
                            || packet.slot >= 8
            ) {

                return;
            }


            // =================================================
            // SUSANOO
            //
            // SLOT 1 É TRATADO SEPARADAMENTE DAS
            // TÉCNICAS NORMAIS.
            // =================================================

            if (
                    packet.slot == SLOT_SUSANOO
            ) {

                toggleSusanoo(
                        player
                );

                return;
            }


            // =================================================
            // OUTRAS TÉCNICAS
            // =================================================

            toggleTecnicaNormal(
                    player,
                    packet.slot
            );
        });


        context.setPacketHandled(
                true
        );
    }


    // =========================================================
    // TOGGLE SUSANOO
    // =========================================================

    private static void toggleSusanoo(
            ServerPlayer player
    ) {

        // =====================================================
        // PEGA OS STATUS DO JOGADOR
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );


        if (
                stats == null
        ) {

            return;
        }


        // =====================================================
        // PRECISA POSSUIR SUSANOO
        //
        // PlayerStats já verifica:
        //
        // - Mangekyou desbloqueado
        // - tipo válido
        // - Susanoo do mesmo tipo
        // - estágio >= 1
        // =====================================================

        if (
                !stats.isSusanooDesbloqueado()
        ) {

            // Mesmo bloqueado, sincroniza para garantir
            // que o cliente não mostre estado antigo.

            sincronizarEstado(
                    player
            );

            return;
        }


        // =====================================================
        // ATIVA / DESATIVA
        // =====================================================

        stats.toggleSusanoo();


        // =====================================================
        // SALVA STATUS
        // =====================================================

        PlayerStatsManager.save(
                player
        );


        // =====================================================
        // ATUALIZA OS ATRIBUTOS
        //
        // FORÇA
        // VIDA
        // RESISTÊNCIA
        //
        // Velocidade continua vindo somente do dojutsu.
        // =====================================================

        PlayerStatsEffects.atualizar(
                player
        );


        // =====================================================
        // SINCRONIZA CLIENTE
        // =====================================================

        sincronizarEstado(
                player
        );


        // =====================================================
        // DEBUG
        // =====================================================

        String tipoNome =
                getNomeSusanoo(
                        stats.getSusanooTipo()
                );


        System.out.println(
                "[NARUTOX] "
                        + player.getName().getString()
                        + " -> Susanoo "
                        + tipoNome
                        + " Estagio "
                        + stats.getSusanooEstagio()
                        + " = "
                        + (
                        stats.isSusanooAtivo()
                                ? "ATIVADO"
                                : "DESATIVADO"
                )
        );
    }


    // =========================================================
    // TOGGLE DE TÉCNICA NORMAL
    // =========================================================

    private static void toggleTecnicaNormal(
            ServerPlayer player,
            int slot
    ) {

        // =====================================================
        // PEGA AS TÉCNICAS DESSE JOGADOR
        // =====================================================

        PlayerTechniques techniques =
                PlayerTechniquesManager.get(
                        player
                );


        if (
                techniques == null
        ) {

            return;
        }


        // =====================================================
        // PEGA TÉCNICA DO SLOT
        // =====================================================

        PlayerTechniques.Tecnica tecnica =
                techniques.getTecnicaDoSlot(
                        slot
                );


        if (
                tecnica == null
        ) {

            return;
        }


        // =====================================================
        // ATIVA / DESATIVA
        // =====================================================

        tecnica.toggle();


        // =====================================================
        // SALVA
        // =====================================================

        PlayerTechniquesManager.save(
                player
        );


        // =====================================================
        // ATUALIZA EFEITOS
        //
        // IMPORTANTE PARA A MARCA DA MALDIÇÃO.
        // =====================================================

        PlayerStatsEffects.atualizar(
                player
        );


        // =====================================================
        // SINCRONIZA
        // =====================================================

        sincronizarEstado(
                player
        );


        // =====================================================
        // DEBUG
        // =====================================================

        System.out.println(
                "[NARUTOX] "
                        + player.getName().getString()
                        + " -> "
                        + tecnica.getNome()
                        + " = "
                        + (
                        tecnica.isAtivada()
                                ? "ATIVADA"
                                : "DESATIVADA"
                )
        );
    }


    // =========================================================
    // SINCRONIZAR ESTADO COM O CLIENTE
    // =========================================================

    private static void sincronizarEstado(
            ServerPlayer player
    ) {

        // =====================================================
        // MARCA DA MALDIÇÃO
        // =====================================================

        boolean possuiMarca =
                false;

        boolean marcaAtiva =
                false;


        PlayerTechniques techniques =
                PlayerTechniquesManager.get(
                        player
                );


        if (
                techniques != null
        ) {

            // =================================================
            // COM ACENTO
            // =================================================

            PlayerTechniques.Tecnica marca =
                    techniques.getTecnica(
                            "Marca da Maldição"
                    );


            // =================================================
            // COMPATIBILIDADE COM SAVE ANTIGO SEM ACENTO
            // =================================================

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


        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );


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


    // =========================================================
    // NOME DO SUSANOO
    // =========================================================

    private static String getNomeSusanoo(
            int tipo
    ) {

        if (
                tipo == 1
        ) {

            return "Kakashi";
        }


        if (
                tipo == 2
        ) {

            return "Sasuke";
        }


        if (
                tipo == 3
        ) {

            return "Madara";
        }


        return "Nenhum";
    }
}