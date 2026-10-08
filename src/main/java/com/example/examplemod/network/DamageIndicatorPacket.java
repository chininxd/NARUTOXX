package com.example.examplemod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class DamageIndicatorPacket {

    private final UUID alvo;
    private final float dano;

    public DamageIndicatorPacket(UUID alvo, float dano) {
        this.alvo = alvo;
        this.dano = dano;
    }

    public static void encode(
            DamageIndicatorPacket packet,
            FriendlyByteBuf buffer
    ) {
        buffer.writeUUID(packet.alvo);
        buffer.writeFloat(packet.dano);
    }

    public static DamageIndicatorPacket decode(
            FriendlyByteBuf buffer
    ) {
        return new DamageIndicatorPacket(
                buffer.readUUID(),
                buffer.readFloat()
        );
    }

    public static void handle(
            DamageIndicatorPacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {

        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {

            System.out.println(
                    ">>> DANO RECEBIDO NO CLIENTE: " + packet.dano
            );

            com.example.examplemod.client.DamageIndicator.addDamage(
                    packet.alvo,
                    packet.dano
            );
        });

        context.setPacketHandled(true);
    }
}
