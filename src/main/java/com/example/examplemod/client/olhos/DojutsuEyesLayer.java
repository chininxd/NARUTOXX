package com.example.examplemod.client.olhos;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class DojutsuEyesLayer
        extends RenderLayer<
        AbstractClientPlayer,
        PlayerModel<AbstractClientPlayer>
        > {

    // =========================================================
    // SHARINGAN NORMAL
    // =========================================================

    private static final ResourceLocation SHARINGAN_TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/player/textura.png"
            );


    // =========================================================
    // MANGEKYOU - KAKASHI
    //
    // TIPO 1
    // =========================================================

    private static final ResourceLocation MANGEKYOU_KAKASHI =
            new ResourceLocation(
                    "examplemod",
                    "textures/player/mangekyou_kakashi.png"
            );


    // =========================================================
    // MANGEKYOU - SASUKE
    //
    // TIPO 2
    // =========================================================

    private static final ResourceLocation MANGEKYOU_SASUKE =
            new ResourceLocation(
                    "examplemod",
                    "textures/player/mangekyou_sasuke.png"
            );


    // =========================================================
    // MANGEKYOU - MADARA
    //
    // TIPO 3
    // =========================================================

    private static final ResourceLocation MANGEKYOU_MADARA =
            new ResourceLocation(
                    "examplemod",
                    "textures/player/mangekyou_madara.png"
            );


    // =========================================================
    // MODELO DOS OLHOS
    //
    // O MESMO JAVA CONTÉM:
    //
    // render(...)
    // = Sharingan normal
    //
    // renderMangekyou(...)
    // = Mangekyou com UV próprio
    // =========================================================

    private final SharinganEyesModel sharinganModel =
            new SharinganEyesModel();


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public DojutsuEyesLayer(
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
            MultiBufferSource buffer,
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
        // PEGA OS STATUS DO PLAYER
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        if (stats == null) {
            return;
        }


        // =====================================================
        // ESTADO DOS DOJUTSUS
        // =====================================================

        boolean sharinganAtivo =
                stats.isSharinganAtivo();

        boolean mangekyouAtivo =
                stats.isMangekyouAtivo();


        // =====================================================
        // NENHUM ATIVO
        // =====================================================

        if (
                !sharinganAtivo
                        && !mangekyouAtivo
        ) {

            return;
        }


        // =====================================================
        // TIPO DO MANGEKYOU
        //
        // 0 = nenhum
        // 1 = Kakashi
        // 2 = Sasuke
        // 3 = Madara
        // =====================================================

        int tipoMangekyou =
                stats.getMangekyouTipo();


        // =====================================================
        // ESCOLHER TEXTURA
        // =====================================================

        ResourceLocation textura;


        // =====================================================
        // MANGEKYOU
        //
        // TEM PRIORIDADE CASO ALGUM ESTADO FIQUE INCONSISTENTE.
        // =====================================================

        if (mangekyouAtivo) {

            // =================================================
            // SEGURANÇA
            // =================================================

            if (
                    tipoMangekyou < 1
                            || tipoMangekyou > 3
            ) {

                return;
            }


            // =================================================
            // KAKASHI
            // =================================================

            if (tipoMangekyou == 1) {

                textura =
                        MANGEKYOU_KAKASHI;
            }


            // =================================================
            // SASUKE
            // =================================================

            else if (tipoMangekyou == 2) {

                textura =
                        MANGEKYOU_SASUKE;
            }


            // =================================================
            // MADARA
            // =================================================

            else {

                textura =
                        MANGEKYOU_MADARA;
            }

        } else {

            // =================================================
            // SHARINGAN NORMAL
            // =================================================

            textura =
                    SHARINGAN_TEXTURE;
        }


        // =====================================================
        // COMEÇA RENDER
        // =====================================================

        poseStack.pushPose();


        // =====================================================
        // PRENDE OS OLHOS NA CABEÇA
        //
        // ASSIM ELES ACOMPANHAM:
        //
        // - cabeça
        // - olhar para cima
        // - olhar para baixo
        // - esquerda
        // - direita
        // =====================================================

        this.getParentModel()
                .head
                .translateAndRotate(
                        poseStack
                );


        // =====================================================
        // BUFFER DA TEXTURA ESCOLHIDA
        // =====================================================

        VertexConsumer vertexConsumer =
                buffer.getBuffer(
                        RenderType.entityCutoutNoCull(
                                textura
                        )
                );


        // =====================================================
        // OVERLAY
        // =====================================================

        int overlay =
                LivingEntityRenderer.getOverlayCoords(
                        player,
                        0.0F
                );


        // =====================================================
        // RENDER MANGEKYOU
        // =====================================================

        if (mangekyouAtivo) {

            sharinganModel.renderMangekyou(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    overlay,
                    tipoMangekyou
            );

        } else {

            // =================================================
            // RENDER SHARINGAN NORMAL
            // =================================================

            sharinganModel.render(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    overlay
            );
        }


        // =====================================================
        // FINALIZA
        // =====================================================

        poseStack.popPose();
    }


    // =========================================================
    // REGISTRAR LAYER NO PLAYER
    // =========================================================

    @Mod.EventBusSubscriber(
            modid = "examplemod",
            value = Dist.CLIENT,
            bus = Mod.EventBusSubscriber.Bus.MOD
    )
    public static class Register {

        @SubscribeEvent
        public static void onAddLayers(
                EntityRenderersEvent.AddLayers event
        ) {

            // =================================================
            // PLAYER DEFAULT / STEVE
            // =================================================

            PlayerRenderer normal =
                    event.getSkin(
                            "default"
                    );

            if (normal != null) {

                normal.addLayer(
                        new DojutsuEyesLayer(
                                normal
                        )
                );
            }


            // =================================================
            // PLAYER SLIM / ALEX
            // =================================================

            PlayerRenderer slim =
                    event.getSkin(
                            "slim"
                    );

            if (slim != null) {

                slim.addLayer(
                        new DojutsuEyesLayer(
                                slim
                        )
                );
            }
        }
    }
}