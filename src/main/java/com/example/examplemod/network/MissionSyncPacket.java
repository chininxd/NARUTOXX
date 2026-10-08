package com.example.examplemod.network;

import com.example.examplemod.client.MissionClientData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MissionSyncPacket {

    private final int activeMissionId;
    private final int nextMissionId;
    private final int progress;
    private final boolean rewardAvailable;

    public MissionSyncPacket(
            int activeMissionId,
            int nextMissionId,
            int progress,
            boolean rewardAvailable
    ) {

        this.activeMissionId = activeMissionId;
        this.nextMissionId = nextMissionId;
        this.progress = progress;
        this.rewardAvailable = rewardAvailable;
    }

    public MissionSyncPacket(
            FriendlyByteBuf buffer
    ) {

        this.activeMissionId =
                buffer.readInt();

        this.nextMissionId =
                buffer.readInt();

        this.progress =
                buffer.readInt();

        this.rewardAvailable =
                buffer.readBoolean();
    }

    // =========================================================
    // ENCODE
    // =========================================================

    public static void encode(
            MissionSyncPacket packet,
            FriendlyByteBuf buffer
    ) {

        buffer.writeInt(
                packet.activeMissionId
        );

        buffer.writeInt(
                packet.nextMissionId
        );

        buffer.writeInt(
                packet.progress
        );

        buffer.writeBoolean(
                packet.rewardAvailable
        );
    }

    // =========================================================
    // DECODE
    // =========================================================

    public static MissionSyncPacket decode(
            FriendlyByteBuf buffer
    ) {

        return new MissionSyncPacket(
                buffer
        );
    }

    // =========================================================
    // HANDLE
    // =========================================================

    public static void handle(
            MissionSyncPacket packet,
            Supplier<NetworkEvent.Context> supplier
    ) {

        NetworkEvent.Context context =
                supplier.get();

        context.enqueueWork(() -> {

            MissionClientData.setMissionData(
                    packet.activeMissionId,
                    packet.nextMissionId,
                    packet.progress,
                    packet.rewardAvailable
            );
        });

        context.setPacketHandled(true);
    }
}