package com.example.examplemod.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MissionRegistry {

    private static final List<Mission> MISSIONS = new ArrayList<>();

    static {

        // =========================================================
        // MISSÃO 1
        // =========================================================

        register(
                new Mission(
                        1,

                        "O Pergaminho Proibido",

                        "Iruka finalmente te alcançou na clareira da floresta e parece decepcionado. "
                                + "Ele sabe exatamente o que você fez na mansão do Hokage e exige que você "
                                + "devolva o Pergaminho dos Selos imediatamente. Você passou a noite inteira "
                                + "treinando e não vai entregar o seu passaporte para se tornar um ninja tão "
                                + "facilmente. Mostre a ele o resultado do seu esforço e derrote seu sensei "
                                + "antes que ele te leve de volta para a Academia.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "iruka",
                                1
                        ),

                        new MissionReward(
                                500,
                                100
                        )
                )
        );


        // =========================================================
        // MISSÃO 2
        // =========================================================

        register(
                new Mission(
                        2,

                        "A Traição de Mizuki",

                        "Mizuki surge das sombras revelando a verdade cruel que todos esconderam de você "
                                + "por doze anos: a Raposa de Nove Caudas está selada no seu corpo. Aproveitando "
                                + "o seu choque, ele arremessa uma shuriken gigante, mas Iruka se joga na frente "
                                + "para te salvar e fica gravemente ferido. Chega de fugir e chorar. Canalize "
                                + "todo o seu ódio, use a força do pergaminho e esmague o traidor com o seu "
                                + "novo Jutsu Clones das Sombras.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "mizuki",
                                1
                        ),

                        new MissionReward(
                                1000,
                                250
                        )
                )
        );


        // =========================================================
        // MISSÃO 3
        // =========================================================

        register(
                new Mission(
                        3,

                        "O Rival da Academia",

                        "Konohamaru continua te perseguindo pela vila, determinado a provar que é capaz de "
                                + "derrotar o ninja mais barulhento de Konoha para reivindicar o título de Hokage. "
                                + "Ele pode ser o neto do Terceiro Hokage, mas não vai conseguir o respeito de "
                                + "ninguém só usando disfarces ruins e técnicas de ocultação infantis. Aceite "
                                + "o desafio no meio da rua, ensine a ele como um verdadeiro ninja luta e mostre "
                                + "a enorme diferença de poder entre vocês dois.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "konohamaru",
                                1
                        ),

                        new MissionReward(
                                1500,
                                400
                        )
                )
        );


        // =========================================================
        // MISSÃO 4
        // =========================================================

        register(
                new Mission(
                        4,

                        "O Teste dos Sinos",

                        "Kakashi Hatake está encostado na arvore lendo o livro dele e claramente não te leva "
                                + "a sério como um shinobi de verdade. Ele colocou apenas dois sinos na cintura "
                                + "e avisou que quem não conseguir um até o meio-dia vai voltar para a Academia "
                                + "de mãos vazias. O tempo está correndo rapidamente e o seu estômago já está "
                                + "roncando de fome. Deixe o trabalho em equipe de lado por um momento, ataque "
                                + "com tudo o que tem e tome um dos sinos à força.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "kakashi",
                                1
                        ),

                        new MissionReward(
                                2000,
                                2200
                        )
                )
        );


        // =========================================================
        // MISSÃO 5
        // =========================================================

        register(
                new Mission(
                        5,

                        "Emboscada na Estrada",

                        "A missão de escolta do construtor de pontes Tazuna parecia uma caminhada tranquila "
                                + "até que uma poça d'água suspeita surgiu no caminho. Em um piscar de olhos, "
                                + "os Irmãos Demônios saíram das sombras com suas garras de aço e aparentam ter "
                                + "dilacerado Kakashi em pedaços. Tazuna está paralisado de pavor bem atrás de "
                                + "você e o medo congelou suas pernas. Supere o pânico imediatamente, saque "
                                + "sua kunai e proteja o cliente antes que a corrente mortal te alcance.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "demon_brothers",
                                2
                        ),

                        new MissionReward(
                                2500,
                                3000
                        )
                )
        );


        // =========================================================
        // MISSÃO 6
        // =========================================================

        register(
                new Mission(
                        6,

                        "O Demônio da Névoa Oculta",

                        "Zabuza Momochi surgiu entre as árvores carregando a massiva lâmina Kubikiribocho "
                                + "e cobriu todo o campo de batalha com uma névoa espessa. Para piorar a "
                                + "situação, Kakashi caiu em uma armadilha e acabou caindo na Prisão d'Água "
                                + "do renegado, deixando o time totalmente desprotegido. Você e Sasuke "
                                + "precisam combinar seus ataques usando a Shuriken do Vento Demoníaco para "
                                + "enganar um mestre assassino. Execute o plano perfeitamente, quebre a "
                                + "técnica de aprisionamento e liberte seu sensei.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "zabuza",
                                1
                        ),

                        new MissionReward(
                                6500,
                                10000
                        )
                )
        );


        // =========================================================
        // MISSÃO 7
        // =========================================================

        register(
                new Mission(
                        7,

                        "Os Espelhos de Gelo Demoníacos",

                        "Haku prendeu você e Sasuke dentro de uma cúpula assustadora formada por dezenas de "
                                + "espelhos de gelo flutuantes. Ele se move entre os reflexos em uma velocidade "
                                + "sobre-humana, arremessando agulhas mortais de todos os ângulos possíveis sem "
                                + "dar espaço para respirar. Sasuke caiu inconsciente no chão coberto de sangue "
                                + "após tentar te proteger de um ataque fatal. Deixe o poder sombrio da Kyuubi "
                                + "despertar, quebre os espelhos com o seu chakra e destrua o oponente.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "haku",
                                1
                        ),

                        new MissionReward(
                                1500,
                                1500
                        )
                )
        );


        register(
                new Mission(
                        8,

                        "Arco do Exame Chunin/O Teste de Taijutsu",

                        "Rock Lee surge no corredor antes da primeira fase e te desafia para um duelo." +
                                "Ele se move numa velocidade incrível usando apenas combate corpo a corpo." +
                                "Nem mesmo o Sharingan do Sasuke conseguiu acompanhar os movimentos rápidos dele" +
                                "Suporte a dor dos golpes diretos e tente acertar um ataque no rival",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "rocklee",
                                1
                        ),

                        new MissionReward(
                                1500,
                                1500
                        )
                )
        );



        register(
                new Mission(
                        9,

                        "O Predador na Floresta da Morte",

                        "Um shinobi misterioso te intercepta na floresta liberando uma aura mortal." +
                                "Ele invocou uma cobra gigante e engoliu o pergaminho necessário para a fase." +
                                "Sasuke está dominado pelo medo e prestes a se render sem tentar lutar." +
                                "Impeça a covardia do seu parceiro e liberte a força da Raposa contra a besta.",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "orochimaro01",
                                1
                        ),

                        new MissionReward(
                                1500,
                                1500
                        )
                )
        );





        register(
                new Mission(
                        10,

                        "A Emboscada dos Ninjas da Chuva",

                        "Ninjas da Chuva usaram genjutsu para criar ilusões e desgastar o seu grupo." +
                                "Eles se escondem no nevoeiro esperando a exaustão para roubar o pergaminho." +
                                "Proteja a Sakura a todo custo, mantenha a calma e ache os alvos reais." +
                                "Use seus clones das sombras para cercar a equipe inimiga antes do amanhecer",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "ninjasdachuva",
                                10
                        ),

                        new MissionReward(
                                1500,
                                2500
                        )
                )
        );



        register(
                new Mission(
                        11,

                        "A Batalha do Inuzuka",

                        "Kiba e Akamaru entram na arena certos de que você é o pior da Academia." +
                                "Eles usam pílulas de chakra e atacam em dupla com o devastador Gatsūga." +
                                "O cheiro de sangue toma conta do local e a plateia dá sua derrota como certa." +
                                "Supere a velocidade da dupla e finalize a luta com um combo inesquecível",

                        new MissionObjective(
                                MissionObjective.Type.KILL,
                                "kiba",
                                1
                        ),

                        new MissionReward(
                                1500,
                                500
                        )
                )
        );




























































































































































































































































































































































































































































    }

















































































































































































































































    // =========================================================
    // REGISTRAR MISSÃO
    // =========================================================

    private static void register(Mission mission) {
        MISSIONS.add(mission);
    }


    // =========================================================
    // PEGAR MISSÃO PELO ID
    // =========================================================

    public static Mission getMission(int id) {

        for (Mission mission : MISSIONS) {

            if (mission.getId() == id) {
                return mission;
            }
        }

        return null;
    }















    // =========================================================
    // TODAS AS MISSÕES
    // =========================================================

    public static List<Mission> getAllMissions() {
        return Collections.unmodifiableList(MISSIONS);
    }
}