package com.example.examplemod.mission;

import com.example.examplemod.NarutoXMod;
import com.example.examplemod.Npcs.IrukaEntity;
import com.example.examplemod.Npcs.MizukiEntity;
import com.example.examplemod.Npcs.KonohamaruEntity;
import com.example.examplemod.Npcs.KakashiEntity;
import com.example.examplemod.Npcs.DemonBrothersEntity;
import com.example.examplemod.Npcs.ZabuzaEntity;
import com.example.examplemod.Npcs.HakuEntity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = NarutoXMod.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class MissionEvents {

    // =========================================================
    // MORTE DE ENTIDADE
    // =========================================================

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {

        // Só processa NPCs de missão
        if (!(event.getEntity() instanceof IrukaEntity)
                && !(event.getEntity() instanceof MizukiEntity)
                && !(event.getEntity() instanceof KonohamaruEntity)
                && !(event.getEntity() instanceof KakashiEntity)
                && !(event.getEntity() instanceof DemonBrothersEntity)
                && !(event.getEntity() instanceof ZabuzaEntity)
                && !(event.getEntity() instanceof HakuEntity)) {
            return;
        }

        LivingEntity entidadeMorta = event.getEntity();

        String nomeNPC;
        String alvoNPC;

        // =====================================================
        // IDENTIFICAR NPC
        // =====================================================

        if (entidadeMorta instanceof IrukaEntity) {

            nomeNPC = "IRUKA";
            alvoNPC = "iruka";

        } else if (entidadeMorta instanceof MizukiEntity) {

            nomeNPC = "MIZUKI";
            alvoNPC = "mizuki";

        } else if (entidadeMorta instanceof KonohamaruEntity) {

            nomeNPC = "KONOHAMARU";
            alvoNPC = "konohamaru";

        } else if (entidadeMorta instanceof KakashiEntity) {

            nomeNPC = "KAKASHI";
            alvoNPC = "kakashi";

        } else if (entidadeMorta instanceof DemonBrothersEntity) {

            nomeNPC = "DEMON BROTHERS";
            alvoNPC = "demon_brothers";

        } else if (entidadeMorta instanceof ZabuzaEntity) {

            nomeNPC = "ZABUZA";
            alvoNPC = "zabuza";

        } else {

            nomeNPC = "HAKU";
            alvoNPC = "haku";
        }

        System.out.println(
                "[NarutoX] =============================="
        );

        System.out.println(
                "[NarutoX] " + nomeNPC + " MORREU!"
        );

        System.out.println(
                "[NarutoX] =============================="
        );

        ServerPlayer player = null;

        // =====================================================
        // TENTATIVA 1
        // JOGADOR QUE CAUSOU O DANO FINAL
        // =====================================================

        if (event.getSource().getEntity()
                instanceof ServerPlayer serverPlayer) {

            player = serverPlayer;

            System.out.println(
                    "[NarutoX] Jogador encontrado pela DamageSource: "
                            + player.getName().getString()
            );
        }

        // =====================================================
        // TENTATIVA 2
        // KILL CREDIT
        // =====================================================

        if (player == null) {

            LivingEntity killer =
                    entidadeMorta.getKillCredit();

            if (killer instanceof ServerPlayer serverPlayer) {

                player = serverPlayer;

                System.out.println(
                        "[NarutoX] Jogador encontrado pelo KillCredit: "
                                + player.getName().getString()
                );
            }
        }

        // =====================================================
        // NENHUM JOGADOR ENCONTRADO
        // =====================================================

        if (player == null) {

            System.out.println(
                    "[NarutoX] Não foi possível encontrar o jogador responsável pela morte do "
                            + nomeNPC
            );

            System.out.println(
                    "[NarutoX] Tipo do dano: "
                            + event.getSource().getMsgId()
            );

            return;
        }

        // =====================================================
        // GERENCIADOR DA MISSÃO
        // =====================================================

        MissionManager manager =
                MissionManager.get(player);

        Mission mission =
                manager.getActiveMission();

        // =====================================================
        // SEM MISSÃO ATIVA
        // =====================================================

        if (mission == null) {

            System.out.println(
                    "[NarutoX] O jogador "
                            + player.getName().getString()
                            + " não possui missão ativa."
            );

            return;
        }

        System.out.println(
                "[NarutoX] Missão ativa: "
                        + mission.getName()
        );

        // =====================================================
        // VERIFICAR TIPO DA MISSÃO
        // =====================================================

        if (mission.getObjectiveType()
                != MissionObjective.Type.KILL) {

            System.out.println(
                    "[NarutoX] A missão não é do tipo KILL."
            );

            return;
        }

        // =====================================================
        // VERIFICAR ALVO DA MISSÃO
        // =====================================================

        String target =
                mission.getObjectiveTarget();

        if (!target.equalsIgnoreCase(alvoNPC)) {

            System.out.println(
                    "[NarutoX] O alvo da missão é: "
                            + target
                            + " e não "
                            + alvoNPC
            );

            return;
        }

        // =====================================================
        // ADICIONAR PROGRESSO
        // =====================================================

        System.out.println(
                "[NarutoX] Adicionando progresso..."
        );

        manager.addProgress(1);

        // =====================================================
        // MOSTRAR PROGRESSO
        // =====================================================

        System.out.println(
                "[NarutoX] PROGRESSO DA MISSÃO: "
                        + manager.getProgress()
                        + " / "
                        + mission.getRequiredAmount()
        );

        player.sendSystemMessage(
                Component.literal(
                        "§eProgresso da missão: "
                                + manager.getProgress()
                                + " / "
                                + mission.getRequiredAmount()
                )
        );

        // =====================================================
        // MISSÃO COMPLETA
        // =====================================================

        if (manager.canCompleteMission()) {

            System.out.println(
                    "[NarutoX] =============================="
            );

            System.out.println(
                    "[NarutoX] MISSÃO COMPLETA!"
            );

            System.out.println(
                    "[NarutoX] Recompensa disponível."
            );

            System.out.println(
                    "[NarutoX] =============================="
            );

            player.sendSystemMessage(
                    Component.literal(
                            "§aMissão concluída! Pegue sua recompensa."
                    )
            );
        }
    }

    // =========================================================
    // JOGADOR ENTROU NO SERVIDOR
    // =========================================================

    @SubscribeEvent
    public static void onPlayerLogin(
            PlayerEvent.PlayerLoggedInEvent event
    ) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        System.out.println(
                "[NarutoX] Jogador entrou: "
                        + player.getName().getString()
        );

        MissionManager manager =
                MissionManager.get(player);

        manager.syncToClient();
    }

    // =========================================================
    // JOGADOR RENASCEU
    // =========================================================

    @SubscribeEvent
    public static void onPlayerRespawn(
            PlayerEvent.PlayerRespawnEvent event
    ) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        System.out.println(
                "[NarutoX] Sincronizando missões após respawn: "
                        + player.getName().getString()
        );

        MissionManager manager =
                MissionManager.get(player);

        manager.syncToClient();
    }

    // =========================================================
    // JOGADOR MUDOU DE DIMENSÃO
    // =========================================================

    @SubscribeEvent
    public static void onPlayerChangedDimension(
            PlayerEvent.PlayerChangedDimensionEvent event
    ) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        System.out.println(
                "[NarutoX] Sincronizando missões após mudança de dimensão: "
                        + player.getName().getString()
        );

        MissionManager manager =
                MissionManager.get(player);

        manager.syncToClient();
    }

    // =========================================================
    // JOGADOR MORREU E FOI CLONADO
    // =========================================================

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

        CompoundTag dadosAntigos =
                event.getOriginal().getPersistentData();

        CompoundTag dadosNovos =
                event.getEntity().getPersistentData();

        // =====================================================
        // COPIAR DADOS DAS MISSÕES
        // =====================================================

        if (dadosAntigos.contains("ExampleModMissions")) {

            dadosNovos.put(
                    "ExampleModMissions",
                    dadosAntigos
                            .getCompound("ExampleModMissions")
                            .copy()
            );

            System.out.println(
                    "[NarutoX] Dados das missões copiados após a morte."
            );
        }
    }
}