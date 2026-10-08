package com.example.examplemod.client.olhos;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class NormalEyesModel {

    // =========================================================
    // RENDER
    // =========================================================

    public void render(
            PoseStack poseStack,
            VertexConsumer consumer,
            int light,
            int overlay
    ) {

        PoseStack.Pose pose =
                poseStack.last();

        Matrix4f matrix =
                pose.pose();

        Matrix3f normal =
                pose.normal();


        // =====================================================
        // CUBO 0 - OLHO / SOBRANCELHA ESQUERDA
        //
        // ALTURA AJUSTADA:
        // Y = -6.0 até -2.8
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                -4.1F, -6.0F, -4.2F,
                0.1F, -2.8F, -2.0F,

                // NORTH
                41.86667F, 16.26667F,
                27.0F, 27.53333F,

                // EAST
                50.0F, 51.0F,
                40.0F, 63.0F,

                // SOUTH
                36.0F, 36.0F,
                26.0F, 52.0F,

                // WEST
                49.0F, 38.0F,
                39.0F, 50.0F,

                // UP
                30.0F, 63.0F,
                38.0F, 56.0F,

                // DOWN
                0.0F, 29.0F,
                3.0F, 33.0F,

                light,
                overlay
        );


        // =====================================================
        // CUBO 2 - OLHO / SOBRANCELHA DIREITA
        //
        // MESMA ALTURA DO OUTRO OLHO
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                -0.1F, -6.0F, -4.2F,
                4.1F, -2.8F, -2.0F,

                // NORTH
                27.0F, 16.26667F,
                41.86667F, 27.53333F,

                // EAST
                39.0F, 38.0F,
                49.0F, 50.0F,

                // SOUTH
                26.0F, 36.0F,
                36.0F, 52.0F,

                // WEST
                40.0F, 51.0F,
                50.0F, 63.0F,

                // UP
                38.0F, 63.0F,
                30.0F, 56.0F,

                // DOWN
                3.0F, 29.0F,
                0.0F, 33.0F,

                light,
                overlay
        );


        // =====================================================
        // CUBO 3 - ÍRIS / PUPILA DIREITA
        //
        // SEM TOMOE
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                1.3F, -4.6F, -4.3F,
                2.5F, -3.4F, -2.1F,

                // NORTH
                40.33334F, 8.8F,
                48.8F, 15.8F,

                // EAST
                39.0F, 38.0F,
                49.0F, 50.0F,

                // SOUTH
                26.0F, 36.0F,
                36.0F, 52.0F,

                // WEST
                40.0F, 51.0F,
                50.0F, 63.0F,

                // UP
                38.0F, 63.0F,
                30.0F, 56.0F,

                // DOWN
                3.0F, 29.0F,
                0.0F, 33.0F,

                light,
                overlay
        );


        // =====================================================
        // CUBO 4 - ÍRIS / PUPILA ESQUERDA
        //
        // SEM TOMOE
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                -2.6F, -4.6F, -4.3F,
                -1.4F, -3.4F, -2.1F,

                // NORTH
                40.33334F, 8.8F,
                48.8F, 15.8F,

                // EAST
                39.0F, 38.0F,
                49.0F, 50.0F,

                // SOUTH
                26.0F, 36.0F,
                36.0F, 52.0F,

                // WEST
                40.0F, 51.0F,
                50.0F, 63.0F,

                // UP
                38.0F, 63.0F,
                30.0F, 56.0F,

                // DOWN
                3.0F, 29.0F,
                0.0F, 33.0F,

                light,
                overlay
        );
    }


    // =========================================================
    // RENDERIZA UM CUBO
    // =========================================================

    private void renderCube(
            Matrix4f matrix,
            Matrix3f normal,
            VertexConsumer consumer,

            float x1,
            float y1,
            float z1,

            float x2,
            float y2,
            float z2,

            float nU1, float nV1,
            float nU2, float nV2,

            float eU1, float eV1,
            float eU2, float eV2,

            float sU1, float sV1,
            float sU2, float sV2,

            float wU1, float wV1,
            float wU2, float wV2,

            float uU1, float uV1,
            float uU2, float uV2,

            float dU1, float dV1,
            float dU2, float dV2,

            int light,
            int overlay
    ) {

        float minX =
                x1 / 16.0F;

        float maxX =
                x2 / 16.0F;

        float minY =
                y1 / 16.0F;

        float maxY =
                y2 / 16.0F;

        float minZ =
                z1 / 16.0F;

        float maxZ =
                z2 / 16.0F;


        // =====================================================
        // NORTH
        // =====================================================

        renderFace(
                matrix,
                normal,
                consumer,

                minX, maxY, minZ,
                maxX, maxY, minZ,
                maxX, minY, minZ,
                minX, minY, minZ,

                nU1, nV1,
                nU2, nV2,

                0.0F,
                0.0F,
                -1.0F,

                light,
                overlay
        );


        // =====================================================
        // SOUTH
        // =====================================================

        renderFace(
                matrix,
                normal,
                consumer,

                maxX, maxY, maxZ,
                minX, maxY, maxZ,
                minX, minY, maxZ,
                maxX, minY, maxZ,

                sU1, sV1,
                sU2, sV2,

                0.0F,
                0.0F,
                1.0F,

                light,
                overlay
        );


        // =====================================================
        // EAST
        // =====================================================

        renderFace(
                matrix,
                normal,
                consumer,

                maxX, maxY, minZ,
                maxX, maxY, maxZ,
                maxX, minY, maxZ,
                maxX, minY, minZ,

                eU1, eV1,
                eU2, eV2,

                1.0F,
                0.0F,
                0.0F,

                light,
                overlay
        );


        // =====================================================
        // WEST
        // =====================================================

        renderFace(
                matrix,
                normal,
                consumer,

                minX, maxY, maxZ,
                minX, maxY, minZ,
                minX, minY, minZ,
                minX, minY, maxZ,

                wU1, wV1,
                wU2, wV2,

                -1.0F,
                0.0F,
                0.0F,

                light,
                overlay
        );


        // =====================================================
        // UP
        // =====================================================

        renderFace(
                matrix,
                normal,
                consumer,

                minX, maxY, maxZ,
                maxX, maxY, maxZ,
                maxX, maxY, minZ,
                minX, maxY, minZ,

                uU1, uV1,
                uU2, uV2,

                0.0F,
                1.0F,
                0.0F,

                light,
                overlay
        );


        // =====================================================
        // DOWN
        // =====================================================

        renderFace(
                matrix,
                normal,
                consumer,

                minX, minY, minZ,
                maxX, minY, minZ,
                maxX, minY, maxZ,
                minX, minY, maxZ,

                dU1, dV1,
                dU2, dV2,

                0.0F,
                -1.0F,
                0.0F,

                light,
                overlay
        );
    }


    // =========================================================
    // RENDERIZA UMA FACE
    // =========================================================

    private void renderFace(
            Matrix4f matrix,
            Matrix3f normal,
            VertexConsumer consumer,

            float x1,
            float y1,
            float z1,

            float x2,
            float y2,
            float z2,

            float x3,
            float y3,
            float z3,

            float x4,
            float y4,
            float z4,

            float u1,
            float v1,

            float u2,
            float v2,

            float nx,
            float ny,
            float nz,

            int light,
            int overlay
    ) {

        // =====================================================
        // BLOCKBENCH USA UV 64x64
        // =====================================================

        float minU =
                u1 / 64.0F;

        float minV =
                v1 / 64.0F;

        float maxU =
                u2 / 64.0F;

        float maxV =
                v2 / 64.0F;


        // =====================================================
        // ESPELHAMENTO HORIZONTAL
        //
        // EXATAMENTE O MESMO DO SHARINGAN.
        // NÃO ALTERAR ESSA ORDEM.
        // =====================================================

        consumer.vertex(
                        matrix,
                        x1,
                        y1,
                        z1
                )
                .color(
                        255,
                        255,
                        255,
                        255
                )
                .uv(
                        maxU,
                        maxV
                )
                .overlayCoords(
                        overlay
                )
                .uv2(
                        light
                )
                .normal(
                        normal,
                        nx,
                        ny,
                        nz
                )
                .endVertex();


        consumer.vertex(
                        matrix,
                        x2,
                        y2,
                        z2
                )
                .color(
                        255,
                        255,
                        255,
                        255
                )
                .uv(
                        minU,
                        maxV
                )
                .overlayCoords(
                        overlay
                )
                .uv2(
                        light
                )
                .normal(
                        normal,
                        nx,
                        ny,
                        nz
                )
                .endVertex();


        consumer.vertex(
                        matrix,
                        x3,
                        y3,
                        z3
                )
                .color(
                        255,
                        255,
                        255,
                        255
                )
                .uv(
                        minU,
                        minV
                )
                .overlayCoords(
                        overlay
                )
                .uv2(
                        light
                )
                .normal(
                        normal,
                        nx,
                        ny,
                        nz
                )
                .endVertex();


        consumer.vertex(
                        matrix,
                        x4,
                        y4,
                        z4
                )
                .color(
                        255,
                        255,
                        255,
                        255
                )
                .uv(
                        maxU,
                        minV
                )
                .overlayCoords(
                        overlay
                )
                .uv2(
                        light
                )
                .normal(
                        normal,
                        nx,
                        ny,
                        nz
                )
                .endVertex();
    }
}