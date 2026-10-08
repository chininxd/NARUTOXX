package com.example.examplemod.client.cabelo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class Hair02Model implements HairModel {

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

        // 1 - base_topo
        cube(
                poseStack, vertexConsumer,
                -4.35F, -0.8F, -4.35F,
                4.35F,  1.0F,  4.35F,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 2 - base_tras
        cube(
                poseStack, vertexConsumer,
                -4.35F, -7.0F, 3.65F,
                4.35F,  0.0F, 5.0F,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 3 - lateral_esquerda
        cube(
                poseStack, vertexConsumer,
                -5.0F, -6.0F, -3.3F,
                -3.65F, 0.0F, 4.1F,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 4 - lateral_direita
        cube(
                poseStack, vertexConsumer,
                3.65F, -6.0F, -3.3F,
                5.0F,   0.0F, 4.1F,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 5 - franja_centro
        cubeRotated(
                poseStack, vertexConsumer,
                -1.2F, -4.0F, -5.0F,
                1.1F,  1.0F, -3.7F,

                0.0F, -1.0F, -4.2F,

                0.0F, 0.0F, 8.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 6 - franja_esquerda
        cubeRotated(
                poseStack, vertexConsumer,
                -3.4F, -3.8F, -4.9F,
                -1.0F,  1.2F, -3.65F,

                -2.0F, -1.0F, -4.1F,

                0.0F, 0.0F, -18.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 7 - franja_direita
        cubeRotated(
                poseStack, vertexConsumer,
                1.0F, -3.7F, -4.9F,
                3.4F,  1.1F, -3.65F,

                2.0F, -1.0F, -4.1F,

                0.0F, 0.0F, 18.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 8 - franja_ponta_esq
        cubeRotated(
                poseStack, vertexConsumer,
                -4.4F, -4.5F, -4.6F,
                -2.7F,  0.3F, -3.5F,

                -3.3F, -1.5F, -4.0F,

                0.0F, 0.0F, -28.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 9 - franja_ponta_dir
        cubeRotated(
                poseStack, vertexConsumer,
                2.7F, -4.5F, -4.6F,
                4.4F,  0.3F, -3.5F,

                3.3F, -1.5F, -4.0F,

                0.0F, 0.0F, 28.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 10 - espinho_topo_centro
        cubeRotated(
                poseStack, vertexConsumer,
                -1.25F, 0.0F, -1.5F,
                1.25F, 5.0F,  1.3F,

                0.0F, 0.3F, 0.0F,

                12.0F, 0.0F, 0.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 11 - espinho_topo_esq
        cubeRotated(
                poseStack, vertexConsumer,
                -3.6F, -0.2F, -1.0F,
                -1.0F,  4.3F,  1.6F,

                -2.0F, 0.2F, 0.2F,

                8.0F, 0.0F, -22.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 12 - espinho_topo_dir
        cubeRotated(
                poseStack, vertexConsumer,
                1.0F, -0.2F, -1.0F,
                3.6F,  4.3F,  1.6F,

                2.0F, 0.2F, 0.2F,

                8.0F, 0.0F, 22.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 13 - espinho_tras_esq
        cubeRotated(
                poseStack, vertexConsumer,
                -3.8F, -2.5F, 3.5F,
                -1.1F,  2.3F, 6.5F,

                -2.2F, -0.5F, 4.0F,

                -20.0F, 0.0F, -12.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 14 - espinho_tras_dir
        cubeRotated(
                poseStack, vertexConsumer,
                1.1F, -2.5F, 3.5F,
                3.8F,  2.3F, 6.5F,

                2.2F, -0.5F, 4.0F,

                -20.0F, 0.0F, 12.0F,

                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        // 15 - espinho_tras_centro
        cubeRotated(
                poseStack, vertexConsumer,
                -1.4F, -2.0F, 3.7F,
                1.4F,  3.0F, 6.8F,

                0.0F, -0.2F, 4.1F,

                -24.0F, 0.0F, 0.0F,

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
                packedLight,
                packedOverlay
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

        PoseStack.Pose pose = poseStack.last();

        Matrix4f matrix = pose.pose();
        Matrix3f normal = pose.normal();

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
                0, 1,
                nx, ny, nz,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        vertex(
                consumer, matrix, normal,
                x2, y2, z2,
                1, 1,
                nx, ny, nz,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        vertex(
                consumer, matrix, normal,
                x3, y3, z3,
                1, 0,
                nx, ny, nz,
                red, green, blue, alpha,
                packedLight, packedOverlay
        );

        vertex(
                consumer, matrix, normal,
                x4, y4, z4,
                0, 0,
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
                .vertex(matrix, x, y, z)
                .color(red, green, blue, alpha)
                .uv(u, v)
                .overlayCoords(packedOverlay)
                .uv2(packedLight)
                .normal(normal, nx, ny, nz)
                .endVertex();
    }
}