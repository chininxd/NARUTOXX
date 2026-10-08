package com.example.examplemod.player;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class FoodHealingHandler {

    private static final Map<ResourceLocation, Double> CURA_COMIDAS =
            new HashMap<>();


    // =====================================================
    // CONFIGURAÇÃO DAS COMIDAS
    // =====================================================

    static {

        adicionar("minecraft:apple", 0.05);
        adicionar("minecraft:bread", 0.10);

        adicionar("minecraft:carrot", 0.05);
        adicionar("minecraft:potato", 0.05);
        adicionar("minecraft:baked_potato", 0.10);

        adicionar("minecraft:beef", 0.10);
        adicionar("minecraft:cooked_beef", 0.20);

        adicionar("minecraft:porkchop", 0.10);
        adicionar("minecraft:cooked_porkchop", 0.20);

        adicionar("minecraft:chicken", 0.10);
        adicionar("minecraft:cooked_chicken", 0.15);

        adicionar("minecraft:mutton", 0.10);
        adicionar("minecraft:cooked_mutton", 0.15);

        adicionar("minecraft:rabbit", 0.10);
        adicionar("minecraft:cooked_rabbit", 0.15);

        adicionar("minecraft:cod", 0.05);
        adicionar("minecraft:cooked_cod", 0.10);

        adicionar("minecraft:salmon", 0.10);
        adicionar("minecraft:cooked_salmon", 0.15);

        adicionar("minecraft:beetroot", 0.05);
        adicionar("minecraft:melon_slice", 0.05);

        adicionar("minecraft:sweet_berries", 0.05);
        adicionar("minecraft:glow_berries", 0.05);

        adicionar("minecraft:golden_carrot", 0.20);

        adicionar("minecraft:rabbit_stew", 0.25);
        adicionar("minecraft:mushroom_stew", 0.20);
        adicionar("minecraft:beetroot_soup", 0.20);
        adicionar("minecraft:suspicious_stew", 0.20);

        adicionar("minecraft:golden_apple", 0.30);
        adicionar("minecraft:enchanted_golden_apple", 0.50);
    }


    // =====================================================
    // CADASTRAR COMIDA
    // =====================================================

    private static void adicionar(
            String id,
            double porcentagem
    ) {

        CURA_COMIDAS.put(
                ResourceLocation.parse(id),
                porcentagem
        );
    }


    // =====================================================
    // PERMITIR COMER COM A FOME CHEIA
    // =====================================================

    @SubscribeEvent
    public static void aoInteragirComComida(
            PlayerInteractEvent.RightClickItem event
    ) {

        Player player =
                event.getEntity();

        ItemStack comida =
                event.getItemStack();

        ResourceLocation id =
                BuiltInRegistries.ITEM.getKey(
                        comida.getItem()
                );

        /*
         * Só interfere nas comidas cadastradas.
         */

        if (!CURA_COMIDAS.containsKey(id)) {
            return;
        }

        /*
         * Se a fome estiver cheia,
         * permitimos o uso da comida.
         */

        if (player.getFoodData().getFoodLevel() >= 20) {

            event.setCanceled(true);

            /*
             * Começa o processo de comer manualmente.
             */

            player.startUsingItem(event.getHand());
        }
    }


    // =====================================================
    // TERMINOU DE COMER
    // =====================================================

    @SubscribeEvent
    public static void aoTerminarDeComer(
            LivingEntityUseItemEvent.Finish event
    ) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        ItemStack comida =
                event.getItem();

        ResourceLocation id =
                BuiltInRegistries.ITEM.getKey(
                        comida.getItem()
                );

        Double porcentagem =
                CURA_COMIDAS.get(id);

        if (porcentagem == null) {
            return;
        }


        // =================================================
        // VIDA MÁXIMA
        // =================================================

        float vidaMaxima =
                player.getMaxHealth();


        // =================================================
        // CALCULA A CURA
        // =================================================

        float cura =
                (float) (
                        vidaMaxima * porcentagem
                );


        // =================================================
        // CURA
        // =================================================

        player.heal(cura);
    }
}