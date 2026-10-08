package com.example.examplemod.client;

import com.example.examplemod.mission.Mission;
import com.example.examplemod.mission.MissionRegistry;
import com.example.examplemod.network.ClaimMissionRewardPacket;
import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.network.StartMissionPacket;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

import org.lwjgl.glfw.GLFW;

import java.util.List;

public class MissionScreen extends Screen {

    public MissionScreen() {
        super(Component.literal("Missões"));
    }

    // =========================================================
    // TEXTURAS
    // =========================================================

    private static final ResourceLocation MENU_BACKGROUND =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/backgrounds/menu_background.png"
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

    private static final ResourceLocation START_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/start_button.png"
            );

    private static final ResourceLocation START_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/start_button_hover.png"
            );


    // =========================================================
    // TAMANHO DO PAINEL
    // =========================================================

    private final int panelWidth = 400;
    private final int panelHeight = 200;


    // =========================================================
    // POSIÇÃO DO PAINEL
    // =========================================================

    private int left;
    private int top;
    private int right;
    private int bottom;


    // =========================================================
    // TAMANHO DO BOTÃO START
    // =========================================================

    private final int buttonWidth = 150;
    private final int buttonHeight = 100;


    // =========================================================
    // =========================================================
    // POSIÇÃO DOS ELEMENTOS
    // =========================================================
    // =========================================================
    //
    // X:
    //  0  = centralizado
    // +20 = 20 pixels para a DIREITA
    // -20 = 20 pixels para a ESQUERDA
    //
    // Y:
    //  0  = posição normal
    // +20 = 20 pixels para BAIXO
    // -20 = 20 pixels para CIMA
    //
    // =========================================================


    // ---------------------------------------------------------
    // TÍTULO "MISSÕES"
    // ---------------------------------------------------------

    private final int tituloX = 0;
    private final int tituloY = 9;


    // ---------------------------------------------------------
    // X DE FECHAR
    // ---------------------------------------------------------

    private final int fecharX = -20;
    private final int fecharY = 7;


    // ---------------------------------------------------------
    // NOME DA MISSÃO
    // ---------------------------------------------------------

    private final int nomeMissaoX = 0;
    private final int nomeMissaoY = 20;


    // ---------------------------------------------------------
    // DESCRIÇÃO
    // ---------------------------------------------------------

    private final int descricaoX = 0;
    private final int descricaoY = 30;


    // ---------------------------------------------------------
    // LARGURA MÁXIMA DA DESCRIÇÃO
    //
    // O texto vai quebrar automaticamente quando chegar
    // aproximadamente em 320 pixels.
    // ---------------------------------------------------------

    private final int larguraDescricao = 320;


    // ---------------------------------------------------------
    // ESPAÇAMENTO ENTRE AS LINHAS DA DESCRIÇÃO
    // ---------------------------------------------------------

    private final int espacoEntreLinhas = 10;


    // ---------------------------------------------------------
    // OBJETIVO
    // ---------------------------------------------------------

    private final int objetivoX = 0;
    private final int objetivoY = 110;


    // ---------------------------------------------------------
    // QUANTIDADE
    // ---------------------------------------------------------

    private final int quantidadeX = 0;
    private final int quantidadeY = 125;


    // ---------------------------------------------------------
    // BOTÃO START
    // ---------------------------------------------------------

    private final int startX = 0;
    private final int startY = 105;


    // ---------------------------------------------------------
    // MISSÃO EM ANDAMENTO
    // ---------------------------------------------------------

    private final int andamentoX = 0;
    private final int andamentoY = 135;


    // ---------------------------------------------------------
    // RECOMPENSA DISPONÍVEL
    // ---------------------------------------------------------

    private final int recompensaX = 0;
    private final int recompensaY = 140;


    // ---------------------------------------------------------
    // PEGAR RECOMPENSA
    // ---------------------------------------------------------

    private final int pegarRecompensaX = 0;
    private final int pegarRecompensaY = 155;


    // =========================================================
    // POSIÇÃO DAS ABAS
    // =========================================================

    private final int abasX = -24;
    private final int abasY = 25;


    // =========================================================
    // INICIALIZAÇÃO
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
        // ABAS
        // =====================================================

        drawTabs(
                guiGraphics,
                mouseX,
                mouseY
        );


        // =====================================================
        // TÍTULO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal("Missões"),
                left
                        + panelWidth / 2
                        + tituloX,
                top
                        + tituloY,
                0xFFFFFFFF
        );


        // =====================================================
        // X DE FECHAR
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal("×"),
                right
                        + fecharX,
                top
                        + fecharY,
                0xFFFFFFFF
        );


        // =====================================================
        // DADOS DA MISSÃO
        // =====================================================

        int activeMissionId =
                MissionClientData.getActiveMissionId();

        int nextMissionId =
                MissionClientData.getNextMissionId();

        int progresso =
                MissionClientData.getProgress();

        boolean recompensaDisponivel =
                MissionClientData.hasRewardAvailable();

        boolean missaoAtiva =
                MissionClientData.hasActiveMission();


        // =====================================================
        // ESCOLHER MISSÃO
        // =====================================================

        int missionId;

        if (missaoAtiva) {

            missionId =
                    activeMissionId;

        } else {

            missionId =
                    nextMissionId;
        }


        // =====================================================
        // TODAS AS MISSÕES CONCLUÍDAS
        // =====================================================

        if (missionId == -1) {

            guiGraphics.drawCenteredString(
                    this.font,
                    Component.literal(
                            "Todas as missões foram concluídas!"
                    ),
                    left
                            + panelWidth / 2,
                    top + 80,
                    0xFFFFD700
            );

            return;
        }


        // =====================================================
        // PEGAR MISSÃO
        // =====================================================

        Mission mission =
                MissionRegistry.getMission(
                        missionId
                );


        if (mission == null) {

            guiGraphics.drawCenteredString(
                    this.font,
                    Component.literal(
                            "Missão não encontrada."
                    ),
                    left
                            + panelWidth / 2,
                    top + 70,
                    0xFFFF5555
            );

            return;
        }


        // =====================================================
        // NOME DA MISSÃO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(
                        mission.getName()
                ),
                left
                        + panelWidth / 2
                        + nomeMissaoX,
                top
                        + nomeMissaoY,
                0xFFFFFFFF
        );


        // =====================================================
        // DESCRIÇÃO
        //
        // AQUI O TEXTO É QUEBRADO AUTOMATICAMENTE.
        //
        // LIMITE: 320 PIXELS
        // =====================================================

        List<FormattedCharSequence> linhasDescricao =
                this.font.split(
                        Component.literal(
                                mission.getDescription()
                        ),
                        larguraDescricao
                );


        int descricaoYAtual =
                top + descricaoY;


        for (
                FormattedCharSequence linha
                : linhasDescricao
        ) {

            guiGraphics.drawCenteredString(
                    this.font,
                    linha,
                    left
                            + panelWidth / 2
                            + descricaoX,
                    descricaoYAtual,
                    0xFFAAAAAA
            );


            descricaoYAtual +=
                    espacoEntreLinhas;
        }


        // =====================================================
        // OBJETIVO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(
                        "Objetivo: Derrote "
                                + mission.getObjectiveTarget()
                ),
                left
                        + panelWidth / 2
                        + objetivoX,
                top
                        + objetivoY,
                0xFFFFFFFF
        );


        // =====================================================
        // QUANTIDADE
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(
                        "Quantidade: "
                                + progresso
                                + " / "
                                + mission.getRequiredAmount()
                ),
                left
                        + panelWidth / 2
                        + quantidadeX,
                top
                        + quantidadeY,
                0xFFAAAAAA
        );


        // =====================================================
        // POSIÇÃO DO BOTÃO START
        // =====================================================

        int buttonX =
                left
                        + (panelWidth - buttonWidth) / 2
                        + startX;

        int buttonY =
                top
                        + startY;


        // =====================================================
        //
        // 1. MISSÃO NÃO INICIADA
        //
        // MOSTRA O PNG START
        //
        // =====================================================

        if (
                !missaoAtiva
                        && !recompensaDisponivel
        ) {

            boolean hover =
                    mouseX >= buttonX
                            && mouseX <=
                            buttonX + buttonWidth
                            && mouseY >= buttonY
                            && mouseY <=
                            buttonY + buttonHeight;


            guiGraphics.blit(
                    hover
                            ? START_BUTTON_HOVER
                            : START_BUTTON,

                    buttonX,
                    buttonY,

                    0,
                    0,

                    buttonWidth,
                    buttonHeight,

                    buttonWidth,
                    buttonHeight
            );
        }


        // =====================================================
        //
        // 2. MISSÃO EM ANDAMENTO
        //
        // =====================================================

        else if (
                missaoAtiva
                        && !recompensaDisponivel
        ) {

            guiGraphics.drawCenteredString(
                    this.font,
                    Component.literal(
                            "Missão em andamento"
                    ),
                    left
                            + panelWidth / 2
                            + andamentoX,
                    top
                            + andamentoY,
                    0xFFFFFF55
            );
        }


        // =====================================================
        //
        // 3. RECOMPENSA DISPONÍVEL
        //
        // NÃO MOSTRA O PNG START
        //
        // =====================================================

        else if (recompensaDisponivel) {

            // -------------------------------------------------
            // RECOMPENSA DISPONÍVEL
            // -------------------------------------------------

            guiGraphics.drawCenteredString(
                    this.font,
                    Component.literal(
                            "Recompensa disponível!"
                    ),
                    left
                            + panelWidth / 2
                            + recompensaX,
                    top
                            + recompensaY,
                    0xFF55FF55
            );


            // -------------------------------------------------
            // HOVER
            // -------------------------------------------------

            boolean hover =
                    mouseX >= buttonX
                            && mouseX <=
                            buttonX + buttonWidth
                            && mouseY >= buttonY
                            && mouseY <=
                            buttonY + buttonHeight;


            int textColor =
                    hover
                            ? 0xFFFFFF55
                            : 0xFFFFFFFF;


            // -------------------------------------------------
            // PEGAR RECOMPENSA
            // -------------------------------------------------

            guiGraphics.drawCenteredString(
                    this.font,
                    Component.literal(
                            "PEGAR RECOMPENSA"
                    ),
                    buttonX
                            + buttonWidth / 2
                            + pegarRecompensaX,
                    buttonY
                            + buttonHeight / 2
                            + pegarRecompensaY,
                    textColor
            );
        }
    }


    // =========================================================
    // FUNDO 9-SLICE
    // =========================================================

    private void drawNineSliceBackground(
            GuiGraphics gui,
            ResourceLocation texture,
            int x,
            int y,
            int width,
            int height
    ) {

        final int textureSize = 32;
        final int border = 14;

        int centerSourceWidth =
                textureSize - border * 2;

        int centerSourceHeight =
                textureSize - border * 2;

        int centerWidth =
                width - border * 2;

        int centerHeight =
                height - border * 2;


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
        // PARTE INFERIOR
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
                left + abasX;

        int tabY =
                top + abasY;

        int tabWidth = 24;
        int tabHeight = 22;


        for (int i = 0; i < 4; i++) {

            int y =
                    tabY + (i * 28);


            boolean hover =
                    mouseX >= tabX
                            && mouseX <=
                            tabX + tabWidth
                            && mouseY >= y
                            && mouseY <=
                            y + tabHeight;


            ResourceLocation normal;
            ResourceLocation hoverTexture;


            switch (i) {

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

                    tabWidth,
                    tabHeight,

                    tabWidth,
                    tabHeight
            );
        }
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

            this.minecraft.setScreen(null);

            return true;
        }


        // =====================================================
        // ABAS
        // =====================================================

        int tabX =
                left + abasX;

        int tabY =
                top + abasY;


        for (int i = 0; i < 4; i++) {

            int y =
                    tabY + (i * 28);


            if (
                    mouseX >= tabX
                            && mouseX <=
                            tabX + 24
                            && mouseY >= y
                            && mouseY <=
                            y + 22
            ) {

                switch (i) {

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
        // ESTADO ATUAL
        // =====================================================

        boolean missaoAtiva =
                MissionClientData.hasActiveMission();

        boolean recompensaDisponivel =
                MissionClientData.hasRewardAvailable();


        // =====================================================
        // POSIÇÃO DO BOTÃO
        // =====================================================

        int buttonX =
                left
                        + (panelWidth - buttonWidth) / 2
                        + startX;

        int buttonY =
                top
                        + startY;


        // =====================================================
        // CLIQUE NO START
        // =====================================================

        if (
                !missaoAtiva
                        && !recompensaDisponivel
        ) {

            if (
                    mouseX >= buttonX
                            && mouseX <=
                            buttonX + buttonWidth
                            && mouseY >= buttonY
                            && mouseY <=
                            buttonY + buttonHeight
            ) {

                iniciarMissao();

                return true;
            }
        }


        // =====================================================
        // CLIQUE EM PEGAR RECOMPENSA
        // =====================================================

        if (recompensaDisponivel) {

            if (
                    mouseX >= buttonX
                            && mouseX <=
                            buttonX + buttonWidth
                            && mouseY >= buttonY
                            && mouseY <=
                            buttonY + buttonHeight
            ) {

                ModNetwork.CHANNEL.sendToServer(
                        new ClaimMissionRewardPacket()
                );

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
    // INICIAR MISSÃO
    // =========================================================

    private void iniciarMissao() {

        int missionId =
                MissionClientData.getNextMissionId();


        if (missionId == -1) {
            return;
        }


        ModNetwork.CHANNEL.sendToServer(
                new StartMissionPacket(
                        missionId
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
                keyCode ==
                        GLFW.GLFW_KEY_ESCAPE
        ) {

            this.minecraft.setScreen(null);

            return true;
        }


        return super.keyPressed(
                keyCode,
                scanCode,
                modifiers
        );
    }


    // =========================================================
    // NÃO PAUSA O JOGO
    // =========================================================

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}