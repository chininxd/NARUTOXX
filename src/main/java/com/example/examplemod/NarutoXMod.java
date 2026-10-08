package com.example.examplemod;

import com.example.examplemod.Npcs.DemonBrothersRenderer;
import com.example.examplemod.Npcs.HakuRenderer;
import com.example.examplemod.Npcs.IrukaRenderer;
import com.example.examplemod.Npcs.KakashiRenderer;
import com.example.examplemod.Npcs.KonohamaruRenderer;
import com.example.examplemod.Npcs.MizukiRenderer;
import com.example.examplemod.Npcs.ModNPCs;
import com.example.examplemod.Npcs.RockLeeRenderer;
import com.example.examplemod.Npcs.ZabuzaRenderer;

import com.example.examplemod.NpcsVendedores.ModNPCsVendedores;
import com.example.examplemod.NpcsVendedores.OrochimaruVendedorRenderer;

import com.example.examplemod.network.ModNetwork;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.slf4j.Logger;


// ============================================================
// MOD
// ============================================================

@Mod(NarutoXMod.MODID)
public class NarutoXMod {

    // ========================================================
    // ID DO MOD
    // ========================================================

    public static final String MODID = "examplemod";


    // ========================================================
    // LOGGER
    // ========================================================

    private static final Logger LOGGER =
            LogUtils.getLogger();


    // ========================================================
    // REGISTRO DE BLOCOS
    // ========================================================

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(
                    ForgeRegistries.BLOCKS,
                    MODID
            );


    // ========================================================
    // REGISTRO DE ITENS
    // ========================================================

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(
                    ForgeRegistries.ITEMS,
                    MODID
            );


    // ========================================================
    // REGISTRO DE ABAS
    // ========================================================

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(
                    Registries.CREATIVE_MODE_TAB,
                    MODID
            );


    // ========================================================
    // BLOCO DE EXEMPLO
    // ========================================================

    public static final RegistryObject<Block> EXAMPLE_BLOCK =
            BLOCKS.register(
                    "example_block",
                    () -> new Block(
                            BlockBehaviour.Properties
                                    .of()
                                    .mapColor(MapColor.STONE)
                    )
            );


    // ========================================================
    // ITEM DO BLOCO
    // ========================================================

    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM =
            ITEMS.register(
                    "example_block",
                    () -> new BlockItem(
                            EXAMPLE_BLOCK.get(),
                            new Item.Properties()
                    )
            );


    // ========================================================
    // ESPADA DE KUSANAGI
    // ========================================================

    public static final RegistryObject<Item> Espada_de_Kusanagi =
            ITEMS.register(
                    "espada_de_kusanagi",
                    () -> new SwordItem(
                            Tiers.DIAMOND,
                            12,
                            -2.4f,
                            new Item.Properties()
                    )
            );


    // ========================================================
    // KUBIKIRIBOCHO
    // ========================================================

    public static final RegistryObject<Item> Kubikiribocho_Espada =
            ITEMS.register(
                    "kubikiribocho",
                    () -> new SwordItem(
                            Tiers.DIAMOND,
                            12,
                            -2.4f,
                            new Item.Properties()
                    )
            );


    // ========================================================
    // ABA NARUTOX
    // ========================================================

    public static final RegistryObject<CreativeModeTab> NarutoX =
            CREATIVE_MODE_TABS.register(
                    "narutox",
                    () -> CreativeModeTab.builder()

                            .title(
                                    Component.literal("NarutoX")
                            )

                            .withTabsBefore(
                                    CreativeModeTabs.COMBAT
                            )

                            .icon(
                                    () -> Espada_de_Kusanagi
                                            .get()
                                            .getDefaultInstance()
                            )

                            .displayItems(
                                    (parameters, output) -> {

                                        output.accept(
                                                Espada_de_Kusanagi.get()
                                        );

                                        output.accept(
                                                Kubikiribocho_Espada.get()
                                        );
                                    }
                            )

                            .build()
            );


    // ========================================================
    // CONSTRUTOR
    // ========================================================

    public NarutoXMod(
            FMLJavaModLoadingContext context
    ) {

        IEventBus modEventBus =
                context.getModEventBus();


        // ====================================================
        // COMMON SETUP
        // ====================================================

        modEventBus.addListener(
                this::commonSetup
        );


        // ====================================================
        // REGISTRA BLOCOS
        // ====================================================

        BLOCKS.register(
                modEventBus
        );


        // ====================================================
        // REGISTRA ITENS
        // ====================================================

        ITEMS.register(
                modEventBus
        );


        // ====================================================
        // REGISTRA ABAS
        // ====================================================

        CREATIVE_MODE_TABS.register(
                modEventBus
        );


        // ====================================================
        // REGISTRA NPCs
        // ====================================================

        ModNPCs.register(
                modEventBus
        );


        // ====================================================
        // REGISTRA NPCs VENDEDORES
        // ====================================================

        ModNPCsVendedores.register(
                modEventBus
        );


        // ====================================================
        // EVENTOS DO FORGE
        // ====================================================

        MinecraftForge.EVENT_BUS.register(
                this
        );


        // ====================================================
        // ITENS NAS ABAS
        // ====================================================

        modEventBus.addListener(
                this::addCreative
        );


        // ====================================================
        // CONFIG
        // ====================================================

        context.registerConfig(
                ModConfig.Type.COMMON,
                Config.SPEC
        );
    }


    // ========================================================
    // COMMON SETUP
    // ========================================================

    private void commonSetup(
            final FMLCommonSetupEvent event
    ) {

        ModNetwork.register(event);


        LOGGER.info(
                "HELLO FROM COMMON SETUP"
        );


        if (Config.logDirtBlock) {

            LOGGER.info(
                    "DIRT BLOCK >> {}",
                    ForgeRegistries.BLOCKS.getKey(
                            Blocks.DIRT
                    )
            );
        }


        LOGGER.info(
                Config.magicNumberIntroduction
                        + Config.magicNumber
        );


        Config.items.forEach(
                (item) ->
                        LOGGER.info(
                                "ITEM >> {}",
                                item.toString()
                        )
        );
    }


    // ========================================================
    // ADICIONAR ITENS À ABA CRIATIVO
    // ========================================================

    private void addCreative(
            BuildCreativeModeTabContentsEvent event
    ) {

        if (
                event.getTabKey()
                        == CreativeModeTabs.BUILDING_BLOCKS
        ) {

            event.accept(
                    EXAMPLE_BLOCK_ITEM
            );
        }
    }


    // ========================================================
    // SERVIDOR INICIANDO
    // ========================================================

    @SubscribeEvent
    public void onServerStarting(
            ServerStartingEvent event
    ) {

        LOGGER.info(
                "HELLO from server starting"
        );
    }


    // ========================================================
    // EVENTOS DO CLIENTE
    // ========================================================

    @Mod.EventBusSubscriber(
            modid = MODID,
            bus = Mod.EventBusSubscriber.Bus.MOD,
            value = Dist.CLIENT
    )
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(
                FMLClientSetupEvent event
        ) {

            // =================================================
            // RENDERER DO IRUKA
            // =================================================

            EntityRenderers.register(
                    ModNPCs.IRUKA.get(),
                    IrukaRenderer::new
            );


            // =================================================
            // RENDERER DO MIZUKI
            // =================================================

            EntityRenderers.register(
                    ModNPCs.MIZUKI.get(),
                    MizukiRenderer::new
            );


            // =================================================
            // RENDERER DO KONOHAMARU
            // =================================================

            EntityRenderers.register(
                    ModNPCs.KONOHAMARU.get(),
                    KonohamaruRenderer::new
            );


            // =================================================
            // RENDERER DO KAKASHI
            // =================================================

            EntityRenderers.register(
                    ModNPCs.KAKASHI.get(),
                    KakashiRenderer::new
            );


            // =================================================
            // RENDERER DOS IRMÃOS DEMÔNIOS
            // =================================================

            EntityRenderers.register(
                    ModNPCs.DEMON_BROTHERS.get(),
                    DemonBrothersRenderer::new
            );


            // =================================================
            // RENDERER DO ZABUZA
            // =================================================

            EntityRenderers.register(
                    ModNPCs.ZABUZA.get(),
                    ZabuzaRenderer::new
            );


            // =================================================
            // RENDERER DO HAKU
            // =================================================

            EntityRenderers.register(
                    ModNPCs.HAKU.get(),
                    HakuRenderer::new
            );


            // =================================================
            // RENDERER DO ROCK LEE
            // =================================================

            EntityRenderers.register(
                    ModNPCs.ROCK_LEE.get(),
                    RockLeeRenderer::new
            );


            // =================================================
            // RENDERER DO OROCHIMARU VENDEDOR
            // =================================================

            EntityRenderers.register(
                    ModNPCsVendedores.OROCHIMARU.get(),
                    OrochimaruVendedorRenderer::new
            );


            // =================================================
            // LOGS
            // =================================================

            LOGGER.info(
                    "HELLO FROM CLIENT SETUP"
            );


            LOGGER.info(
                    "MINECRAFT NAME >> {}",
                    Minecraft.getInstance()
                            .getUser()
                            .getName()
            );
        }
    }
}