package com.example.examplemod.NpcsVendedores;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OrochimaruVendedorRenderer
        extends HumanoidMobRenderer<
        OrochimaruVendedorEntity,
        HumanoidModel<OrochimaruVendedorEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/npcs_vendedores/orochimaru_vendedor.png"
            );

    public OrochimaruVendedorRenderer(
            EntityRendererProvider.Context context
    ) {
        super(
                context,
                new HumanoidModel<>(
                        context.bakeLayer(ModelLayers.PLAYER)
                ),
                0.5F
        );
    }

    @Override
    public ResourceLocation getTextureLocation(
            OrochimaruVendedorEntity entity
    ) {
        return TEXTURE;
    }
}
