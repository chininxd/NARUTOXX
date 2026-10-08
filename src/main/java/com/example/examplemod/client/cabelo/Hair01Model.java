package com.example.examplemod.client.cabelo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class Hair01Model implements HairModel {

    // =========================================================
    // RENDER
    // =========================================================

    @Override
    public void render(
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {

        // =====================================================
        // 01 - TOUCA PRINCIPAL
        // Blockbench:
        // [-4.5, 30, -4.5]
        // [ 4.5, 34.5, 4.5]
        // =====================================================

        renderCube(
                poseStack,
                vertexConsumer,

                -4.5F, -2.0F, -4.5F,
                4.5F,  2.5F,  4.5F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 02 - FRANJA CENTRO
        // =====================================================

        renderCube(
                poseStack,
                vertexConsumer,

                -2.0F, -7.0F, -5.2F,
                0.2F, -0.5F, -3.7F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 03 - FRANJA ESQUERDA
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                -4.2F, -6.0F, -5.0F,
                -2.0F,  0.0F, -3.5F,

                -3.1F, -3.0F, -4.25F,
                -8.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 04 - FRANJA DIREITA
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                0.2F, -6.0F, -5.0F,
                2.8F,  0.0F, -3.5F,

                1.5F, -3.0F, -4.25F,
                8.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 05 - MECHA ESQUERDA
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                -5.0F, -5.0F, -4.0F,
                -3.5F,  1.0F, -1.8F,

                -4.25F, -2.0F, -2.9F,
                -10.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 06 - MECHA DIREITA
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                3.5F, -5.0F, -4.0F,
                5.0F,  1.0F, -1.8F,

                4.25F, -2.0F, -2.9F,
                10.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 07 - ESPINHO TRÁS ALTO
        // =====================================================

        renderCube(
                poseStack,
                vertexConsumer,

                -2.5F, 0.5F, 2.5F,
                2.5F, 3.0F, 6.8F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 08 - ESPINHO TRÁS ESQUERDO
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                -5.0F, -0.5F, 1.0F,
                -2.0F,  2.0F, 6.5F,

                -3.5F, 0.75F, 3.75F,
                -18.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 09 - ESPINHO TRÁS DIREITO
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                2.0F, -0.5F, 1.0F,
                5.0F,  2.0F, 6.5F,

                3.5F, 0.75F, 3.75F,
                18.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 10 - ESPINHO LATERAL ESQUERDO
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                -6.0F, -1.5F, -1.0F,
                -3.8F,  1.5F,  3.0F,

                -4.9F, 0.0F, 1.0F,
                -22.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 11 - ESPINHO LATERAL DIREITO
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                3.8F, -1.5F, -1.0F,
                6.0F,  1.5F,  3.0F,

                4.9F, 0.0F, 1.0F,
                22.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 12 - ESPINHO INFERIOR ESQUERDO
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                -5.0F, -3.0F, 1.0F,
                -3.2F,  0.0F, 4.8F,

                -4.1F, -1.5F, 2.9F,
                -28.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // 13 - ESPINHO INFERIOR DIREITO
        // =====================================================

        renderCubeRotatedZ(
                poseStack,
                vertexConsumer,

                3.2F, -3.0F, 1.0F,
                5.0F,  0.0F, 4.8F,

                4.1F, -1.5F, 2.9F,
                28.0F,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );
    }


    // =========================================================
    // CUBO COM ROTAÇÃO NO EIXO Z
    // =========================================================

    private void renderCubeRotatedZ(
            PoseStack poseStack,
            VertexConsumer vertexConsumer,

            float x1,
            float y1,
            float z1,

            float x2,
            float y2,
            float z2,

            float pivotX,
            float pivotY,
            float pivotZ,

            float angle,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        poseStack.pushPose();

        // =====================================================
        // VAI ATÉ O PIVÔ
        // =====================================================

        poseStack.translate(
                pivotX / 16.0F,
                pivotY / 16.0F,
                pivotZ / 16.0F
        );


        // =====================================================
        // ROTAÇÃO
        // =====================================================

        poseStack.mulPose(
                com.mojang.math.Axis.ZP.rotationDegrees(
                        angle
                )
        );


        // =====================================================
        // VOLTA DO PIVÔ
        // =====================================================

        poseStack.translate(
                -pivotX / 16.0F,
                -pivotY / 16.0F,
                -pivotZ / 16.0F
        );


        // =====================================================
        // CUBO
        // =====================================================

        renderCube(
                poseStack,
                vertexConsumer,

                x1, y1, z1,
                x2, y2, z2,

                red, green, blue, alpha,

                packedLight,
                packedOverlay
        );

        poseStack.popPose();
    }


    // =========================================================
    // RENDERIZA UM CUBO
    // =========================================================

    private void renderCube(
            PoseStack poseStack,
            VertexConsumer vertexConsumer,

            float x1,
            float y1,
            float z1,

            float x2,
            float y2,
            float z2,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        // =====================================================
        // CONVERTE PIXELS DO BLOCKBENCH PARA UNIDADES MINECRAFT
        // =====================================================

        float minX = x1 / 16.0F;
        float minY = y1 / 16.0F;
        float minZ = z1 / 16.0F;

        float maxX = x2 / 16.0F;
        float maxY = y2 / 16.0F;
        float maxZ = z2 / 16.0F;


        // =====================================================
        // MATRIZES
        // =====================================================

        PoseStack.Pose pose =
                poseStack.last();

        Matrix4f matrix =
                pose.pose();

        Matrix3f normal =
                pose.normal();


        // =====================================================
        // FRENTE
        // =====================================================

        quad(
                vertexConsumer,
                matrix,
                normal,

                minX, minY, minZ,
                maxX, minY, minZ,
                maxX, maxY, minZ,
                minX, maxY, minZ,

                0, 0, -1,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // TRÁS
        // =====================================================

        quad(
                vertexConsumer,
                matrix,
                normal,

                maxX, minY, maxZ,
                minX, minY, maxZ,
                minX, maxY, maxZ,
                maxX, maxY, maxZ,

                0, 0, 1,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // ESQUERDA
        // =====================================================

        quad(
                vertexConsumer,
                matrix,
                normal,

                minX, minY, maxZ,
                minX, minY, minZ,
                minX, maxY, minZ,
                minX, maxY, maxZ,

                -1, 0, 0,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // DIREITA
        // =====================================================

        quad(
                vertexConsumer,
                matrix,
                normal,

                maxX, minY, minZ,
                maxX, minY, maxZ,
                maxX, maxY, maxZ,
                maxX, maxY, minZ,

                1, 0, 0,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // TOPO
        // =====================================================

        quad(
                vertexConsumer,
                matrix,
                normal,

                minX, maxY, minZ,
                maxX, maxY, minZ,
                maxX, maxY, maxZ,
                minX, maxY, maxZ,

                0, 1, 0,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );


        // =====================================================
        // BAIXO
        // =====================================================

        quad(
                vertexConsumer,
                matrix,
                normal,

                minX, minY, maxZ,
                maxX, minY, maxZ,
                maxX, minY, minZ,
                minX, minY, minZ,

                0, -1, 0,

                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );
    }


    // =========================================================
    // FACE
    // =========================================================

    private void quad(
            VertexConsumer vertexConsumer,
            Matrix4f matrix,
            Matrix3f normal,

            float x1, float y1, float z1,
            float x2, float y2, float z2,
            float x3, float y3, float z3,
            float x4, float y4, float z4,

            float normalX,
            float normalY,
            float normalZ,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        vertex(
                vertexConsumer,
                matrix,
                normal,
                x1, y1, z1,
                0.0F, 1.0F,
                normalX, normalY, normalZ,
                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );

        vertex(
                vertexConsumer,
                matrix,
                normal,
                x2, y2, z2,
                1.0F, 1.0F,
                normalX, normalY, normalZ,
                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );

        vertex(
                vertexConsumer,
                matrix,
                normal,
                x3, y3, z3,
                1.0F, 0.0F,
                normalX, normalY, normalZ,
                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );

        vertex(
                vertexConsumer,
                matrix,
                normal,
                x4, y4, z4,
                0.0F, 0.0F,
                normalX, normalY, normalZ,
                red, green, blue, alpha,
                packedLight,
                packedOverlay
        );
    }


    // =========================================================
    // VÉRTICE
    // =========================================================

    private void vertex(
            VertexConsumer vertexConsumer,
            Matrix4f matrix,
            Matrix3f normal,

            float x,
            float y,
            float z,

            float u,
            float v,

            float normalX,
            float normalY,
            float normalZ,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        vertexConsumer
                .vertex(
                        matrix,
                        x,
                        y,
                        z
                )
                .color(
                        red,
                        green,
                        blue,
                        alpha
                )
                .uv(
                        u,
                        v
                )
                .overlayCoords(
                        packedOverlay
                )
                .uv2(
                        packedLight
                )
                .normal(
                        normal,
                        normalX,
                        normalY,
                        normalZ
                )
                .endVertex();
    }
}