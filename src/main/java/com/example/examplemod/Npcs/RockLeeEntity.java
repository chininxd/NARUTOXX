package com.example.examplemod.Npcs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;

public class RockLeeEntity extends Monster {

    public RockLeeEntity(EntityType<? extends Monster> entityType,
                         net.minecraft.world.level.Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 25.0D)
                .add(Attributes.ARMOR, 8.0D);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));

        // Ataca jogadores automaticamente
        this.goalSelector.addGoal(
                1,
                new MeleeAttackGoal(this, 1.5D, false)
        );

        this.goalSelector.addGoal(
                2,
                new net.minecraft.world.entity.ai.goal.LookAtPlayerGoal(
                        this,
                        Player.class,
                        8.0F
                )
        );

        this.goalSelector.addGoal(
                3,
                new RandomStrollGoal(this, 1.0D)
        );

        this.goalSelector.addGoal(
                4,
                new RandomLookAroundGoal(this)
        );

        this.targetSelector.addGoal(
                1,
                new HurtByTargetGoal(this)
        );

        this.targetSelector.addGoal(
                2,
                new NearestAttackableTargetGoal<>(
                        this,
                        Player.class,
                        true
                )
        );
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}