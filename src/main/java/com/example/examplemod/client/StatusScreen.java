package com.example.examplemod.client;

import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.network.SpendStatPacket;
import com.example.examplemod.network.TechniqueStatePacket;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsEffects;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import org.lwjgl.glfw.GLFW;

public class StatusScreen extends Screen {

    private static final ResourceLocation MENU_BACKGROUND =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/backgrounds/menu_background.png"
            );

    private static final ResourceLocation UP_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/up_button.png"
            );

    private static final ResourceLocation UP_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/up_button_hover.png"
            );

    private static final ResourceLocation STATUS_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/status_button.png"
            );

    private static final ResourceLocation STATUS_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/status_button_hover.png"
            );

    private static final ResourceLocation MISSION_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/mission_button.png"
            );

    private static final ResourceLocation MISSION_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/mission_button_hover.png"
            );

    private static final ResourceLocation DOJUTSU_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/dojutsu_button.png"
            );

    private static final ResourceLocation DOJUTSU_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/dojutsu_button_hover.png"
            );

    private final int panelWidth = 400;
    private final int panelHeight = 200;

    private int left;
    private int top;
    private int right;
    private int bottom;

    private int quantidadeSelecionada = 1;

    private int scrollOffset = 0;
    private boolean arrastandoScroll = false;
    private int scrollMouseOffset = 0;

    private static final int SCROLL_MAX = 42;

    public StatusScreen() {

        super(
                Component.literal(
                        "Estatísticas"
                )
        );
    }

    @Override
    protected void init() {

        super.init();

        left =
                (this.width - panelWidth) / 2;

        top =
                (this.height - panelHeight) / 2;

        right =
                left + panelWidth;

        bottom =
                top + panelHeight;
    }

    @Override
    public void render(
            GuiGraphics gui,
            int mouseX,
            int mouseY,
            float partialTick
    ) {

        Player player =
                Minecraft.getInstance().player;

        if (
                player == null
        ) {

            return;
        }

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
        // MULTIPLICADORES
        // =====================================================

        double multiplicadorForca =
                calcularMultiplicadorForca(
                        stats
                );

        double multiplicadorDefesa =
                calcularMultiplicadorDefesa(
                        stats
                );

        double multiplicadorChakra =
                calcularMultiplicadorChakra(
                        stats
                );

        double multiplicadorMente =
                1.0;

        double multiplicadorResistencia =
                calcularMultiplicadorResistencia(
                        stats
                );

        // =====================================================
        // FUNDO
        // =====================================================

        drawNineSliceBackground(
                gui,
                MENU_BACKGROUND,
                left,
                top,
                panelWidth,
                panelHeight
        );

        // =====================================================
        // ÁREA DE SCROLL
        // =====================================================

        int areaTop =
                top + 25;

        int areaBottom =
                bottom - 10;

        gui.enableScissor(
                left + 8,
                areaTop,
                right - 8,
                areaBottom
        );

        // =====================================================
        // INFORMAÇÕES
        // =====================================================

        int textX =
                left + 18;

        int textY =
                top
                        + 32
                        - scrollOffset;

        drawLabelValue(
                gui,
                "Nível:",
                String.valueOf(
                        stats.getNivel()
                ),
                textX,
                textY
        );

        gui.drawString(
                this.font,
                Component.literal(
                        "[0/100]"
                ),
                textX + 55,
                textY,
                0xFF777777
        );

        drawLabelValue(
                gui,
                "Pontos:",
                String.valueOf(
                        stats.getPontos()
                ),
                textX,
                textY + 12
        );

        drawLabelValue(
                gui,
                "Rank:",
                stats.getLinhagem(),
                textX,
                textY + 24
        );

        drawLabelValue(
                gui,
                "Clã:",
                stats.getCla(),
                textX,
                textY + 36
        );

        drawLabelValue(
                gui,
                "Alinhamento:",
                stats.getAlinhamento(),
                textX,
                textY + 48
        );

        // =====================================================
        // ATRIBUTOS
        // =====================================================

        int attributeY =
                top
                        + 92
                        - scrollOffset;

        gui.drawString(
                this.font,
                Component.literal(
                        "Atributos:"
                ),
                textX,
                attributeY,
                0xFFFFFFFF
        );

        // =====================================================
        // QUANTIDADE
        // =====================================================

        int quantidadeY =
                attributeY + 13;

        drawUpButton(
                gui,
                textX,
                quantidadeY,
                mouseX,
                mouseY
        );

        gui.drawString(
                this.font,
                Component.literal(
                        "Quantidade:"
                ),
                textX + 21,
                quantidadeY,
                0xFFFFFFFF
        );

        gui.drawString(
                this.font,
                Component.literal(
                        quantidadeSelecionada
                                + "x"
                ),
                textX + 90,
                quantidadeY,
                0xFFFFFFFF
        );

        // =====================================================
        // CUSTO
        // =====================================================

        int custo =
                stats.getCustoUpgrades(
                        quantidadeSelecionada
                );

        gui.drawString(
                this.font,
                Component.literal(
                        "Custo: "
                                + custo
                                + " pontos"
                ),
                textX,
                quantidadeY + 18,
                custo <= stats.getPontos()
                        ? 0xFFFFFFFF
                        : 0xFFFF5555
        );

        // =====================================================
        // ATRIBUTOS
        // =====================================================

        int atributosInicio =
                quantidadeY + 37;

        // =====================================================
        // FORÇA
        // =====================================================

        drawAttribute(
                gui,
                textX,
                atributosInicio,
                "Força",
                String.valueOf(
                        stats.getForca()
                ),
                multiplicadorForca,
                custo,
                mouseX,
                mouseY
        );

        // =====================================================
        // DEFESA
        // =====================================================

        drawAttribute(
                gui,
                textX,
                atributosInicio + 17,
                "Defesa",
                String.valueOf(
                        stats.getDefesa()
                ),
                multiplicadorDefesa,
                custo,
                mouseX,
                mouseY
        );

        // =====================================================
        // CHAKARA
        // =====================================================

        drawAttribute(
                gui,
                textX,
                atributosInicio + 34,
                "Chakara",
                String.valueOf(
                        stats.getChakara()
                ),
                multiplicadorChakra,
                custo,
                mouseX,
                mouseY
        );

        // =====================================================
        // MENTE
        // =====================================================

        drawAttribute(
                gui,
                textX,
                atributosInicio + 51,
                "Mente",
                String.valueOf(
                        stats.getMente()
                ),
                multiplicadorMente,
                custo,
                mouseX,
                mouseY
        );

        // =====================================================
        // RESISTÊNCIA
        //
        // SUSANOO 1:
        // FORÇA ×1
        // MAS RESISTÊNCIA DANO ÷2
        // =====================================================

        drawResistencia(
                gui,
                textX,
                atributosInicio + 68,
                multiplicadorResistencia,
                stats
        );

        gui.disableScissor();

        // =====================================================
        // TÍTULO
        // =====================================================

        gui.drawCenteredString(
                this.font,
                Component.literal(
                        "Estatísticas"
                ),
                left + panelWidth / 2,
                top + 9,
                0xFFFFFFFF
        );

        // =====================================================
        // FECHAR
        // =====================================================

        gui.drawString(
                this.font,
                Component.literal(
                        "×"
                ),
                right - 20,
                top + 7,
                0xFFFFFFFF
        );

        // =====================================================
        // ABAS
        // =====================================================

        drawTabs(
                gui,
                mouseX,
                mouseY
        );

        // =====================================================
        // PLAYER
        // =====================================================

        renderPlayer(
                gui,
                mouseX,
                mouseY
        );

        // =====================================================
        // BARRA DE SCROLL
        // =====================================================

        int scrollX =
                right - 13;

        int scrollTop =
                top + 30;

        int scrollBottom =
                bottom - 20;

        gui.fill(
                scrollX,
                scrollTop,
                scrollX + 3,
                scrollBottom,
                0xFF222222
        );

        int cursorHeight =
                60;

        int cursorRange =
                (
                        scrollBottom
                                - scrollTop
                )
                        - cursorHeight;

        int cursorY =
                scrollTop
                        + (int) (
                        (
                                scrollOffset
                                        / (double) SCROLL_MAX
                        )
                                * cursorRange
                );

        gui.fill(
                scrollX,
                cursorY,
                scrollX + 3,
                cursorY + cursorHeight,
                0xFFFFFFFF
        );
    }

    // =========================================================
    // MULTIPLICADOR FORÇA
    //
    // DOJUTSU × MARCA × SUSANOO
    // =========================================================

    private double calcularMultiplicadorForca(
            PlayerStats stats
    ) {

        double multiplicador =
                1.0;

        // =====================================================
        // MANGEKYOU
        // =====================================================

        if (
                stats.isMangekyouAtivo()
        ) {

            multiplicador =
                    PlayerStatsEffects
                            .MANGEKYOU_FORCA;
        }

        // =====================================================
        // BYAKUGAN DESPERTADO
        // =====================================================

        else if (
                stats.isByakuganDespertadoAtivo()
        ) {

            multiplicador =
                    PlayerStatsEffects
                            .BYAKUGAN_DESPERTADO_FORCA;
        }

        // =====================================================
        // SHARINGAN
        // =====================================================

        else if (
                stats.isSharinganAtivo()
        ) {

            int tomoe =
                    stats.getSharinganTomoeAtivo();

            if (
                    tomoe == 1
            ) {

                multiplicador =
                        PlayerStatsEffects
                                .SHARINGAN_1_FORCA;

            } else if (
                    tomoe == 2
            ) {

                multiplicador =
                        PlayerStatsEffects
                                .SHARINGAN_2_FORCA;

            } else if (
                    tomoe == 3
            ) {

                multiplicador =
                        PlayerStatsEffects
                                .SHARINGAN_3_FORCA;
            }
        }

        // =====================================================
        // BYAKUGAN
        // =====================================================

        else if (
                stats.isByakuganAtivo()
        ) {

            multiplicador =
                    PlayerStatsEffects
                            .BYAKUGAN_FORCA;
        }

        // =====================================================
        // MARCA DA MALDIÇÃO
        //
        // MULTIPLICA.
        // NÃO SOMA.
        // =====================================================

        if (
                TechniqueStatePacket
                        .isClientMarcaDaMaldicaoAtiva()
        ) {

            multiplicador *=
                    PlayerStatsEffects
                            .MARCA_FORCA;
        }

        // =====================================================
        // SUSANOO
        // =====================================================

        if (
                isSusanooAtivo(
                        stats
                )
        ) {

            int estagio =
                    getSusanooEstagioAtual(
                            stats
                    );

            switch (
                    estagio
            ) {

                case 1 ->

                        multiplicador *=
                                PlayerStatsEffects
                                        .SUSANOO_1_FORCA;

                case 2 ->

                        multiplicador *=
                                PlayerStatsEffects
                                        .SUSANOO_2_FORCA;

                case 3 ->

                        multiplicador *=
                                PlayerStatsEffects
                                        .SUSANOO_3_FORCA;

                case 4 ->

                        multiplicador *=
                                PlayerStatsEffects
                                        .SUSANOO_4_FORCA;

                case 5 ->

                        multiplicador *=
                                PlayerStatsEffects
                                        .SUSANOO_5_FORCA;

                default -> {
                }
            }
        }

        return multiplicador;
    }

    // =========================================================
    // MULTIPLICADOR DEFESA
    // =========================================================

    private double calcularMultiplicadorDefesa(
            PlayerStats stats
    ) {

        if (
                stats.isMangekyouAtivo()
        ) {

            return PlayerStatsEffects
                    .MANGEKYOU_DEFESA;
        }

        if (
                stats.isByakuganDespertadoAtivo()
        ) {

            return PlayerStatsEffects
                    .BYAKUGAN_DESPERTADO_DEFESA;
        }

        if (
                stats.isSharinganAtivo()
        ) {

            int tomoe =
                    stats.getSharinganTomoeAtivo();

            if (
                    tomoe == 1
            ) {

                return PlayerStatsEffects
                        .SHARINGAN_1_DEFESA;
            }

            if (
                    tomoe == 2
            ) {

                return PlayerStatsEffects
                        .SHARINGAN_2_DEFESA;
            }

            if (
                    tomoe == 3
            ) {

                return PlayerStatsEffects
                        .SHARINGAN_3_DEFESA;
            }
        }

        if (
                stats.isByakuganAtivo()
        ) {

            return PlayerStatsEffects
                    .BYAKUGAN_DEFESA;
        }

        return 1.0;
    }

    // =========================================================
    // RESISTÊNCIA
    //
    // MARCA:
    // DANO ÷1.5
    //
    // SUSANOO:
    //
    // 1 = ÷2
    // 2 = ÷2.75
    // 3 = ÷3.5
    // 4 = ÷4.5
    // 5 = ÷6
    //
    // MARCA E SUSANOO NÃO MULTIPLICAM ENTRE SI.
    // USA O MAIOR.
    // =========================================================

    private double calcularMultiplicadorResistencia(
            PlayerStats stats
    ) {

        double resistencia =
                1.0;

        // =====================================================
        // MARCA
        // =====================================================

        if (
                TechniqueStatePacket
                        .isClientMarcaDaMaldicaoAtiva()
        ) {

            resistencia =
                    Math.max(
                            resistencia,
                            PlayerStatsEffects
                                    .MARCA_RESISTENCIA
                    );
        }

        // =====================================================
        // SUSANOO
        // =====================================================

        if (
                isSusanooAtivo(
                        stats
                )
        ) {

            int estagio =
                    getSusanooEstagioAtual(
                            stats
                    );

            double resistenciaSusanoo =
                    1.0;

            switch (
                    estagio
            ) {

                case 1 ->

                        resistenciaSusanoo =
                                PlayerStatsEffects
                                        .SUSANOO_1_RESISTENCIA;

                case 2 ->

                        resistenciaSusanoo =
                                PlayerStatsEffects
                                        .SUSANOO_2_RESISTENCIA;

                case 3 ->

                        resistenciaSusanoo =
                                PlayerStatsEffects
                                        .SUSANOO_3_RESISTENCIA;

                case 4 ->

                        resistenciaSusanoo =
                                PlayerStatsEffects
                                        .SUSANOO_4_RESISTENCIA;

                case 5 ->

                        resistenciaSusanoo =
                                PlayerStatsEffects
                                        .SUSANOO_5_RESISTENCIA;

                default -> {
                }
            }

            resistencia =
                    Math.max(
                            resistencia,
                            resistenciaSusanoo
                    );
        }

        return resistencia;
    }

    // =========================================================
    // CHAKRA
    // =========================================================

    private double calcularMultiplicadorChakra(
            PlayerStats stats
    ) {

        return 1.0;
    }

    // =========================================================
    // SUSANOO ATIVO
    // =========================================================

    private boolean isSusanooAtivo(
            PlayerStats stats
    ) {

        return TechniqueStatePacket
                .isClientSusanooAtivo()
                || stats.isSusanooAtivo();
    }

    // =========================================================
    // PEGAR ESTÁGIO ATUAL
    //
    // USA O MAIOR ENTRE PACKET E PLAYERSTATS
    // PARA EVITAR CACHE ATRASADO.
    // =========================================================

    private int getSusanooEstagioAtual(
            PlayerStats stats
    ) {

        int estagioPacket =
                TechniqueStatePacket
                        .getClientSusanooEstagio();

        int estagioStats =
                stats.getSusanooEstagio();

        int estagio =
                Math.max(
                        estagioPacket,
                        estagioStats
                );

        return Math.max(
                0,
                Math.min(
                        5,
                        estagio
                )
        );
    }

    // =========================================================
    // NINE SLICE
    // =========================================================

    private void drawNineSliceBackground(
            GuiGraphics gui,
            ResourceLocation texture,
            int x,
            int y,
            int width,
            int height
    ) {

        final int textureSize =
                32;

        final int border =
                14;

        int centerSourceWidth =
                textureSize
                        - border * 2;

        int centerSourceHeight =
                textureSize
                        - border * 2;

        int centerWidth =
                width
                        - border * 2;

        int centerHeight =
                height
                        - border * 2;

        // =====================================================
        // CANTO SUPERIOR ESQUERDO
        // =====================================================

        gui.blit(
                texture,
                x,
                y,
                border,
                border,
                0,
                0,
                border,
                border,
                textureSize,
                textureSize
        );

        // =====================================================
        // CANTO SUPERIOR DIREITO
        // =====================================================

        gui.blit(
                texture,
                x + width - border,
                y,
                border,
                border,
                textureSize - border,
                0,
                border,
                border,
                textureSize,
                textureSize
        );

        // =====================================================
        // CANTO INFERIOR ESQUERDO
        // =====================================================

        gui.blit(
                texture,
                x,
                y + height - border,
                border,
                border,
                0,
                textureSize - border,
                border,
                border,
                textureSize,
                textureSize
        );

        // =====================================================
        // CANTO INFERIOR DIREITO
        // =====================================================

        gui.blit(
                texture,
                x + width - border,
                y + height - border,
                border,
                border,
                textureSize - border,
                textureSize - border,
                border,
                border,
                textureSize,
                textureSize
        );

        // =====================================================
        // TOPO
        // =====================================================

        gui.blit(
                texture,
                x + border,
                y,
                centerWidth,
                border,
                border,
                0,
                centerSourceWidth,
                border,
                textureSize,
                textureSize
        );

        // =====================================================
        // BAIXO
        // =====================================================

        gui.blit(
                texture,
                x + border,
                y + height - border,
                centerWidth,
                border,
                border,
                textureSize - border,
                centerSourceWidth,
                border,
                textureSize,
                textureSize
        );

        // =====================================================
        // ESQUERDA
        // =====================================================

        gui.blit(
                texture,
                x,
                y + border,
                border,
                centerHeight,
                0,
                border,
                border,
                centerSourceHeight,
                textureSize,
                textureSize
        );

        // =====================================================
        // DIREITA
        // =====================================================

        gui.blit(
                texture,
                x + width - border,
                y + border,
                border,
                centerHeight,
                textureSize - border,
                border,
                border,
                centerSourceHeight,
                textureSize,
                textureSize
        );

        // =====================================================
        // CENTRO
        // =====================================================

        gui.blit(
                texture,
                x + border,
                y + border,
                centerWidth,
                centerHeight,
                border,
                border,
                centerSourceWidth,
                centerSourceHeight,
                textureSize,
                textureSize
        );
    }

    // =========================================================
    // ABAS
    // =========================================================

    private void drawTabs(
            GuiGraphics gui,
            int mouseX,
            int mouseY
    ) {

        int tabX =
                left - 24;

        int tabY =
                top + 25;

        int buttonWidth =
                24;

        int buttonHeight =
                22;

        for (
                int i = 0;
                i < 4;
                i++
        ) {

            int y =
                    tabY
                            + i * 28;

            boolean hover =
                    mouseX >= tabX
                            && mouseX <=
                            tabX + buttonWidth
                            && mouseY >= y
                            && mouseY <=
                            y + buttonHeight;

            ResourceLocation normal;
            ResourceLocation hoverTexture;

            switch (
                    i
            ) {

                case 0 -> {

                    normal =
                            STATUS_BUTTON;

                    hoverTexture =
                            STATUS_BUTTON_HOVER;
                }

                case 1 -> {

                    normal =
                            MISSION_BUTTON;

                    hoverTexture =
                            MISSION_BUTTON_HOVER;
                }

                case 2 -> {

                    normal =
                            DOJUTSU_BUTTON;

                    hoverTexture =
                            DOJUTSU_BUTTON_HOVER;
                }

                default -> {

                    normal =
                            STATUS_BUTTON;

                    hoverTexture =
                            STATUS_BUTTON_HOVER;
                }
            }

            gui.blit(
                    hover
                            ? hoverTexture
                            : normal,
                    tabX,
                    y,
                    0,
                    0,
                    buttonWidth,
                    buttonHeight,
                    buttonWidth,
                    buttonHeight
            );
        }
    }

    // =========================================================
    // LABEL
    // =========================================================

    private void drawLabelValue(
            GuiGraphics gui,
            String label,
            String value,
            int x,
            int y
    ) {

        gui.drawString(
                this.font,
                Component.literal(
                        label
                ),
                x,
                y,
                0xFFFFFFFF
        );

        int width =
                this.font.width(
                        label
                );

        gui.drawString(
                this.font,
                Component.literal(
                        value
                ),
                x + width + 5,
                y,
                0xFFFFFFFF
        );
    }

    // =========================================================
    // BOTÃO +
    // =========================================================

    private void drawUpButton(
            GuiGraphics gui,
            int x,
            int y,
            int mouseX,
            int mouseY
    ) {

        boolean hover =
                mouseX >= x
                        && mouseX <= x + 14
                        && mouseY >= y - 2
                        && mouseY <= y + 13;

        gui.blit(
                hover
                        ? UP_BUTTON_HOVER
                        : UP_BUTTON,
                x,
                y - 2,
                0,
                0,
                14,
                14,
                14,
                14
        );
    }

    // =========================================================
    // ATRIBUTO
    // =========================================================

    private void drawAttribute(
            GuiGraphics gui,
            int x,
            int y,
            String name,
            String value,
            double multiplicador,
            int custo,
            int mouseX,
            int mouseY
    ) {

        boolean hover =
                mouseX >= x
                        && mouseX <= x + 14
                        && mouseY >= y - 2
                        && mouseY <= y + 13;

        gui.blit(
                hover
                        ? UP_BUTTON_HOVER
                        : UP_BUTTON,
                x,
                y - 2,
                0,
                0,
                14,
                14,
                14,
                14
        );

        // =====================================================
        // NOME
        // =====================================================

        gui.drawString(
                this.font,
                Component.literal(
                        name
                ),
                x + 21,
                y,
                0xFFFFFFFF
        );

        int nameWidth =
                this.font.width(
                        name
                );

        int valueX =
                x
                        + 27
                        + nameWidth;

        // =====================================================
        // VALOR
        // =====================================================

        gui.drawString(
                this.font,
                Component.literal(
                        value
                ),
                valueX,
                y,
                0xFFFFFFFF
        );

        // =====================================================
        // MULTIPLICADOR
        // =====================================================

        if (
                multiplicador > 1.0
        ) {

            int valueWidth =
                    this.font.width(
                            value
                    );

            gui.drawString(
                    this.font,
                    Component.literal(
                            formatarMultiplicador(
                                    multiplicador
                            )
                    ),
                    valueX
                            + valueWidth
                            + 6,
                    y,
                    0xFFFF5555
            );
        }

        // =====================================================
        // CUSTO
        // =====================================================

        Player player =
                Minecraft.getInstance().player;

        if (
                player != null
        ) {

            PlayerStats stats =
                    PlayerStatsManager.get(
                            player
                    );

            if (
                    stats != null
            ) {

                gui.drawString(
                        this.font,
                        Component.literal(
                                "Custo: "
                                        + custo
                        ),
                        x + 170,
                        y,
                        custo <= stats.getPontos()
                                ? 0xFFFFFFFF
                                : 0xFFFF5555
                );
            }
        }
    }

    // =========================================================
    // DESENHAR RESISTÊNCIA
    // =========================================================

    private void drawResistencia(
            GuiGraphics gui,
            int x,
            int y,
            double resistencia,
            PlayerStats stats
    ) {

        gui.drawString(
                this.font,
                Component.literal(
                        "Resistência:"
                ),
                x + 21,
                y,
                0xFFFFFFFF
        );

        String valor;
        int cor;

        if (
                resistencia > 1.0
        ) {

            valor =
                    "dano ÷"
                            + formatarNumero(
                            resistencia
                    );

            cor =
                    0xFF55FFFF;

        } else {

            valor =
                    "normal";

            cor =
                    0xFFAAAAAA;
        }

        gui.drawString(
                this.font,
                Component.literal(
                        valor
                ),
                x + 90,
                y,
                cor
        );

        // =====================================================
        // MOSTRAR ESTÁGIO DO SUSANOO
        // =====================================================

        if (
                isSusanooAtivo(
                        stats
                )
        ) {

            int estagio =
                    getSusanooEstagioAtual(
                            stats
                    );

            if (
                    estagio >= 1
                            && estagio <= 5
            ) {

                String nomeSusanoo =
                        estagio == 5
                                ? "Susanoo Perfeito"
                                : "Susanoo "
                                + estagio;

                gui.drawString(
                        this.font,
                        Component.literal(
                                nomeSusanoo
                        ),
                        x + 170,
                        y,
                        0xFFAA66FF
                );
            }
        }
    }

    // =========================================================
    // FORMATAR NÚMERO
    // =========================================================

    private String formatarNumero(
            double valor
    ) {

        if (
                valor ==
                        Math.floor(
                                valor
                        )
        ) {

            return String.valueOf(
                    (long) valor
            );
        }

        return String.valueOf(
                valor
        );
    }

    // =========================================================
    // FORMATAR MULTIPLICADOR
    // =========================================================

    private String formatarMultiplicador(
            double multiplicador
    ) {

        if (
                multiplicador ==
                        Math.floor(
                                multiplicador
                        )
        ) {

            return "×"
                    + (long) multiplicador;
        }

        return "×"
                + multiplicador;
    }

    // =========================================================
    // PLAYER
    // =========================================================

    private void renderPlayer(
            GuiGraphics gui,
            int mouseX,
            int mouseY
    ) {

        Player player =
                this.minecraft.player;

        if (
                player == null
        ) {

            return;
        }

        int x =
                left + 325;

        int y =
                bottom - 25;

        InventoryScreen
                .renderEntityInInventoryFollowsMouse(
                        gui,
                        x,
                        y,
                        45,
                        mouseX,
                        mouseY,
                        player
                );
    }

    // =========================================================
    // CLIQUE
    // =========================================================

    @Override
    public boolean mouseClicked(
            double mouseX,
            double mouseY,
            int button
    ) {

        // =====================================================
        // FECHAR
        // =====================================================

        if (
                mouseX >= right - 30
                        && mouseX <= right
                        && mouseY >= top
                        && mouseY <= top + 30
        ) {

            this.minecraft.setScreen(
                    null
            );

            return true;
        }

        // =====================================================
        // ABAS
        // =====================================================

        int tabX =
                left - 24;

        int tabY =
                top + 25;

        for (
                int i = 0;
                i < 4;
                i++
        ) {

            int y =
                    tabY
                            + i * 28;

            if (
                    mouseX >= tabX
                            && mouseX <=
                            tabX + 24
                            && mouseY >= y
                            && mouseY <=
                            y + 22
            ) {

                switch (
                        i
                ) {

                    case 0 ->

                            this.minecraft.setScreen(
                                    new StatusScreen()
                            );

                    case 1 ->

                            this.minecraft.setScreen(
                                    new MissionScreen()
                            );

                    case 2 ->

                            this.minecraft.setScreen(
                                    new DojutsuUpgradeScreen()
                            );

                    case 3 -> {
                    }
                }

                return true;
            }
        }

        // =====================================================
        // SCROLL
        // =====================================================

        int scrollX =
                right - 13;

        int scrollTop =
                top + 30;

        int scrollBottom =
                bottom - 20;

        int cursorHeight =
                60;

        int cursorRange =
                (
                        scrollBottom
                                - scrollTop
                )
                        - cursorHeight;

        int cursorY =
                scrollTop
                        + (int) (
                        (
                                scrollOffset
                                        / (double) SCROLL_MAX
                        )
                                * cursorRange
                );

        if (
                mouseX >= scrollX - 5
                        && mouseX <= scrollX + 8
                        && mouseY >= cursorY
                        && mouseY <=
                        cursorY + cursorHeight
        ) {

            arrastandoScroll =
                    true;

            scrollMouseOffset =
                    (int) mouseY
                            - cursorY;

            return true;
        }

        // =====================================================
        // ÁREA
        // =====================================================

        int areaTop =
                top + 25;

        int areaBottom =
                bottom - 10;

        int attributeY =
                top
                        + 92
                        - scrollOffset;

        int quantidadeY =
                attributeY + 13;

        int quantidadeX =
                left + 18;

        // =====================================================
        // QUANTIDADE
        // =====================================================

        if (
                mouseX >= quantidadeX
                        && mouseX <=
                        quantidadeX + 14
                        && mouseY >=
                        quantidadeY - 2
                        && mouseY <=
                        quantidadeY + 13
                        && mouseY >= areaTop
                        && mouseY <= areaBottom
        ) {

            if (
                    quantidadeSelecionada == 1
            ) {

                quantidadeSelecionada =
                        5;

            } else if (
                    quantidadeSelecionada == 5
            ) {

                quantidadeSelecionada =
                        100;

            } else if (
                    quantidadeSelecionada == 100
            ) {

                quantidadeSelecionada =
                        1000;

            } else {

                quantidadeSelecionada =
                        1;
            }

            return true;
        }

        // =====================================================
        // ATRIBUTOS
        // =====================================================

        int atributosInicio =
                quantidadeY + 37;

        int plusX1 =
                left + 18;

        int plusX2 =
                plusX1 + 14;

        // =====================================================
        // FORÇA
        // =====================================================

        int forcaY =
                atributosInicio;

        if (
                mouseX >= plusX1
                        && mouseX <= plusX2
                        && mouseY >= forcaY - 2
                        && mouseY <= forcaY + 13
                        && mouseY >= areaTop
                        && mouseY <= areaBottom
        ) {

            gastarPonto(
                    0
            );

            return true;
        }

        // =====================================================
        // DEFESA
        // =====================================================

        int defesaY =
                atributosInicio + 17;

        if (
                mouseX >= plusX1
                        && mouseX <= plusX2
                        && mouseY >= defesaY - 2
                        && mouseY <= defesaY + 13
                        && mouseY >= areaTop
                        && mouseY <= areaBottom
        ) {

            gastarPonto(
                    1
            );

            return true;
        }

        // =====================================================
        // CHAKARA
        // =====================================================

        int chakaraY =
                atributosInicio + 34;

        if (
                mouseX >= plusX1
                        && mouseX <= plusX2
                        && mouseY >= chakaraY - 2
                        && mouseY <= chakaraY + 13
                        && mouseY >= areaTop
                        && mouseY <= areaBottom
        ) {

            gastarPonto(
                    2
            );

            return true;
        }

        // =====================================================
        // MENTE
        // =====================================================

        int menteY =
                atributosInicio + 51;

        if (
                mouseX >= plusX1
                        && mouseX <= plusX2
                        && mouseY >= menteY - 2
                        && mouseY <= menteY + 13
                        && mouseY >= areaTop
                        && mouseY <= areaBottom
        ) {

            gastarPonto(
                    3
            );

            return true;
        }

        return super.mouseClicked(
                mouseX,
                mouseY,
                button
        );
    }

    // =========================================================
    // ARRASTAR SCROLL
    // =========================================================

    @Override
    public boolean mouseDragged(
            double mouseX,
            double mouseY,
            int button,
            double dragX,
            double dragY
    ) {

        if (
                arrastandoScroll
        ) {

            int scrollTop =
                    top + 30;

            int scrollBottom =
                    bottom - 20;

            int cursorHeight =
                    60;

            int cursorRange =
                    (
                            scrollBottom
                                    - scrollTop
                    )
                            - cursorHeight;

            double novoCursorY =
                    mouseY
                            - scrollMouseOffset;

            if (
                    novoCursorY < scrollTop
            ) {

                novoCursorY =
                        scrollTop;
            }

            if (
                    novoCursorY >
                            scrollTop
                                    + cursorRange
            ) {

                novoCursorY =
                        scrollTop
                                + cursorRange;
            }

            double porcentagem =
                    (
                            novoCursorY
                                    - scrollTop
                    )
                            / (double) cursorRange;

            scrollOffset =
                    (int) (
                            porcentagem
                                    * SCROLL_MAX
                    );

            return true;
        }

        return super.mouseDragged(
                mouseX,
                mouseY,
                button,
                dragX,
                dragY
        );
    }

    // =========================================================
    // SCROLL DO MOUSE
    // =========================================================

    @Override
    public boolean mouseScrolled(
            double mouseX,
            double mouseY,
            double deltaY
    ) {

        if (
                mouseX >= left
                        && mouseX <= right
                        && mouseY >= top
                        && mouseY <= bottom
        ) {

            scrollOffset -=
                    (int) (
                            deltaY * 10
                    );

            if (
                    scrollOffset < 0
            ) {

                scrollOffset =
                        0;
            }

            if (
                    scrollOffset > SCROLL_MAX
            ) {

                scrollOffset =
                        SCROLL_MAX;
            }

            return true;
        }

        return super.mouseScrolled(
                mouseX,
                mouseY,
                deltaY
        );
    }

    // =========================================================
    // SOLTAR SCROLL
    // =========================================================

    @Override
    public boolean mouseReleased(
            double mouseX,
            double mouseY,
            int button
    ) {

        if (
                arrastandoScroll
        ) {

            arrastandoScroll =
                    false;

            return true;
        }

        return super.mouseReleased(
                mouseX,
                mouseY,
                button
        );
    }

    // =========================================================
    // GASTAR PONTO
    // =========================================================

    private void gastarPonto(
            int atributo
    ) {

        Player player =
                Minecraft.getInstance().player;

        if (
                player == null
        ) {

            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        if (
                stats == null
        ) {

            return;
        }

        int custo =
                stats.getCustoUpgrades(
                        quantidadeSelecionada
                );

        if (
                stats.getPontos()
                        < custo
        ) {

            return;
        }

        ModNetwork.CHANNEL.sendToServer(
                new SpendStatPacket(
                        atributo,
                        quantidadeSelecionada
                )
        );
    }

    // =========================================================
    // TECLAS
    // =========================================================

    @Override
    public boolean keyPressed(
            int keyCode,
            int scanCode,
            int modifiers
    ) {

        if (
                keyCode ==
                        GLFW.GLFW_KEY_ESCAPE
        ) {

            this.minecraft.setScreen(
                    null
            );

            return true;
        }

        return super.keyPressed(
                keyCode,
                scanCode,
                modifiers
        );
    }

    // =========================================================
    // NÃO PAUSA
    // =========================================================

    @Override
    public boolean isPauseScreen() {

        return false;
    }
}