package com.example.examplemod.mission;

public class MissionObjective {

    public enum Type {
        KILL
    }

    private final Type type;
    private final String target;
    private final int requiredAmount;

    public MissionObjective(
            Type type,
            String target,
            int requiredAmount
    ) {
        this.type = type;
        this.target = target;
        this.requiredAmount = requiredAmount;
    }

    public Type getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }
}