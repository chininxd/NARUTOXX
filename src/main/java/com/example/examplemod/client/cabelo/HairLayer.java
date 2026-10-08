package com.example.examplemod.client.cabelo;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HairLayer extends RenderLayer<
        AbstractClientPlayer,
        PlayerModel<AbstractClientPlayer>
        > {

    // =========================================================
    // TEXTURA BASE DO CABELO
    // =========================================================

    private static final ResourceLocation HAIR_TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/cabelos/hair_base.png"
            );


    // =========================================================
    // MODELOS
    // =========================================================

    private final Hair01Model hair01 =
            new Hair01Model();

    private final Hair02Model hair02 =
            new Hair02Model();

    private final Hair03Model hair03 =
            new Hair03Model();


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public HairLayer(
            RenderLayerParent<
                    AbstractClientPlayer,
                    PlayerModel<AbstractClientPlayer>
                    > parent
    ) {
        super(parent);
    }


    // =========================================================
    // RENDER
    // =========================================================

    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,

            AbstractClientPlayer player,

            float limbSwing,
            float limbSwingAmount,
            float partialTick,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {

        // =====================================================
        // STATS
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return;
        }


        // =====================================================
        // CABELO SELECIONADO
        // =====================================================

        int cabelo =
                stats.getCabeloSelecionado();

        if (cabelo <= 0) {
            return;
        }


        // =====================================================
        // COR RGB
        // =====================================================

        float red =
                stats.getCabeloR() / 255.0F;

        float green =
                stats.getCabeloG() / 255.0F;

        float blue =
                stats.getCabeloB() / 255.0F;


        // =====================================================
        // TEXTURA
        // =====================================================

        VertexConsumer vertexConsumer =
                bufferSource.getBuffer(
                        RenderType.entityCutoutNoCull(
                                HAIR_TEXTURE
                        )
                );


        // =====================================================
        // INICIA TRANSFORMAÇÃO
        // =====================================================

        poseStack.pushPose();


        // =====================================================
        // PRENDE NA CABEÇA
        // =====================================================

        getParentModel()
                .head
                .translateAndRotate(
                        poseStack
                );


        // =====================================================
        // CORRIGE CIMA / BAIXO
        //
        // Essa rotação deu certo para os 3 cabelos.
        // =====================================================

        poseStack.mulPose(
                Axis.XP.rotationDegrees(
                        180.0F
                )
        );


        // =====================================================
        // POSICIONA NA CABEÇA
        // =====================================================

        poseStack.translate(
                0.0D,
                0.50D,
                0.0D
        );


        // =====================================================
        // CORRIGE FRENTE / TRÁS
        //
        // Hair 1 = já está correto
        // Hair 2 = estava ao contrário
        // Hair 3 = estava ao contrário
        // =====================================================

        if (cabelo == 2 || cabelo == 3) {

            poseStack.mulPose(
                    Axis.YP.rotationDegrees(
                            180.0F
                    )
            );
        }


        // =====================================================
        // ESCOLHE MODELO
        // =====================================================

        HairModel modelo;

        switch (cabelo) {

            case 2:
                modelo = hair02;
                break;

            case 3:
                modelo = hair03;
                break;

            case 1:
            default:
                modelo = hair01;
                break;
        }


        // =====================================================
        // RENDERIZA
        // =====================================================

        modelo.render(
                poseStack,
                vertexConsumer,

                packedLight,
                OverlayTexture.NO_OVERLAY,

                red,
                green,
                blue,
                1.0F
        );


        // =====================================================
        // FINALIZA
        // =====================================================

        poseStack.popPose();
    }
}