package com.example.examplemod.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class DamageIndicatorEvents {

    @SubscribeEvent
    public static void onDamage(LivingHurtEvent event) {

        if (event.getEntity().level().isClientSide()) {
            return;
        }

        LivingEntity alvo = event.getEntity();

        if (!(event.getSource().getEntity() instanceof Player jogador)) {
            return;
        }

        if (!(jogador instanceof ServerPlayer serverPlayer)) {
            return;
        }

        float dano = event.getAmount();

        if (dano <= 0) {
            return;
        }

        ModNetwork.CHANNEL.sendTo(
                new DamageIndicatorPacket(
                        alvo.getUUID(),
                        dano
                ),
                serverPlayer.connection.connection,
                net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT
        );
    }
}