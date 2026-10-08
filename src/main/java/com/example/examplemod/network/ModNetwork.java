package com.example.examplemod.network;

import com.example.examplemod.NarutoXMod;

import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {

    // =========================================================
    // VERSÃO DO PROTOCOLO
    // =========================================================

    private static final String PROTOCOL_VERSION = "1";

    // =========================================================
    // CANAL
    // =========================================================

    public static final SimpleChannel CHANNEL =
            NetworkRegistry.newSimpleChannel(
                    new ResourceLocation(
                            NarutoXMod.MODID,
                            "main"
                    ),
                    () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals,
                    PROTOCOL_VERSION::equals
            );

    // =========================================================
    // ID DOS PACOTES
    // =========================================================

    private static int packetId = 0;

    // =========================================================
    // REGISTRAR PACOTES
    // =========================================================

    public static void register(
            FMLCommonSetupEvent event
    ) {

        event.enqueueWork(() -> {

            // =================================================
            // SPEND STAT
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    SpendStatPacket.class,
                    SpendStatPacket::encode,
                    SpendStatPacket::decode,
                    SpendStatPacket::handle
            );

            // =================================================
            // DAMAGE INDICATOR
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    DamageIndicatorPacket.class,
                    DamageIndicatorPacket::encode,
                    DamageIndicatorPacket::decode,
                    DamageIndicatorPacket::handle
            );

            // =================================================
            // ESCOLHER CLÃ
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    ChooseClanPacket.class,
                    ChooseClanPacket::encode,
                    ChooseClanPacket::decode,
                    ChooseClanPacket::handle
            );

            // =================================================
            // ESCOLHER PERSONALIZAÇÃO
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    ChooseCustomizationPacket.class,
                    ChooseCustomizationPacket::encode,
                    ChooseCustomizationPacket::decode,
                    ChooseCustomizationPacket::handle
            );

            // =================================================
            // SINCRONIZAR PERSONALIZAÇÃO
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    CustomizationStatePacket.class,
                    CustomizationStatePacket::encode,
                    CustomizationStatePacket::decode,
                    CustomizationStatePacket::handle
            );

            // =================================================
            // ATIVAR / DESATIVAR DOJUTSU
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    ToggleDojutsuPacket.class,
                    ToggleDojutsuPacket::encode,
                    ToggleDojutsuPacket::decode,
                    ToggleDojutsuPacket::handle
            );

            // =================================================
            // SINCRONIZAR DOJUTSU
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    DojutsuStatePacket.class,
                    DojutsuStatePacket::encode,
                    DojutsuStatePacket::decode,
                    DojutsuStatePacket::handle
            );

            // =================================================
            // COMPRAR DOJUTSU
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    BuyDojutsuPacket.class,
                    BuyDojutsuPacket::encode,
                    BuyDojutsuPacket::decode,
                    BuyDojutsuPacket::handle
            );

            // =================================================
            // INICIAR MISSÃO
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    StartMissionPacket.class,
                    StartMissionPacket::encode,
                    StartMissionPacket::decode,
                    StartMissionPacket::handle
            );

            // =================================================
            // SINCRONIZAR MISSÃO
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    MissionSyncPacket.class,
                    MissionSyncPacket::encode,
                    MissionSyncPacket::decode,
                    MissionSyncPacket::handle
            );

            // =================================================
            // PEGAR RECOMPENSA DA MISSÃO
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    ClaimMissionRewardPacket.class,
                    ClaimMissionRewardPacket::encode,
                    ClaimMissionRewardPacket::decode,
                    ClaimMissionRewardPacket::handle
            );

            // =================================================
            // ATIVAR / DESATIVAR TÉCNICA
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    ToggleTechniquePacket.class,
                    ToggleTechniquePacket::encode,
                    ToggleTechniquePacket::decode,
                    ToggleTechniquePacket::handle
            );

            // =================================================
            // COMPRAR / APRENDER TÉCNICA
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    BuyTechniquePacket.class,
                    BuyTechniquePacket::encode,
                    BuyTechniquePacket::decode,
                    BuyTechniquePacket::handle
            );

            // =================================================
            // SINCRONIZAR ESTADO DAS TÉCNICAS
            // =================================================

            CHANNEL.registerMessage(
                    packetId++,
                    TechniqueStatePacket.class,
                    TechniqueStatePacket::encode,
                    TechniqueStatePacket::decode,
                    TechniqueStatePacket::handle
            );
        });
    }
}