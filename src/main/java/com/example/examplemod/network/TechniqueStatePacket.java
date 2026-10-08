package com.example.examplemod.network;

import net.minecraft.network.FriendlyByteBuf;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TechniqueStatePacket {

    // =========================================================
    // MARCA - CLIENTE
    // =========================================================

    private static boolean clientMarcaDaMaldicaoPossui =
            false;

    private static boolean clientMarcaDaMaldicaoAtiva =
            false;


    // =========================================================
    // SUSANOO - CLIENTE
    //
    // TIPO:
    //
    // 0 = Nenhum
    // 1 = Kakashi
    // 2 = Sasuke
    // 3 = Madara
    //
    // ESTÁGIO:
    //
    // 0 = Nenhum
    // 1 = Susanoo 1
    // 2 = Susanoo 2
    // 3 = Susanoo 3
    // 4 = Susanoo 4
    // 5 = Susanoo Perfeito
    // =========================================================

    private static boolean clientSusanooPossui =
            false;

    private static boolean clientSusanooAtivo =
            false;

    private static int clientSusanooTipo =
            0;

    private static int clientSusanooEstagio =
            0;


    // =========================================================
    // DADOS DO PACKET
    // =========================================================

    private final boolean marcaDaMaldicaoPossui;
    private final boolean marcaDaMaldicaoAtiva;

    private final boolean susanooPossui;
    private final boolean susanooAtivo;

    private final int susanooTipo;
    private final int susanooEstagio;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public TechniqueStatePacket(
            boolean marcaDaMaldicaoPossui,
            boolean marcaDaMaldicaoAtiva,
            boolean susanooPossui,
            boolean susanooAtivo,
            int susanooTipo,
            int susanooEstagio
    ) {

        this.marcaDaMaldicaoPossui =
                marcaDaMaldicaoPossui;

        this.marcaDaMaldicaoAtiva =
                marcaDaMaldicaoAtiva;

        this.susanooPossui =
                susanooPossui;

        this.susanooAtivo =
                susanooAtivo;

        this.susanooTipo =
                Math.max(
                        0,
                        Math.min(
                                3,
                                susanooTipo
                        )
                );

        this.susanooEstagio =
                Math.max(
                        0,
                        Math.min(
                                5,
                                susanooEstagio
                        )
                );
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            TechniqueStatePacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeBoolean(
                packet.marcaDaMaldicaoPossui
        );

        buffer.writeBoolean(
                packet.marcaDaMaldicaoAtiva
        );

        buffer.writeBoolean(
                packet.susanooPossui
        );

        buffer.writeBoolean(
                packet.susanooAtivo
        );

        buffer.writeInt(
                packet.susanooTipo
        );

        buffer.writeInt(
                packet.susanooEstagio
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static TechniqueStatePacket decode(
            FriendlyByteBuf buffer
    ) {

        boolean marcaPossui =
                buffer.readBoolean();

        boolean marcaAtiva =
                buffer.readBoolean();

        boolean susanooPossui =
                buffer.readBoolean();

        boolean susanooAtivo =
                buffer.readBoolean();

        int susanooTipo =
                buffer.readInt();

        int susanooEstagio =
                buffer.readInt();


        return new TechniqueStatePacket(
                marcaPossui,
                marcaAtiva,
                susanooPossui,
                susanooAtivo,
                susanooTipo,
                susanooEstagio
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            TechniqueStatePacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {

        NetworkEvent.Context context =
                contextSupplier.get();


        context.enqueueWork(() -> {

            if (
                    context.getDirection()
                            .getReceptionSide()
                            .isClient()
            ) {

                atualizarCliente(
                        packet.marcaDaMaldicaoPossui,
                        packet.marcaDaMaldicaoAtiva,
                        packet.susanooPossui,
                        packet.susanooAtivo,
                        packet.susanooTipo,
                        packet.susanooEstagio
                );
            }
        });


        context.setPacketHandled(
                true
        );
    }


    // =========================================================
    // ATUALIZAR CLIENTE
    // =========================================================

    @OnlyIn(Dist.CLIENT)
    private static void atualizarCliente(
            boolean marcaPossui,
            boolean marcaAtiva,
            boolean susanooPossui,
            boolean susanooAtivo,
            int susanooTipo,
            int susanooEstagio
    ) {

        clientMarcaDaMaldicaoPossui =
                marcaPossui;

        clientMarcaDaMaldicaoAtiva =
                marcaAtiva;


        clientSusanooPossui =
                susanooPossui;

        clientSusanooAtivo =
                susanooAtivo;


        clientSusanooTipo =
                Math.max(
                        0,
                        Math.min(
                                3,
                                susanooTipo
                        )
                );


        clientSusanooEstagio =
                Math.max(
                        0,
                        Math.min(
                                5,
                                susanooEstagio
                        )
                );


        if (
                !clientSusanooPossui
        ) {

            clientSusanooAtivo =
                    false;

            clientSusanooTipo =
                    0;

            clientSusanooEstagio =
                    0;
        }
    }


    // =========================================================
    // MARCA
    // =========================================================

    @OnlyIn(Dist.CLIENT)
    public static boolean isClientMarcaDaMaldicaoPossui() {

        return clientMarcaDaMaldicaoPossui;
    }


    @OnlyIn(Dist.CLIENT)
    public static boolean isClientMarcaDaMaldicaoAtiva() {

        return clientMarcaDaMaldicaoAtiva;
    }


    // =========================================================
    // SUSANOO
    // =========================================================

    @OnlyIn(Dist.CLIENT)
    public static boolean isClientSusanooPossui() {

        return clientSusanooPossui;
    }


    @OnlyIn(Dist.CLIENT)
    public static boolean isClientSusanooAtivo() {

        return clientSusanooAtivo;
    }


    @OnlyIn(Dist.CLIENT)
    public static int getClientSusanooTipo() {

        return clientSusanooTipo;
    }


    @OnlyIn(Dist.CLIENT)
    public static int getClientSusanooEstagio() {

        return clientSusanooEstagio;
    }


    // =========================================================
    // TIPO
    // =========================================================

    @OnlyIn(Dist.CLIENT)
    public static boolean isClientSusanooKakashi() {

        return clientSusanooPossui
                && clientSusanooTipo == 1;
    }


    @OnlyIn(Dist.CLIENT)
    public static boolean isClientSusanooSasuke() {

        return clientSusanooPossui
                && clientSusanooTipo == 2;
    }


    @OnlyIn(Dist.CLIENT)
    public static boolean isClientSusanooMadara() {

        return clientSusanooPossui
                && clientSusanooTipo == 3;
    }


    // =========================================================
    // RESET
    // =========================================================

    @OnlyIn(Dist.CLIENT)
    public static void resetClientState() {

        clientMarcaDaMaldicaoPossui =
                false;

        clientMarcaDaMaldicaoAtiva =
                false;

        clientSusanooPossui =
                false;

        clientSusanooAtivo =
                false;

        clientSusanooTipo =
                0;

        clientSusanooEstagio =
                0;
    }
}