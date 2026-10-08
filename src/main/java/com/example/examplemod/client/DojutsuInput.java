package com.example.examplemod.client;

import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.network.ToggleDojutsuPacket;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class DojutsuInput {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        // =========================================================
        // X = ABRIR / FECHAR TECHNIQUE HOTBAR
        // =========================================================

        if (event.getKey() == GLFW.GLFW_KEY_X
                && event.getAction() == GLFW.GLFW_PRESS) {

            System.out.println("[NARUTOX] X APERTADO!");

            Minecraft mc = Minecraft.getInstance();

            if (mc.player == null) {
                return;
            }

            TechniqueHotbar.alternar();

            return;
        }


        // =========================================================
        // G = ATIVAR DŌJUTSU
        // =========================================================

        // Verifica se a tecla apertada foi G
        if (event.getKey() != GLFW.GLFW_KEY_G) {
            return;
        }

        // Só executa quando apertar a tecla,
        // e não quando ficar segurando
        if (event.getAction() != GLFW.GLFW_PRESS) {
            return;
        }

        // TESTE
        System.out.println("[NARUTOX] G APERTADO!");

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }

        // Pega o slot atualmente selecionado
        int slot = DojutsuHotbar.getSlotSelecionado();

        // TESTE
        System.out.println("[NARUTOX] SLOT: " + slot);

        // Envia para o servidor
        ModNetwork.CHANNEL.sendToServer(
                new ToggleDojutsuPacket(slot)
        );
    }
}