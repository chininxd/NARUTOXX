package com.example.examplemod.NpcsVendedores;

import com.example.examplemod.NarutoXMod;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(
        modid = NarutoXMod.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ModNPCsVendedores {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(
                    ForgeRegistries.ENTITY_TYPES,
                    NarutoXMod.MODID
            );


    // =========================================================
    // OROCHIMARU VENDEDOR
    // =========================================================

    public static final RegistryObject<EntityType<OrochimaruVendedorEntity>> OROCHIMARU =
            ENTITIES.register(
                    "orochimaru_vendedor",
                    () -> EntityType.Builder
                            .of(
                                    OrochimaruVendedorEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 1.8F)
                            .build("orochimaru_vendedor")
            );


    // =========================================================
    // REGISTRO
    // =========================================================

    public static void register(IEventBus eventBus) {

        ENTITIES.register(eventBus);
    }


    // =========================================================
    // ATRIBUTOS
    // =========================================================

    @SubscribeEvent
    public static void onEntityAttributeCreation(
            EntityAttributeCreationEvent event
    ) {

        event.put(
                OROCHIMARU.get(),
                OrochimaruVendedorEntity.createAttributes().build()
        );
    }
}