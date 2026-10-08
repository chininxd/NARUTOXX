package com.example.examplemod.network;

import com.example.examplemod.mission.MissionManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClaimMissionRewardPacket {

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public ClaimMissionRewardPacket() {
    }

    // =========================================================
    // CONSTRUTOR DO PACOTE RECEBIDO
    // =========================================================

    public ClaimMissionRewardPacket(
            FriendlyByteBuf buffer
    ) {
    }

    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            ClaimMissionRewardPacket packet,
            FriendlyByteBuf buffer
    ) {
        // Este pacote não precisa enviar dados.
    }

    // =========================================================
    // DECODE
    // =========================================================

    public static ClaimMissionRewardPacket decode(
            FriendlyByteBuf buffer
    ) {
        return new ClaimMissionRewardPacket(buffer);
    }

    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            ClaimMissionRewardPacket packet,
            Supplier<NetworkEvent.Context> supplier
    ) {

        NetworkEvent.Context context =
                supplier.get();

        context.enqueueWork(() -> {

            ServerPlayer player =
                    context.getSender();

            // Segurança
            if (player == null) {
                return;
            }

            // =================================================
            // PEGAR RECOMPENSA
            // =================================================

            MissionManager manager =
                    MissionManager.get(player);

            manager.claimReward();
        });

        context.setPacketHandled(true);
    }
}