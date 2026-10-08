package com.example.examplemod.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.player.AbstractClientPlayer;

public class CustomPlayerRenderer {

    // =========================================================
    // RENDER
    // =========================================================

    public static void render(
            AbstractClientPlayer player,
            PlayerRenderer renderer,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            float partialTick
    ) {

        // =====================================================
        // NÃO RENDERIZA OVERLAY
        // =====================================================
        //
        // O Sharingan e o Byakugan agora são colocados
        // diretamente na textura combinada do jogador
        // pelo PlayerSkinHandler.
        //
        // Isso evita que a textura fique flutuando.
        //

        return;
    }
}