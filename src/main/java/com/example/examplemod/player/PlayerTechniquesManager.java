package com.example.examplemod.player;

import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.network.TechniqueStatePacket;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class PlayerTechniquesManager {

    // =========================================================
    // TÉCNICAS DE CADA JOGADOR
    // =========================================================

    private static final Map<UUID, PlayerTechniques> PLAYER_TECHNIQUES =
            new ConcurrentHashMap<>();


    // =========================================================
    // PEGAR TÉCNICAS DO JOGADOR
    // =========================================================

    public static PlayerTechniques get(
            Player player
    ) {

        if (
                player == null
        ) {

            return null;
        }


        UUID uuid =
                player.getUUID();


        PlayerTechniques techniques =
                PLAYER_TECHNIQUES.get(
                        uuid
                );


        // =====================================================
        // PRIMEIRA VEZ
        // =====================================================

        if (
                techniques == null
        ) {

            techniques =
                    new PlayerTechniques();


            // =================================================
            // CARREGA DO PERSISTENT DATA
            // =================================================

            techniques.load(
                    player
            );


            PLAYER_TECHNIQUES.put(
                    uuid,
                    techniques
            );
        }


        return techniques;
    }


    // =========================================================
    // SALVAR
    // =========================================================

    public static void save(
            Player player
    ) {

        if (
                player == null
        ) {

            return;
        }


        PlayerTechniques techniques =
                get(
                        player
                );


        if (
                techniques == null
        ) {

            return;
        }


        techniques.save(
                player
        );
    }


    // =========================================================
    // RECARREGAR
    //
    // IGNORA O CACHE E LÊ NOVAMENTE O PERSISTENT DATA.
    // =========================================================

    public static void reload(
            Player player
    ) {

        if (
                player == null
        ) {

            return;
        }


        PlayerTechniques techniques =
                new PlayerTechniques();


        techniques.load(
                player
        );


        PLAYER_TECHNIQUES.put(
                player.getUUID(),
                techniques
        );
    }


    // =========================================================
    // SINCRONIZAR TÉCNICAS COM O CLIENTE
    //
    // SINCRONIZA:
    //
    // - Marca possui
    // - Marca ativa
    // - Susanoo possui
    // - Susanoo ativo
    // - Susanoo tipo
    // - Susanoo estágio
    // =========================================================

    public static void syncToClient(
            ServerPlayer player
    ) {

        if (
                player == null
        ) {

            return;
        }


        // =====================================================
        // TÉCNICAS
        // =====================================================

        PlayerTechniques techniques =
                get(
                        player
                );


        if (
                techniques == null
        ) {

            return;
        }


        // =====================================================
        // PLAYER STATS
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );


        if (
                stats == null
        ) {

            return;
        }


        // =====================================================
        // PROCURAR MARCA DA MALDIÇÃO
        //
        // ACEITA COM E SEM ACENTO PARA SAVES ANTIGOS.
        // =====================================================

        PlayerTechniques.Tecnica marca =
                encontrarMarcaDaMaldicao(
                        techniques
                );


        // =====================================================
        // POSSUI MARCA
        // =====================================================

        boolean possuiMarca =
                marca != null;


        // =====================================================
        // MARCA ATIVA
        // =====================================================

        boolean marcaAtiva =
                possuiMarca
                        && marca.isAtivada();


        // =====================================================
        // SUSANOO
        // =====================================================

        boolean possuiSusanoo =
                stats.isSusanooDesbloqueado();


        boolean susanooAtivo =
                stats.isSusanooAtivo();


        int susanooTipo =
                stats.getSusanooTipo();


        int susanooEstagio =
                stats.getSusanooEstagio();


        // =====================================================
        // SEGURANÇA
        //
        // SE NÃO POSSUI SUSANOO,
        // NÃO ENVIA TIPO/ESTÁGIO ANTIGO.
        // =====================================================

        if (
                !possuiSusanoo
        ) {

            susanooAtivo =
                    false;


            susanooTipo =
                    0;


            susanooEstagio =
                    0;
        }


        // =====================================================
        // ENVIA PARA O CLIENTE DESSE PLAYER
        // =====================================================

        ModNetwork.CHANNEL.send(
                PacketDistributor.PLAYER.with(
                        () -> player
                ),

                new TechniqueStatePacket(
                        possuiMarca,
                        marcaAtiva,

                        possuiSusanoo,
                        susanooAtivo,

                        susanooTipo,
                        susanooEstagio
                )
        );


        // =====================================================
        // DEBUG
        // =====================================================

        System.out.println(
                "[NARUTOX] Tecnicas sincronizadas para "
                        + player.getGameProfile().getName()
                        + " | Marca possui: "
                        + possuiMarca
                        + " | Marca ativa: "
                        + marcaAtiva
                        + " | Susanoo possui: "
                        + possuiSusanoo
                        + " | Susanoo ativo: "
                        + susanooAtivo
                        + " | Tipo: "
                        + susanooTipo
                        + " | Estagio: "
                        + susanooEstagio
        );
    }


    // =========================================================
    // PROCURAR MARCA DA MALDIÇÃO
    // =========================================================

    private static PlayerTechniques.Tecnica encontrarMarcaDaMaldicao(
            PlayerTechniques techniques
    ) {

        if (
                techniques == null
        ) {

            return null;
        }


        // =====================================================
        // PROCURA NOS 8 SLOTS
        // =====================================================

        for (
                int i = 0;
                i < 8;
                i++
        ) {

            PlayerTechniques.Tecnica tecnica =
                    techniques.getTecnicaDoSlot(
                            i
                    );


            if (
                    tecnica == null
            ) {

                continue;
            }


            String nome =
                    tecnica.getNome();


            if (
                    nome == null
            ) {

                continue;
            }


            // =================================================
            // COMPATIBILIDADE
            //
            // SAVE NOVO:
            // Marca da Maldição
            //
            // SAVE ANTIGO:
            // Marca da Maldicao
            // =================================================

            if (
                    nome.equalsIgnoreCase(
                            "Marca da Maldição"
                    )
                            ||
                            nome.equalsIgnoreCase(
                                    "Marca da Maldicao"
                            )
            ) {

                return tecnica;
            }
        }


        return null;
    }


    // =========================================================
    // PLAYER ENTROU NO SERVIDOR / MUNDO
    // =========================================================

    @SubscribeEvent
    public static void onPlayerLoggedIn(
            PlayerEvent.PlayerLoggedInEvent event
    ) {

        if (
                !(event.getEntity()
                        instanceof ServerPlayer player)
        ) {

            return;
        }


        // =====================================================
        // FORÇA RECARGA DAS TÉCNICAS
        // =====================================================

        reload(
                player
        );


        // =====================================================
        // IMPORTANTE:
        //
        // GARANTE QUE O PLAYERSTATS ESTEJA CARREGADO.
        //
        // O PlayerStatsManager.get() DEVE RETORNAR
        // OS DADOS INDIVIDUAIS DESSE JOGADOR.
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );


        if (
                stats != null
        ) {

            // =================================================
            // ATUALIZA OS EFEITOS AO ENTRAR
            //
            // ISSO É IMPORTANTE SE O SUSANOO ESTAVA ATIVO
            // QUANDO O JOGADOR SAIU.
            // =================================================

            PlayerStatsEffects.atualizar(
                    player
            );
        }


        // =====================================================
        // ENVIA PARA O CLIENTE IMEDIATAMENTE
        // =====================================================

        syncToClient(
                player
        );


        System.out.println(
                "[NARUTOX] Tecnicas carregadas no login de "
                        + player.getGameProfile().getName()
        );
    }


    // =========================================================
    // PLAYER SAIU
    //
    // REMOVE CACHE PARA NA PRÓXIMA ENTRADA
    // CARREGAR NOVAMENTE DO SAVE.
    // =========================================================

    @SubscribeEvent
    public static void onPlayerLoggedOut(
            PlayerEvent.PlayerLoggedOutEvent event
    ) {

        if (
                !(event.getEntity()
                        instanceof ServerPlayer player)
        ) {

            return;
        }


        remove(
                player
        );


        System.out.println(
                "[NARUTOX] Cache de tecnicas removido de "
                        + player.getGameProfile().getName()
        );
    }


    // =========================================================
    // REMOVER DA MEMÓRIA
    // =========================================================

    public static void remove(
            Player player
    ) {

        if (
                player == null
        ) {

            return;
        }


        PLAYER_TECHNIQUES.remove(
                player.getUUID()
        );
    }


    // =========================================================
    // LIMPAR TUDO
    // =========================================================

    public static void clear() {

        PLAYER_TECHNIQUES.clear();
    }


    // =========================================================
    // CONSTRUTOR PRIVADO
    // =========================================================

    private PlayerTechniquesManager() {
    }
}