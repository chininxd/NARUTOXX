package com.example.examplemod.client.cabelo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class Hair03Model implements HairModel {

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
        // 01 - CROWN
        // =====================================================

        cube(
                poseStack, vertexConsumer,

                -3.8F, -0.9F, -3.8F,
                3.8F,  1.2F,  3.8F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 02 - LEFT CAP
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -4.7F, -3.0F, -3.4F,
                -2.7F,  0.5F,  3.7F,

                -3.5F, -1.0F, 0.0F,

                0.0F, 0.0F, -8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 03 - RIGHT CAP
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                2.7F, -3.0F, -3.4F,
                4.7F,  0.5F,  3.7F,

                3.5F, -1.0F, 0.0F,

                0.0F, 0.0F, 8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 04 - PART LEFT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -1.5F, 0.0F, -4.3F,
                -0.05F, 3.0F, 2.4F,

                -0.5F, 0.2F, -1.0F,

                0.0F, 0.0F, -8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 05 - PART RIGHT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                0.05F, 0.0F, -4.3F,
                1.5F,  3.0F,  2.4F,

                0.5F, 0.2F, -1.0F,

                0.0F, 0.0F, 8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 06 - TOP LEFT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -3.5F, -0.3F, -1.8F,
                -1.1F,  2.7F,  3.7F,

                -2.0F, 0.0F, 1.0F,

                5.0F, 0.0F, -12.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 07 - TOP RIGHT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                1.1F, -0.3F, -1.8F,
                3.5F,  2.7F,  3.7F,

                2.0F, 0.0F, 1.0F,

                5.0F, 0.0F, 12.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 08 - BANG LEFT INNER
        // ATUALIZADO PELO BBMODEL
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -1.9F, -2.3F, -5.0F,
                -0.15F, 0.5F, -3.65F,

                -0.7F, -1.0F, -4.0F,

                0.0F, 0.0F, -8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 09 - BANG RIGHT INNER
        // ATUALIZADO PELO BBMODEL
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                0.15F, -2.3F, -5.0F,
                1.9F,   0.5F, -3.65F,

                0.7F, -1.0F, -4.0F,

                0.0F, 0.0F, 8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 10 - BANG LEFT MID
        // ATUALIZADO PELO BBMODEL
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -3.15F, -4.4F, -4.9F,
                -1.55F,  0.1F, -3.55F,

                -2.1F, -1.5F, -4.0F,

                0.0F, 0.0F, -16.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 11 - BANG RIGHT MID
        // ATUALIZADO PELO BBMODEL
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                1.55F, -3.4F, -4.9F,
                3.15F,  0.1F, -3.55F,

                2.1F, -1.5F, -4.0F,

                0.0F, 0.0F, 16.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 12 - BANG LEFT OUTER
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -4.25F, -4.5F, -4.5F,
                -2.8F,  -0.2F, -3.35F,

                -3.2F, -2.0F, -3.8F,

                0.0F, 0.0F, -24.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 13 - BANG RIGHT OUTER
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                2.8F,  -4.5F, -4.5F,
                4.25F, -0.2F, -3.35F,

                3.2F, -2.0F, -3.8F,

                0.0F, 0.0F, 24.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 14 - SIDE LEFT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -5.0F, -6.2F, -3.2F,
                -3.65F, -0.8F, 3.8F,

                -4.0F, -3.0F, 0.0F,

                0.0F, 0.0F, -8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 15 - SIDE RIGHT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                3.65F, -6.2F, -3.2F,
                5.0F,  -0.8F,  3.8F,

                4.0F, -3.0F, 0.0F,

                0.0F, 0.0F, 8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 16 - BACK LOCK 1
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -4.0F, -6.2F, 3.55F,
                -2.4F, -0.3F, 5.15F,

                -3.2F, -2.0F, 4.0F,

                0.0F, 0.0F, -10.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 17 - BACK LOCK 2
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -2.7F, -6.2F, 3.55F,
                -1.1F, -0.3F, 5.15F,

                -1.9F, -2.0F, 4.0F,

                0.0F, 0.0F, -6.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 18 - BACK LOCK 3
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -1.4F, -6.2F, 3.55F,
                0.0F, -0.3F, 5.15F,

                -0.7F, -2.0F, 4.0F,

                0.0F, 0.0F, -2.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 19 - BACK LOCK 4
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                0.0F, -6.2F, 3.55F,
                1.4F, -0.3F, 5.15F,

                0.7F, -2.0F, 4.0F,

                0.0F, 0.0F, 2.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 20 - BACK LOCK 5
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                1.1F, -6.2F, 3.55F,
                2.7F, -0.3F, 5.15F,

                1.9F, -2.0F, 4.0F,

                0.0F, 0.0F, 6.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 21 - BACK LOCK 6
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                2.4F, -6.2F, 3.55F,
                4.0F, -0.3F, 5.15F,

                3.2F, -2.0F, 4.0F,

                0.0F, 0.0F, 10.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 22 - BACK TIP LEFT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -3.8F, -7.1F, 3.9F,
                -1.8F, -3.7F, 5.5F,

                -2.6F, -5.0F, 4.0F,

                -12.0F, 0.0F, -8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 23 - BACK TIP CENTER
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                -1.5F, -7.4F, 4.0F,
                1.5F, -4.0F, 5.7F,

                0.0F, -5.0F, 4.0F,

                -14.0F, 0.0F, 0.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // =====================================================
        // 24 - BACK TIP RIGHT
        // =====================================================

        cubeRotated(
                poseStack, vertexConsumer,

                1.8F, -7.1F, 3.9F,
                3.8F, -3.7F, 5.5F,

                2.6F, -5.0F, 4.0F,

                -12.0F, 0.0F, 8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );
    }


    // =========================================================
    // CUBO COM ROTAÇÃO
    // =========================================================

    private void cubeRotated(
            PoseStack poseStack,
            VertexConsumer consumer,

            float x1, float y1, float z1,
            float x2, float y2, float z2,

            float pivotX,
            float pivotY,
            float pivotZ,

            float rotX,
            float rotY,
            float rotZ,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        poseStack.pushPose();

        poseStack.translate(
                pivotX / 16.0F,
                pivotY / 16.0F,
                pivotZ / 16.0F
        );

        if (rotX != 0.0F) {
            poseStack.mulPose(
                    Axis.XP.rotationDegrees(rotX)
            );
        }

        if (rotY != 0.0F) {
            poseStack.mulPose(
                    Axis.YP.rotationDegrees(rotY)
            );
        }

        if (rotZ != 0.0F) {
            poseStack.mulPose(
                    Axis.ZP.rotationDegrees(rotZ)
            );
        }

        poseStack.translate(
                -pivotX / 16.0F,
                -pivotY / 16.0F,
                -pivotZ / 16.0F
        );

        cube(
                poseStack,
                consumer,

                x1, y1, z1,
                x2, y2, z2,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        poseStack.popPose();
    }


    // =========================================================
    // CUBO
    // =========================================================

    private void cube(
            PoseStack poseStack,
            VertexConsumer consumer,

            float x1, float y1, float z1,
            float x2, float y2, float z2,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        float minX = x1 / 16.0F;
        float minY = y1 / 16.0F;
        float minZ = z1 / 16.0F;

        float maxX = x2 / 16.0F;
        float maxY = y2 / 16.0F;
        float maxZ = z2 / 16.0F;

        PoseStack.Pose pose =
                poseStack.last();

        Matrix4f matrix =
                pose.pose();

        Matrix3f normal =
                pose.normal();


        // FRENTE
        quad(
                consumer, matrix, normal,

                minX, minY, minZ,
                maxX, minY, minZ,
                maxX, maxY, minZ,
                minX, maxY, minZ,

                0, 0, -1,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // TRÁS
        quad(
                consumer, matrix, normal,

                maxX, minY, maxZ,
                minX, minY, maxZ,
                minX, maxY, maxZ,
                maxX, maxY, maxZ,

                0, 0, 1,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // ESQUERDA
        quad(
                consumer, matrix, normal,

                minX, minY, maxZ,
                minX, minY, minZ,
                minX, maxY, minZ,
                minX, maxY, maxZ,

                -1, 0, 0,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // DIREITA
        quad(
                consumer, matrix, normal,

                maxX, minY, minZ,
                maxX, minY, maxZ,
                maxX, maxY, maxZ,
                maxX, maxY, minZ,

                1, 0, 0,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // TOPO
        quad(
                consumer, matrix, normal,

                minX, maxY, minZ,
                maxX, maxY, minZ,
                maxX, maxY, maxZ,
                minX, maxY, maxZ,

                0, 1, 0,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );


        // BAIXO
        quad(
                consumer, matrix, normal,

                minX, minY, maxZ,
                maxX, minY, maxZ,
                maxX, minY, minZ,
                minX, minY, minZ,

                0, -1, 0,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );
    }


    // =========================================================
    // FACE
    // =========================================================

    private void quad(
            VertexConsumer consumer,
            Matrix4f matrix,
            Matrix3f normal,

            float x1, float y1, float z1,
            float x2, float y2, float z2,
            float x3, float y3, float z3,
            float x4, float y4, float z4,

            float nx,
            float ny,
            float nz,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        vertex(
                consumer, matrix, normal,
                x1, y1, z1,
                0.0F, 1.0F,
                nx, ny, nz,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        vertex(
                consumer, matrix, normal,
                x2, y2, z2,
                1.0F, 1.0F,
                nx, ny, nz,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        vertex(
                consumer, matrix, normal,
                x3, y3, z3,
                1.0F, 0.0F,
                nx, ny, nz,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        vertex(
                consumer, matrix, normal,
                x4, y4, z4,
                0.0F, 0.0F,
                nx, ny, nz,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );
    }


    // =========================================================
    // VÉRTICE
    // =========================================================

    private void vertex(
            VertexConsumer consumer,
            Matrix4f matrix,
            Matrix3f normal,

            float x,
            float y,
            float z,

            float u,
            float v,

            float nx,
            float ny,
            float nz,

            float red,
            float green,
            float blue,
            float alpha,

            int packedLight,
            int packedOverlay
    ) {

        consumer
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
                        nx,
                        ny,
                        nz
                )
                .endVertex();
    }
}