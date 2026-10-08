package com.example.examplemod.Npcs;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HakuRenderer
        extends MobRenderer<HakuEntity, PlayerModel<HakuEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/npcs_mission/haku_1.png"
            );

    public HakuRenderer(EntityRendererProvider.Context context) {
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
    public ResourceLocation getTextureLocation(HakuEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(
            HakuEntity entity,
            PoseStack poseStack,
            float partialTick
    ) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
    }
}