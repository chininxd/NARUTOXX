package com.example.examplemod.Npcs;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KakashiRenderer
        extends MobRenderer<KakashiEntity, PlayerModel<KakashiEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/npcs_mission/kakashi_1.png"
            );

    public KakashiRenderer(EntityRendererProvider.Context context) {
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
    public ResourceLocation getTextureLocation(KakashiEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(
            KakashiEntity entity,
            PoseStack poseStack,
            float partialTick
    ) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
    }
}
