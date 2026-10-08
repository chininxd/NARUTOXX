package com.example.examplemod.player;

import java.util.UUID;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class PlayerStatsEffects {

    // =========================================================
    // MULTIPLICADORES
    //
    // MUDE OS VALORES SOMENTE AQUI.
    //
    // Os efeitos reais e as telas podem ler estes mesmos valores.
    // =========================================================

    // =========================================================
    // SHARINGAN
    // =========================================================

    public static final double SHARINGAN_1_FORCA = 2.0;
    public static final double SHARINGAN_1_DEFESA = 2.0;
    public static final double SHARINGAN_1_VELOCIDADE = 1.0;

    public static final double SHARINGAN_2_FORCA = 9.0;
    public static final double SHARINGAN_2_DEFESA = 9.0;
    public static final double SHARINGAN_2_VELOCIDADE = 2.0;

    public static final double SHARINGAN_3_FORCA = 15.0;
    public static final double SHARINGAN_3_DEFESA = 15.0;
    public static final double SHARINGAN_3_VELOCIDADE = 2.0;


    // =========================================================
    // MANGEKYŌ
    // =========================================================

    public static final double MANGEKYOU_FORCA = 30.0;
    public static final double MANGEKYOU_DEFESA = 30.0;
    public static final double MANGEKYOU_VELOCIDADE = 5.0;


    // =========================================================
    // BYAKUGAN
    // =========================================================

    public static final double BYAKUGAN_FORCA = 2.0;
    public static final double BYAKUGAN_DEFESA = 2.0;
    public static final double BYAKUGAN_VELOCIDADE = 1.0;

    public static final double BYAKUGAN_DESPERTADO_FORCA = 16.5;
    public static final double BYAKUGAN_DESPERTADO_DEFESA = 16.5;
    public static final double BYAKUGAN_DESPERTADO_VELOCIDADE = 2.0;


    // =========================================================
    // MARCA DA MALDIÇÃO
    //
    // Nerfada para continuar forte, mas sem superar sozinha
    // Mangekyō/Susanoo.
    //
    // FORÇA       = ×4
    // VIDA        = ×2
    // RESISTÊNCIA = dano ÷1.5
    //
    // A resistência da Marca NÃO multiplica a do Susanoo.
    // O sistema usa o maior valor entre os dois.
    // =========================================================

    public static final double MARCA_FORCA = 4.0;
    public static final double MARCA_VIDA = 2.0;
    public static final double MARCA_RESISTENCIA = 1.5;


    // =========================================================
    // SUSANOO
    //
    // Todos os tipos usam os MESMOS buffs.
    // O tipo do Susanoo serve apenas para modelo/textura.
    //
    // O Susanoo funciona principalmente como armadura:
    // ele reduz dano, em vez de multiplicar absurdamente a vida.
    //
    // Estágio 1 - Costelas:
    // Força ×1 | Vida ×1 | Resistência ÷2
    //
    // Estágio 2:
    // Força ×1.25 | Vida ×1 | Resistência ÷2.75
    //
    // Estágio 3:
    // Força ×1.5 | Vida ×1 | Resistência ÷3.5
    //
    // Estágio 4 - Blindado/Armado:
    // Força ×2 | Vida ×1 | Resistência ÷4.5
    //
    // Estágio 5 - Perfeito:
    // Força ×3 | Vida ×1 | Resistência ÷6
    //
    // Nenhum estágio dá velocidade.
    // =========================================================

    public static final double SUSANOO_1_FORCA = 1.0;
    public static final double SUSANOO_1_VIDA = 1.0;
    public static final double SUSANOO_1_RESISTENCIA = 2.0;

    public static final double SUSANOO_2_FORCA = 1.25;
    public static final double SUSANOO_2_VIDA = 1.0;
    public static final double SUSANOO_2_RESISTENCIA = 2.75;

    public static final double SUSANOO_3_FORCA = 1.5;
    public static final double SUSANOO_3_VIDA = 1.0;
    public static final double SUSANOO_3_RESISTENCIA = 3.5;

    public static final double SUSANOO_4_FORCA = 2.0;
    public static final double SUSANOO_4_VIDA = 1.0;
    public static final double SUSANOO_4_RESISTENCIA = 4.5;

    public static final double SUSANOO_5_FORCA = 3.0;
    public static final double SUSANOO_5_VIDA = 1.0;
    public static final double SUSANOO_5_RESISTENCIA = 6.0;


    // =========================================================
    // UUIDs - ATRIBUTOS NORMAIS
    // =========================================================

    private static final UUID FORCA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-111111111111");

    private static final UUID VIDA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-333333333333");


    // =========================================================
    // UUIDs - SHARINGAN / MANGEKYŌ
    // =========================================================

    private static final UUID SHARINGAN_FORCA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-555555555555");

    private static final UUID SHARINGAN_VIDA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-666666666666");

    private static final UUID SHARINGAN_VELOCIDADE_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-777777777777");

    private static final UUID MANGEKYOU_FORCA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-888888888888");

    private static final UUID MANGEKYOU_VIDA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-999999999999");

    private static final UUID MANGEKYOU_VELOCIDADE_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-aaaaaaaaaaaa");


    // =========================================================
    // UUIDs - BYAKUGAN
    // =========================================================

    private static final UUID BYAKUGAN_FORCA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-bbbbbbbbbbbb");

    private static final UUID BYAKUGAN_VIDA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-cccccccccccc");

    private static final UUID BYAKUGAN_DESPERTADO_FORCA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-dddddddddddd");

    private static final UUID BYAKUGAN_DESPERTADO_VIDA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-eeeeeeeeeeee");

    private static final UUID BYAKUGAN_DESPERTADO_VELOCIDADE_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-ffffffffffff");


    // =========================================================
    // UUIDs - MARCA
    // =========================================================

    private static final UUID MARCA_FORCA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-101010101010");

    private static final UUID MARCA_VIDA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-202020202020");


    // =========================================================
    // UUIDs - SUSANOO
    // =========================================================

    private static final UUID SUSANOO_FORCA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-303030303030");

    private static final UUID SUSANOO_VIDA_ID =
            UUID.fromString("7f1c2a10-4b8e-4f91-9a21-404040404040");


    // =========================================================
    // GETTERS DOS MULTIPLICADORES
    // =========================================================

    public static double getMultiplicadorForca(Player player) {

        if (player == null) {
            return 1.0;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return 1.0;
        }

        double multiplicador =
                getMultiplicadorDojutsuForca(stats);

        if (isMarcaDaMaldicaoAtiva(player)) {
            multiplicador *= MARCA_FORCA;
        }

        if (stats.isSusanooAtivo()) {
            multiplicador *=
                    getMultiplicadorSusanooForca(
                            stats.getSusanooEstagio()
                    );
        }

        return multiplicador;
    }


    public static double getMultiplicadorDefesa(Player player) {

        if (player == null) {
            return 1.0;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return 1.0;
        }

        return getMultiplicadorDojutsuDefesa(stats);
    }


    public static double getMultiplicadorVelocidade(Player player) {

        if (player == null) {
            return 1.0;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return 1.0;
        }

        return getMultiplicadorDojutsuVelocidade(stats);
    }


    public static double getMultiplicadorVida(Player player) {

        if (player == null) {
            return 1.0;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return 1.0;
        }

        double multiplicador =
                getMultiplicadorDojutsuDefesa(stats);

        if (isMarcaDaMaldicaoAtiva(player)) {
            multiplicador *= MARCA_VIDA;
        }

        if (stats.isSusanooAtivo()) {
            multiplicador *=
                    getMultiplicadorSusanooVida(
                            stats.getSusanooEstagio()
                    );
        }

        return multiplicador;
    }


    // =========================================================
    // RESISTÊNCIA
    //
    // Marca e Susanoo não multiplicam a resistência entre si.
    // É usado o maior dos dois valores.
    // =========================================================

    public static double getMultiplicadorResistencia(Player player) {

        if (player == null) {
            return 1.0;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return 1.0;
        }

        double resistencia = 1.0;

        if (isMarcaDaMaldicaoAtiva(player)) {
            resistencia =
                    Math.max(
                            resistencia,
                            MARCA_RESISTENCIA
                    );
        }

        if (stats.isSusanooAtivo()) {

            double resistenciaSusanoo =
                    getMultiplicadorSusanooResistencia(
                            stats.getSusanooEstagio()
                    );

            resistencia =
                    Math.max(
                            resistencia,
                            resistenciaSusanoo
                    );
        }

        return resistencia;
    }


    // =========================================================
    // MULTIPLICADORES DO SUSANOO POR ESTÁGIO
    // =========================================================

    private static double getMultiplicadorSusanooForca(int estagio) {

        switch (estagio) {

            case 1:
                return SUSANOO_1_FORCA;

            case 2:
                return SUSANOO_2_FORCA;

            case 3:
                return SUSANOO_3_FORCA;

            case 4:
                return SUSANOO_4_FORCA;

            case 5:
                return SUSANOO_5_FORCA;

            default:
                return 1.0;
        }
    }


    private static double getMultiplicadorSusanooVida(int estagio) {

        switch (estagio) {

            case 1:
                return SUSANOO_1_VIDA;

            case 2:
                return SUSANOO_2_VIDA;

            case 3:
                return SUSANOO_3_VIDA;

            case 4:
                return SUSANOO_4_VIDA;

            case 5:
                return SUSANOO_5_VIDA;

            default:
                return 1.0;
        }
    }


    private static double getMultiplicadorSusanooResistencia(int estagio) {

        switch (estagio) {

            case 1:
                return SUSANOO_1_RESISTENCIA;

            case 2:
                return SUSANOO_2_RESISTENCIA;

            case 3:
                return SUSANOO_3_RESISTENCIA;

            case 4:
                return SUSANOO_4_RESISTENCIA;

            case 5:
                return SUSANOO_5_RESISTENCIA;

            default:
                return 1.0;
        }
    }


    // =========================================================
    // MULTIPLICADORES DOS DŌJUTSUS
    // =========================================================

    private static double getMultiplicadorDojutsuForca(PlayerStats stats) {

        if (stats == null) {
            return 1.0;
        }

        if (stats.isMangekyouAtivo()) {
            return MANGEKYOU_FORCA;
        }

        if (stats.isByakuganDespertadoAtivo()) {
            return BYAKUGAN_DESPERTADO_FORCA;
        }

        if (stats.isSharinganAtivo()) {

            int tomoe =
                    stats.getSharinganTomoeAtivo();

            if (tomoe == 1) {
                return SHARINGAN_1_FORCA;
            }

            if (tomoe == 2) {
                return SHARINGAN_2_FORCA;
            }

            if (tomoe == 3) {
                return SHARINGAN_3_FORCA;
            }
        }

        if (stats.isByakuganAtivo()) {
            return BYAKUGAN_FORCA;
        }

        return 1.0;
    }


    private static double getMultiplicadorDojutsuDefesa(PlayerStats stats) {

        if (stats == null) {
            return 1.0;
        }

        if (stats.isMangekyouAtivo()) {
            return MANGEKYOU_DEFESA;
        }

        if (stats.isByakuganDespertadoAtivo()) {
            return BYAKUGAN_DESPERTADO_DEFESA;
        }

        if (stats.isSharinganAtivo()) {

            int tomoe =
                    stats.getSharinganTomoeAtivo();

            if (tomoe == 1) {
                return SHARINGAN_1_DEFESA;
            }

            if (tomoe == 2) {
                return SHARINGAN_2_DEFESA;
            }

            if (tomoe == 3) {
                return SHARINGAN_3_DEFESA;
            }
        }

        if (stats.isByakuganAtivo()) {
            return BYAKUGAN_DEFESA;
        }

        return 1.0;
    }


    private static double getMultiplicadorDojutsuVelocidade(PlayerStats stats) {

        if (stats == null) {
            return 1.0;
        }

        if (stats.isMangekyouAtivo()) {
            return MANGEKYOU_VELOCIDADE;
        }

        if (stats.isByakuganDespertadoAtivo()) {
            return BYAKUGAN_DESPERTADO_VELOCIDADE;
        }

        if (stats.isSharinganAtivo()) {

            int tomoe =
                    stats.getSharinganTomoeAtivo();

            if (tomoe == 1) {
                return SHARINGAN_1_VELOCIDADE;
            }

            if (tomoe == 2) {
                return SHARINGAN_2_VELOCIDADE;
            }

            if (tomoe == 3) {
                return SHARINGAN_3_VELOCIDADE;
            }
        }

        if (stats.isByakuganAtivo()) {
            return BYAKUGAN_VELOCIDADE;
        }

        return 1.0;
    }


    // =========================================================
    // MARCA DA MALDIÇÃO
    // =========================================================

    public static boolean isMarcaDaMaldicaoAtiva(Player player) {

        PlayerTechniques.Tecnica marca =
                getMarcaDaMaldicao(player);

        return marca != null
                && marca.isAtivada();
    }


    private static PlayerTechniques.Tecnica getMarcaDaMaldicao(
            Player player
    ) {

        if (player == null) {
            return null;
        }

        PlayerTechniques techniques =
                PlayerTechniquesManager.get(player);

        if (techniques == null) {
            return null;
        }

        PlayerTechniques.Tecnica marca =
                techniques.getTecnica(
                        "Marca da Maldição"
                );

        if (marca == null) {

            marca =
                    techniques.getTecnica(
                            "Marca da Maldicao"
                    );
        }

        return marca;
    }


    // =========================================================
    // ATUALIZAR TODOS OS EFEITOS
    // =========================================================

    public static void atualizar(Player player) {

        if (player == null) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (stats == null) {
            return;
        }

        atualizarForca(
                player,
                stats
        );

        atualizarDefesa(
                player,
                stats
        );

        atualizarSharingan(
                player,
                stats
        );

        atualizarByakugan(
                player,
                stats
        );

        atualizarByakuganDespertado(
                player,
                stats
        );

        atualizarMarcaDaMaldicao(
                player
        );

        atualizarSusanoo(
                player,
                stats
        );
    }


    // =========================================================
    // FORÇA NORMAL
    // =========================================================

    private static void atualizarForca(
            Player player,
            PlayerStats stats
    ) {

        AttributeInstance ataque =
                player.getAttribute(
                        Attributes.ATTACK_DAMAGE
                );

        if (ataque == null) {
            return;
        }

        removerModifier(
                ataque,
                FORCA_ID
        );

        double bonus =
                stats.getForca() - 1;

        if (bonus <= 0) {
            return;
        }

        AttributeModifier modifier =
                new AttributeModifier(
                        FORCA_ID,
                        "examplemod_forca",
                        bonus,
                        AttributeModifier.Operation.ADDITION
                );

        ataque.addPermanentModifier(
                modifier
        );
    }


    // =========================================================
    // DEFESA NORMAL -> VIDA
    // =========================================================

    private static void atualizarDefesa(
            Player player,
            PlayerStats stats
    ) {

        AttributeInstance maxHealth =
                player.getAttribute(
                        Attributes.MAX_HEALTH
                );

        if (maxHealth == null) {
            return;
        }

        removerModifier(
                maxHealth,
                VIDA_ID
        );

        double bonusVida =
                (stats.getDefesa() - 1)
                        * 2.0;

        if (bonusVida > 0) {

            AttributeModifier modifier =
                    new AttributeModifier(
                            VIDA_ID,
                            "examplemod_vida_defesa",
                            bonusVida,
                            AttributeModifier.Operation.ADDITION
                    );

            maxHealth.addPermanentModifier(
                    modifier
            );
        }

        corrigirVida(player);
    }


    // =========================================================
    // SHARINGAN / MANGEKYŌ
    // =========================================================

    private static void atualizarSharingan(
            Player player,
            PlayerStats stats
    ) {

        AttributeInstance ataque =
                player.getAttribute(
                        Attributes.ATTACK_DAMAGE
                );

        AttributeInstance maxHealth =
                player.getAttribute(
                        Attributes.MAX_HEALTH
                );

        AttributeInstance velocidade =
                player.getAttribute(
                        Attributes.MOVEMENT_SPEED
                );


        removerModifier(
                ataque,
                SHARINGAN_FORCA_ID
        );

        removerModifier(
                maxHealth,
                SHARINGAN_VIDA_ID
        );

        removerModifier(
                velocidade,
                SHARINGAN_VELOCIDADE_ID
        );

        removerModifier(
                ataque,
                MANGEKYOU_FORCA_ID
        );

        removerModifier(
                maxHealth,
                MANGEKYOU_VIDA_ID
        );

        removerModifier(
                velocidade,
                MANGEKYOU_VELOCIDADE_ID
        );


        // =====================================================
        // MANGEKYŌ
        // =====================================================

        if (stats.isMangekyouAtivo()) {

            if (ataque != null) {

                double bonus =
                        stats.getForca()
                                * (MANGEKYOU_FORCA - 1.0);

                AttributeModifier modifier =
                        new AttributeModifier(
                                MANGEKYOU_FORCA_ID,
                                "examplemod_mangekyou_forca",
                                bonus,
                                AttributeModifier.Operation.ADDITION
                        );

                ataque.addPermanentModifier(
                        modifier
                );
            }


            if (maxHealth != null) {

                double bonusVida =
                        stats.getDefesa()
                                * (MANGEKYOU_DEFESA - 1.0)
                                * 2.0;

                if (bonusVida > 0) {

                    AttributeModifier modifier =
                            new AttributeModifier(
                                    MANGEKYOU_VIDA_ID,
                                    "examplemod_mangekyou_vida",
                                    bonusVida,
                                    AttributeModifier.Operation.ADDITION
                            );

                    maxHealth.addPermanentModifier(
                            modifier
                    );
                }
            }


            if (
                    velocidade != null
                            && MANGEKYOU_VELOCIDADE > 1.0
            ) {

                AttributeModifier modifier =
                        new AttributeModifier(
                                MANGEKYOU_VELOCIDADE_ID,
                                "examplemod_mangekyou_velocidade",
                                MANGEKYOU_VELOCIDADE - 1.0,
                                AttributeModifier.Operation.MULTIPLY_TOTAL
                        );

                velocidade.addPermanentModifier(
                        modifier
                );
            }

            corrigirVida(player);
            return;
        }


        // =====================================================
        // SHARINGAN
        // =====================================================

        if (!stats.isSharinganAtivo()) {

            corrigirVida(player);
            return;
        }

        int tomoe =
                stats.getSharinganTomoeAtivo();

        if (tomoe < 1) {

            corrigirVida(player);
            return;
        }


        double multForca;
        double multDefesa;
        double multVelocidade;

        if (tomoe == 1) {

            multForca =
                    SHARINGAN_1_FORCA;

            multDefesa =
                    SHARINGAN_1_DEFESA;

            multVelocidade =
                    SHARINGAN_1_VELOCIDADE;

        } else if (tomoe == 2) {

            multForca =
                    SHARINGAN_2_FORCA;

            multDefesa =
                    SHARINGAN_2_DEFESA;

            multVelocidade =
                    SHARINGAN_2_VELOCIDADE;

        } else {

            multForca =
                    SHARINGAN_3_FORCA;

            multDefesa =
                    SHARINGAN_3_DEFESA;

            multVelocidade =
                    SHARINGAN_3_VELOCIDADE;
        }


        if (ataque != null) {

            double bonus =
                    stats.getForca()
                            * (multForca - 1.0);

            AttributeModifier modifier =
                    new AttributeModifier(
                            SHARINGAN_FORCA_ID,
                            "examplemod_sharingan_forca",
                            bonus,
                            AttributeModifier.Operation.ADDITION
                    );

            ataque.addPermanentModifier(
                    modifier
            );
        }


        if (maxHealth != null) {

            double bonusVida;

            if (tomoe == 1) {

                bonusVida =
                        (stats.getDefesa() - 1)
                                * (multDefesa - 1.0)
                                * 2.0;

            } else {

                bonusVida =
                        stats.getDefesa()
                                * (multDefesa - 1.0)
                                * 2.0;
            }

            if (bonusVida > 0) {

                AttributeModifier modifier =
                        new AttributeModifier(
                                SHARINGAN_VIDA_ID,
                                "examplemod_sharingan_vida",
                                bonusVida,
                                AttributeModifier.Operation.ADDITION
                        );

                maxHealth.addPermanentModifier(
                        modifier
                );
            }
        }


        if (
                velocidade != null
                        && multVelocidade > 1.0
        ) {

            AttributeModifier modifier =
                    new AttributeModifier(
                            SHARINGAN_VELOCIDADE_ID,
                            "examplemod_sharingan_velocidade",
                            multVelocidade - 1.0,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );

            velocidade.addPermanentModifier(
                    modifier
            );
        }

        corrigirVida(player);
    }


    // =========================================================
    // BYAKUGAN NORMAL
    // =========================================================

    private static void atualizarByakugan(
            Player player,
            PlayerStats stats
    ) {

        AttributeInstance ataque =
                player.getAttribute(
                        Attributes.ATTACK_DAMAGE
                );

        AttributeInstance maxHealth =
                player.getAttribute(
                        Attributes.MAX_HEALTH
                );

        removerModifier(
                ataque,
                BYAKUGAN_FORCA_ID
        );

        removerModifier(
                maxHealth,
                BYAKUGAN_VIDA_ID
        );

        if (!stats.isByakuganAtivo()) {

            corrigirVida(player);
            return;
        }


        if (ataque != null) {

            double bonus =
                    stats.getForca()
                            * (BYAKUGAN_FORCA - 1.0);

            AttributeModifier modifier =
                    new AttributeModifier(
                            BYAKUGAN_FORCA_ID,
                            "examplemod_byakugan_forca",
                            bonus,
                            AttributeModifier.Operation.ADDITION
                    );

            ataque.addPermanentModifier(
                    modifier
            );
        }


        if (maxHealth != null) {

            double bonusVida =
                    (stats.getDefesa() - 1)
                            * (BYAKUGAN_DEFESA - 1.0)
                            * 2.0;

            if (bonusVida > 0) {

                AttributeModifier modifier =
                        new AttributeModifier(
                                BYAKUGAN_VIDA_ID,
                                "examplemod_byakugan_vida",
                                bonusVida,
                                AttributeModifier.Operation.ADDITION
                        );

                maxHealth.addPermanentModifier(
                        modifier
                );
            }
        }

        corrigirVida(player);
    }


    // =========================================================
    // BYAKUGAN DESPERTADO
    // =========================================================

    private static void atualizarByakuganDespertado(
            Player player,
            PlayerStats stats
    ) {

        AttributeInstance ataque =
                player.getAttribute(
                        Attributes.ATTACK_DAMAGE
                );

        AttributeInstance maxHealth =
                player.getAttribute(
                        Attributes.MAX_HEALTH
                );

        AttributeInstance velocidade =
                player.getAttribute(
                        Attributes.MOVEMENT_SPEED
                );


        removerModifier(
                ataque,
                BYAKUGAN_DESPERTADO_FORCA_ID
        );

        removerModifier(
                maxHealth,
                BYAKUGAN_DESPERTADO_VIDA_ID
        );

        removerModifier(
                velocidade,
                BYAKUGAN_DESPERTADO_VELOCIDADE_ID
        );


        if (!stats.isByakuganDespertadoAtivo()) {

            corrigirVida(player);
            return;
        }


        if (ataque != null) {

            double bonus =
                    stats.getForca()
                            * (BYAKUGAN_DESPERTADO_FORCA - 1.0);

            AttributeModifier modifier =
                    new AttributeModifier(
                            BYAKUGAN_DESPERTADO_FORCA_ID,
                            "examplemod_byakugan_despertado_forca",
                            bonus,
                            AttributeModifier.Operation.ADDITION
                    );

            ataque.addPermanentModifier(
                    modifier
            );
        }


        if (maxHealth != null) {

            double bonusVida =
                    stats.getDefesa()
                            * (BYAKUGAN_DESPERTADO_DEFESA - 1.0)
                            * 2.0;

            if (bonusVida > 0) {

                AttributeModifier modifier =
                        new AttributeModifier(
                                BYAKUGAN_DESPERTADO_VIDA_ID,
                                "examplemod_byakugan_despertado_vida",
                                bonusVida,
                                AttributeModifier.Operation.ADDITION
                        );

                maxHealth.addPermanentModifier(
                        modifier
                );
            }
        }


        if (
                velocidade != null
                        && BYAKUGAN_DESPERTADO_VELOCIDADE > 1.0
        ) {

            AttributeModifier modifier =
                    new AttributeModifier(
                            BYAKUGAN_DESPERTADO_VELOCIDADE_ID,
                            "examplemod_byakugan_despertado_velocidade",
                            BYAKUGAN_DESPERTADO_VELOCIDADE - 1.0,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );

            velocidade.addPermanentModifier(
                    modifier
            );
        }

        corrigirVida(player);
    }


    // =========================================================
    // MARCA DA MALDIÇÃO
    // =========================================================

    private static void atualizarMarcaDaMaldicao(
            Player player
    ) {

        AttributeInstance ataque =
                player.getAttribute(
                        Attributes.ATTACK_DAMAGE
                );

        AttributeInstance maxHealth =
                player.getAttribute(
                        Attributes.MAX_HEALTH
                );


        removerModifier(
                ataque,
                MARCA_FORCA_ID
        );

        removerModifier(
                maxHealth,
                MARCA_VIDA_ID
        );


        if (!isMarcaDaMaldicaoAtiva(player)) {

            corrigirVida(player);
            return;
        }


        // =====================================================
        // FORÇA ×4
        // =====================================================

        if (ataque != null) {

            AttributeModifier modifier =
                    new AttributeModifier(
                            MARCA_FORCA_ID,
                            "examplemod_marca_maldicao_forca",
                            MARCA_FORCA - 1.0,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );

            ataque.addPermanentModifier(
                    modifier
            );
        }


        // =====================================================
        // VIDA ×2
        // =====================================================

        if (maxHealth != null) {

            AttributeModifier modifier =
                    new AttributeModifier(
                            MARCA_VIDA_ID,
                            "examplemod_marca_maldicao_vida",
                            MARCA_VIDA - 1.0,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );

            maxHealth.addPermanentModifier(
                    modifier
            );
        }

        corrigirVida(player);
    }


    // =========================================================
    // SUSANOO
    //
    // Não altera velocidade.
    // A defesa principal vem da resistência no LivingHurtEvent.
    // =========================================================

    private static void atualizarSusanoo(
            Player player,
            PlayerStats stats
    ) {

        AttributeInstance ataque =
                player.getAttribute(
                        Attributes.ATTACK_DAMAGE
                );

        AttributeInstance maxHealth =
                player.getAttribute(
                        Attributes.MAX_HEALTH
                );


        removerModifier(
                ataque,
                SUSANOO_FORCA_ID
        );

        removerModifier(
                maxHealth,
                SUSANOO_VIDA_ID
        );


        if (!stats.isSusanooAtivo()) {

            corrigirVida(player);
            return;
        }


        int estagio =
                stats.getSusanooEstagio();

        if (
                estagio < 1
                        || estagio > 5
        ) {

            corrigirVida(player);
            return;
        }


        double multForca =
                getMultiplicadorSusanooForca(
                        estagio
                );

        double multVida =
                getMultiplicadorSusanooVida(
                        estagio
                );


        // =====================================================
        // FORÇA
        //
        // Exemplo:
        // Mangekyō ×30 + Susanoo 4 ×2 = ×60.
        // =====================================================

        if (
                ataque != null
                        && multForca > 1.0
        ) {

            AttributeModifier modifier =
                    new AttributeModifier(
                            SUSANOO_FORCA_ID,
                            "examplemod_susanoo_forca",
                            multForca - 1.0,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );

            ataque.addPermanentModifier(
                    modifier
            );
        }


        // =====================================================
        // VIDA
        //
        // Atualmente todos os estágios estão em ×1.
        // O bloco fica preparado caso você queira mudar depois.
        // =====================================================

        if (
                maxHealth != null
                        && multVida > 1.0
        ) {

            AttributeModifier modifier =
                    new AttributeModifier(
                            SUSANOO_VIDA_ID,
                            "examplemod_susanoo_vida",
                            multVida - 1.0,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );

            maxHealth.addPermanentModifier(
                    modifier
            );
        }

        corrigirVida(player);
    }


    // =========================================================
    // RESISTÊNCIA REAL
    //
    // Marca:
    // dano ÷1.5
    //
    // Susanoo 1:
    // dano ÷2
    //
    // Susanoo 2:
    // dano ÷2.75
    //
    // Susanoo 3:
    // dano ÷3.5
    //
    // Susanoo 4:
    // dano ÷4.5
    //
    // Susanoo 5:
    // dano ÷6
    // =========================================================

    @SubscribeEvent
    public static void onLivingHurt(
            LivingHurtEvent event
    ) {

        if (
                !(event.getEntity()
                        instanceof Player player)
        ) {
            return;
        }

        if (player.level().isClientSide()) {
            return;
        }

        double resistencia =
                getMultiplicadorResistencia(
                        player
                );

        if (resistencia <= 1.0) {
            return;
        }

        float danoOriginal =
                event.getAmount();

        if (danoOriginal <= 0.0F) {
            return;
        }

        float danoFinal =
                (float) (
                        danoOriginal
                                / resistencia
                );

        event.setAmount(
                danoFinal
        );
    }


    // =========================================================
    // HELPERS
    // =========================================================

    private static void removerModifier(
            AttributeInstance attribute,
            UUID id
    ) {

        if (attribute == null) {
            return;
        }

        AttributeModifier modifier =
                attribute.getModifier(id);

        if (modifier != null) {

            attribute.removeModifier(
                    modifier
            );
        }
    }


    private static void corrigirVida(
            Player player
    ) {

        if (player == null) {
            return;
        }

        if (
                player.getHealth()
                        > player.getMaxHealth()
        ) {

            player.setHealth(
                    player.getMaxHealth()
            );
        }
    }


    private PlayerStatsEffects() {
    }
}