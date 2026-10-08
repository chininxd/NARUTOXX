package com.example.examplemod.entity;

import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.entity.ZetsuBrancoEntity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ZetsuBrancoRenderer {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    "examplemod",
                    "textures/npcs/zetsu_branco.png"
            );

    @SubscribeEvent
    public static void registerRenderers(
            EntityRenderersEvent.RegisterRenderers event
    ) {

        event.registerEntityRenderer(
                ModEntities.ZETSU_BRANCO,
                ZetsuRenderer::new
        );
    }

    public static class ZetsuRenderer
            extends MobRenderer<
            ZetsuBrancoEntity,
            HumanoidModel<ZetsuBrancoEntity>
            > {

        public ZetsuRenderer(
                EntityRendererProvider.Context context
        ) {

            super(
                    context,
                    new HumanoidModel<>(
                            context.bakeLayer(
                                    ModelLayers.PLAYER
                            )
                    ),
                    0.5F
            );
        }

        @Override
        public ResourceLocation getTextureLocation(
                ZetsuBrancoEntity entity
        ) {
            return TEXTURE;
        }
    }
}