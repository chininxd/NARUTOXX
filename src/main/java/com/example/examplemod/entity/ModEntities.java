package com.example.examplemod.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Zombie;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

public class ModEntities {

    public static EntityType<ZetsuBrancoEntity> ZETSU_BRANCO;

    @Mod.EventBusSubscriber(
            modid = "examplemod",
            bus = Mod.EventBusSubscriber.Bus.MOD
    )
    public static class Registration {

        @SubscribeEvent
        public static void registerEntities(RegisterEvent event) {

            if (event.getRegistryKey() != Registries.ENTITY_TYPE) {
                return;
            }

            ZETSU_BRANCO = EntityType.Builder
                    .of(
                            ZetsuBrancoEntity::new,
                            MobCategory.CREATURE
                    )
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8)
                    .build("zetsu_branco");

            event.getForgeRegistry().register(
                    "zetsu_branco",
                    ZETSU_BRANCO
            );
        }

        @SubscribeEvent
        public static void registerAttributes(
                EntityAttributeCreationEvent event
        ) {

            event.put(
                    ZETSU_BRANCO,
                    Zombie.createAttributes().build()
            );
        }
    }
}