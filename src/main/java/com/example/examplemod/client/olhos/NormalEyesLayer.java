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
import net.minecraft.resources.ResourceLocation;

public class NormalEyesLayer
        extends RenderLayer<
        AbstractClientPlayer,
        PlayerModel<AbstractClientPlayer>
        > {

    // =========================================================
    // TEXTURAS DOS OLHOS NORMAIS
    // =========================================================

    private static final ResourceLocation OLHOS_AMARELOS =
            new ResourceLocation(
                    "examplemod",
                    "textures/olhos/olhos_amarelos.png"
            );

    private static final ResourceLocation OLHOS_BRANCOS =
            new ResourceLocation(
                    "examplemod",
                    "textures/olhos/olhos_brancos.png"
            );

    private static final ResourceLocation OLHOS_PRETOS =
            new ResourceLocation(
                    "examplemod",
                    "textures/olhos/olhos_pretos.png"
            );

    private static final ResourceLocation OLHOS_AZUL =
            new ResourceLocation(
                    "examplemod",
                    "textures/olhos/olho_azul.png"
            );

    private static final ResourceLocation OLHOS_ROXO =
            new ResourceLocation(
                    "examplemod",
                    "textures/olhos/olho_roxo.png"
            );

    private static final ResourceLocation OLHOS_VERDE =
            new ResourceLocation(
                    "examplemod",
                    "textures/olhos/olho_verde.png"
            );


    // =========================================================
    // TEXTURA DO BYAKUGAN
    // =========================================================

    private static final ResourceLocation BYAKUGAN =
            new ResourceLocation(
                    "examplemod",
                    "textures/olhos/byakugan.png"
            );


    // =========================================================
    // MODELO
    // =========================================================

    private final NormalEyesModel normalEyesModel =
            new NormalEyesModel();


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public NormalEyesLayer(
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
        // STATUS
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        if (stats == null) {
            return;
        }


        // =====================================================
        // SHARINGAN / MANGEKYOU
        //
        // OS DOIS POSSUEM A PRÓPRIA RENDER LAYER.
        //
        // SE UM DELES ESTIVER ATIVO,
        // NÃO DESENHA O OLHO NORMAL.
        // =====================================================

        if (
                stats.isSharinganAtivo()
                        || stats.isMangekyouAtivo()
        ) {

            return;
        }


        // =====================================================
        // ESCOLHE A TEXTURA
        // =====================================================

        ResourceLocation textura;


        // =====================================================
        // BYAKUGAN
        //
        // BYAKUGAN NORMAL E DESPERTADO
        // USAM A MESMA TEXTURA.
        // =====================================================

        if (
                stats.isByakuganAtivo()
                        || stats.isByakuganDespertadoAtivo()
        ) {

            textura =
                    BYAKUGAN;

        } else {

            // =================================================
            // OLHOS NORMAIS DA PERSONALIZAÇÃO
            // =================================================

            int corOlhos =
                    stats.getCorOlhos();

            switch (corOlhos) {

                case 1:
                    textura =
                            OLHOS_AMARELOS;
                    break;

                case 2:
                    textura =
                            OLHOS_BRANCOS;
                    break;

                case 3:
                    textura =
                            OLHOS_PRETOS;
                    break;

                case 4:
                    textura =
                            OLHOS_AZUL;
                    break;

                case 5:
                    textura =
                            OLHOS_ROXO;
                    break;

                case 6:
                    textura =
                            OLHOS_VERDE;
                    break;

                default:
                    textura =
                            OLHOS_AMARELOS;
                    break;
            }
        }


        // =====================================================
        // PRENDE O MODELO NA CABEÇA
        // =====================================================

        poseStack.pushPose();

        this.getParentModel()
                .head
                .translateAndRotate(
                        poseStack
                );


        // =====================================================
        // BUFFER
        // =====================================================

        VertexConsumer vertexConsumer =
                buffer.getBuffer(
                        RenderType.entityCutoutNoCull(
                                textura
                        )
                );


        // =====================================================
        // RENDER
        // =====================================================

        normalEyesModel.render(
                poseStack,
                vertexConsumer,
                packedLight,
                LivingEntityRenderer.getOverlayCoords(
                        player,
                        0.0F
                )
        );


        // =====================================================
        // FINALIZA
        // =====================================================

        poseStack.popPose();
    }
}