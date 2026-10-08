package com.example.examplemod.mission;

public class Mission {

    private final int id;
    private final String name;
    private final String description;

    private final MissionObjective objective;

    private final MissionReward reward;

    public Mission(
            int id,
            String name,
            String description,
            MissionObjective objective,
            MissionReward reward
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.reward = reward;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MissionObjective getObjective() {
        return objective;
    }

    public MissionReward getReward() {
        return reward;
    }

    public String getObjectiveTarget() {
        return objective.getTarget();
    }

    public int getRequiredAmount() {
        return objective.getRequiredAmount();
    }

    public MissionObjective.Type getObjectiveType() {
        return objective.getType();
    }

    public int getXpReward() {
        return reward.getXp();
    }

    public int getTpReward() {
        return reward.getTp();
    }
}