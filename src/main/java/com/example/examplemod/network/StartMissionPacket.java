package com.example.examplemod.network;

import com.example.examplemod.mission.MissionManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StartMissionPacket {

    private final int missionId;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public StartMissionPacket(int missionId) {
        this.missionId = missionId;
    }

    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            StartMissionPacket packet,
            FriendlyByteBuf buffer
    ) {
        buffer.writeInt(packet.missionId);
    }

    // =========================================================
    // DECODE
    // =========================================================

    public static StartMissionPacket decode(
            FriendlyByteBuf buffer
    ) {
        return new StartMissionPacket(
                buffer.readInt()
        );
    }

    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            StartMissionPacket packet,
            Supplier<NetworkEvent.Context> supplier
    ) {

        NetworkEvent.Context context =
                supplier.get();

        context.enqueueWork(() -> {

            // =================================================
            // PEGAR JOGADOR
            // =================================================

            ServerPlayer player =
                    context.getSender();

            if (player == null) {
                System.out.println(
                        "[NarutoX] ERRO: jogador do pacote é null!"
                );

                return;
            }

            // =================================================
            // DEBUG
            // =================================================

            System.out.println(
                    "[NarutoX] StartMissionPacket recebido!"
            );

            System.out.println(
                    "[NarutoX] Jogador: "
                            + player.getName().getString()
            );

            System.out.println(
                    "[NarutoX] ID da missão: "
                            + packet.missionId
            );

            // =================================================
            // GERENCIADOR
            // =================================================

            MissionManager manager =
                    MissionManager.get(player);

            System.out.println(
                    "[NarutoX] Missão ativa antes: "
                            + manager.hasActiveMission()
            );

            // =================================================
            // INICIAR MISSÃO
            // =================================================

            manager.startMission(
                    packet.missionId
            );

            // =================================================
            // VERIFICAR RESULTADO
            // =================================================

            System.out.println(
                    "[NarutoX] Missão ativa depois: "
                            + manager.hasActiveMission()
            );

            System.out.println(
                    "[NarutoX] ID ativo depois: "
                            + manager.getActiveMissionId()
            );

            // =================================================
            // MENSAGEM PARA O JOGADOR
            // =================================================

            if (manager.hasActiveMission()) {

                player.sendSystemMessage(
                        net.minecraft.network.chat.Component.literal(
                                "§aMissão iniciada!"
                        )
                );

            } else {

                player.sendSystemMessage(
                        net.minecraft.network.chat.Component.literal(
                                "§cA missão NÃO foi iniciada!"
                        )
                );
            }
        });

        context.setPacketHandled(true);
    }
}