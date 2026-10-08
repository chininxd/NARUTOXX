package com.example.examplemod.entity;

import com.example.examplemod.economy.RyoSystem;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class ZetsuBrancoEntity extends Zombie {

    public ZetsuBrancoEntity(
            EntityType<? extends Zombie> entityType,
            Level level
    ) {
        super(entityType, level);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public void die(DamageSource source) {

        if (!this.level().isClientSide) {

            if (this.getKillCredit() instanceof ServerPlayer player) {

                RyoSystem.adicionarRyo(
                        player,
                        100
                );
            }
        }

        super.die(source);
    }
}