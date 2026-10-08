package com.example.examplemod.network;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class ChooseCustomizationPacket {

    // =========================================================
    // DADOS
    // =========================================================

    private final int corPele;
    private final int corOlhos;

    private final int cabeloSelecionado;

    private final int cabeloR;
    private final int cabeloG;
    private final int cabeloB;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public ChooseCustomizationPacket(
            int corPele,
            int corOlhos,
            int cabeloSelecionado,
            int cabeloR,
            int cabeloG,
            int cabeloB
    ) {

        this.corPele =
                corPele;

        this.corOlhos =
                corOlhos;

        this.cabeloSelecionado =
                cabeloSelecionado;

        this.cabeloR =
                cabeloR;

        this.cabeloG =
                cabeloG;

        this.cabeloB =
                cabeloB;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            ChooseCustomizationPacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeInt(
                packet.corPele
        );

        buffer.writeInt(
                packet.corOlhos
        );

        buffer.writeInt(
                packet.cabeloSelecionado
        );

        buffer.writeInt(
                packet.cabeloR
        );

        buffer.writeInt(
                packet.cabeloG
        );

        buffer.writeInt(
                packet.cabeloB
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static ChooseCustomizationPacket decode(
            FriendlyByteBuf buffer
    ) {

        int corPele =
                buffer.readInt();

        int corOlhos =
                buffer.readInt();

        int cabeloSelecionado =
                buffer.readInt();

        int cabeloR =
                buffer.readInt();

        int cabeloG =
                buffer.readInt();

        int cabeloB =
                buffer.readInt();


        return new ChooseCustomizationPacket(
                corPele,
                corOlhos,
                cabeloSelecionado,
                cabeloR,
                cabeloG,
                cabeloB
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            ChooseCustomizationPacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {

        NetworkEvent.Context context =
                contextSupplier.get();


        context.enqueueWork(() -> {

            ServerPlayer player =
                    context.getSender();

            if (player == null) {
                return;
            }


            PlayerStats stats =
                    PlayerStatsManager.get(
                            player
                    );

            if (stats == null) {
                return;
            }


            // =================================================
            // PELE
            // 1 - 5
            // =================================================

            int pele =
                    Math.max(
                            1,
                            Math.min(
                                    5,
                                    packet.corPele
                            )
                    );


            // =================================================
            // OLHOS
            // 1 - 6
            // =================================================

            int olhos =
                    Math.max(
                            1,
                            Math.min(
                                    6,
                                    packet.corOlhos
                            )
                    );


            // =================================================
            // CABELO
            // 1 - 3
            // =================================================

            int cabelo =
                    Math.max(
                            1,
                            Math.min(
                                    3,
                                    packet.cabeloSelecionado
                            )
                    );


            // =================================================
            // RGB
            // 0 - 255
            // =================================================

            int red =
                    Math.max(
                            0,
                            Math.min(
                                    255,
                                    packet.cabeloR
                            )
                    );

            int green =
                    Math.max(
                            0,
                            Math.min(
                                    255,
                                    packet.cabeloG
                            )
                    );

            int blue =
                    Math.max(
                            0,
                            Math.min(
                                    255,
                                    packet.cabeloB
                            )
                    );


            // =================================================
            // SALVA NO SERVIDOR
            // =================================================

            stats.setCorPele(
                    pele
            );

            stats.setCorOlhos(
                    olhos
            );

            stats.setCabeloSelecionado(
                    cabelo
            );

            stats.setCorCabelo(
                    red,
                    green,
                    blue
            );

            stats.setCustomizacaoConcluida(
                    true
            );


            // =================================================
            // SALVA NO NBT
            // =================================================

            stats.save(
                    player
            );


            // =================================================
            // DEVOLVE PARA O CLIENTE
            // =================================================
            //
            // Por enquanto mantemos o CustomizationStatePacket
            // atual para pele/olhos/concluída.
            //
            // Depois vamos adicionar cabelo + RGB nele também
            // para sincronizar a aparência entre jogadores.
            // =================================================

            ModNetwork.CHANNEL.send(
                    PacketDistributor.PLAYER.with(
                            () -> player
                    ),
                    new CustomizationStatePacket(
                            stats.getCorPele(),
                            stats.getCorOlhos(),
                            stats.getCabeloSelecionado(),
                            stats.getCabeloR(),
                            stats.getCabeloG(),
                            stats.getCabeloB(),
                            stats.isCustomizacaoConcluida()
                    )
            );


            // =================================================
            // DEBUG
            // =================================================

            System.out.println(
                    "[NARUTOX] Personalização salva para "
                            + player.getName().getString()
                            + " | Pele: "
                            + pele
                            + " | Olhos: "
                            + olhos
                            + " | Cabelo: "
                            + cabelo
                            + " | RGB: "
                            + red
                            + ", "
                            + green
                            + ", "
                            + blue
            );
        });


        context.setPacketHandled(
                true
        );
    }
}