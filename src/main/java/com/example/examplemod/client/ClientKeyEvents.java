package com.example.examplemod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ClientKeyEvents {

    // =========================
    // ANDAR SOBRE A ÁGUA
    // =========================

    private static boolean andarSobreAgua = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        // =========================
        // ABRIR STATUS
        // =========================

        while (ModKeyMappings.STATUS_KEY.consumeClick()) {

            minecraft.setScreen(
                    new StatusScreen()
            );
        }

        // =========================
        // CARREGAR CHAKRA
        // =========================

        if (ModKeyMappings.CHAKRA_KEY.isDown()) {

            var stats = com.example.examplemod.player.PlayerStatsManager
                    .get(minecraft.player);

            stats.recuperarChakra(1);
        }

        // =========================
        // ATIVAR / DESATIVAR
        // =========================

        while (ModKeyMappings.AGUA_KEY.consumeClick()) {

            andarSobreAgua = !andarSobreAgua;
        }

        // =========================
        // ANDAR SOBRE A ÁGUA
        // =========================

        if (andarSobreAgua) {

            var player = minecraft.player;

            BlockPos pos = BlockPos.containing(
                    player.getX(),
                    player.getY() - 0.1,
                    player.getZ()
            );

            if (player.level().getBlockState(pos).is(Blocks.WATER)) {

                // Mantém o jogador na superfície
                double alturaDaAgua = pos.getY() + 1.0;

                player.setPos(
                        player.getX(),
                        alturaDaAgua,
                        player.getZ()
                );

                // Impede de cair
                player.setDeltaMovement(
                        player.getDeltaMovement().x,
                        0,
                        player.getDeltaMovement().z
                );

                // Remove a gravidade
                player.setOnGround(true);
            }
        }
    }
}