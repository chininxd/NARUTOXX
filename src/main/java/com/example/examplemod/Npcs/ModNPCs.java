package com.example.examplemod.Npcs;

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
public class ModNPCs {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(
                    ForgeRegistries.ENTITY_TYPES,
                    NarutoXMod.MODID
            );

    // =========================================================
    // IRUKA
    // =========================================================

    public static final RegistryObject<EntityType<IrukaEntity>> IRUKA =
            ENTITIES.register(
                    "iruka",
                    () -> EntityType.Builder
                            .of(
                                    IrukaEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 1.8F)
                            .build("iruka")
            );

    // =========================================================
    // MIZUKI
    // =========================================================

    public static final RegistryObject<EntityType<MizukiEntity>> MIZUKI =
            ENTITIES.register(
                    "mizuki",
                    () -> EntityType.Builder
                            .of(
                                    MizukiEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 1.8F)
                            .build("mizuki")
            );

    // =========================================================
    // KONOHAMARU
    // =========================================================

    public static final RegistryObject<EntityType<KonohamaruEntity>> KONOHAMARU =
            ENTITIES.register(
                    "konohamaru",
                    () -> EntityType.Builder
                            .of(
                                    KonohamaruEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 1.4F)
                            .build("konohamaru")
            );

    // =========================================================
    // KAKASHI
    // =========================================================

    public static final RegistryObject<EntityType<KakashiEntity>> KAKASHI =
            ENTITIES.register(
                    "kakashi",
                    () -> EntityType.Builder
                            .of(
                                    KakashiEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 1.8F)
                            .build("kakashi")
            );

    // =========================================================
    // IRMÃOS DEMÔNIOS
    // =========================================================

    public static final RegistryObject<EntityType<DemonBrothersEntity>> DEMON_BROTHERS =
            ENTITIES.register(
                    "demon_brothers",
                    () -> EntityType.Builder
                            .of(
                                    DemonBrothersEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.7F, 1.8F)
                            .build("demon_brothers")
            );

    // =========================================================
    // ZABUZA
    // =========================================================

    public static final RegistryObject<EntityType<ZabuzaEntity>> ZABUZA =
            ENTITIES.register(
                    "zabuza",
                    () -> EntityType.Builder
                            .of(
                                    ZabuzaEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.7F, 2.0F)
                            .build("zabuza")
            );

    // =========================================================
    // HAKU
    // =========================================================

    public static final RegistryObject<EntityType<HakuEntity>> HAKU =
            ENTITIES.register(
                    "haku",
                    () -> EntityType.Builder
                            .of(
                                    HakuEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 1.8F)
                            .build("haku")
            );

    // =========================================================
    // ROCK LEE
    // =========================================================

    public static final RegistryObject<EntityType<RockLeeEntity>> ROCK_LEE =
            ENTITIES.register(
                    "rock_lee",
                    () -> EntityType.Builder
                            .of(
                                    RockLeeEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 1.8F)
                            .build("rock_lee")
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
                IRUKA.get(),
                IrukaEntity.createAttributes().build()
        );

        event.put(
                MIZUKI.get(),
                MizukiEntity.createAttributes().build()
        );

        event.put(
                KONOHAMARU.get(),
                KonohamaruEntity.createAttributes().build()
        );

        event.put(
                KAKASHI.get(),
                KakashiEntity.createAttributes().build()
        );

        event.put(
                DEMON_BROTHERS.get(),
                DemonBrothersEntity.createAttributes().build()
        );

        event.put(
                ZABUZA.get(),
                ZabuzaEntity.createAttributes().build()
        );

        event.put(
                HAKU.get(),
                HakuEntity.createAttributes().build()
        );

        event.put(
                ROCK_LEE.get(),
                RockLeeEntity.createAttributes().build()
        );
    }
}