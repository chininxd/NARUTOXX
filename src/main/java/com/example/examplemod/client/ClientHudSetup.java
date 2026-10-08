package com.example.examplemod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ClientHudSetup {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {

        // =====================================================
        // REMOVE OS CORAÇÕES VANILLA
        // =====================================================

        if (event.getOverlay().id().equals(
                VanillaGuiOverlay.PLAYER_HEALTH.id()
        )) {
            event.setCanceled(true);
        }
    }
}
