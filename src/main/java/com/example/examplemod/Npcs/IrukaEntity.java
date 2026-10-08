package com.example.examplemod.Npcs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class IrukaEntity extends PathfinderMob {

    public IrukaEntity(
            EntityType<? extends PathfinderMob> entityType,
            Level level
    ) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {

        return PathfinderMob.createMobAttributes()

                // ❤️ VIDA
                .add(
                        Attributes.MAX_HEALTH,
                        100.0D
                )

                // ⚔️ DANO
                .add(
                        Attributes.ATTACK_DAMAGE,
                        5.0D
                )

                // 🏃 VELOCIDADE
                .add(
                        Attributes.MOVEMENT_SPEED,
                        0.30D
                )

                // 📏 DISTÂNCIA PARA SEGUIR O JOGADOR
                .add(
                        Attributes.FOLLOW_RANGE,
                        20.0D
                )

                // 🛡️ ARMADURA
                .add(
                        Attributes.ARMOR,
                        5.0D
                );
    }

    @Override
    protected void registerGoals() {

        // =====================================================
        // COMPORTAMENTO
        // =====================================================

        // Ficar na água
        this.goalSelector.addGoal(
                0,
                new FloatGoal(this)
        );

        // Atacar o jogador
        this.goalSelector.addGoal(
                1,
                new MeleeAttackGoal(
                        this,
                        1.2D,
                        false
                )
        );

        // Olhar para jogadores próximos
        this.goalSelector.addGoal(
                2,
                new LookAtPlayerGoal(
                        this,
                        Player.class,
                        8.0F
                )
        );

        // Andar aleatoriamente
        this.goalSelector.addGoal(
                3,
                new RandomStrollGoal(
                        this,
                        1.0D
                )
        );

        // Olhar ao redor
        this.goalSelector.addGoal(
                4,
                new RandomLookAroundGoal(this)
        );


        // =====================================================
        // ALVOS
        // =====================================================

        // Se for atacado, reage contra quem bateu
        this.targetSelector.addGoal(
                1,
                new HurtByTargetGoal(this)
        );

        // Procura jogadores próximos automaticamente
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
    public boolean removeWhenFarAway(
            double distanceToClosestPlayer
    ) {
        return false;
    }
}