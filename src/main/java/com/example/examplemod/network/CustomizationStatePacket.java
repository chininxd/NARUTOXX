package com.example.examplemod.network;

import com.example.examplemod.client.CustomizationScreen;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;

import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CustomizationStatePacket {

    // =========================================================
    // DADOS
    // =========================================================

    private final int corPele;
    private final int corOlhos;

    private final int cabeloSelecionado;

    private final int cabeloR;
    private final int cabeloG;
    private final int cabeloB;

    private final boolean concluida;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public CustomizationStatePacket(
            int corPele,
            int corOlhos,
            int cabeloSelecionado,
            int cabeloR,
            int cabeloG,
            int cabeloB,
            boolean concluida
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

        this.concluida =
                concluida;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            CustomizationStatePacket packet,
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

        buffer.writeBoolean(
                packet.concluida
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static CustomizationStatePacket decode(
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

        boolean concluida =
                buffer.readBoolean();


        return new CustomizationStatePacket(
                corPele,
                corOlhos,
                cabeloSelecionado,
                cabeloR,
                cabeloG,
                cabeloB,
                concluida
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            CustomizationStatePacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {

        NetworkEvent.Context context =
                contextSupplier.get();


        context.enqueueWork(() -> {

            Minecraft minecraft =
                    Minecraft.getInstance();

            if (minecraft.player == null) {
                return;
            }


            PlayerStats stats =
                    PlayerStatsManager.get(
                            minecraft.player
                    );

            if (stats == null) {
                return;
            }


            // =================================================
            // COR DA PELE
            // 1 - 5
            // =================================================

            int corPele =
                    packet.corPele;

            if (corPele < 1) {
                corPele = 1;
            }

            if (
                    corPele >
                            CustomizationScreen.PELES.length
            ) {

                corPele =
                        CustomizationScreen.PELES.length;
            }


            // =================================================
            // COR DOS OLHOS
            // 1 - 6
            // =================================================

            int corOlhos =
                    packet.corOlhos;

            if (corOlhos < 1) {
                corOlhos = 1;
            }

            if (
                    corOlhos >
                            CustomizationScreen.TOTAL_OLHOS
            ) {

                corOlhos =
                        CustomizationScreen.TOTAL_OLHOS;
            }


            // =================================================
            // CABELO
            // 1 - 3
            // =================================================

            int cabelo =
                    packet.cabeloSelecionado;

            if (cabelo < 1) {
                cabelo = 1;
            }

            if (cabelo > 3) {
                cabelo = 3;
            }


            // =================================================
            // RED
            // 0 - 255
            // =================================================

            int red =
                    packet.cabeloR;

            if (red < 0) {
                red = 0;
            }

            if (red > 255) {
                red = 255;
            }


            // =================================================
            // GREEN
            // 0 - 255
            // =================================================

            int green =
                    packet.cabeloG;

            if (green < 0) {
                green = 0;
            }

            if (green > 255) {
                green = 255;
            }


            // =================================================
            // BLUE
            // 0 - 255
            // =================================================

            int blue =
                    packet.cabeloB;

            if (blue < 0) {
                blue = 0;
            }

            if (blue > 255) {
                blue = 255;
            }


            // =================================================
            // RECEBE OS DADOS REAIS DO SERVIDOR
            // =================================================

            stats.setCorPele(
                    corPele
            );

            stats.setCorOlhos(
                    corOlhos
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
                    packet.concluida
            );


            // =================================================
            // DEBUG
            // =================================================

            System.out.println(
                    "[NARUTOX] Customização recebida do servidor"
                            + " | Pele: "
                            + corPele
                            + " | Olhos: "
                            + corOlhos
                            + " | Cabelo: "
                            + cabelo
                            + " | RGB: "
                            + red
                            + ", "
                            + green
                            + ", "
                            + blue
                            + " | Concluída: "
                            + packet.concluida
            );


            // =================================================
            // ABRE PERSONALIZAÇÃO
            //
            // Só abre caso o jogador ainda não tenha concluído.
            // =================================================

            if (!packet.concluida) {

                minecraft.setScreen(
                        new CustomizationScreen()
                );
            }
        });


        context.setPacketHandled(
                true
        );
    }
}