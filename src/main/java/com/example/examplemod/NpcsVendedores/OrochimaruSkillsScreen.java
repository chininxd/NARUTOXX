package com.example.examplemod.NpcsVendedores;

import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import com.example.examplemod.network.BuyTechniquePacket;
import com.example.examplemod.network.ModNetwork;

public class OrochimaruSkillsScreen extends Screen {

    // =========================================================
    // TEXTURAS
    // =========================================================

    private static final ResourceLocation MENU_BACKGROUND_VILAO =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/backgrounds/menu_background_vilao.png"
            );

    private static final ResourceLocation UP_BUTTON_VILAO =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/up_button_vilao.png"
            );

    private static final ResourceLocation UP_BUTTON_VILAO_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/up_button_vilao_hover.png"
            );


    // =========================================================
    // CUSTOS
    // =========================================================

    private static final int CUSTO_MARCA_TP = 1_000_000;
    private static final int CUSTO_MARCA_MENTE = 50;


    // =========================================================
    // TAMANHO DO MENU
    // =========================================================

    private final int panelWidth = 300;
    private final int panelHeight = 150;

    private int left;
    private int top;


    // =========================================================
    // BOTÃO MARCA DA MALDIÇÃO
    // =========================================================

    private final int buyButtonSize = 16;

    private int marcaButtonX;
    private int marcaButtonY;


    // =========================================================
    // BOTÃO MODO SÁBIO
    // =========================================================

    private int modoSabioButtonX;
    private int modoSabioButtonY;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public OrochimaruSkillsScreen() {

        super(
                Component.literal("Aprender Skills")
        );
    }


    // =========================================================
    // INIT
    // =========================================================

    @Override
    protected void init() {

        super.init();


        // =====================================================
        // CENTRALIZA MENU
        // =====================================================

        left =
                (this.width - panelWidth) / 2;

        top =
                (this.height - panelHeight) / 2;


        // =====================================================
        // BOTÃO MARCA DA MALDIÇÃO
        // =====================================================

        marcaButtonX =
                left + panelWidth - 38;

        marcaButtonY =
                top + 44;


        // =====================================================
        // BOTÃO MODO SÁBIO
        // =====================================================

        modoSabioButtonX =
                left + panelWidth - 38;

        modoSabioButtonY =
                top + 76;
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
        // FUNDO ESCURO
        // =====================================================

        guiGraphics.fill(
                0,
                0,
                this.width,
                this.height,
                0x80000000
        );


        // =====================================================
        // FUNDO DO MENU
        // =====================================================

        drawMenuNineSlice(
                guiGraphics,
                MENU_BACKGROUND_VILAO,
                left,
                top,
                panelWidth,
                panelHeight
        );


        // =====================================================
        // DADOS DO JOGADOR
        // =====================================================

        Minecraft minecraft =
                Minecraft.getInstance();


        if (minecraft.player == null) {

            super.render(
                    guiGraphics,
                    mouseX,
                    mouseY,
                    partialTick
            );

            return;
        }


        PlayerStats stats =
                PlayerStatsManager.get(
                        minecraft.player
                );


        int tp =
                stats.getPontos();

        int mente =
                stats.getMente();


        // =====================================================
        // TP
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "TP: " + tp
                ),
                left + 15,
                top + 15,
                0xFFFFFFFF,
                false
        );


        // =====================================================
        // MENTE
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Available Mind: " + mente
                ),
                left + 15,
                top + 28,
                0xFFFFFFFF,
                false
        );


        // =====================================================
        // MARCA DA MALDIÇÃO
        // =====================================================

        int marcaY =
                top + 44;


        // =====================================================
        // NOME
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Marca da Maldicao"
                ),
                left + 18,
                marcaY + 4,
                0xFFFFFFFF,
                false
        );


        // =====================================================
        // CUSTO
        // =====================================================

        int custoX =
                left + 125;


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Cost: 1000000"
                ),
                custoX,
                marcaY,
                0xFFFFFFFF,
                false
        );


        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Mind: 50"
                ),
                custoX,
                marcaY + 11,
                0xFFFFFFFF,
                false
        );


        // =====================================================
        // HOVER BOTÃO MARCA
        // =====================================================

        boolean hoverMarca =
                mouseX >= marcaButtonX
                        && mouseX < marcaButtonX + buyButtonSize
                        && mouseY >= marcaButtonY
                        && mouseY < marcaButtonY + buyButtonSize;


        ResourceLocation marcaTexture;

        if (hoverMarca) {

            marcaTexture =
                    UP_BUTTON_VILAO_HOVER;

        } else {

            marcaTexture =
                    UP_BUTTON_VILAO;
        }


        // =====================================================
        // BOTÃO MARCA
        // =====================================================

        guiGraphics.blit(
                marcaTexture,
                marcaButtonX,
                marcaButtonY,
                0,
                0,
                buyButtonSize,
                buyButtonSize,
                buyButtonSize,
                buyButtonSize
        );


        // =====================================================
        // MODO SÁBIO
        // =====================================================

        int modoSabioY =
                top + 76;


        // =====================================================
        // NOME MODO SÁBIO
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Aprender Modo Sabio"
                ),
                left + 18,
                modoSabioY + 4,
                0xFFFFFFFF,
                false
        );


        // =====================================================
        // EM BREVE
        // =====================================================

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "EM BREVE"
                ),
                left + 150,
                modoSabioY + 4,
                0xFFFFC928,
                false
        );


        // =====================================================
        // HOVER BOTÃO MODO SÁBIO
        // =====================================================

        boolean hoverModoSabio =
                mouseX >= modoSabioButtonX
                        && mouseX < modoSabioButtonX + buyButtonSize
                        && mouseY >= modoSabioButtonY
                        && mouseY < modoSabioButtonY + buyButtonSize;


        ResourceLocation modoSabioTexture;

        if (hoverModoSabio) {

            modoSabioTexture =
                    UP_BUTTON_VILAO_HOVER;

        } else {

            modoSabioTexture =
                    UP_BUTTON_VILAO;
        }


        // =====================================================
        // BOTÃO MODO SÁBIO
        // =====================================================

        guiGraphics.blit(
                modoSabioTexture,
                modoSabioButtonX,
                modoSabioButtonY,
                0,
                0,
                buyButtonSize,
                buyButtonSize,
                buyButtonSize,
                buyButtonSize
        );


        super.render(
                guiGraphics,
                mouseX,
                mouseY,
                partialTick
        );
    }


    // =========================================================
    // CLIQUES
    // =========================================================

    @Override
    public boolean mouseClicked(
            double mouseX,
            double mouseY,
            int button
    ) {

        if (button == 0) {

            // =================================================
            // MARCA DA MALDIÇÃO
            // =================================================

            boolean clicouMarca =
                    mouseX >= marcaButtonX
                            && mouseX < marcaButtonX + buyButtonSize
                            && mouseY >= marcaButtonY
                            && mouseY < marcaButtonY + buyButtonSize;


            if (clicouMarca) {

                Minecraft minecraft =
                        Minecraft.getInstance();


                if (minecraft.player == null) {

                    return true;
                }


                PlayerStats stats =
                        PlayerStatsManager.get(
                                minecraft.player
                        );


                // =================================================
                // SEM MENTE
                // =================================================

                if (stats.getMente() < CUSTO_MARCA_MENTE) {

                    minecraft.player.displayClientMessage(
                            Component.literal(
                                    "§cVoce precisa de 50 de Mente."
                            ),
                            false
                    );

                    return true;
                }


                // =================================================
                // SEM TP
                // =================================================

                if (stats.getPontos() < CUSTO_MARCA_TP) {

                    minecraft.player.displayClientMessage(
                            Component.literal(
                                    "§cVoce precisa de 1.000.000 TP."
                            ),
                            false
                    );

                    return true;
                }



                // =================================================
                // ENVIA COMPRA PARA O SERVIDOR
                // =================================================

                ModNetwork.CHANNEL.sendToServer(
                        new BuyTechniquePacket(
                                BuyTechniquePacket.MARCA_DA_MALDICAO
                        )
                );




                return true;
            }


            // =================================================
            // MODO SÁBIO
            // =================================================

            boolean clicouModoSabio =
                    mouseX >= modoSabioButtonX
                            && mouseX < modoSabioButtonX + buyButtonSize
                            && mouseY >= modoSabioButtonY
                            && mouseY < modoSabioButtonY + buyButtonSize;


            if (clicouModoSabio) {

                // Por enquanto não faz nada.
                // Só mostramos uma mensagem.

                Minecraft minecraft =
                        Minecraft.getInstance();


                if (minecraft.player != null) {

                    minecraft.player.displayClientMessage(
                            Component.literal(
                                    "§eModo Sabio: EM BREVE"
                            ),
                            false
                    );
                }


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
    // NINE-SLICE DO MENU
    // =========================================================

    private void drawMenuNineSlice(
            GuiGraphics gui,
            ResourceLocation texture,
            int x,
            int y,
            int width,
            int height
    ) {

        final int textureSize = 32;
        final int border = 14;

        int sourceCenter =
                textureSize - border * 2;

        int centerWidth =
                width - border * 2;

        int centerHeight =
                height - border * 2;


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
                sourceCenter,
                border,
                textureSize,
                textureSize
        );


        // BAIXO

        gui.blit(
                texture,
                x + border,
                y + height - border,
                centerWidth,
                border,
                border,
                textureSize - border,
                sourceCenter,
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
                sourceCenter,
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
                sourceCenter,
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
                sourceCenter,
                sourceCenter,
                textureSize,
                textureSize
        );
    }


    // =========================================================
    // ESC VOLTA PARA A TELA DO OROCHIMARU
    // =========================================================

    @Override
    public boolean keyPressed(
            int keyCode,
            int scanCode,
            int modifiers
    ) {

        if (keyCode == 256) {

            Minecraft.getInstance().setScreen(
                    new OrochimaruShopScreen()
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
    // NÃO PAUSA O JOGO
    // =========================================================

    @Override
    public boolean isPauseScreen() {

        return false;
    }
}