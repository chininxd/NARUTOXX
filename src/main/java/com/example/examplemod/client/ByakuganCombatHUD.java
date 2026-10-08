package com.example.examplemod.client;

import com.example.examplemod.network.DojutsuStatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ByakuganCombatHUD {



    @SubscribeEvent
    public static void render(
            RenderGuiOverlayEvent.Post event
    ) {

        Minecraft mc =
                Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }

        if (mc.options.hideGui) {
            return;
        }

        if (!DojutsuStatePacket.isClientByakuganAtivo()) {
            return;
        }

        GuiGraphics gui =
                event.getGuiGraphics();

        int largura =
                mc.getWindow()
                        .getGuiScaledWidth();

        int altura =
                mc.getWindow()
                        .getGuiScaledHeight();

        // =========================================
        // BYAKUGAN
        // COR ORIGINAL
        // 10% VISÍVEL
        // =========================================

        gui.setColor(
                1.0F,
                1.0F,
                1.0F,
                0.10F
        );




        // =========================================
        // RESETAR COR E OPACIDADE
        // =========================================

        gui.setColor(
                1.0F,
                1.0F,
                1.0F,
                1.0F
        );
    }
}