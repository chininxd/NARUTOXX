package com.example.examplemod.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.lwjgl.glfw.GLFW;

import java.awt.Color;

public class HairColorScreen extends Screen {

    // =========================================================
    // TEXTURA DO FUNDO
    // =========================================================

    private static final ResourceLocation MENU_BACKGROUND =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/backgrounds/menu_background.png"
            );

    // =========================================================
    // CORES DO BOTÃO
    // =========================================================

    private static final int DOURADO =
            0xFFC58A00;

    private static final int DOURADO_HOVER =
            0xFFFFC928;

    private static final int DOURADO_ESCURO =
            0xFF6B4300;

    private static final int BRANCO =
            0xFFFFFFFF;

    // =========================================================
    // TELA ANTERIOR
    // =========================================================

    private final CustomizationScreen parent;

    // =========================================================
    // COR
    // =========================================================

    private int red;
    private int green;
    private int blue;

    // =========================================================
    // HSV
    // =========================================================

    private float hue = 0.0F;
    private float saturation = 0.0F;
    private float brightness = 1.0F;

    // =========================================================
    // PAINEL
    // =========================================================

    private final int panelWidth = 250;
    private final int panelHeight = 205;

    private int left;
    private int top;

    // =========================================================
    // QUADRADO DE COR
    // =========================================================

    private final int colorWidth = 150;
    private final int colorHeight = 100;

    private int colorX;
    private int colorY;

    // =========================================================
    // BARRA HUE
    // =========================================================

    private int hueX;
    private int hueY;

    private final int hueWidth = 150;
    private final int hueHeight = 10;

    // =========================================================
    // BARRA BRILHO
    // =========================================================

    private int brightnessX;
    private int brightnessY;

    private final int brightnessWidth = 150;
    private final int brightnessHeight = 10;

    // =========================================================
    // ARRASTANDO
    // =========================================================

    private boolean arrastandoCor = false;
    private boolean arrastandoHue = false;
    private boolean arrastandoBrilho = false;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public HairColorScreen(
            CustomizationScreen parent,
            int red,
            int green,
            int blue
    ) {

        super(
                Component.literal(
                        "Cor do Cabelo"
                )
        );

        this.parent = parent;

        this.red = limitar255(red);
        this.green = limitar255(green);
        this.blue = limitar255(blue);

        atualizarHSVDoRGB();
    }

    // =========================================================
    // INIT
    // =========================================================

    @Override
    protected void init() {

        super.init();

        left =
                (width - panelWidth) / 2;

        top =
                (height - panelHeight) / 2;

        colorX =
                left + 18;

        colorY =
                top + 28;

        hueX =
                colorX;

        hueY =
                colorY + colorHeight + 8;

        brightnessX =
                colorX;

        brightnessY =
                hueY + hueHeight + 8;
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

        renderBackground(
                guiGraphics
        );

        // =====================================================
        // FUNDO
        //
        // MESMO NINE SLICE DO CUSTOMIZATIONSCREEN
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
                font,
                "COR DO CABELO",
                left + panelWidth / 2,
                top + 9,
                BRANCO
        );

        // =====================================================
        // QUADRADO DE CORES
        // =====================================================

        desenharQuadradoCor(
                guiGraphics
        );

        // =====================================================
        // MARCADOR DO QUADRADO
        // =====================================================

        int marcadorX =
                colorX
                        + Math.round(
                        saturation * colorWidth
                );

        int marcadorY =
                colorY
                        + Math.round(
                        (1.0F - brightness)
                                * colorHeight
                );

        marcadorX =
                Math.max(
                        colorX,
                        Math.min(
                                colorX + colorWidth,
                                marcadorX
                        )
                );

        marcadorY =
                Math.max(
                        colorY,
                        Math.min(
                                colorY + colorHeight,
                                marcadorY
                        )
                );

        guiGraphics.fill(
                marcadorX - 2,
                marcadorY - 2,
                marcadorX + 3,
                marcadorY + 3,
                0xFF000000
        );

        guiGraphics.fill(
                marcadorX - 1,
                marcadorY - 1,
                marcadorX + 2,
                marcadorY + 2,
                BRANCO
        );

        // =====================================================
        // HUE
        // =====================================================

        desenharBarraHue(
                guiGraphics
        );

        int marcadorHue =
                hueX
                        + Math.round(
                        hue * hueWidth
                );

        marcadorHue =
                Math.max(
                        hueX,
                        Math.min(
                                hueX + hueWidth,
                                marcadorHue
                        )
                );

        guiGraphics.fill(
                marcadorHue - 1,
                hueY - 2,
                marcadorHue + 2,
                hueY + hueHeight + 2,
                BRANCO
        );

        // =====================================================
        // BRILHO
        // =====================================================

        desenharBarraBrilho(
                guiGraphics
        );

        int marcadorBrilho =
                brightnessX
                        + Math.round(
                        brightness
                                * brightnessWidth
                );

        marcadorBrilho =
                Math.max(
                        brightnessX,
                        Math.min(
                                brightnessX + brightnessWidth,
                                marcadorBrilho
                        )
                );

        guiGraphics.fill(
                marcadorBrilho - 1,
                brightnessY - 2,
                marcadorBrilho + 2,
                brightnessY + brightnessHeight + 2,
                BRANCO
        );

        // =====================================================
        // PREVIEW
        // =====================================================

        int previewX =
                left + 183;

        int previewY =
                top + 31;

        guiGraphics.drawCenteredString(
                font,
                "COR",
                previewX + 24,
                previewY - 12,
                BRANCO
        );

        // =====================================================
        // CONTORNO DOURADO
        // =====================================================

        guiGraphics.fill(
                previewX - 2,
                previewY - 2,
                previewX + 50,
                previewY + 50,
                DOURADO_ESCURO
        );

        guiGraphics.fill(
                previewX,
                previewY,
                previewX + 48,
                previewY + 48,
                0xFF000000
        );

        guiGraphics.fill(
                previewX + 2,
                previewY + 2,
                previewX + 46,
                previewY + 46,
                getARGB()
        );

        // =====================================================
        // RGB
        // =====================================================

        guiGraphics.drawString(
                font,
                "R: " + red,
                previewX,
                previewY + 59,
                0xFFFF7777
        );

        guiGraphics.drawString(
                font,
                "G: " + green,
                previewX,
                previewY + 72,
                0xFF77FF77
        );

        guiGraphics.drawString(
                font,
                "B: " + blue,
                previewX,
                previewY + 85,
                0xFF7777FF
        );

        // =====================================================
        // BOTÃO CONFIRMAR
        // =====================================================

        int confirmarX =
                left + 75;

        int confirmarY =
                top + 174;

        int confirmarWidth =
                100;

        int confirmarHeight =
                20;

        boolean confirmarHover =
                dentro(
                        mouseX,
                        mouseY,
                        confirmarX,
                        confirmarY,
                        confirmarWidth,
                        confirmarHeight
                );

        // =====================================================
        // SOMBRA / CONTORNO
        // =====================================================

        guiGraphics.fill(
                confirmarX - 2,
                confirmarY - 2,
                confirmarX + confirmarWidth + 2,
                confirmarY + confirmarHeight + 2,
                DOURADO_ESCURO
        );

        // =====================================================
        // COR PRINCIPAL
        // =====================================================

        guiGraphics.fill(
                confirmarX,
                confirmarY,
                confirmarX + confirmarWidth,
                confirmarY + confirmarHeight,
                confirmarHover
                        ? DOURADO_HOVER
                        : DOURADO
        );

        // =====================================================
        // BORDA SUPERIOR
        // =====================================================

        guiGraphics.fill(
                confirmarX + 1,
                confirmarY + 1,
                confirmarX + confirmarWidth - 1,
                confirmarY + 2,
                confirmarHover
                        ? 0xFFFFFF8A
                        : 0xFFFFD45A
        );

        // =====================================================
        // BORDA INFERIOR
        // =====================================================

        guiGraphics.fill(
                confirmarX + 1,
                confirmarY + confirmarHeight - 2,
                confirmarX + confirmarWidth - 1,
                confirmarY + confirmarHeight - 1,
                0xFF7A4B00
        );

        guiGraphics.drawCenteredString(
                font,
                "CONFIRMAR",
                confirmarX + confirmarWidth / 2,
                confirmarY + 6,
                BRANCO
        );

        super.render(
                guiGraphics,
                mouseX,
                mouseY,
                partialTick
        );
    }

    // =========================================================
    // QUADRADO DE COR
    // =========================================================

    private void desenharQuadradoCor(
            GuiGraphics gui
    ) {

        int hueColor =
                Color.HSBtoRGB(
                        hue,
                        1.0F,
                        1.0F
                );

        int hueR =
                (hueColor >> 16) & 255;

        int hueG =
                (hueColor >> 8) & 255;

        int hueB =
                hueColor & 255;

        for (
                int x = 0;
                x < colorWidth;
                x++
        ) {

            float sat =
                    x / (float) (colorWidth - 1);

            int baseR =
                    Math.round(
                            255
                                    + (hueR - 255)
                                    * sat
                    );

            int baseG =
                    Math.round(
                            255
                                    + (hueG - 255)
                                    * sat
                    );

            int baseB =
                    Math.round(
                            255
                                    + (hueB - 255)
                                    * sat
                    );

            for (
                    int y = 0;
                    y < colorHeight;
                    y++
            ) {

                float brilho =
                        1.0F
                                - y
                                / (float)
                                (colorHeight - 1);

                int r =
                        Math.round(
                                baseR * brilho
                        );

                int g =
                        Math.round(
                                baseG * brilho
                        );

                int b =
                        Math.round(
                                baseB * brilho
                        );

                int cor =
                        0xFF000000
                                | (r << 16)
                                | (g << 8)
                                | b;

                gui.fill(
                        colorX + x,
                        colorY + y,
                        colorX + x + 1,
                        colorY + y + 1,
                        cor
                );
            }
        }
    }

    // =========================================================
    // BARRA HUE
    // =========================================================

    private void desenharBarraHue(
            GuiGraphics gui
    ) {

        for (
                int x = 0;
                x < hueWidth;
                x++
        ) {

            float h =
                    x / (float) (hueWidth - 1);

            int cor =
                    0xFF000000
                            | (
                            Color.HSBtoRGB(
                                    h,
                                    1.0F,
                                    1.0F
                            )
                                    & 0x00FFFFFF
                    );

            gui.fill(
                    hueX + x,
                    hueY,
                    hueX + x + 1,
                    hueY + hueHeight,
                    cor
            );
        }
    }

    // =========================================================
    // BARRA BRILHO
    // =========================================================

    private void desenharBarraBrilho(
            GuiGraphics gui
    ) {

        int corMax =
                Color.HSBtoRGB(
                        hue,
                        saturation,
                        1.0F
                );

        int maxR =
                (corMax >> 16) & 255;

        int maxG =
                (corMax >> 8) & 255;

        int maxB =
                corMax & 255;

        for (
                int x = 0;
                x < brightnessWidth;
                x++
        ) {

            float valor =
                    x
                            / (float)
                            (brightnessWidth - 1);

            int r =
                    Math.round(
                            maxR * valor
                    );

            int g =
                    Math.round(
                            maxG * valor
                    );

            int b =
                    Math.round(
                            maxB * valor
                    );

            int cor =
                    0xFF000000
                            | (r << 16)
                            | (g << 8)
                            | b;

            gui.fill(
                    brightnessX + x,
                    brightnessY,
                    brightnessX + x + 1,
                    brightnessY + brightnessHeight,
                    cor
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

        if (
                button !=
                        GLFW.GLFW_MOUSE_BUTTON_LEFT
        ) {

            return super.mouseClicked(
                    mouseX,
                    mouseY,
                    button
            );
        }

        // =====================================================
        // QUADRADO
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        colorX,
                        colorY,
                        colorWidth,
                        colorHeight
                )
        ) {

            arrastandoCor = true;

            atualizarPeloQuadrado(
                    mouseX,
                    mouseY
            );

            return true;
        }

        // =====================================================
        // HUE
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        hueX,
                        hueY,
                        hueWidth,
                        hueHeight
                )
        ) {

            arrastandoHue = true;

            atualizarHue(
                    mouseX
            );

            return true;
        }

        // =====================================================
        // BRILHO
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        brightnessX,
                        brightnessY,
                        brightnessWidth,
                        brightnessHeight
                )
        ) {

            arrastandoBrilho = true;

            atualizarBrilho(
                    mouseX
            );

            return true;
        }

        // =====================================================
        // CONFIRMAR
        // =====================================================

        int confirmarX =
                left + 75;

        int confirmarY =
                top + 174;

        if (
                dentro(
                        mouseX,
                        mouseY,
                        confirmarX,
                        confirmarY,
                        100,
                        20
                )
        ) {

            confirmarCor();

            return true;
        }

        return super.mouseClicked(
                mouseX,
                mouseY,
                button
        );
    }

    // =========================================================
    // ARRASTAR
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
                button ==
                        GLFW.GLFW_MOUSE_BUTTON_LEFT
        ) {

            if (arrastandoCor) {

                atualizarPeloQuadrado(
                        mouseX,
                        mouseY
                );

                return true;
            }

            if (arrastandoHue) {

                atualizarHue(
                        mouseX
                );

                return true;
            }

            if (arrastandoBrilho) {

                atualizarBrilho(
                        mouseX
                );

                return true;
            }
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
    // SOLTA MOUSE
    // =========================================================

    @Override
    public boolean mouseReleased(
            double mouseX,
            double mouseY,
            int button
    ) {

        if (
                button ==
                        GLFW.GLFW_MOUSE_BUTTON_LEFT
        ) {

            arrastandoCor = false;
            arrastandoHue = false;
            arrastandoBrilho = false;
        }

        return super.mouseReleased(
                mouseX,
                mouseY,
                button
        );
    }

    // =========================================================
    // ATUALIZA QUADRADO
    // =========================================================

    private void atualizarPeloQuadrado(
            double mouseX,
            double mouseY
    ) {

        saturation =
                (float) (
                        (mouseX - colorX)
                                / colorWidth
                );

        brightness =
                1.0F
                        - (float) (
                        (mouseY - colorY)
                                / colorHeight
                );

        saturation =
                limitar01(
                        saturation
                );

        brightness =
                limitar01(
                        brightness
                );

        atualizarRGBDoHSV();

        enviarPreview();
    }

    // =========================================================
    // ATUALIZA HUE
    // =========================================================

    private void atualizarHue(
            double mouseX
    ) {

        hue =
                (float) (
                        (mouseX - hueX)
                                / hueWidth
                );

        hue =
                limitar01(
                        hue
                );

        atualizarRGBDoHSV();

        enviarPreview();
    }

    // =========================================================
    // ATUALIZA BRILHO
    // =========================================================

    private void atualizarBrilho(
            double mouseX
    ) {

        brightness =
                (float) (
                        (mouseX - brightnessX)
                                / brightnessWidth
                );

        brightness =
                limitar01(
                        brightness
                );

        atualizarRGBDoHSV();

        enviarPreview();
    }

    // =========================================================
    // HSV -> RGB
    // =========================================================

    private void atualizarRGBDoHSV() {

        int rgb =
                Color.HSBtoRGB(
                        hue,
                        saturation,
                        brightness
                );

        red =
                (rgb >> 16) & 255;

        green =
                (rgb >> 8) & 255;

        blue =
                rgb & 255;
    }

    // =========================================================
    // RGB -> HSV
    // =========================================================

    private void atualizarHSVDoRGB() {

        float[] hsv =
                Color.RGBtoHSB(
                        red,
                        green,
                        blue,
                        null
                );

        hue =
                hsv[0];

        saturation =
                hsv[1];

        brightness =
                hsv[2];
    }

    // =========================================================
    // ENVIA PARA PREVIEW
    // =========================================================

    private void enviarPreview() {

        if (parent == null) {
            return;
        }

        parent.setCorCabeloPreview(
                red,
                green,
                blue
        );
    }

    // =========================================================
    // CONFIRMAR
    // =========================================================

    private void confirmarCor() {

        enviarPreview();

        if (minecraft != null) {

            minecraft.setScreen(
                    parent
            );
        }
    }

    // =========================================================
    // NINE SLICE
    //
    // IGUAL AO CUSTOMIZATIONSCREEN
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
    // ESC VOLTA
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

            confirmarCor();

            return true;
        }

        return super.keyPressed(
                keyCode,
                scanCode,
                modifiers
        );
    }

    // =========================================================
    // FECHAR
    // =========================================================

    @Override
    public void onClose() {

        confirmarCor();
    }

    // =========================================================
    // HITBOX
    // =========================================================

    private boolean dentro(
            double mouseX,
            double mouseY,
            int x,
            int y,
            int width,
            int height
    ) {

        return mouseX >= x
                && mouseX <= x + width
                && mouseY >= y
                && mouseY <= y + height;
    }

    // =========================================================
    // ARGB
    // =========================================================

    private int getARGB() {

        return 0xFF000000
                | (red << 16)
                | (green << 8)
                | blue;
    }

    // =========================================================
    // LIMITES
    // =========================================================

    private static int limitar255(
            int valor
    ) {

        return Math.max(
                0,
                Math.min(
                        255,
                        valor
                )
        );
    }

    private static float limitar01(
            float valor
    ) {

        return Math.max(
                0.0F,
                Math.min(
                        1.0F,
                        valor
                )
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