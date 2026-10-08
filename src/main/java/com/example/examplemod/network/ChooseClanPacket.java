package com.example.examplemod.network;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsEffects;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class ChooseClanPacket {

    private final int cla;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public ChooseClanPacket(int cla) {

        this.cla = cla;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            ChooseClanPacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeInt(
                packet.cla
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static ChooseClanPacket decode(
            FriendlyByteBuf buffer
    ) {

        return new ChooseClanPacket(
                buffer.readInt()
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            ChooseClanPacket packet,
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
                return;
            }


            // =================================================
            // VALIDAR CLÃ
            //
            // 0 = Uchiha
            // 1 = Hyuga
            // =================================================

            if (
                    packet.cla < 0
                            || packet.cla > 1
            ) {

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
                return;
            }


            // =================================================
            // NÃO PERMITE TROCAR DE CLÃ
            // =================================================

            if (
                    stats.getCla() != null
                            && !stats.getCla().isEmpty()
                            && !stats.getCla().equals("Nenhum")
            ) {

                return;
            }


            // =================================================
            // UCHIHA
            // =================================================

            if (
                    packet.cla == 0
            ) {

                // =================================================
                // CLÃ
                // =================================================

                stats.setCla(
                        "Uchiha"
                );


                // =================================================
                // ATRIBUTOS INICIAIS
                // =================================================

                stats.setForca(
                        15
                );

                stats.setDefesa(
                        12
                );

                stats.setChakara(
                        65
                );

                stats.setMente(
                        4
                );


                // =================================================
                // SHARINGAN
                //
                // UCHIHA COMEÇA COM 1 TOMOE
                // =================================================

                stats.setSharinganTomoe(
                        1
                );

                stats.setSharinganAtivo(
                        false
                );

                stats.setSharinganTomoeAtivo(
                        0
                );


                // =================================================
                // MANGEKYOU
                //
                // AINDA NÃO POSSUI.
                // O TIPO SÓ SERÁ SORTEADO QUANDO DESBLOQUEAR.
                // =================================================

                stats.setMangekyouDesbloqueado(
                        false
                );

                stats.setMangekyouAtivo(
                        false
                );

                stats.setMangekyouTipo(
                        0
                );


                // =================================================
                // BYAKUGAN NORMAL
                // =================================================

                stats.setByakuganDesbloqueado(
                        false
                );

                stats.setByakuganAtivo(
                        false
                );


                // =================================================
                // BYAKUGAN DESPERTADO
                // =================================================

                stats.setByakuganDespertado(
                        false
                );

                stats.setByakuganDespertadoAtivo(
                        false
                );


                // =================================================
                // CHAKRA
                // =================================================

                stats.recuperarTodoChakra();


                System.out.println(
                        "[NARUTOX] "
                                + player.getGameProfile().getName()
                                + " escolheu o clã Uchiha!"
                );
            }


            // =================================================
            // HYUGA
            // =================================================

            else {

                // =================================================
                // CLÃ
                // =================================================

                stats.setCla(
                        "Hyuga"
                );


                // =================================================
                // ATRIBUTOS INICIAIS
                // =================================================

                stats.setForca(
                        25
                );

                stats.setDefesa(
                        7
                );

                stats.setChakara(
                        98
                );

                stats.setMente(
                        5
                );


                // =================================================
                // SHARINGAN
                //
                // HYUGA NÃO POSSUI.
                // =================================================

                stats.setSharinganTomoe(
                        0
                );

                stats.setSharinganAtivo(
                        false
                );

                stats.setSharinganTomoeAtivo(
                        0
                );


                // =================================================
                // MANGEKYOU
                // =================================================

                stats.setMangekyouDesbloqueado(
                        false
                );

                stats.setMangekyouAtivo(
                        false
                );

                stats.setMangekyouTipo(
                        0
                );


                // =================================================
                // BYAKUGAN NORMAL
                //
                // HYUGA RECEBE AUTOMATICAMENTE.
                // =================================================

                stats.setByakuganDesbloqueado(
                        true
                );

                stats.setByakuganAtivo(
                        false
                );


                // =================================================
                // BYAKUGAN DESPERTADO
                //
                // PRECISA SER COMPRADO DEPOIS.
                // =================================================

                stats.setByakuganDespertado(
                        false
                );

                stats.setByakuganDespertadoAtivo(
                        false
                );


                // =================================================
                // CHAKRA
                // =================================================

                stats.recuperarTodoChakra();


                System.out.println(
                        "[NARUTOX] "
                                + player.getGameProfile().getName()
                                + " escolheu o clã Hyuga!"
                );
            }


            // =================================================
            // SALVAR
            // =================================================

            PlayerStatsManager.save(
                    player
            );


            // =================================================
            // ATUALIZAR EFEITOS
            // =================================================

            PlayerStatsEffects.atualizar(
                    player
            );


            // =================================================
            // SINCRONIZAR DOJUTSUS COM O CLIENTE
            // =================================================

            ModNetwork.CHANNEL.send(
                    PacketDistributor.PLAYER.with(
                            () -> player
                    ),
                    new DojutsuStatePacket(

                            // =====================================
                            // SHARINGAN
                            // =====================================

                            stats.getSharinganTomoe(),

                            stats.isSharinganAtivo(),


                            // =====================================
                            // MANGEKYOU
                            // =====================================

                            stats.isMangekyouDesbloqueado(),

                            stats.isMangekyouAtivo(),

                            // 0 = nenhum
                            // 1 = Kakashi
                            // 2 = Sasuke
                            // 3 = Madara
                            stats.getMangekyouTipo(),


                            // =====================================
                            // BYAKUGAN NORMAL
                            // =====================================

                            stats.isByakuganDesbloqueado(),

                            stats.isByakuganAtivo(),


                            // =====================================
                            // BYAKUGAN DESPERTADO
                            // =====================================

                            stats.isByakuganDespertado(),

                            stats.isByakuganDespertadoAtivo()
                    )
            );


            System.out.println(
                    "[NARUTOX] ESTADO DOS DOJUTSUS SINCRONIZADO!"
            );
        });


        context.setPacketHandled(
                true
        );
    }
}