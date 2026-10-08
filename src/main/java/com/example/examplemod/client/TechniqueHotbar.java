package com.example.examplemod.client;

import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.network.TechniqueStatePacket;
import com.example.examplemod.network.ToggleTechniquePacket;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.lwjgl.glfw.GLFW;

public class TechniqueHotbar extends Screen {

    // =========================================================
    // TAMANHO DA HOTBAR
    // =========================================================

    private static final int LARGURA = 260;
    private static final int ALTURA = 200;


    // =========================================================
    // ESPAÇAMENTO
    // =========================================================

    private static final int ESPACO = 10;


    // =========================================================
    // TAMANHO DOS BOTÕES
    // =========================================================

    private static final int LARGURA_BOTAO = 80;
    private static final int ALTURA_BOTAO = 60;


    // =========================================================
    // OPACIDADE
    // =========================================================

    private static final float OPACIDADE = 0.99F;


    // =========================================================
    // TEXTURAS
    // =========================================================

    private static final ResourceLocation FUNCTION_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/function_button.png"
            );


    private static final ResourceLocation FUNCTION_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/function_button_hover.png"
            );


    // =========================================================
    // SLOT VISUAL SELECIONADO
    // =========================================================

    private static int slotSelecionado = 0;


    // =========================================================
    // PÁGINA
    // =========================================================

    private static int pagina = 0;


    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public TechniqueHotbar() {

        super(
                Component.literal(
                        "Técnicas"
                )
        );
    }


    // =========================================================
    // ABRIR
    // =========================================================

    public static void abrir() {

        Minecraft mc =
                Minecraft.getInstance();


        if (
                mc.player == null
        ) {

            return;
        }


        mc.setScreen(
                new TechniqueHotbar()
        );
    }


    // =========================================================
    // ALTERNAR
    // =========================================================

    public static void alternar() {

        Minecraft mc =
                Minecraft.getInstance();


        if (
                mc.player == null
        ) {

            return;
        }


        if (
                mc.screen instanceof TechniqueHotbar
        ) {

            mc.setScreen(
                    null
            );

            return;
        }


        if (
                mc.screen == null
        ) {

            mc.setScreen(
                    new TechniqueHotbar()
            );
        }
    }


    // =========================================================
    // RENDER
    // =========================================================

    @Override
    public void render(
            GuiGraphics gui,
            int mouseX,
            int mouseY,
            float partialTick
    ) {

        int x =
                (this.width - LARGURA) / 2;


        int y =
                (this.height - ALTURA) / 2;


        // =====================================================
        // SLOT 0
        //
        // MARCA DA MALDIÇÃO
        // =====================================================

        desenharBotao(
                gui,
                x,
                y,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                0
        );


        if (
                TechniqueStatePacket
                        .isClientMarcaDaMaldicaoPossui()
        ) {

            desenharMarcaDaMaldicao(
                    gui,
                    x,
                    y,
                    LARGURA_BOTAO,
                    ALTURA_BOTAO
            );
        }


        // =====================================================
        // SLOT 1
        //
        // SUSANOO
        // =====================================================

        int slot1X =
                x
                        + LARGURA_BOTAO
                        + ESPACO;


        desenharBotao(
                gui,
                slot1X,
                y,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                1
        );


        if (
                TechniqueStatePacket
                        .isClientSusanooPossui()
        ) {

            desenharSusanoo(
                    gui,
                    slot1X,
                    y,
                    LARGURA_BOTAO,
                    ALTURA_BOTAO
            );
        }


        // =====================================================
        // SLOT 2
        // =====================================================

        desenharBotao(
                gui,
                x + (
                        LARGURA_BOTAO
                                + ESPACO
                ) * 2,
                y,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                2
        );


        // =====================================================
        // SLOT 3
        // =====================================================

        desenharBotao(
                gui,
                x,
                y
                        + LARGURA_BOTAO
                        - 20
                        + ESPACO,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                3
        );


        // =====================================================
        // BOTÃO CENTRAL MAIS
        // =====================================================

        int larguraCentro =
                52;

        int alturaCentro =
                40;


        int centroX =
                x
                        + (
                        LARGURA
                                - larguraCentro
                ) / 2;


        int centroY =
                y
                        + (
                        ALTURA
                                - alturaCentro
                ) / 2;


        desenharBotao(
                gui,
                centroX,
                centroY,
                larguraCentro,
                alturaCentro,
                mouseX,
                mouseY,
                4
        );


        // =====================================================
        // TEXTO MAIS
        // =====================================================

        String textoMais =
                "MAIS";


        int textoMaisX =
                centroX
                        + (
                        larguraCentro
                                - this.font.width(
                                textoMais
                        )
                ) / 2;


        int textoMaisY =
                centroY
                        + (
                        alturaCentro
                                - this.font.lineHeight
                ) / 2;


        gui.drawString(
                this.font,
                textoMais,
                textoMaisX,
                textoMaisY,
                0xFFFFFFFF
        );


        // =====================================================
        // SLOT VISUAL 5
        //
        // TÉCNICA REAL 4
        // =====================================================

        desenharBotao(
                gui,
                x
                        + (
                        LARGURA_BOTAO
                                + ESPACO
                ) * 2,
                y
                        + ALTURA_BOTAO
                        + ESPACO,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                5
        );


        // =====================================================
        // SLOT VISUAL 6
        //
        // TÉCNICA REAL 5
        // =====================================================

        desenharBotao(
                gui,
                x,
                y
                        + (
                        ALTURA_BOTAO
                                + ESPACO
                ) * 2,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                6
        );


        // =====================================================
        // SLOT VISUAL 7
        //
        // TÉCNICA REAL 6
        // =====================================================

        desenharBotao(
                gui,
                x
                        + LARGURA_BOTAO
                        + ESPACO,
                y
                        + (
                        ALTURA_BOTAO
                                + ESPACO
                ) * 2,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                7
        );


        // =====================================================
        // SLOT VISUAL 8
        //
        // TÉCNICA REAL 7
        // =====================================================

        desenharBotao(
                gui,
                x
                        + (
                        LARGURA_BOTAO
                                + ESPACO
                ) * 2,
                y
                        + (
                        ALTURA_BOTAO
                                + ESPACO
                ) * 2,
                LARGURA_BOTAO,
                ALTURA_BOTAO,
                mouseX,
                mouseY,
                8
        );


        super.render(
                gui,
                mouseX,
                mouseY,
                partialTick
        );
    }


    // =========================================================
    // DESENHAR MARCA DA MALDIÇÃO
    // =========================================================

    private void desenharMarcaDaMaldicao(
            GuiGraphics gui,
            int x,
            int y,
            int width,
            int height
    ) {

        // =====================================================
        // NOME
        // =====================================================

        String linha1 =
                "Marca da";


        String linha2 =
                "Maldicao";


        int linha1X =
                x
                        + (
                        width
                                - this.font.width(
                                linha1
                        )
                ) / 2;


        int linha2X =
                x
                        + (
                        width
                                - this.font.width(
                                linha2
                        )
                ) / 2;


        gui.drawString(
                this.font,
                linha1,
                linha1X,
                y + 10,
                0xFFFFFFFF
        );


        gui.drawString(
                this.font,
                linha2,
                linha2X,
                y + 21,
                0xFFFFFFFF
        );


        // =====================================================
        // ESTADO
        // =====================================================

        boolean ativa =
                TechniqueStatePacket
                        .isClientMarcaDaMaldicaoAtiva();


        String estado;

        int corEstado;


        if (
                ativa
        ) {

            estado =
                    "ATIVADA";

            corEstado =
                    0xFF55FF55;

        } else {

            estado =
                    "DESATIVADA";

            corEstado =
                    0xFFFF5555;
        }


        int estadoX =
                x
                        + (
                        width
                                - this.font.width(
                                estado
                        )
                ) / 2;


        gui.drawString(
                this.font,
                estado,
                estadoX,
                y + 41,
                corEstado
        );
    }


    // =========================================================
    // DESENHAR SUSANOO
    // =========================================================

    private void desenharSusanoo(
            GuiGraphics gui,
            int x,
            int y,
            int width,
            int height
    ) {

        // =====================================================
        // DADOS
        // =====================================================

        int tipo =
                TechniqueStatePacket
                        .getClientSusanooTipo();


        int estagio =
                TechniqueStatePacket
                        .getClientSusanooEstagio();


        boolean ativo =
                TechniqueStatePacket
                        .isClientSusanooAtivo();


        // =====================================================
        // NOME DO PERSONAGEM
        // =====================================================

        String nome;


        if (
                tipo == 1
        ) {

            nome =
                    "Kakashi";

        } else if (
                tipo == 2
        ) {

            nome =
                    "Sasuke";

        } else if (
                tipo == 3
        ) {

            nome =
                    "Madara";

        } else {

            nome =
                    "";
        }


        // =====================================================
        // LINHA 1
        // =====================================================

        String linha1 =
                "Susanoo";


        int linha1X =
                x
                        + (
                        width
                                - this.font.width(
                                linha1
                        )
                ) / 2;


        gui.drawString(
                this.font,
                linha1,
                linha1X,
                y + 5,
                0xFFFFFFFF
        );


        // =====================================================
        // LINHA 2
        //
        // SASUKE / KAKASHI / MADARA
        // =====================================================

        int nomeX =
                x
                        + (
                        width
                                - this.font.width(
                                nome
                        )
                ) / 2;


        gui.drawString(
                this.font,
                nome,
                nomeX,
                y + 17,
                0xFFFFFFFF
        );


        // =====================================================
        // ESTÁGIO
        // =====================================================

        String textoEstagio =
                "Estagio "
                        + estagio;


        int estagioX =
                x
                        + (
                        width
                                - this.font.width(
                                textoEstagio
                        )
                ) / 2;


        gui.drawString(
                this.font,
                textoEstagio,
                estagioX,
                y + 29,
                0xFFFFCC55
        );


        // =====================================================
        // ESTADO
        // =====================================================

        String estado;

        int corEstado;


        if (
                ativo
        ) {

            estado =
                    "ATIVADO";

            corEstado =
                    0xFF55FF55;

        } else {

            estado =
                    "DESATIVADO";

            corEstado =
                    0xFFFF5555;
        }


        int estadoX =
                x
                        + (
                        width
                                - this.font.width(
                                estado
                        )
                ) / 2;


        gui.drawString(
                this.font,
                estado,
                estadoX,
                y + 43,
                corEstado
        );
    }


    // =========================================================
    // DESENHAR BOTÃO
    // =========================================================

    private void desenharBotao(
            GuiGraphics gui,
            int x,
            int y,
            int width,
            int height,
            int mouseX,
            int mouseY,
            int slot
    ) {

        boolean hover =
                mouseX >= x
                        && mouseX <= x + width
                        && mouseY >= y
                        && mouseY <= y + height;


        if (
                hover
        ) {

            slotSelecionado =
                    slot;
        }


        ResourceLocation texture;


        if (
                hover
                        || slotSelecionado == slot
        ) {

            texture =
                    FUNCTION_BUTTON_HOVER;

        } else {

            texture =
                    FUNCTION_BUTTON;
        }


        // =====================================================
        // NINE SLICE
        // =====================================================

        final int textureSize =
                12;

        final int border =
                3;


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


        RenderSystem.enableBlend();

        RenderSystem.defaultBlendFunc();


        RenderSystem.setShaderColor(
                1.0F,
                1.0F,
                1.0F,
                OPACIDADE
        );


        // =====================================================
        // CANTO SUPERIOR ESQUERDO
        // =====================================================

        gui.blit(
                texture,
                x,
                y,
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
                border + centerSourceWidth,
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
                0,
                border + centerSourceHeight,
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
                border + centerSourceWidth,
                border + centerSourceHeight,
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
                border + centerSourceHeight,
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
                border + centerSourceWidth,
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


        RenderSystem.setShaderColor(
                1.0F,
                1.0F,
                1.0F,
                1.0F
        );


        RenderSystem.disableBlend();
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


        int x =
                (
                        this.width
                                - LARGURA
                ) / 2;


        int y =
                (
                        this.height
                                - ALTURA
                ) / 2;


        // =====================================================
        // SLOT 0
        //
        // MARCA DA MALDIÇÃO
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x,
                        y,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    0;


            if (
                    TechniqueStatePacket
                            .isClientMarcaDaMaldicaoPossui()
            ) {

                executarTecnica(
                        0
                );
            }


            return true;
        }


        // =====================================================
        // SLOT 1
        //
        // SUSANOO
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x
                                + LARGURA_BOTAO
                                + ESPACO,
                        y,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    1;


            // =================================================
            // SÓ MANDA PARA O SERVIDOR SE POSSUI SUSANOO
            // =================================================

            if (
                    TechniqueStatePacket
                            .isClientSusanooPossui()
            ) {

                executarTecnica(
                        1
                );
            }


            return true;
        }


        // =====================================================
        // SLOT 2
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x
                                + (
                                LARGURA_BOTAO
                                        + ESPACO
                        ) * 2,
                        y,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    2;


            executarTecnica(
                    2
            );


            return true;
        }


        // =====================================================
        // SLOT 3
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x,
                        y
                                + ALTURA_BOTAO
                                + ESPACO,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    3;


            executarTecnica(
                    3
            );


            return true;
        }


        // =====================================================
        // MAIS
        // =====================================================

        int larguraCentro =
                52;

        int alturaCentro =
                40;


        int centroX =
                x
                        + (
                        LARGURA
                                - larguraCentro
                ) / 2;


        int centroY =
                y
                        + (
                        ALTURA
                                - alturaCentro
                ) / 2;


        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        centroX,
                        centroY,
                        larguraCentro,
                        alturaCentro
                )
        ) {

            pagina++;


            System.out.println(
                    "[NARUTOX] CATALOGO DE TECNICAS: PAGINA "
                            + (
                            pagina
                                    + 1
                    )
            );


            return true;
        }


        // =====================================================
        // SLOT VISUAL 5
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x
                                + (
                                LARGURA_BOTAO
                                        + ESPACO
                        ) * 2,
                        y
                                + ALTURA_BOTAO
                                + ESPACO,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    5;


            executarTecnica(
                    5
            );


            return true;
        }


        // =====================================================
        // SLOT VISUAL 6
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x,
                        y
                                + (
                                ALTURA_BOTAO
                                        + ESPACO
                        ) * 2,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    6;


            executarTecnica(
                    6
            );


            return true;
        }


        // =====================================================
        // SLOT VISUAL 7
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x
                                + LARGURA_BOTAO
                                + ESPACO,
                        y
                                + (
                                ALTURA_BOTAO
                                        + ESPACO
                        ) * 2,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    7;


            executarTecnica(
                    7
            );


            return true;
        }


        // =====================================================
        // SLOT VISUAL 8
        // =====================================================

        if (
                mouseDentro(
                        mouseX,
                        mouseY,
                        x
                                + (
                                LARGURA_BOTAO
                                        + ESPACO
                        ) * 2,
                        y
                                + (
                                ALTURA_BOTAO
                                        + ESPACO
                        ) * 2,
                        LARGURA_BOTAO,
                        ALTURA_BOTAO
                )
        ) {

            slotSelecionado =
                    8;


            executarTecnica(
                    8
            );


            return true;
        }


        return true;
    }


    // =========================================================
    // MOUSE DENTRO
    // =========================================================

    private boolean mouseDentro(
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
    // EXECUTAR TÉCNICA
    // =========================================================

    private void executarTecnica(
            int slotVisual
    ) {

        int slotTecnica;


        // =====================================================
        // SLOTS DIRETOS
        //
        // 0 = Marca
        // 1 = Susanoo
        // 2
        // 3
        // =====================================================

        if (
                slotVisual >= 0
                        && slotVisual <= 3
        ) {

            slotTecnica =
                    slotVisual;
        }


        // =====================================================
        // SLOTS VISUAIS
        //
        // 5 -> técnica 4
        // 6 -> técnica 5
        // 7 -> técnica 6
        // 8 -> técnica 7
        // =====================================================

        else if (
                slotVisual >= 5
                        && slotVisual <= 8
        ) {

            slotTecnica =
                    slotVisual - 1;

        } else {

            return;
        }


        ModNetwork.CHANNEL.sendToServer(
                new ToggleTechniquePacket(
                        slotTecnica
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

        // =====================================================
        // X FECHA
        // =====================================================

        if (
                keyCode ==
                        GLFW.GLFW_KEY_X
        ) {

            Minecraft.getInstance()
                    .setScreen(
                            null
                    );


            return true;
        }


        // =====================================================
        // ESC FECHA
        // =====================================================

        if (
                keyCode ==
                        GLFW.GLFW_KEY_ESCAPE
        ) {

            Minecraft.getInstance()
                    .setScreen(
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


    // =========================================================
    // GETTERS
    // =========================================================

    public static int getSlotSelecionado() {

        return slotSelecionado;
    }


    public static int getPagina() {

        return pagina;
    }
}