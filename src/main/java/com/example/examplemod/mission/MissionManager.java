package com.example.examplemod.mission;
import com.example.examplemod.Npcs.RockLeeEntity;
import com.example.examplemod.Npcs.DemonBrothersEntity;
import com.example.examplemod.Npcs.HakuEntity;
import com.example.examplemod.Npcs.IrukaEntity;
import com.example.examplemod.Npcs.KakashiEntity;
import com.example.examplemod.Npcs.KonohamaruEntity;
import com.example.examplemod.Npcs.MizukiEntity;
import com.example.examplemod.Npcs.ModNPCs;
import com.example.examplemod.Npcs.ZabuzaEntity;
import com.example.examplemod.network.MissionSyncPacket;
import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class MissionManager {

    private static final String DATA_KEY = "ExampleModMissions";
    private static final String ACTIVE_MISSION = "ActiveMission";
    private static final String PROGRESS = "Progress";
    private static final String COMPLETED_MISSIONS = "CompletedMissions";
    private static final String REWARD_AVAILABLE = "RewardAvailable";

    private final Player player;

    private MissionManager(Player player) {
        this.player = player;
    }

    public static MissionManager get(Player player) {
        return new MissionManager(player);
    }

    // =========================================================
    // DADOS
    // =========================================================

    private CompoundTag getMissionData() {

        CompoundTag playerData =
                player.getPersistentData();

        if (!playerData.contains(DATA_KEY)) {
            playerData.put(
                    DATA_KEY,
                    new CompoundTag()
            );
        }

        return playerData.getCompound(DATA_KEY);
    }

    // =========================================================
    // SINCRONIZAR COM O CLIENTE
    // =========================================================

    public void syncToClient() {

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        ModNetwork.CHANNEL.send(
                PacketDistributor.PLAYER.with(
                        () -> serverPlayer
                ),
                new MissionSyncPacket(
                        getActiveMissionId(),
                        getNextAvailableMissionId(),
                        getProgress(),
                        hasRewardAvailable()
                )
        );
    }

    // =========================================================
    // INICIAR MISSÃO
    // =========================================================

    public void startMission(int missionId) {

        if (hasActiveMission()) {
            return;
        }

        if (isMissionCompleted(missionId)) {
            return;
        }

        Mission mission =
                MissionRegistry.getMission(missionId);

        if (mission == null) {
            return;
        }

        CompoundTag data =
                getMissionData();

        data.putInt(
                ACTIVE_MISSION,
                missionId
        );

        data.putInt(
                PROGRESS,
                0
        );

        data.putBoolean(
                REWARD_AVAILABLE,
                false
        );

        // =====================================================
        // SPAWN DO NPC
        // =====================================================

        spawnMissionNPC(missionId);

        player.sendSystemMessage(
                Component.literal(
                        "§aMissão iniciada!"
                )
        );

        syncToClient();
    }

    // =========================================================
    // NPC DA MISSÃO
    // =========================================================

    private void spawnMissionNPC(int missionId) {

        // Só pode spawnar no servidor
        if (player.level().isClientSide()) {
            return;
        }

        double distancia = 5.0D;

        double x =
                player.getX()
                        - Math.sin(
                        Math.toRadians(
                                player.getYRot()
                        )
                ) * distancia;

        double z =
                player.getZ()
                        + Math.cos(
                        Math.toRadians(
                                player.getYRot()
                        )
                ) * distancia;

        double y =
                player.getY();

        // =====================================================
        // MISSÃO 1 - IRUKA
        // =====================================================

        if (missionId == 1) {

            IrukaEntity iruka =
                    ModNPCs.IRUKA.get()
                            .create(player.level());

            if (iruka == null) {
                return;
            }

            iruka.moveTo(
                    x,
                    y,
                    z,
                    player.getYRot() + 180.0F,
                    0.0F
            );

            player.level().addFreshEntity(iruka);

            player.sendSystemMessage(
                    Component.literal(
                            "§eIruka apareceu!"
                    )
            );

            return;
        }

        // =====================================================
        // MISSÃO 2 - MIZUKI
        // =====================================================

        if (missionId == 2) {

            MizukiEntity mizuki =
                    ModNPCs.MIZUKI.get()
                            .create(player.level());

            if (mizuki == null) {
                return;
            }

            mizuki.moveTo(
                    x,
                    y,
                    z,
                    player.getYRot() + 180.0F,
                    0.0F
            );

            player.level().addFreshEntity(mizuki);

            player.sendSystemMessage(
                    Component.literal(
                            "§cMizuki apareceu!"
                    )
            );

            return;
        }

        // =====================================================
        // MISSÃO 3 - KONOHAMARU
        // =====================================================

        if (missionId == 3) {

            KonohamaruEntity konohamaru =
                    ModNPCs.KONOHAMARU.get()
                            .create(player.level());

            if (konohamaru == null) {
                return;
            }

            konohamaru.moveTo(
                    x,
                    y,
                    z,
                    player.getYRot() + 180.0F,
                    0.0F
            );

            player.level().addFreshEntity(konohamaru);

            player.sendSystemMessage(
                    Component.literal(
                            "§eKonohamaru apareceu!"
                    )
            );

            return;
        }

        // =====================================================
        // MISSÃO 4 - KAKASHI
        // =====================================================

        if (missionId == 4) {

            KakashiEntity kakashi =
                    ModNPCs.KAKASHI.get()
                            .create(player.level());

            if (kakashi == null) {
                return;
            }

            kakashi.moveTo(
                    x,
                    y,
                    z,
                    player.getYRot() + 180.0F,
                    0.0F
            );

            player.level().addFreshEntity(kakashi);

            player.sendSystemMessage(
                    Component.literal(
                            "§7Kakashi apareceu!"
                    )
            );

            return;
        }

        // =====================================================
        // MISSÃO 5 - IRMÃOS DEMÔNIOS
        // =====================================================

        if (missionId == 5) {

            // -----------------------------
            // PRIMEIRO IRMÃO
            // -----------------------------

            DemonBrothersEntity irmao1 =
                    ModNPCs.DEMON_BROTHERS.get()
                            .create(player.level());

            if (irmao1 != null) {

                irmao1.moveTo(
                        x,
                        y,
                        z,
                        player.getYRot() + 180.0F,
                        0.0F
                );

                player.level().addFreshEntity(
                        irmao1
                );
            }

            // -----------------------------
            // SEGUNDO IRMÃO
            // -----------------------------

            DemonBrothersEntity irmao2 =
                    ModNPCs.DEMON_BROTHERS.get()
                            .create(player.level());

            if (irmao2 != null) {

                // Coloca o segundo um pouco
                // para o lado do primeiro

                irmao2.moveTo(
                        x + 2.0D,
                        y,
                        z + 1.0D,
                        player.getYRot() + 180.0F,
                        0.0F
                );

                player.level().addFreshEntity(
                        irmao2
                );
            }

            player.sendSystemMessage(
                    Component.literal(
                            "§cOs Irmãos Demônios apareceram!"
                    )
            );

            return;
        }

        // =====================================================
        // MISSÃO 6 - ZABUZA
        // =====================================================

        if (missionId == 6) {

            ZabuzaEntity zabuza =
                    ModNPCs.ZABUZA.get()
                            .create(player.level());

            if (zabuza == null) {
                return;
            }

            zabuza.moveTo(
                    x,
                    y,
                    z,
                    player.getYRot() + 180.0F,
                    0.0F
            );

            player.level().addFreshEntity(zabuza);

            player.sendSystemMessage(
                    Component.literal(
                            "§3Zabuza apareceu!"
                    )
            );

            return;
        }

        // =====================================================
        // MISSÃO 7 - HAKU
        // =====================================================

        if (missionId == 7) {

            HakuEntity haku =
                    ModNPCs.HAKU.get()
                            .create(player.level());

            if (haku == null) {
                return;
            }

            haku.moveTo(
                    x,
                    y,
                    z,
                    player.getYRot() + 180.0F,
                    0.0F
            );

            player.level().addFreshEntity(haku);

            player.sendSystemMessage(
                    Component.literal(
                            "§bHaku apareceu!"
                    )
            );

            return;
        }

        // =========================================================
        // MISSÃO 8 - ROCK LEE
        // =========================================================

        if (missionId == 8) {

            RockLeeEntity rockLee =
                    ModNPCs.ROCK_LEE.get()
                            .create(player.level());

            if (rockLee == null) {
                return;
            }

            rockLee.moveTo(
                    x,
                    y,
                    z,
                    player.getYRot() + 180.0F,
                    0.0F
            );

            player.level().addFreshEntity(rockLee);

            player.sendSystemMessage(
                    Component.literal(
                            "§aRock Lee apareceu!"
                    )
            );

            return;
        }








    }










    // =========================================================
    // MISSÃO ATIVA
    // =========================================================

    public Mission getActiveMission() {

        int missionId =
                getActiveMissionId();

        if (missionId == -1) {
            return null;
        }

        return MissionRegistry.getMission(
                missionId
        );
    }

    public int getActiveMissionId() {

        CompoundTag data =
                getMissionData();

        if (!data.contains(
                ACTIVE_MISSION
        )) {
            return -1;
        }

        return data.getInt(
                ACTIVE_MISSION
        );
    }

    public boolean hasActiveMission() {
        return getActiveMissionId() != -1;
    }

    // =========================================================
    // PRÓXIMA MISSÃO DISPONÍVEL
    // =========================================================

    public Mission getNextAvailableMission() {

        for (
                Mission mission :
                MissionRegistry.getAllMissions()
        ) {

            if (!isMissionCompleted(
                    mission.getId()
            )) {

                return mission;
            }
        }

        return null;
    }

    public int getNextAvailableMissionId() {

        Mission mission =
                getNextAvailableMission();

        if (mission == null) {
            return -1;
        }

        return mission.getId();
    }

    // =========================================================
    // PROGRESSO
    // =========================================================

    public int getProgress() {

        CompoundTag data =
                getMissionData();

        if (!data.contains(
                PROGRESS
        )) {
            return 0;
        }

        return data.getInt(
                PROGRESS
        );
    }

    public void setProgress(
            int progress
    ) {

        CompoundTag data =
                getMissionData();

        Mission mission =
                getActiveMission();

        if (mission == null) {
            return;
        }

        int requiredAmount =
                mission.getRequiredAmount();

        int newProgress =
                Math.max(
                        0,
                        Math.min(
                                progress,
                                requiredAmount
                        )
                );

        data.putInt(
                PROGRESS,
                newProgress
        );

        if (
                newProgress
                        >= requiredAmount
        ) {

            data.putBoolean(
                    REWARD_AVAILABLE,
                    true
            );
        }

        syncToClient();
    }

    public void addProgress(
            int amount
    ) {

        if (!hasActiveMission()) {
            return;
        }

        if (amount <= 0) {
            return;
        }

        int currentProgress =
                getProgress();

        setProgress(
                currentProgress + amount
        );
    }

    // =========================================================
    // COMPLETAR MISSÃO
    // =========================================================

    public boolean canCompleteMission() {

        Mission mission =
                getActiveMission();

        if (mission == null) {
            return false;
        }

        return getProgress()
                >= mission.getRequiredAmount();
    }

    public boolean hasRewardAvailable() {

        CompoundTag data =
                getMissionData();

        return data.getBoolean(
                REWARD_AVAILABLE
        );
    }

    // =========================================================
    // PEGAR RECOMPENSA
    // =========================================================

    public void claimReward() {

        if (!hasRewardAvailable()) {
            return;
        }

        Mission mission =
                getActiveMission();

        if (mission == null) {
            return;
        }

        if (!canCompleteMission()) {
            return;
        }

        int missionId =
                mission.getId();

        // =====================================================
        // DAR PT
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        int tpReward =
                mission.getTpReward();

        if (tpReward > 0) {

            int pontosAtuais =
                    stats.getPontos();

            int novosPontos =
                    pontosAtuais + tpReward;

            stats.setPontos(
                    novosPontos
            );

            PlayerStatsManager.save(
                    player
            );

            player.sendSystemMessage(
                    Component.literal(
                            "§a+"
                                    + tpReward
                                    + " PT!"
                    )
            );
        }

        // =====================================================
        // MARCAR MISSÃO COMO CONCLUÍDA
        // =====================================================

        CompoundTag data =
                getMissionData();

        CompoundTag completed;

        if (
                data.contains(
                        COMPLETED_MISSIONS
                )
        ) {

            completed =
                    data.getCompound(
                            COMPLETED_MISSIONS
                    );

        } else {

            completed =
                    new CompoundTag();
        }

        completed.putBoolean(
                String.valueOf(
                        missionId
                ),
                true
        );

        data.put(
                COMPLETED_MISSIONS,
                completed
        );

        // =====================================================
        // LIMPAR MISSÃO ATIVA
        // =====================================================

        data.remove(
                ACTIVE_MISSION
        );

        data.remove(
                PROGRESS
        );

        data.remove(
                REWARD_AVAILABLE
        );

        player.sendSystemMessage(
                Component.literal(
                        "§aMissão concluída!"
                )
        );

        // =====================================================
        // PRÓXIMA MISSÃO
        // =====================================================

        Mission nextMission =
                getNextAvailableMission();

        if (nextMission != null) {

            player.sendSystemMessage(
                    Component.literal(
                            "§ePróxima missão: §f"
                                    + nextMission.getName()
                    )
            );

        } else {

            player.sendSystemMessage(
                    Component.literal(
                            "§6Todas as missões foram concluídas!"
                    )
            );
        }

        // =====================================================
        // SINCRONIZAR CLIENTE
        // =====================================================

        syncToClient();
    }

    // =========================================================
    // MISSÕES CONCLUÍDAS
    // =========================================================

    public boolean isMissionCompleted(
            int missionId
    ) {

        CompoundTag data =
                getMissionData();

        if (
                !data.contains(
                        COMPLETED_MISSIONS
                )
        ) {
            return false;
        }

        CompoundTag completed =
                data.getCompound(
                        COMPLETED_MISSIONS
                );

        return completed.getBoolean(
                String.valueOf(
                        missionId
                )
        );
    }

    // =========================================================
    // CANCELAR
    // =========================================================

    public void cancelMission() {

        CompoundTag data =
                getMissionData();

        data.remove(
                ACTIVE_MISSION
        );

        data.remove(
                PROGRESS
        );

        data.remove(
                REWARD_AVAILABLE
        );

        syncToClient();
    }

    // =========================================================
    // RESETAR UMA MISSÃO
    // =========================================================

    public void resetMission(
            int missionId
    ) {

        CompoundTag data =
                getMissionData();

        if (
                data.contains(
                        COMPLETED_MISSIONS
                )
        ) {

            CompoundTag completed =
                    data.getCompound(
                            COMPLETED_MISSIONS
                    );

            completed.remove(
                    String.valueOf(
                            missionId
                    )
            );
        }

        if (
                getActiveMissionId()
                        == missionId
        ) {

            data.remove(
                    ACTIVE_MISSION
            );

            data.remove(
                    PROGRESS
            );

            data.remove(
                    REWARD_AVAILABLE
            );
        }

        syncToClient();
    }

    // =========================================================
    // RESETAR TODAS
    // =========================================================

    public void resetAllMissions() {

        player.getPersistentData()
                .remove(DATA_KEY);

        syncToClient();
    }

    // =========================================================
    // TEXTO DO PROGRESSO
    // =========================================================

    public String getProgressText() {

        Mission mission =
                getActiveMission();

        if (mission == null) {
            return "0 / 0";
        }

        return getProgress()
                + " / "
                + mission.getRequiredAmount();
    }

    // =========================================================
    // PLAYER
    // =========================================================

    public Player getPlayer() {
        return player;
    }
}