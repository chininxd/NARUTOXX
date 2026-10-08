package com.example.examplemod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DojutsuStatePacket {

    // =========================================================
    // SHARINGAN
    // =========================================================

    private final int sharinganTomoe;
    private final boolean sharinganAtivo;

    // =========================================================
    // MANGEKYOU
    // =========================================================

    private final boolean mangekyouDesbloqueado;
    private final boolean mangekyouAtivo;

    // 0 = Nenhum
    // 1 = Kakashi
    // 2 = Sasuke
    // 3 = Madara
    private final int mangekyouTipo;

    // =========================================================
    // BYAKUGAN NORMAL
    // =========================================================

    private final boolean byakuganDesbloqueado;
    private final boolean byakuganAtivo;

    // =========================================================
    // BYAKUGAN DESPERTADO
    // =========================================================

    private final boolean byakuganDespertado;
    private final boolean byakuganDespertadoAtivo;


    // =========================================================
    // ESTADO NO CLIENTE
    // =========================================================

    private static int clientSharinganTomoe = 0;

    private static boolean clientSharinganAtivo = false;

    private static boolean clientMangekyouDesbloqueado = false;

    private static boolean clientMangekyouAtivo = false;

    private static int clientMangekyouTipo = 0;

    // BYAKUGAN NORMAL
    private static boolean clientByakuganDesbloqueado = false;

    private static boolean clientByakuganAtivo = false;

    // BYAKUGAN DESPERTADO
    private static boolean clientByakuganDespertado = false;

    private static boolean clientByakuganDespertadoAtivo = false;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public DojutsuStatePacket(
            int sharinganTomoe,
            boolean sharinganAtivo,
            boolean mangekyouDesbloqueado,
            boolean mangekyouAtivo,
            int mangekyouTipo,
            boolean byakuganDesbloqueado,
            boolean byakuganAtivo,
            boolean byakuganDespertado,
            boolean byakuganDespertadoAtivo
    ) {

        // =====================================================
        // SHARINGAN
        // =====================================================

        this.sharinganTomoe =
                sharinganTomoe;

        this.sharinganAtivo =
                sharinganAtivo;

        // =====================================================
        // MANGEKYOU
        // =====================================================

        this.mangekyouDesbloqueado =
                mangekyouDesbloqueado;

        this.mangekyouAtivo =
                mangekyouAtivo;

        this.mangekyouTipo =
                Math.max(
                        0,
                        Math.min(
                                3,
                                mangekyouTipo
                        )
                );

        // =====================================================
        // BYAKUGAN NORMAL
        // =====================================================

        this.byakuganDesbloqueado =
                byakuganDesbloqueado;

        this.byakuganAtivo =
                byakuganAtivo;

        // =====================================================
        // BYAKUGAN DESPERTADO
        // =====================================================

        this.byakuganDespertado =
                byakuganDespertado;

        this.byakuganDespertadoAtivo =
                byakuganDespertadoAtivo;
    }


    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            DojutsuStatePacket packet,
            FriendlyByteBuf buffer
    ) {

        // =====================================================
        // SHARINGAN
        // =====================================================

        buffer.writeInt(
                packet.sharinganTomoe
        );

        buffer.writeBoolean(
                packet.sharinganAtivo
        );

        // =====================================================
        // MANGEKYOU
        // =====================================================

        buffer.writeBoolean(
                packet.mangekyouDesbloqueado
        );

        buffer.writeBoolean(
                packet.mangekyouAtivo
        );

        buffer.writeInt(
                packet.mangekyouTipo
        );

        // =====================================================
        // BYAKUGAN NORMAL
        // =====================================================

        buffer.writeBoolean(
                packet.byakuganDesbloqueado
        );

        buffer.writeBoolean(
                packet.byakuganAtivo
        );

        // =====================================================
        // BYAKUGAN DESPERTADO
        // =====================================================

        buffer.writeBoolean(
                packet.byakuganDespertado
        );

        buffer.writeBoolean(
                packet.byakuganDespertadoAtivo
        );
    }


    // =========================================================
    // DECODE
    // =========================================================

    public static DojutsuStatePacket decode(
            FriendlyByteBuf buffer
    ) {

        int sharinganTomoe =
                buffer.readInt();

        boolean sharinganAtivo =
                buffer.readBoolean();

        boolean mangekyouDesbloqueado =
                buffer.readBoolean();

        boolean mangekyouAtivo =
                buffer.readBoolean();

        int mangekyouTipo =
                buffer.readInt();

        boolean byakuganDesbloqueado =
                buffer.readBoolean();

        boolean byakuganAtivo =
                buffer.readBoolean();

        boolean byakuganDespertado =
                buffer.readBoolean();

        boolean byakuganDespertadoAtivo =
                buffer.readBoolean();


        return new DojutsuStatePacket(
                sharinganTomoe,
                sharinganAtivo,
                mangekyouDesbloqueado,
                mangekyouAtivo,
                mangekyouTipo,
                byakuganDesbloqueado,
                byakuganAtivo,
                byakuganDespertado,
                byakuganDespertadoAtivo
        );
    }


    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            DojutsuStatePacket packet,
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

                // =================================================
                // SHARINGAN
                // =================================================

                clientSharinganTomoe =
                        packet.sharinganTomoe;

                clientSharinganAtivo =
                        packet.sharinganAtivo;


                // =================================================
                // MANGEKYOU
                // =================================================

                clientMangekyouDesbloqueado =
                        packet.mangekyouDesbloqueado;

                clientMangekyouAtivo =
                        packet.mangekyouAtivo;

                clientMangekyouTipo =
                        packet.mangekyouTipo;


                // =================================================
                // BYAKUGAN NORMAL
                // =================================================

                clientByakuganDesbloqueado =
                        packet.byakuganDesbloqueado;

                clientByakuganAtivo =
                        packet.byakuganAtivo;


                // =================================================
                // BYAKUGAN DESPERTADO
                // =================================================

                clientByakuganDespertado =
                        packet.byakuganDespertado;

                clientByakuganDespertadoAtivo =
                        packet.byakuganDespertadoAtivo;
            }
        });


        context.setPacketHandled(
                true
        );
    }


    // =========================================================
    // SHARINGAN
    // =========================================================

    public static int getClientSharinganTomoe() {

        return clientSharinganTomoe;
    }


    public static boolean clientPossuiSharingan() {

        return clientSharinganTomoe >= 1;
    }


    public static boolean isClientSharinganAtivo() {

        return clientSharinganAtivo;
    }


    // =========================================================
    // MANGEKYOU
    // =========================================================

    public static boolean isClientMangekyouDesbloqueado() {

        return clientMangekyouDesbloqueado;
    }


    public static boolean isClientMangekyouAtivo() {

        return clientMangekyouAtivo;
    }


    public static int getClientMangekyouTipo() {

        return clientMangekyouTipo;
    }


    // =========================================================
    // NOME DO MANGEKYOU
    // =========================================================

    public static String getClientMangekyouNome() {

        switch (
                clientMangekyouTipo
        ) {

            case 1:
                return "Kakashi";

            case 2:
                return "Sasuke";

            case 3:
                return "Madara";

            default:
                return "Nenhum";
        }
    }


    // =========================================================
    // BYAKUGAN NORMAL
    // =========================================================

    public static boolean isClientByakuganDesbloqueado() {

        return clientByakuganDesbloqueado;
    }


    public static boolean isClientByakuganAtivo() {

        return clientByakuganAtivo;
    }


    // =========================================================
    // BYAKUGAN DESPERTADO
    // =========================================================

    public static boolean isClientByakuganDespertado() {

        return clientByakuganDespertado;
    }


    public static boolean isClientByakuganDespertadoAtivo() {

        return clientByakuganDespertadoAtivo;
    }


    // =========================================================
    // RESET CLIENT
    // =========================================================

    public static void resetClientState() {

        clientSharinganTomoe =
                0;

        clientSharinganAtivo =
                false;

        clientMangekyouDesbloqueado =
                false;

        clientMangekyouAtivo =
                false;

        clientMangekyouTipo =
                0;

        clientByakuganDesbloqueado =
                false;

        clientByakuganAtivo =
                false;

        clientByakuganDespertado =
                false;

        clientByakuganDespertadoAtivo =
                false;
    }
}