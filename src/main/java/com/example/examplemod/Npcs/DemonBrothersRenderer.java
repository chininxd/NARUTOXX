package com.example.examplemod.Npcs;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DemonBrothersRenderer
        extends MobRenderer<DemonBrothersEntity, PlayerModel<DemonBrothersEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/npcs_mission/irmao1.png"
            );

    public DemonBrothersRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new PlayerModel<>(
                        context.bakeLayer(ModelLayers.PLAYER),
                        false
                ),
                0.5F
        );
    }

    @Override
    public ResourceLocation getTextureLocation(DemonBrothersEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(
            DemonBrothersEntity entity,
            PoseStack poseStack,
            float partialTick
    ) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
    }
}