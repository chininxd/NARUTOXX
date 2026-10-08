package com.example.examplemod.mission;

public class MissionReward {

    private final int xp;
    private final int tp;

    public MissionReward(
            int xp,
            int tp
    ) {
        this.xp = xp;
        this.tp = tp;
    }

    public int getXp() {
        return xp;
    }

    public int getTp() {
        return tp;
    }
}