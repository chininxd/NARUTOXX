package com.example.examplemod.client;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ModKeyMappings {

    // =========================
    // TECLA DO STATUS
    // =========================

    public static final KeyMapping STATUS_KEY = new KeyMapping(
            "status",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "Narutox"
    );


    // =========================
    // TECLA DO CHAKRA
    // =========================

    public static final KeyMapping CHAKRA_KEY = new KeyMapping(
            "Chakara",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "Narutox"
    );


    // =========================
    // TECLA DE ANDAR SOBRE ÁGUA
    // =========================

    public static final KeyMapping AGUA_KEY = new KeyMapping(
            "Caminhar Sobre a agua",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "Narutox"
    );


    // =========================
    // TECLA DA HOTBAR LATERAL
    // =========================

    public static final KeyMapping DOJUTSU_KEY = new KeyMapping(
            "Hotbar de Dōjutsu",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "Narutox"
    );


    // =========================
    // TECLA PARA ATIVAR DŌJUTSU
    // =========================

    public static final KeyMapping DOJUTSU_ATIVAR_KEY = new KeyMapping(
            "Ativar Dōjutsu",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "Narutox"
    );


    // =========================
    // REGISTRAR TECLAS
    // =========================

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {

        event.register(STATUS_KEY);
        event.register(CHAKRA_KEY);
        event.register(AGUA_KEY);
        event.register(DOJUTSU_KEY);
        event.register(DOJUTSU_ATIVAR_KEY);
    }
}