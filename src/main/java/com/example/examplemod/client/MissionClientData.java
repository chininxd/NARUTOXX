
package com.example.examplemod.client;

public class MissionClientData {

    // =========================================================
    // DADOS DA MISSÃO NO CLIENTE
    // =========================================================

    private static int activeMissionId = -1;

    private static int nextMissionId = -1;

    private static int progress = 0;

    private static boolean rewardAvailable = false;

    // =========================================================
    // ATUALIZAR DADOS
    // =========================================================

    public static void setMissionData(
            int newActiveMissionId,
            int newNextMissionId,
            int newProgress,
            boolean newRewardAvailable
    ) {

        activeMissionId = newActiveMissionId;

        nextMissionId = newNextMissionId;

        progress = newProgress;

        rewardAvailable = newRewardAvailable;
    }

    // =========================================================
    // ID DA MISSÃO ATIVA
    // =========================================================

    public static int getActiveMissionId() {
        return activeMissionId;
    }

    // =========================================================
    // ID DA PRÓXIMA MISSÃO
    // =========================================================

    public static int getNextMissionId() {
        return nextMissionId;
    }

    // =========================================================
    // VERIFICAR SE EXISTE MISSÃO ATIVA
    // =========================================================

    public static boolean hasActiveMission() {
        return activeMissionId != -1;
    }

    // =========================================================
    // PROGRESSO
    // =========================================================

    public static int getProgress() {
        return progress;
    }

    // =========================================================
    // RECOMPENSA
    // =========================================================

    public static boolean hasRewardAvailable() {
        return rewardAvailable;
    }

    // =========================================================
    // LIMPAR DADOS
    // =========================================================

    public static void clear() {

        activeMissionId = -1;

        nextMissionId = -1;

        progress = 0;

        rewardAvailable = false;
    }
}
