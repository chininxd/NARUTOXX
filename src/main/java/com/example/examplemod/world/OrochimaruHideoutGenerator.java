package com.example.examplemod.world;

import com.example.examplemod.NpcsVendedores.ModNPCsVendedores;
import com.example.examplemod.NpcsVendedores.OrochimaruVendedorEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;
import java.util.Random;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class OrochimaruHideoutGenerator {

    // =========================================================
    // TAMANHO DA ESTRUTURA
    // NBT = 18 x 17 x 25
    // =========================================================

    private static final int LARGURA_ESTRUTURA = 18;
    private static final int ALTURA_ESTRUTURA = 17;
    private static final int COMPRIMENTO_ESTRUTURA = 25;

    // =========================================================
    // QUANTOS BLOCOS FICAM PARA FORA DA TERRA
    // =========================================================

    private static final int ALTURA_VISIVEL = 5;

    // =========================================================
    // DIFERENÇA MÁXIMA DE ALTURA DO TERRENO
    // =========================================================

    private static final int DIFERENCA_MAXIMA_TERRENO = 5;

    // =========================================================
    // ÁREA DE BUSCA
    //
    // Pode gerar até 5000 blocos do spawn
    // =========================================================

    private static final int DISTANCIA_MAXIMA = 5000;

    // Não deixa gerar colado no spawn
    private static final int DISTANCIA_MINIMA = 300;

    // Quantas posições aleatórias serão testadas
    private static final int MAX_TENTATIVAS = 1000;


    // =========================================================
    // POSIÇÃO ATUAL
    // =========================================================

    private static BlockPos posicaoEstrutura = null;


    // =========================================================
    // SERVIDOR INICIOU
    // =========================================================

    @SubscribeEvent
    public static void onServerStarted(
            ServerStartedEvent event
    ) {

        MinecraftServer server =
                event.getServer();

        ServerLevel level =
                server.overworld();


        // =====================================================
        // CARREGA OS DADOS DESSE MUNDO
        // =====================================================

        OrochimaruHideoutData data =
                OrochimaruHideoutData.get(level);


        // =====================================================
        // JÁ EXISTE NESSE MUNDO?
        // =====================================================

        if (data.isGerado()) {

            posicaoEstrutura =
                    data.getPosicao();

            System.out.println(
                    "[NarutoX] Esconderijo do Orochimaru já existe."
            );

            System.out.println(
                    "[NarutoX] Coordenadas: "
                            + posicaoEstrutura.getX()
                            + ", "
                            + posicaoEstrutura.getY()
                            + ", "
                            + posicaoEstrutura.getZ()
            );

            return;
        }


        // =====================================================
        // PEGA O NBT
        // =====================================================

        ResourceLocation estrutura =
                new ResourceLocation(
                        "examplemod",
                        "esconderijo_orochimaru"
                );


        Optional<StructureTemplate> templateOptional =
                level.getStructureManager()
                        .get(estrutura);


        if (templateOptional.isEmpty()) {

            System.out.println(
                    "[NarutoX] ERRO: esconderijo_orochimaru.nbt não foi encontrado!"
            );

            return;
        }


        StructureTemplate template =
                templateOptional.get();


        // =====================================================
        // SPAWN DO MUNDO
        // =====================================================

        BlockPos spawn =
                level.getSharedSpawnPos();


        Random random =
                new Random();


        // =====================================================
        // PROCURA UM LOCAL BOM
        // =====================================================

        BlockPos terrenoEncontrado =
                procurarLocalAdequado(
                        level,
                        spawn,
                        random
                );


        if (terrenoEncontrado == null) {

            System.out.println(
                    "[NarutoX] ERRO: nenhum terreno adequado foi encontrado."
            );

            return;
        }


        // =====================================================
        // ALTURA DA SUPERFÍCIE
        // =====================================================

        int terrenoY =
                terrenoEncontrado.getY();


        // =====================================================
        // ENTERRA A ESTRUTURA
        //
        // 17 total
        // 5 para fora
        // 12 enterrados
        // =====================================================

        int blocosEnterrados =
                ALTURA_ESTRUTURA
                        - ALTURA_VISIVEL;


        int estruturaY =
                terrenoY
                        - blocosEnterrados;


        // =====================================================
        // PROTEÇÃO CONTRA BEDROCK
        // =====================================================

        int yMinimoSeguro =
                level.getMinBuildHeight()
                        + 20;


        if (estruturaY < yMinimoSeguro) {

            System.out.println(
                    "[NarutoX] ERRO: posição subterrânea muito baixa."
            );

            return;
        }


        BlockPos posicao =
                new BlockPos(
                        terrenoEncontrado.getX(),
                        estruturaY,
                        terrenoEncontrado.getZ()
                );


        // =====================================================
        // CONFIGURAÇÃO
        // =====================================================

        StructurePlaceSettings settings =
                new StructurePlaceSettings();


        // =====================================================
        // COLOCA A ESTRUTURA
        // =====================================================

        boolean colocou =
                template.placeInWorld(
                        level,
                        posicao,
                        posicao,
                        settings,
                        level.getRandom(),
                        2
                );


        if (!colocou) {

            System.out.println(
                    "[NarutoX] ERRO: não foi possível colocar o esconderijo!"
            );

            return;
        }


        // =====================================================
        // SALVA A POSIÇÃO
        // =====================================================

        posicaoEstrutura =
                posicao;


        data.definirPosicao(
                posicao
        );


        level.getDataStorage()
                .save();


        // =====================================================
        // CRIA OROCHIMARU
        // =====================================================

        OrochimaruVendedorEntity orochimaru =
                ModNPCsVendedores.OROCHIMARU
                        .get()
                        .create(level);


        if (orochimaru != null) {

            // =================================================
            // POSIÇÃO DENTRO DO NBT
            // =================================================

            double npcX =
                    posicao.getX()
                            + 14.0D;

            double npcY =
                    posicao.getY()
                            + 2.0D;

            double npcZ =
                    posicao.getZ()
                            + 21.0D;


            orochimaru.moveTo(
                    npcX,
                    npcY,
                    npcZ,
                    0.0F,
                    0.0F
            );


            level.addFreshEntity(
                    orochimaru
            );


            System.out.println(
                    "[NarutoX] Orochimaru criado em: "
                            + npcX
                            + ", "
                            + npcY
                            + ", "
                            + npcZ
            );
        }


        // =====================================================
        // LOG
        // =====================================================

        System.out.println(
                "[NarutoX] Esconderijo gerado pela PRIMEIRA VEZ em: "
                        + posicao.getX()
                        + ", "
                        + posicao.getY()
                        + ", "
                        + posicao.getZ()
        );

        System.out.println(
                "[NarutoX] Superfície Y: "
                        + terrenoY
        );

        System.out.println(
                "[NarutoX] Blocos enterrados: "
                        + blocosEnterrados
        );

        System.out.println(
                "[NarutoX] Blocos para fora: "
                        + ALTURA_VISIVEL
        );
    }


    // =========================================================
    // PROCURA UM LOCAL ADEQUADO
    // =========================================================

    private static BlockPos procurarLocalAdequado(
            ServerLevel level,
            BlockPos spawn,
            Random random
    ) {

        for (
                int tentativa = 1;
                tentativa <= MAX_TENTATIVAS;
                tentativa++
        ) {

            // =================================================
            // POSIÇÃO ALEATÓRIA
            // =================================================

            int offsetX =
                    random.nextInt(
                            DISTANCIA_MAXIMA * 2 + 1
                    ) - DISTANCIA_MAXIMA;


            int offsetZ =
                    random.nextInt(
                            DISTANCIA_MAXIMA * 2 + 1
                    ) - DISTANCIA_MAXIMA;


            // =================================================
            // NÃO DEIXA MUITO PERTO DO SPAWN
            // =================================================

            if (
                    Math.abs(offsetX) < DISTANCIA_MINIMA
                            && Math.abs(offsetZ) < DISTANCIA_MINIMA
            ) {

                continue;
            }


            int x =
                    spawn.getX()
                            + offsetX;

            int z =
                    spawn.getZ()
                            + offsetZ;


            // =================================================
            // CARREGA OS CHUNKS DA ÁREA
            // =================================================

            int chunkMinX =
                    x >> 4;

            int chunkMinZ =
                    z >> 4;


            int chunkMaxX =
                    (x + LARGURA_ESTRUTURA - 1)
                            >> 4;

            int chunkMaxZ =
                    (z + COMPRIMENTO_ESTRUTURA - 1)
                            >> 4;


            for (
                    int chunkX = chunkMinX;
                    chunkX <= chunkMaxX;
                    chunkX++
            ) {

                for (
                        int chunkZ = chunkMinZ;
                        chunkZ <= chunkMaxZ;
                        chunkZ++
                ) {

                    level.getChunk(
                            chunkX,
                            chunkZ
                    );
                }
            }


            // =================================================
            // ANALISA A ÁREA INTEIRA
            // =================================================

            int menorY =
                    Integer.MAX_VALUE;

            int maiorY =
                    Integer.MIN_VALUE;


            boolean encontrouAgua =
                    false;


            boolean encontrouTerrenoRuim =
                    false;


            for (
                    int dx = 0;
                    dx < LARGURA_ESTRUTURA;
                    dx++
            ) {

                for (
                        int dz = 0;
                        dz < COMPRIMENTO_ESTRUTURA;
                        dz++
                ) {

                    int atualX =
                            x + dx;

                    int atualZ =
                            z + dz;


                    // =========================================
                    // PEGA ALTURA DA SUPERFÍCIE
                    // =========================================

                    int altura =
                            level.getHeight(
                                    Heightmap.Types.WORLD_SURFACE,
                                    atualX,
                                    atualZ
                            );


                    // =========================================
                    // PROTEÇÃO CONTRA ALTURA INVÁLIDA
                    // =========================================

                    if (
                            altura
                                    <= level.getMinBuildHeight()
                                    + 30
                    ) {

                        encontrouTerrenoRuim =
                                true;

                        break;
                    }


                    // =========================================
                    // BLOCO DA SUPERFÍCIE
                    // =========================================

                    BlockPos superficie =
                            new BlockPos(
                                    atualX,
                                    altura - 1,
                                    atualZ
                            );


                    BlockState estadoSuperficie =
                            level.getBlockState(
                                    superficie
                            );


                    // =========================================
                    // NUNCA ACEITA ÁGUA
                    // =========================================

                    if (
                            !estadoSuperficie
                                    .getFluidState()
                                    .isEmpty()
                    ) {

                        encontrouAgua =
                                true;

                        break;
                    }


                    if (
                            estadoSuperficie
                                    .getFluidState()
                                    .is(FluidTags.WATER)
                    ) {

                        encontrouAgua =
                                true;

                        break;
                    }


                    // =========================================
                    // VERIFICA O BLOCO ACIMA TAMBÉM
                    // =========================================

                    BlockPos blocoAcima =
                            superficie.above();


                    if (
                            !level.getFluidState(
                                    blocoAcima
                            ).isEmpty()
                    ) {

                        encontrouAgua =
                                true;

                        break;
                    }


                    // =========================================
                    // GUARDA ALTURAS
                    // =========================================

                    if (altura < menorY) {

                        menorY =
                                altura;
                    }


                    if (altura > maiorY) {

                        maiorY =
                                altura;
                    }
                }


                if (
                        encontrouAgua
                                || encontrouTerrenoRuim
                ) {

                    break;
                }
            }


            // =================================================
            // SE ACHOU ÁGUA, DESCARTA
            // =================================================

            if (encontrouAgua) {

                continue;
            }


            // =================================================
            // TERRENO INVÁLIDO
            // =================================================

            if (encontrouTerrenoRuim) {

                continue;
            }


            // =================================================
            // GARANTIA EXTRA
            // =================================================

            if (
                    menorY == Integer.MAX_VALUE
                            || maiorY == Integer.MIN_VALUE
            ) {

                continue;
            }


            // =================================================
            // VERIFICA INCLINAÇÃO DO TERRENO
            // =================================================

            int diferencaAltura =
                    maiorY
                            - menorY;


            if (
                    diferencaAltura
                            > DIFERENCA_MAXIMA_TERRENO
            ) {

                continue;
            }


            // =================================================
            // VERIFICA NOVAMENTE O CENTRO
            // =================================================

            int centroX =
                    x
                            + LARGURA_ESTRUTURA / 2;

            int centroZ =
                    z
                            + COMPRIMENTO_ESTRUTURA / 2;


            int centroY =
                    level.getHeight(
                            Heightmap.Types.WORLD_SURFACE,
                            centroX,
                            centroZ
                    );


            BlockPos centroSuperficie =
                    new BlockPos(
                            centroX,
                            centroY - 1,
                            centroZ
                    );


            if (
                    !level.getFluidState(
                            centroSuperficie
                    ).isEmpty()
            ) {

                continue;
            }


            // =================================================
            // LOCAL APROVADO
            // =================================================

            System.out.println(
                    "[NarutoX] Local encontrado na tentativa "
                            + tentativa
            );

            System.out.println(
                    "[NarutoX] X: "
                            + x
                            + " Z: "
                            + z
            );

            System.out.println(
                    "[NarutoX] Menor Y: "
                            + menorY
            );

            System.out.println(
                    "[NarutoX] Maior Y: "
                            + maiorY
            );

            System.out.println(
                    "[NarutoX] Diferença do terreno: "
                            + diferencaAltura
            );


            // Usa a menor altura da área para não deixar
            // a estrutura flutuando em partes mais baixas.
            return new BlockPos(
                    x,
                    menorY,
                    z
            );
        }


        // =====================================================
        // NÃO ACHOU
        // =====================================================

        return null;
    }


    // =========================================================
    // PEGAR POSIÇÃO
    // =========================================================

    public static BlockPos getPosicaoEstrutura() {

        return posicaoEstrutura;
    }


    // =========================================================
    // VERIFICAR SE EXISTE
    // =========================================================

    public static boolean isGerado() {

        return posicaoEstrutura != null;
    }
}