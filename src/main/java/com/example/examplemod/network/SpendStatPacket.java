package com.example.examplemod.network;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsEffects;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpendStatPacket {

    private final int atributo;
    private final int quantidade;

    public SpendStatPacket(int atributo, int quantidade) {
        this.atributo = atributo;
        this.quantidade = quantidade;
    }

    public static void encode(
            SpendStatPacket packet,
            FriendlyByteBuf buffer
    ) {
        buffer.writeInt(packet.atributo);
        buffer.writeInt(packet.quantidade);
    }

    public static SpendStatPacket decode(
            FriendlyByteBuf buffer
    ) {
        return new SpendStatPacket(
                buffer.readInt(),
                buffer.readInt()
        );
    }

    public static void handle(
            SpendStatPacket packet,
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

            if (
                    packet.quantidade != 1 &&
                            packet.quantidade != 5 &&
                            packet.quantidade != 100 &&
                            packet.quantidade != 1000
            ) {
                return;
            }

            if (
                    packet.atributo < 0 ||
                            packet.atributo > 3
            ) {
                return;
            }

            PlayerStats stats =
                    PlayerStatsManager.get(player);

            int custo =
                    stats.getCustoUpgrades(
                            packet.quantidade
                    );

            if (stats.getPontos() < custo) {
                return;
            }

            switch (packet.atributo) {

                case 0:
                    stats.setForca(
                            stats.getForca()
                                    + packet.quantidade
                    );
                    break;

                case 1:
                    stats.setDefesa(
                            stats.getDefesa()
                                    + packet.quantidade
                    );
                    break;

                case 2:
                    stats.setChakara(
                            stats.getChakara()
                                    + packet.quantidade
                    );
                    break;

                case 3:
                    stats.setMente(
                            stats.getMente()
                                    + packet.quantidade
                    );
                    break;

                default:
                    return;
            }

            stats.setPontos(
                    stats.getPontos() - custo
            );

            stats.adicionarUpgrades(
                    packet.quantidade
            );

            PlayerStatsManager.save(player);

            PlayerStatsEffects.atualizar(player);
        });

        context.setPacketHandled(true);
    }
}