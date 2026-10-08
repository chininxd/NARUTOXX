package com.example.examplemod.NpcsVendedores;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class OrochimaruShopScreen extends Screen {

    // =========================================================
    // TEXTURAS
    // =========================================================

    private static final ResourceLocation MENU_BACKGROUND_VILAO =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/backgrounds/menu_background_vilao.png"
            );

    private static final ResourceLocation SHOP_ITEM_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/shop_item_button_vilao.png"
            );

    private static final ResourceLocation SHOP_ITEM_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/shop_item_button_vilao_hover.png"
            );


    // =========================================================
    // TAMANHO DO MENU
    // =========================================================

    private final int panelWidth = 300;
    private final int panelHeight = 150;

    private int left;
    private int top;


    // =========================================================
    // BOTÃO
    // =========================================================

    private final int buttonWidth = 100;
    private final int buttonHeight = 28;

    private int buttonX;
    private int buttonY;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public OrochimaruShopScreen() {
        super(
                Component.literal("Orochimaru")
        );
    }


    // =========================================================
    // INIT
    // =========================================================

    @Override
    protected void init() {

        super.init();


        // =====================================================
        // MENU CENTRALIZADO
        // =====================================================

        left =
                (this.width - panelWidth) / 2;

        top =
                (this.height - panelHeight) / 2;


        // =====================================================
        // BOTÃO CENTRALIZADO
        // =====================================================

        buttonX =
                left + (panelWidth - buttonWidth) / 2;

        buttonY =
                top + 88;
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
        // ESCURECER FUNDO
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
        // TÍTULO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal("OROCHIMARU"),
                left + panelWidth / 2,
                top + 12,
                0xFFFFFFFF
        );


        // =====================================================
        // FALA
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(
                        "Posso lhe ensinar algumas habilidades..."
                ),
                left + panelWidth / 2,
                top + 48,
                0xFFD0D0D0
        );


        // =====================================================
        // HOVER
        // =====================================================

        boolean hover =
                mouseX >= buttonX
                        && mouseX < buttonX + buttonWidth
                        && mouseY >= buttonY
                        && mouseY < buttonY + buttonHeight;


        ResourceLocation buttonTexture;

        if (hover) {

            buttonTexture =
                    SHOP_ITEM_BUTTON_HOVER;

        } else {

            buttonTexture =
                    SHOP_ITEM_BUTTON;
        }


        // =====================================================
        // BOTÃO
        // =====================================================

        drawButtonNineSlice(
                guiGraphics,
                buttonTexture,
                buttonX,
                buttonY,
                buttonWidth,
                buttonHeight
        );


        // =====================================================
        // TEXTO DO BOTÃO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(
                        "APRENDER SKILLS"
                ),
                buttonX + buttonWidth / 2,
                buttonY
                        + (buttonHeight - this.font.lineHeight) / 2,
                0xFFFFFFFF
        );


        super.render(
                guiGraphics,
                mouseX,
                mouseY,
                partialTick
        );
    }


    // =========================================================
    // CLIQUE DO MOUSE
    // =========================================================

    @Override
    public boolean mouseClicked(
            double mouseX,
            double mouseY,
            int button
    ) {

        if (button == 0) {

            boolean clicou =
                    mouseX >= buttonX
                            && mouseX < buttonX + buttonWidth
                            && mouseY >= buttonY
                            && mouseY < buttonY + buttonHeight;


            if (clicou) {

                // =================================================
                // ABRE A TELA DE SKILLS DO OROCHIMARU
                // =================================================

                Minecraft.getInstance().setScreen(
                        new OrochimaruSkillsScreen()
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
    // NINE-SLICE DO BOTÃO
    // =========================================================

    private void drawButtonNineSlice(
            GuiGraphics gui,
            ResourceLocation texture,
            int x,
            int y,
            int width,
            int height
    ) {

        final int textureSize = 12;
        final int border = 3;

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
    // ESC FECHA
    // =========================================================

    @Override
    public boolean keyPressed(
            int keyCode,
            int scanCode,
            int modifiers
    ) {

        if (keyCode == 256) {

            this.onClose();

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