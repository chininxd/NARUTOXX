package com.example.examplemod.client;

import com.example.examplemod.network.BuyDojutsuPacket;
import com.example.examplemod.network.DojutsuStatePacket;
import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.network.TechniqueStatePacket;
import com.example.examplemod.player.DojutsuConfig;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.lwjgl.glfw.GLFW;

public class DojutsuUpgradeScreen extends Screen {

    // =========================================================
    // TEXTURAS
    // =========================================================

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


    // =========================================================
    // TAMANHO
    // =========================================================

    private final int panelWidth = 400;
    private final int panelHeight = 200;

    private int left;
    private int top;
    private int right;
    private int bottom;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public DojutsuUpgradeScreen() {

        super(
                Component.literal(
                        "Dōjutsu"
                )
        );
    }


    // =========================================================
    // INIT
    // =========================================================

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


    // =========================================================
    // RENDER
    // =========================================================

    @Override
    public void render(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {

        Minecraft mc =
                Minecraft.getInstance();

        if (
                mc.player == null
        ) {

            return;
        }


        PlayerStats stats =
                PlayerStatsManager.get(
                        mc.player
                );

        if (
                stats == null
        ) {

            return;
        }


        // =====================================================
        // FUNDO
        // =====================================================

        drawNineSliceBackground(
                guiGraphics,
                MENU_BACKGROUND,
                left,
                top,
                panelWidth,
                panelHeight
        );


        // =====================================================
        // TÍTULO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(
                        "Dōjutsu"
                ),
                left + panelWidth / 2,
                top + 9,
                0xFFFFFFFF
        );


        // =====================================================
        // FECHAR
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "×"
                ),
                right - 20,
                top + 7,
                0xFFFFFFFF
        );


        // =====================================================
        // TABS
        // =====================================================

        drawTabs(
                guiGraphics,
                mouseX,
                mouseY
        );


        // =====================================================
        // CLÃ
        // =====================================================

        boolean ehUchiha =
                "Uchiha".equalsIgnoreCase(
                        stats.getCla()
                );

        boolean ehHyuga =
                "Hyuga".equalsIgnoreCase(
                        stats.getCla()
                );


        // =====================================================
        // SEM DŌJUTSU
        // =====================================================

        if (
                !ehUchiha
                        && !ehHyuga
        ) {

            guiGraphics.drawCenteredString(
                    this.font,
                    Component.literal(
                            "Este clã não possui Dōjutsu."
                    ),
                    left + panelWidth / 2,
                    top + 80,
                    0xFFFFFFFF
            );

            guiGraphics.drawCenteredString(
                    this.font,
                    Component.literal(
                            "Apenas Uchiha e Hyuga possuem Dōjutsu."
                    ),
                    left + panelWidth / 2,
                    top + 100,
                    0xFFAAAAAA
            );

            return;
        }


        // =====================================================
        // VALORES
        // =====================================================

        int mente =
                stats.getMente();

        int pontos =
                stats.getPontos();


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Available Mind: "
                                + mente
                ),
                left + 10,
                top + 30,
                0xFFFFFFFF
        );


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "TP and Mind Cost"
                ),
                right - 115,
                top + 30,
                0xFFFFC928
        );


        // =====================================================
        // UCHIHA
        // =====================================================

        if (
                ehUchiha
        ) {

            renderUchiha(
                    guiGraphics,
                    mouseX,
                    mouseY,
                    stats
            );
        }


        // =====================================================
        // HYUGA
        // =====================================================

        if (
                ehHyuga
        ) {

            renderHyuga(
                    guiGraphics,
                    mouseX,
                    mouseY
            );
        }


        // =====================================================
        // TP
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        pontos
                                + " Training Points (TP)"
                ),
                left + 10,
                bottom - 18,
                0xFFFFFFFF
        );
    }


    // =========================================================
    // RENDER UCHIHA
    // =========================================================

    private void renderUchiha(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            PlayerStats stats
    ) {

        int tomoe =
                DojutsuStatePacket
                        .getClientSharinganTomoe();


        boolean mangekyou =
                DojutsuStatePacket
                        .isClientMangekyouDesbloqueado();


        // =====================================================
        // SHARINGAN
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Sharingan (Tomoe: "
                                + tomoe
                                + ")"
                ),
                left + 10,
                top + 48,
                0xFFFFFFFF
        );


        // =====================================================
        // 2 TOMOE
        // =====================================================

        int linha2Y =
                top + 70;


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "+ 2 Tomoe"
                ),
                left + 10,
                linha2Y,
                0xFFFFFFFF
        );


        if (
                tomoe >= 2
        ) {

            drawAdquirido(
                    guiGraphics,
                    linha2Y
            );

        } else {

            drawCusto(
                    guiGraphics,
                    DojutsuConfig.CUSTO_2_TOMOE,
                    DojutsuConfig.MENTE_2_TOMOE,
                    linha2Y
            );


            if (
                    tomoe >= 1
            ) {

                drawUpButton(
                        guiGraphics,
                        right - 30,
                        linha2Y - 2,
                        mouseX,
                        mouseY
                );
            }
        }


        // =====================================================
        // 3 TOMOE
        // =====================================================

        int linha3Y =
                top + 88;


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "+ 3 Tomoe"
                ),
                left + 10,
                linha3Y,
                0xFFFFFFFF
        );


        if (
                tomoe >= 3
        ) {

            drawAdquirido(
                    guiGraphics,
                    linha3Y
            );

        } else {

            drawCusto(
                    guiGraphics,
                    DojutsuConfig.CUSTO_3_TOMOE,
                    DojutsuConfig.MENTE_3_TOMOE,
                    linha3Y
            );


            if (
                    tomoe >= 2
            ) {

                drawUpButton(
                        guiGraphics,
                        right - 30,
                        linha3Y - 2,
                        mouseX,
                        mouseY
                );
            }
        }


        // =====================================================
        // MANGEKYŌ
        // =====================================================

        int linhaMangekyouY =
                top + 106;


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "+ Mangekyō Sharingan"
                ),
                left + 10,
                linhaMangekyouY,
                0xFFFFFFFF
        );


        if (
                mangekyou
        ) {

            drawAdquirido(
                    guiGraphics,
                    linhaMangekyouY
            );

        } else {

            drawCusto(
                    guiGraphics,
                    DojutsuConfig.CUSTO_MANGEKYOU,
                    DojutsuConfig.MENTE_MANGEKYOU,
                    linhaMangekyouY
            );


            if (
                    tomoe >= 3
            ) {

                drawUpButton(
                        guiGraphics,
                        right - 30,
                        linhaMangekyouY - 2,
                        mouseX,
                        mouseY
                );
            }
        }


        // =====================================================
        // SEM MANGEKYŌ, NÃO MOSTRA SUSANOO
        // =====================================================

        if (
                !mangekyou
        ) {

            return;
        }


        // =====================================================
        // ESTÁGIO ATUAL DO SUSANOO
        //
        // PRIMEIRO TENTA O PACKET.
        //
        // SE A SINCRONIZAÇÃO AINDA NÃO CHEGOU,
        // USA O PLAYERSTATS COMO FALLBACK.
        // =====================================================

        int susanooEstagio =
                TechniqueStatePacket
                        .getClientSusanooEstagio();


        if (
                susanooEstagio <= 0
        ) {

            susanooEstagio =
                    stats.getSusanooEstagio();
        }


        // =====================================================
        // COM MANGEKYŌ O ESTÁGIO 1 É AUTOMÁTICO
        // =====================================================

        if (
                susanooEstagio <= 0
        ) {

            susanooEstagio =
                    1;
        }


        susanooEstagio =
                Math.max(
                        1,
                        Math.min(
                                5,
                                susanooEstagio
                        )
                );


        // =====================================================
        // SUSANOO ATUAL
        // =====================================================

        int linhaSusanooAtualY =
                top + 124;


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        getNomeSusanooAtual(
                                susanooEstagio
                        )
                ),
                left + 10,
                linhaSusanooAtualY,
                0xFFFFFFFF
        );


        drawAdquirido(
                guiGraphics,
                linhaSusanooAtualY
        );


        // =====================================================
        // JÁ TEM SUSANOO PERFEITO
        // =====================================================

        if (
                susanooEstagio >= 5
        ) {

            return;
        }


        // =====================================================
        // PRÓXIMO ESTÁGIO
        // =====================================================

        int proximoEstagio =
                susanooEstagio + 1;


        int linhaProximoY =
                top + 142;


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        getNomeProximoSusanoo(
                                proximoEstagio
                        )
                ),
                left + 10,
                linhaProximoY,
                0xFFFFFFFF
        );


        // =====================================================
        // ESTÁGIO 5
        //
        // PRECISA DE MANGEKYŌ ETERNO
        // =====================================================

        if (
                proximoEstagio == 5
                        && !stats.isMangekyouEternoDesbloqueado()
        ) {

            guiGraphics.drawString(
                    this.font,
                    Component.literal(
                            "REQUER EMS"
                    ),
                    right - 82,
                    linhaProximoY,
                    0xFFFF5555
            );

            return;
        }


        // =====================================================
        // MOSTRAR CUSTO + BOTÃO DO PRÓXIMO
        // =====================================================

        int custo =
                getCustoSusanoo(
                        proximoEstagio
                );


        int mente =
                getMenteSusanoo(
                        proximoEstagio
                );


        drawCusto(
                guiGraphics,
                custo,
                mente,
                linhaProximoY
        );


        drawUpButton(
                guiGraphics,
                right - 30,
                linhaProximoY - 2,
                mouseX,
                mouseY
        );
    }


    // =========================================================
    // RENDER HYUGA
    // =========================================================

    private void renderHyuga(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY
    ) {

        boolean byakuganDespertado =
                DojutsuStatePacket
                        .isClientByakuganDespertado();


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Byakugan"
                ),
                left + 10,
                top + 48,
                0xFFFFFFFF
        );


        // =====================================================
        // BYAKUGAN NORMAL
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Normal"
                ),
                left + 10,
                top + 65,
                0xFFAAAAAA
        );


        drawAdquirido(
                guiGraphics,
                top + 65
        );


        // =====================================================
        // BYAKUGAN DESPERTADO
        // =====================================================

        int linhaDespertadoY =
                top + 88;


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "+ Byakugan Despertado"
                ),
                left + 10,
                linhaDespertadoY,
                0xFFFFFFFF
        );


        if (
                byakuganDespertado
        ) {

            drawAdquirido(
                    guiGraphics,
                    linhaDespertadoY
            );

        } else {

            drawCusto(
                    guiGraphics,
                    DojutsuConfig
                            .CUSTO_BYAKUGAN_DESPERTADO,
                    DojutsuConfig
                            .MENTE_BYAKUGAN_DESPERTADO,
                    linhaDespertadoY
            );


            drawUpButton(
                    guiGraphics,
                    right - 30,
                    linhaDespertadoY - 2,
                    mouseX,
                    mouseY
            );
        }
    }


    // =========================================================
    // DESENHAR ADQUIRIDO
    // =========================================================

    private void drawAdquirido(
            GuiGraphics gui,
            int y
    ) {

        gui.drawString(
                this.font,
                Component.literal(
                        "ADQUIRIDO"
                ),
                right - 70,
                y,
                0xFF55FF55
        );
    }


    // =========================================================
    // DESENHAR CUSTO
    // =========================================================

    private void drawCusto(
            GuiGraphics gui,
            int custoTP,
            int requisitoMente,
            int y
    ) {

        gui.drawString(
                this.font,
                Component.literal(
                        "TP: "
                                + custoTP
                                + "  M: "
                                + requisitoMente
                ),
                right - 165,
                y,
                0xFFFFFFFF
        );
    }


    // =========================================================
    // NOME SUSANOO ATUAL
    // =========================================================

    private String getNomeSusanooAtual(
            int estagio
    ) {

        if (
                estagio >= 5
        ) {

            return "Susanoo Perfeito";
        }


        return "Susanoo Estágio "
                + estagio;
    }


    // =========================================================
    // NOME PRÓXIMO SUSANOO
    // =========================================================

    private String getNomeProximoSusanoo(
            int estagio
    ) {

        if (
                estagio >= 5
        ) {

            return "+ Susanoo Perfeito";
        }


        return "+ Susanoo Estágio "
                + estagio;
    }


    // =========================================================
    // CUSTO DO SUSANOO
    // =========================================================

    private int getCustoSusanoo(
            int estagio
    ) {

        switch (
                estagio
        ) {

            case 2:

                return DojutsuConfig
                        .CUSTO_SUSANOO_ESTAGIO_2;


            case 3:

                return DojutsuConfig
                        .CUSTO_SUSANOO_ESTAGIO_3;


            case 4:

                return DojutsuConfig
                        .CUSTO_SUSANOO_ESTAGIO_4;


            case 5:

                return DojutsuConfig
                        .CUSTO_SUSANOO_ESTAGIO_5;


            default:

                return 0;
        }
    }


    // =========================================================
    // MENTE DO SUSANOO
    // =========================================================

    private int getMenteSusanoo(
            int estagio
    ) {

        switch (
                estagio
        ) {

            case 2:

                return DojutsuConfig
                        .MENTE_SUSANOO_ESTAGIO_2;


            case 3:

                return DojutsuConfig
                        .MENTE_SUSANOO_ESTAGIO_3;


            case 4:

                return DojutsuConfig
                        .MENTE_SUSANOO_ESTAGIO_4;


            case 5:

                return DojutsuConfig
                        .MENTE_SUSANOO_ESTAGIO_5;


            default:

                return 0;
        }
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


        // CANTO SUPERIOR ESQUERDO

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


        // CANTO SUPERIOR DIREITO

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


        // CANTO INFERIOR ESQUERDO

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


        // CANTO INFERIOR DIREITO

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


        // TOPO

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


        // FUNDO

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


        // ESQUERDA

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


        // DIREITA

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


        // CENTRO

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
    // TABS
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

                case 0:

                    normal =
                            STATUS_BUTTON;

                    hoverTexture =
                            STATUS_BUTTON_HOVER;

                    break;


                case 1:

                    normal =
                            MISSION_BUTTON;

                    hoverTexture =
                            MISSION_BUTTON_HOVER;

                    break;


                case 2:

                    normal =
                            DOJUTSU_BUTTON;

                    hoverTexture =
                            DOJUTSU_BUTTON_HOVER;

                    break;


                default:

                    normal =
                            STATUS_BUTTON;

                    hoverTexture =
                            STATUS_BUTTON_HOVER;

                    break;
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
                        && mouseX <=
                        x + 14
                        && mouseY >= y
                        && mouseY <=
                        y + 14;


        gui.blit(
                hover
                        ? UP_BUTTON_HOVER
                        : UP_BUTTON,
                x,
                y,
                0,
                0,
                14,
                14,
                14,
                14
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

        if (
                button != 0
        ) {

            return super.mouseClicked(
                    mouseX,
                    mouseY,
                    button
            );
        }


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
        // TABS
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
                            && mouseX <= tabX + 24
                            && mouseY >= y
                            && mouseY <= y + 22
            ) {

                switch (
                        i
                ) {

                    case 0:

                        this.minecraft.setScreen(
                                new StatusScreen()
                        );

                        break;


                    case 1:

                        this.minecraft.setScreen(
                                new MissionScreen()
                        );

                        break;


                    case 2:

                        this.minecraft.setScreen(
                                new DojutsuUpgradeScreen()
                        );

                        break;


                    case 3:

                        break;
                }

                return true;
            }
        }


        // =====================================================
        // PLAYER
        // =====================================================

        Minecraft mc =
                Minecraft.getInstance();

        if (
                mc.player == null
        ) {

            return true;
        }


        PlayerStats stats =
                PlayerStatsManager.get(
                        mc.player
                );

        if (
                stats == null
        ) {

            return true;
        }


        boolean ehUchiha =
                "Uchiha".equalsIgnoreCase(
                        stats.getCla()
                );


        boolean ehHyuga =
                "Hyuga".equalsIgnoreCase(
                        stats.getCla()
                );


        // =====================================================
        // UCHIHA
        // =====================================================

        if (
                ehUchiha
        ) {

            int tomoe =
                    DojutsuStatePacket
                            .getClientSharinganTomoe();


            // =================================================
            // 2 TOMOE
            // =================================================

            if (
                    tomoe < 2
            ) {

                if (
                        clicouBotaoUpgrade(
                                mouseX,
                                mouseY,
                                top + 70
                        )
                ) {

                    comprarSegundoTomoe();

                    return true;
                }
            }


            // =================================================
            // 3 TOMOE
            // =================================================

            if (
                    tomoe >= 2
                            && tomoe < 3
            ) {

                if (
                        clicouBotaoUpgrade(
                                mouseX,
                                mouseY,
                                top + 88
                        )
                ) {

                    comprarTerceiroTomoe();

                    return true;
                }
            }


            // =================================================
            // MANGEKYŌ
            // =================================================

            boolean mangekyou =
                    DojutsuStatePacket
                            .isClientMangekyouDesbloqueado();


            if (
                    tomoe >= 3
                            && !mangekyou
            ) {

                if (
                        clicouBotaoUpgrade(
                                mouseX,
                                mouseY,
                                top + 106
                        )
                ) {

                    comprarMangekyou();

                    return true;
                }
            }


            // =================================================
            // SUSANOO
            //
            // SÓ EXISTE DEPOIS DO MANGEKYŌ.
            // =================================================

            if (
                    mangekyou
            ) {

                int estagio =
                        TechniqueStatePacket
                                .getClientSusanooEstagio();


                if (
                        estagio <= 0
                ) {

                    estagio =
                            stats.getSusanooEstagio();
                }


                if (
                        estagio <= 0
                ) {

                    estagio =
                            1;
                }


                estagio =
                        Math.max(
                                1,
                                Math.min(
                                        5,
                                        estagio
                                )
                        );


                // =============================================
                // SOMENTE O PRÓXIMO ESTÁGIO PODE SER COMPRADO
                // =============================================

                int proximoEstagio =
                        estagio + 1;


                if (
                        proximoEstagio <= 5
                                && clicouBotaoUpgrade(
                                mouseX,
                                mouseY,
                                top + 142
                        )
                ) {

                    // =========================================
                    // SUSANOO 2
                    // =========================================

                    if (
                            proximoEstagio == 2
                    ) {

                        comprarSusanooEstagio2();

                        return true;
                    }


                    // =========================================
                    // SUSANOO 3
                    // =========================================

                    if (
                            proximoEstagio == 3
                    ) {

                        comprarSusanooEstagio3();

                        return true;
                    }


                    // =========================================
                    // SUSANOO 4
                    // =========================================

                    if (
                            proximoEstagio == 4
                    ) {

                        comprarSusanooEstagio4();

                        return true;
                    }


                    // =========================================
                    // SUSANOO 5
                    //
                    // SÓ PODE CLICAR SE TIVER EMS.
                    // =========================================

                    if (
                            proximoEstagio == 5
                                    && stats
                                    .isMangekyouEternoDesbloqueado()
                    ) {

                        comprarSusanooEstagio5();

                        return true;
                    }
                }
            }
        }


        // =====================================================
        // HYUGA
        // =====================================================

        if (
                ehHyuga
        ) {

            boolean despertado =
                    DojutsuStatePacket
                            .isClientByakuganDespertado();


            if (
                    !despertado
                            && clicouBotaoUpgrade(
                            mouseX,
                            mouseY,
                            top + 88
                    )
            ) {

                comprarByakuganDespertado();

                return true;
            }
        }


        return super.mouseClicked(
                mouseX,
                mouseY,
                button
        );
    }


    // =========================================================
    // HITBOX DO +
    // =========================================================

    private boolean clicouBotaoUpgrade(
            double mouseX,
            double mouseY,
            int linhaY
    ) {

        int x =
                right - 30;

        int y =
                linhaY - 2;


        return mouseX >= x
                && mouseX <= x + 14
                && mouseY >= y
                && mouseY <= y + 14;
    }


    // =========================================================
    // COMPRAR 2 TOMOE
    // =========================================================

    private void comprarSegundoTomoe() {

        enviarCompra(
                2
        );
    }


    // =========================================================
    // COMPRAR 3 TOMOE
    // =========================================================

    private void comprarTerceiroTomoe() {

        enviarCompra(
                3
        );
    }


    // =========================================================
    // COMPRAR MANGEKYŌ
    // =========================================================

    private void comprarMangekyou() {

        enviarCompra(
                4
        );
    }


    // =========================================================
    // COMPRAR BYAKUGAN
    // =========================================================

    private void comprarByakuganDespertado() {

        enviarCompra(
                5
        );
    }


    // =========================================================
    // SUSANOO 2
    // =========================================================

    private void comprarSusanooEstagio2() {

        enviarCompra(
                6
        );
    }


    // =========================================================
    // SUSANOO 3
    // =========================================================

    private void comprarSusanooEstagio3() {

        enviarCompra(
                7
        );
    }


    // =========================================================
    // SUSANOO 4
    // =========================================================

    private void comprarSusanooEstagio4() {

        enviarCompra(
                8
        );
    }


    // =========================================================
    // SUSANOO 5
    // =========================================================

    private void comprarSusanooEstagio5() {

        enviarCompra(
                9
        );
    }


    // =========================================================
    // ENVIAR COMPRA
    // =========================================================

    private void enviarCompra(
            int tipo
    ) {

        Minecraft mc =
                Minecraft.getInstance();


        if (
                mc.player == null
        ) {

            return;
        }


        ModNetwork.CHANNEL.sendToServer(
                new BuyDojutsuPacket(
                        tipo
                )
        );
    }


    // =========================================================
    // ESC
    // =========================================================

    @Override
    public boolean keyPressed(
            int keyCode,
            int scanCode,
            int modifiers
    ) {

        if (
                keyCode
                        == GLFW.GLFW_KEY_ESCAPE
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