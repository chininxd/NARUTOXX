package com.example.examplemod.NpcsVendedores;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class OrochimaruVendedorEntity extends Monster {

    public OrochimaruVendedorEntity(
            EntityType<? extends Monster> entityType,
            Level level
    ) {
        super(entityType, level);

        this.setInvulnerable(true);
        this.setPersistenceRequired();
        this.setNoAi(true);
    }


    // ========================================================
    // ATRIBUTOS
    // ========================================================

    public static AttributeSupplier.Builder createAttributes() {

        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }


    // ========================================================
    // SEM IA
    // ========================================================

    @Override
    protected void registerGoals() {
        // Orochimaru não possui IA.
    }


    // ========================================================
    // NÃO RECEBE DANO
    // ========================================================

    @Override
    public boolean hurt(
            DamageSource source,
            float amount
    ) {
        return false;
    }


    @Override
    public boolean isInvulnerableTo(
            DamageSource source
    ) {
        return true;
    }


    // ========================================================
    // NÃO SE MOVE
    // ========================================================

    @Override
    public void travel(
            Vec3 travelVector
    ) {

        this.setDeltaMovement(
                Vec3.ZERO
        );
    }


    @Override
    public void tick() {

        super.tick();

        this.setDeltaMovement(
                Vec3.ZERO
        );
    }


    // ========================================================
    // CLIQUE COM BOTÃO DIREITO
    // ========================================================

    @Override
    protected InteractionResult mobInteract(
            net.minecraft.world.entity.player.Player player,
            InteractionHand hand
    ) {

        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }


        // ====================================================
        // SERVIDOR
        // ====================================================

        if (!this.level().isClientSide) {
            return InteractionResult.SUCCESS;
        }


        // ====================================================
        // CLIENTE
        // ====================================================

        Minecraft minecraft =
                Minecraft.getInstance();


        minecraft.setScreen(
                new OrochimaruShopScreen()
        );


        return InteractionResult.SUCCESS;
    }
}