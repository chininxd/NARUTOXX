package com.example.examplemod.Npcs;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RockLeeRenderer extends HumanoidMobRenderer<RockLeeEntity, HumanoidModel<RockLeeEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/npcs_mission/rock_lee_1.png"
            );

    public RockLeeRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new HumanoidModel<>(
                        context.bakeLayer(ModelLayers.PLAYER)
                ),
                0.5F
        );
    }

    @Override
    public ResourceLocation getTextureLocation(RockLeeEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(
            RockLeeEntity entity,
            PoseStack poseStack,
            float partialTick
    ) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
    }
}

