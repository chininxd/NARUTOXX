package com.example.examplemod.client;

import com.example.examplemod.client.cabelo.HairLayer;
import com.example.examplemod.client.olhos.NormalEyesLayer;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ClientLayerRegister {

    // =========================================================
    // ADICIONA AS LAYERS AO PLAYER
    // =========================================================

    @SubscribeEvent
    public static void onAddLayers(
            EntityRenderersEvent.AddLayers event
    ) {

        System.out.println(
                "[NARUTOX] EVENTO AddLayers CHAMADO"
        );


        // =====================================================
        // PLAYER NORMAL / STEVE
        // =====================================================

        PlayerRenderer normal =
                event.getSkin(
                        "default"
                );

        if (normal != null) {

            // =============================================
            // OLHOS
            // =============================================

            normal.addLayer(
                    new NormalEyesLayer(
                            normal
                    )
            );

            System.out.println(
                    "[NARUTOX] NORMAL EYES ADICIONADO AO DEFAULT"
            );


            // =============================================
            // CABELO
            // =============================================

            normal.addLayer(
                    new HairLayer(
                            normal
                    )
            );

            System.out.println(
                    "[NARUTOX] HAIR LAYER ADICIONADO AO DEFAULT"
            );
        }


        // =====================================================
        // PLAYER SLIM / ALEX
        // =====================================================

        PlayerRenderer slim =
                event.getSkin(
                        "slim"
                );

        if (slim != null) {

            // =============================================
            // OLHOS
            // =============================================

            slim.addLayer(
                    new NormalEyesLayer(
                            slim
                    )
            );

            System.out.println(
                    "[NARUTOX] NORMAL EYES ADICIONADO AO SLIM"
            );


            // =============================================
            // CABELO
            // =============================================

            slim.addLayer(
                    new HairLayer(
                            slim
                    )
            );

            System.out.println(
                    "[NARUTOX] HAIR LAYER ADICIONADO AO SLIM"
            );
        }
    }
}