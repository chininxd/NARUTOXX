package com.example.examplemod.client.olhos;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SharinganEyesModel {

    // =========================================================
    // SHARINGAN NORMAL
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
        // CUBO 0 - OLHO ESQUERDO
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
        // CUBO 2 - OLHO DIREITO
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
        // CUBO 3 - PARTE VERMELHA DIREITA
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
        // CUBO 4 - PARTE VERMELHA ESQUERDA
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


        // =====================================================
        // CUBO 5 - TOMOE ESQUERDO
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                -2.1F, -3.8F, -4.4F,
                -1.9F, -3.6F, -2.2F,

                // NORTH
                52.6F, 2.4F,
                61.06667F, 9.4F,

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
        // CUBO 6 - TOMOE DIREITO
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                1.8F, -3.8F, -4.4F,
                2.0F, -3.6F, -2.2F,

                // NORTH
                52.6F, 2.4F,
                61.06667F, 9.4F,

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
        // CUBO 7 - TOMOE ESQUERDO
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                -2.3F, -4.2F, -4.4F,
                -2.1F, -4.0F, -2.2F,

                // NORTH
                52.6F, 2.4F,
                61.06667F, 9.4F,

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
        // CUBO 8 NÃO É RENDERIZADO
        // =====================================================


        // =====================================================
        // CUBO 9 - TOMOE ESQUERDO
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                -1.9F, -4.2F, -4.4F,
                -1.7F, -4.0F, -2.2F,

                // NORTH
                52.6F, 2.4F,
                61.06667F, 9.4F,

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
        // CUBO 10 - TOMOE DIREITO
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                2.0F, -4.2F, -4.4F,
                2.2F, -4.0F, -2.2F,

                // NORTH
                52.6F, 2.4F,
                61.06667F, 9.4F,

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
        // CUBO 11 - TOMOE DIREITO
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                1.5F, -4.2F, -4.4F,
                1.7F, -4.0F, -2.2F,

                // NORTH
                52.6F, 2.4F,
                61.06667F, 9.4F,

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
    // MANGEKYOU
    //
    // 1 = KAKASHI
    // 2 = SASUKE
    // 3 = MADARA
    // =========================================================

    public void renderMangekyou(
            PoseStack poseStack,
            VertexConsumer consumer,
            int light,
            int overlay,
            int tipoMangekyou
    ) {

        PoseStack.Pose pose =
                poseStack.last();

        Matrix4f matrix =
                pose.pose();

        Matrix3f normal =
                pose.normal();


        // =====================================================
        // TIPO INVÁLIDO
        // =====================================================

        if (
                tipoMangekyou < 1
                        || tipoMangekyou > 3
        ) {

            return;
        }


        // =====================================================
        // OLHO ESQUERDO
        //
        // IGUAL NOS 3 MODELOS.
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
                10.0F, 23.8F,
                0.0F, 35.8F,

                // SOUTH
                10.0F, 23.2F,
                0.0F, 39.2F,

                // WEST
                10.0F, 25.2F,
                0.0F, 37.2F,

                // UP
                0.0F, 31.53333F,
                8.0F, 24.53333F,

                // DOWN
                0.0F, 29.0F,
                3.0F, 33.0F,

                light,
                overlay
        );


        // =====================================================
        // OLHO DIREITO
        //
        // IGUAL NOS 3 MODELOS.
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
                1.6F, 26.26667F,
                11.6F, 38.26667F,

                // SOUTH
                0.0F, 24.26667F,
                5.73333F, 37.6F,

                // WEST
                0.0F, 24.33333F,
                10.0F, 31.53333F,

                // UP
                15.06667F, 33.66667F,
                7.06667F, 26.66667F,

                // DOWN
                3.0F, 29.0F,
                0.0F, 33.0F,

                light,
                overlay
        );


        // =====================================================
        // UV PUPILA ESQUERDA
        // =====================================================

        float esquerdaU1;
        float esquerdaV1;
        float esquerdaU2;
        float esquerdaV2;


        // =====================================================
        // UV PUPILA DIREITA
        // =====================================================

        float direitaU1;
        float direitaV1;
        float direitaU2;
        float direitaV2;


        // =====================================================
        // TIPO 1 = KAKASHI
        //
        // UV DO kakashi_mangkyo(2).bbmodel
        // =====================================================

        if (tipoMangekyou == 1) {

            // =================================================
            // ESQUERDO
            // =================================================

            esquerdaU1 =
                    20.6F;

            esquerdaV1 =
                    39.73333F;

            esquerdaU2 =
                    31.73334F;

            esquerdaV2 =
                    49.4F;


            // =================================================
            // DIREITO
            // =================================================

            direitaU1 =
                    21.66666F;

            direitaV1 =
                    40.26667F;

            direitaU2 =
                    32.8F;

            direitaV2 =
                    49.93334F;
        }


        // =====================================================
        // TIPO 2 = SASUKE
        // =====================================================

        else if (tipoMangekyou == 2) {

            // =================================================
            // ESQUERDO
            // =================================================

            esquerdaU1 =
                    3.53333F;

            esquerdaV1 =
                    39.73333F;

            esquerdaU2 =
                    14.66667F;

            esquerdaV2 =
                    49.4F;


            // =================================================
            // DIREITO
            // =================================================

            direitaU1 =
                    3.53333F;

            direitaV1 =
                    40.8F;

            direitaU2 =
                    14.66667F;

            direitaV2 =
                    50.46667F;
        }


        // =====================================================
        // TIPO 3 = MADARA
        // =====================================================

        else {

            // =================================================
            // ESQUERDO
            // =================================================

            esquerdaU1 =
                    27.53333F;

            esquerdaV1 =
                    51.46667F;

            esquerdaU2 =
                    38.66667F;

            esquerdaV2 =
                    61.13333F;


            // =================================================
            // DIREITO
            // =================================================

            direitaU1 =
                    27.53333F;

            direitaV1 =
                    51.46667F;

            direitaU2 =
                    38.66667F;

            direitaV2 =
                    61.13333F;
        }


        // =====================================================
        // MANGEKYOU ESQUERDO
        //
        // GEOMETRIA EXATA DO BBMODEL:
        //
        // from [-2.6, 27.5, -4.4]
        // to   [-1.4, 28.7, -2.2]
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                -2.6F, -4.6F, -4.4F,
                -1.4F, -3.4F, -2.2F,

                // NORTH
                esquerdaU1, esquerdaV1,
                esquerdaU2, esquerdaV2,

                // EAST
                0.06667F, 23.6F,
                10.06667F, 35.6F,

                // SOUTH
                0.0F, 23.2F,
                10.0F, 39.2F,

                // WEST
                0.53333F, 25.93333F,
                10.53333F, 37.93333F,

                // UP
                8.66667F, 35.26667F,
                0.66667F, 28.26667F,

                // DOWN
                3.0F, 29.0F,
                0.0F, 33.0F,

                light,
                overlay
        );


        // =====================================================
        // MANGEKYOU DIREITO
        //
        // GEOMETRIA EXATA DO BBMODEL:
        //
        // from [1.4, 27.5, -4.4]
        // to   [2.6, 28.7, -2.2]
        // =====================================================

        renderCube(
                matrix,
                normal,
                consumer,

                1.4F, -4.6F, -4.4F,
                2.6F, -3.4F, -2.2F,

                // NORTH
                direitaU1, direitaV1,
                direitaU2, direitaV2,

                // EAST
                0.06667F, 23.6F,
                10.06667F, 35.6F,

                // SOUTH
                0.0F, 23.2F,
                10.0F, 39.2F,

                // WEST
                0.53333F, 25.93333F,
                10.53333F, 37.93333F,

                // UP
                8.66667F, 35.26667F,
                0.66667F, 28.26667F,

                // DOWN
                3.0F, 29.0F,
                0.0F, 33.0F,

                light,
                overlay
        );
    }


    // =========================================================
    // RENDERIZA CUBO
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

            float nU1,
            float nV1,
            float nU2,
            float nV2,

            float eU1,
            float eV1,
            float eU2,
            float eV2,

            float sU1,
            float sV1,
            float sU2,
            float sV2,

            float wU1,
            float wV1,
            float wU2,
            float wV2,

            float uU1,
            float uV1,
            float uU2,
            float uV2,

            float dU1,
            float dV1,
            float dU2,
            float dV2,

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
        // UV DO BLOCKBENCH = 64x64
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
        // NÃO ALTERAR.
        // ISSO É O QUE DEIXA OS OLHOS NA ORIENTAÇÃO CERTA.
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