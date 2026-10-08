package com.example.examplemod.player;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ByakuganCombat {

    // =========================================================
    // CONFIGURAÇÕES
    // =========================================================

    private static final int ATAQUES_NECESSARIOS = 64;

    // 16 segundos = 320 ticks
    private static final int DURACAO_IMOBILIZACAO = 320;


    // =========================================================
    // CONTADOR DE ATAQUES POR ALVO
    // =========================================================

    private static final Map<UUID, Integer> CONTADORES =
            new HashMap<>();


    // =========================================================
    // ALVOS IMOBILIZADOS
    // UUID -> TICKS RESTANTES
    // =========================================================

    private static final Map<UUID, Integer> IMOBILIZADOS =
            new HashMap<>();


    // =========================================================
    // DETECTAR ATAQUE
    // =========================================================

    @SubscribeEvent
    public static void aoAtacar(LivingAttackEvent event) {

        // =====================================================
        // PRIMEIRO:
        // IMPEDIR ATAQUE DE QUEM ESTÁ PARALISADO
        // =====================================================

        if (event.getSource().getEntity()
                instanceof LivingEntity atacante) {

            if (estaImobilizado(atacante)) {

                event.setCanceled(true);

                return;
            }
        }


        // =====================================================
        // PEGAR O JOGADOR QUE ESTÁ ATACANDO
        // =====================================================

        if (!(event.getSource().getEntity()
                instanceof Player atacante)) {

            return;
        }


        LivingEntity alvo =
                event.getEntity();


        // =====================================================
        // NÃO DEIXAR CONTAR A SI MESMO
        // =====================================================

        if (alvo == atacante) {
            return;
        }


        // =====================================================
        // PEGAR STATUS
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(atacante);


        // =====================================================
        // BYAKUGAN PRECISA ESTAR ATIVO
        // =====================================================

        if (!stats.isByakuganAtivo()) {
            return;
        }


        // =====================================================
        // NÃO CONTAR ALVO JÁ PARALISADO
        // =====================================================

        if (estaImobilizado(alvo)) {
            return;
        }


        UUID alvoId =
                alvo.getUUID();


        // =====================================================
        // PEGAR CONTADOR
        // =====================================================

        int contador =
                CONTADORES.getOrDefault(
                        alvoId,
                        0
                );


        contador++;


        // =====================================================
        // ATUALIZAR CONTADOR
        // =====================================================

        CONTADORES.put(
                alvoId,
                contador
        );


        // =====================================================
        // MOSTRAR NA TELA DO ATACANTE
        // =====================================================

        mostrarContador(
                atacante,
                contador
        );


        // =====================================================
        // CHEGOU A 64
        // =====================================================

        if (contador >= ATAQUES_NECESSARIOS) {

            CONTADORES.remove(alvoId);


            IMOBILIZADOS.put(
                    alvoId,
                    DURACAO_IMOBILIZACAO
            );


            imobilizar(alvo);


            // =============================================
            // AVISAR O JOGADOR
            // =============================================

            mostrarParalisia(
                    atacante
            );
        }
    }


    // =========================================================
    // MOSTRAR CONTADOR
    // =========================================================

    private static void mostrarContador(
            Player jogador,
            int contador
    ) {

        if (!(jogador instanceof ServerPlayer player)) {
            return;
        }


        player.displayClientMessage(
                Component.literal(
                        "§bByakugan §f- §e"
                                + contador
                                + "§f/§e"
                                + ATAQUES_NECESSARIOS
                                + " §7ataques"
                ),
                true
        );
    }


    // =========================================================
    // MOSTRAR PARALISIA
    // =========================================================

    private static void mostrarParalisia(
            Player jogador
    ) {

        if (!(jogador instanceof ServerPlayer player)) {
            return;
        }


        player.displayClientMessage(
                Component.literal(
                        "§bByakugan §f- §cPARALISADO! §f16s"
                ),
                true
        );
    }


    // =========================================================
    // IMOBILIZAR
    // =========================================================

    private static void imobilizar(
            LivingEntity entidade
    ) {

        entidade.setDeltaMovement(
                Vec3.ZERO
        );

        entidade.hurtMarked = true;


        if (entidade instanceof Player player) {

            player.setJumping(false);

            player.stopUsingItem();
        }
    }


    // =========================================================
    // TICK DO SERVIDOR
    // =========================================================

    @SubscribeEvent
    public static void tick(
            TickEvent.ServerTickEvent event
    ) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }


        // =====================================================
        // PEGAR SERVIDOR
        // =====================================================

        MinecraftServer server =
                ServerLifecycleHooks
                        .getCurrentServer();


        if (server == null) {
            return;
        }


        // =====================================================
        // ATUALIZAR TODAS AS PARALISIAS
        // =====================================================

        var iterator =
                IMOBILIZADOS.entrySet().iterator();


        while (iterator.hasNext()) {

            var entry =
                    iterator.next();


            UUID uuid =
                    entry.getKey();


            int tempo =
                    entry.getValue();


            // =================================================
            // PROCURAR ENTIDADE
            // =================================================

            LivingEntity entidade =
                    encontrarEntidade(
                            uuid
                    );


            // =================================================
            // MANTER PARALISADO
            // =================================================

            if (entidade != null) {

                entidade.setDeltaMovement(
                        Vec3.ZERO
                );

                entidade.hurtMarked = true;


                if (entidade instanceof Player player) {

                    player.setJumping(false);

                    player.stopUsingItem();
                }


                // =============================================
                // MOSTRAR TEMPO PARA O PRÓPRIO PLAYER
                // =============================================

                if (entidade instanceof ServerPlayer player) {

                    int segundos =
                            (int) Math.ceil(
                                    tempo / 20.0
                            );


                    player.displayClientMessage(
                            Component.literal(
                                    "§cPARALISADO §f- §e"
                                            + segundos
                                            + "s"
                            ),
                            true
                    );
                }
            }


            // =================================================
            // DIMINUIR TEMPO
            // =================================================

            tempo--;


            // =================================================
            // TERMINOU
            // =================================================

            if (tempo <= 0) {

                iterator.remove();

                if (entidade != null) {

                    entidade.setDeltaMovement(
                            Vec3.ZERO
                    );

                    entidade.hurtMarked = true;
                }

            } else {

                // =============================================
                // MUITO IMPORTANTE:
                // SALVAR O NOVO TEMPO NO MAP
                // =============================================

                entry.setValue(
                        tempo
                );
            }
        }
    }


    // =========================================================
    // ENCONTRAR ENTIDADE
    // =========================================================

    private static LivingEntity encontrarEntidade(
            UUID uuid
    ) {

        MinecraftServer server =
                ServerLifecycleHooks
                        .getCurrentServer();


        if (server == null) {
            return null;
        }


        for (ServerLevel level :
                server.getAllLevels()) {

            var entidade =
                    level.getEntity(uuid);


            if (entidade instanceof LivingEntity living) {

                return living;
            }
        }


        return null;
    }


    // =========================================================
    // VERIFICAR PARALISIA
    // =========================================================

    public static boolean estaImobilizado(
            LivingEntity entidade
    ) {

        if (entidade == null) {
            return false;
        }


        return IMOBILIZADOS.containsKey(
                entidade.getUUID()
        );
    }


    // =========================================================
    // PEGAR CONTADOR
    // =========================================================

    public static int getContador(
            LivingEntity entidade
    ) {

        if (entidade == null) {
            return 0;
        }


        return CONTADORES.getOrDefault(
                entidade.getUUID(),
                0
        );
    }


    // =========================================================
    // PEGAR TEMPO DE PARALISIA
    // =========================================================

    public static int getTempoImobilizado(
            LivingEntity entidade
    ) {

        if (entidade == null) {
            return 0;
        }


        return IMOBILIZADOS.getOrDefault(
                entidade.getUUID(),
                0
        );
    }
}