package com.example.examplemod.client;

import com.example.examplemod.network.ChooseCustomizationPacket;
import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import com.mojang.blaze3d.platform.NativeImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.io.InputStream;

public class CustomizationScreen extends Screen {

    // =========================================================
    // FUNDO
    // =========================================================

    private static final ResourceLocation MENU_BACKGROUND =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/backgrounds/menu_background.png"
            );

    // =========================================================
    // BOTÕES
    // =========================================================

    private static final ResourceLocation PREVIOUS_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/previous_button.png"
            );

    private static final ResourceLocation PREVIOUS_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/previous_button_hover.png"
            );

    private static final ResourceLocation NEXT_BUTTON =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/next_button.png"
            );

    private static final ResourceLocation NEXT_BUTTON_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/next_button_hover.png"
            );

    // =========================================================
    // PELES
    // =========================================================

    public static final ResourceLocation[] PELES = {

            new ResourceLocation(
                    "examplemod",
                    "textures/player/male_base_1.png"
            ),

            new ResourceLocation(
                    "examplemod",
                    "textures/player/male_base_2.png"
            ),

            new ResourceLocation(
                    "examplemod",
                    "textures/player/male_base_3.png"
            ),

            new ResourceLocation(
                    "examplemod",
                    "textures/player/male_base_4.png"
            ),

            new ResourceLocation(
                    "examplemod",
                    "textures/player/male_base_5.png"
            )
    };

    // =========================================================
    // OLHOS / CABELOS
    // =========================================================

    public static final int TOTAL_OLHOS = 6;
    public static final int TOTAL_CABELOS = 3;

    // =========================================================
    // NOMES
    // =========================================================

    private final String[] nomesPele = {
            "Pele 1",
            "Pele 2",
            "Pele 3",
            "Pele 4",
            "Pele 5"
    };

    private final String[] nomesOlhos = {
            "Olhos Amarelos",
            "Olhos Brancos",
            "Olhos Pretos",
            "Olhos Azuis",
            "Olhos Roxos",
            "Olhos Verdes"
    };

    // =========================================================
    // SELEÇÃO
    // =========================================================

    private int peleSelecionada = 0;
    private int olhosSelecionados = 0;

    // Cabelo usa 1, 2 e 3
    private int cabeloSelecionado = 1;

    private int cabeloR = 255;
    private int cabeloG = 255;
    private int cabeloB = 255;

    // =========================================================
    // PAINEL
    // =========================================================

    private final int panelWidth = 330;
    private final int panelHeight = 230;

    private int left;
    private int top;
    private int right;
    private int bottom;

    // =========================================================
    // TEXTURA DO PLAYER
    // =========================================================

    private ResourceLocation texturaOriginal;

    private int ultimaPeleAplicada = -1;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public CustomizationScreen() {

        super(
                Component.literal(
                        "Personalização"
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

        Player player =
                Minecraft.getInstance().player;

        if (!(player instanceof AbstractClientPlayer clientPlayer)) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        if (stats == null) {
            return;
        }

        // =====================================================
        // PELE
        // =====================================================

        peleSelecionada =
                stats.getCorPele() - 1;

        peleSelecionada =
                Math.max(
                        0,
                        Math.min(
                                PELES.length - 1,
                                peleSelecionada
                        )
                );

        // =====================================================
        // OLHOS
        // =====================================================

        olhosSelecionados =
                stats.getCorOlhos() - 1;

        olhosSelecionados =
                Math.max(
                        0,
                        Math.min(
                                TOTAL_OLHOS - 1,
                                olhosSelecionados
                        )
                );

        // =====================================================
        // CABELO
        // =====================================================

        cabeloSelecionado =
                stats.getCabeloSelecionado();

        cabeloSelecionado =
                Math.max(
                        1,
                        Math.min(
                                TOTAL_CABELOS,
                                cabeloSelecionado
                        )
                );

        // =====================================================
        // COR DO CABELO
        // =====================================================

        cabeloR =
                stats.getCabeloR();

        cabeloG =
                stats.getCabeloG();

        cabeloB =
                stats.getCabeloB();

        // =====================================================
        // TEXTURA ORIGINAL
        // =====================================================

        texturaOriginal =
                clientPlayer.getSkinTextureLocation();

        atualizarPreview();
    }

    // =========================================================
    // APLICA SOMENTE PELE
    // =========================================================

    private void aplicarPelePreview(
            AbstractClientPlayer player
    ) {

        if (player == null) {
            return;
        }

        if (texturaOriginal == null) {

            texturaOriginal =
                    player.getSkinTextureLocation();
        }

        if (texturaOriginal == null) {
            return;
        }

        if (
                ultimaPeleAplicada ==
                        peleSelecionada
        ) {

            return;
        }

        try {

            InputStream peleStream =
                    Minecraft.getInstance()
                            .getResourceManager()
                            .getResource(
                                    PELES[peleSelecionada]
                            )
                            .get()
                            .open();

            NativeImage pele =
                    NativeImage.read(
                            peleStream
                    );

            peleStream.close();

            int largura =
                    pele.getWidth();

            int altura =
                    pele.getHeight();

            NativeImage combinada =
                    new NativeImage(
                            largura,
                            altura,
                            true
                    );

            for (
                    int y = 0;
                    y < altura;
                    y++
            ) {

                for (
                        int x = 0;
                        x < largura;
                        x++
                ) {

                    combinada.setPixelRGBA(
                            x,
                            y,
                            pele.getPixelRGBA(
                                    x,
                                    y
                            )
                    );
                }
            }

            DynamicTexture texturaFinal =
                    new DynamicTexture(
                            combinada
                    );

            TextureManager textureManager =
                    Minecraft.getInstance()
                            .getTextureManager();

            textureManager.register(
                    texturaOriginal,
                    texturaFinal
            );

            pele.close();

            ultimaPeleAplicada =
                    peleSelecionada;

        } catch (Exception e) {

            System.err.println(
                    "[NARUTOX] Erro ao aplicar pele:"
            );

            e.printStackTrace();
        }
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

        Player player =
                Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        renderBackground(
                guiGraphics
        );

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
                "PERSONALIZAÇÃO",
                left + panelWidth / 2,
                top + 9,
                0xFFFFFFFF
        );

        // =====================================================
        // PLAYER
        // =====================================================

        int avatarX =
                left + 75;

        int avatarY =
                bottom - 22;

        InventoryScreen.renderEntityInInventoryFollowsMouse(
                guiGraphics,
                avatarX,
                avatarY,
                48,
                mouseX,
                mouseY,
                player
        );

        // =====================================================
        // PELE
        // =====================================================

        int colunaX =
                left + 230;

        guiGraphics.drawCenteredString(
                this.font,
                "COR DA PELE",
                colunaX,
                top + 32,
                0xFFFFFFFF
        );

        guiGraphics.drawCenteredString(
                this.font,
                nomesPele[peleSelecionada],
                colunaX,
                top + 48,
                0xFFFFFFFF
        );

        desenharSetas(
                guiGraphics,
                mouseX,
                mouseY,
                top + 44
        );

        // =====================================================
        // OLHOS
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                "COR DOS OLHOS",
                colunaX,
                top + 72,
                0xFFFFFFFF
        );

        guiGraphics.drawCenteredString(
                this.font,
                nomesOlhos[olhosSelecionados],
                colunaX,
                top + 88,
                0xFFFFFFFF
        );

        desenharSetasOlhos(
                guiGraphics,
                mouseX,
                mouseY
        );

        // =====================================================
        // CABELO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                "CABELO",
                colunaX,
                top + 112,
                0xFFFFFFFF
        );

        guiGraphics.drawCenteredString(
                this.font,
                "Cabelo " + cabeloSelecionado,
                colunaX,
                top + 128,
                0xFFFFFFFF
        );

        desenharSetasCabelo(
                guiGraphics,
                mouseX,
                mouseY
        );

        // =====================================================
        // COR
        // =====================================================

        int corX =
                left + 190;

        int corY =
                top + 149;

        int corWidth =
                80;

        int corHeight =
                18;

        boolean corHover =
                mouseX >= corX
                        && mouseX <= corX + corWidth
                        && mouseY >= corY
                        && mouseY <= corY + corHeight;

        guiGraphics.fill(
                corX,
                corY,
                corX + corWidth,
                corY + corHeight,
                corHover
                        ? 0xFF777777
                        : 0xFF555555
        );

        guiGraphics.drawCenteredString(
                this.font,
                "COR",
                corX + corWidth / 2,
                corY + 5,
                0xFFFFFFFF
        );

        // =====================================================
        // PREVIEW DA COR
        // =====================================================

        int previewX =
                left + 280;

        int previewY =
                top + 149;

        int rgb =
                0xFF000000
                        | (cabeloR << 16)
                        | (cabeloG << 8)
                        | cabeloB;

        guiGraphics.fill(
                previewX,
                previewY,
                previewX + 25,
                previewY + 18,
                0xFF000000
        );

        guiGraphics.fill(
                previewX + 2,
                previewY + 2,
                previewX + 23,
                previewY + 16,
                rgb
        );

        // =====================================================
        // RGB TEXTO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                "RGB "
                        + cabeloR
                        + ", "
                        + cabeloG
                        + ", "
                        + cabeloB,
                colunaX,
                top + 174,
                0xFFCCCCCC
        );

        // =====================================================
        // CONFIRMAR
        // =====================================================

        int confirmarX =
                left + 185;

        int confirmarY =
                bottom - 30;

        int confirmarWidth =
                90;

        int confirmarHeight =
                18;

        boolean confirmarHover =
                mouseX >= confirmarX
                        && mouseX <= confirmarX + confirmarWidth
                        && mouseY >= confirmarY
                        && mouseY <= confirmarY + confirmarHeight;

        guiGraphics.fill(
                confirmarX,
                confirmarY,
                confirmarX + confirmarWidth,
                confirmarY + confirmarHeight,
                confirmarHover
                        ? 0xFF777777
                        : 0xFF555555
        );

        guiGraphics.drawCenteredString(
                this.font,
                "CONFIRMAR",
                confirmarX + confirmarWidth / 2,
                confirmarY + 5,
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
    // SETAS PELE
    // =========================================================

    private void desenharSetas(
            GuiGraphics gui,
            int mouseX,
            int mouseY,
            int y
    ) {

        desenharBotaoAnterior(
                gui,
                left + 170,
                y,
                mouseX,
                mouseY
        );

        desenharBotaoProximo(
                gui,
                left + 290,
                y,
                mouseX,
                mouseY
        );
    }

    // =========================================================
    // SETAS OLHOS
    // =========================================================

    private void desenharSetasOlhos(
            GuiGraphics gui,
            int mouseX,
            int mouseY
    ) {

        int y =
                top + 84;

        desenharBotaoAnterior(
                gui,
                left + 170,
                y,
                mouseX,
                mouseY
        );

        desenharBotaoProximo(
                gui,
                left + 290,
                y,
                mouseX,
                mouseY
        );
    }

    // =========================================================
    // SETAS CABELO
    // =========================================================

    private void desenharSetasCabelo(
            GuiGraphics gui,
            int mouseX,
            int mouseY
    ) {

        int y =
                top + 124;

        desenharBotaoAnterior(
                gui,
                left + 170,
                y,
                mouseX,
                mouseY
        );

        desenharBotaoProximo(
                gui,
                left + 290,
                y,
                mouseX,
                mouseY
        );
    }

    // =========================================================
    // BOTÃO ANTERIOR
    // =========================================================

    private void desenharBotaoAnterior(
            GuiGraphics gui,
            int x,
            int y,
            int mouseX,
            int mouseY
    ) {

        boolean hover =
                mouseX >= x
                        && mouseX <= x + 14
                        && mouseY >= y
                        && mouseY <= y + 12;

        gui.blit(
                hover
                        ? PREVIOUS_BUTTON_HOVER
                        : PREVIOUS_BUTTON,
                x,
                y,
                0,
                0,
                14,
                12,
                14,
                12
        );
    }

    // =========================================================
    // BOTÃO PRÓXIMO
    // =========================================================

    private void desenharBotaoProximo(
            GuiGraphics gui,
            int x,
            int y,
            int mouseX,
            int mouseY
    ) {

        boolean hover =
                mouseX >= x
                        && mouseX <= x + 14
                        && mouseY >= y
                        && mouseY <= y + 12;

        gui.blit(
                hover
                        ? NEXT_BUTTON_HOVER
                        : NEXT_BUTTON,
                x,
                y,
                0,
                0,
                14,
                12,
                14,
                12
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

        if (button != 0) {

            return super.mouseClicked(
                    mouseX,
                    mouseY,
                    button
            );
        }

        // =====================================================
        // PELE <
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 170,
                        top + 44,
                        14,
                        12
                )
        ) {

            peleSelecionada--;

            if (peleSelecionada < 0) {

                peleSelecionada =
                        PELES.length - 1;
            }

            atualizarPreview();

            return true;
        }

        // =====================================================
        // PELE >
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 290,
                        top + 44,
                        14,
                        12
                )
        ) {

            peleSelecionada++;

            if (
                    peleSelecionada >=
                            PELES.length
            ) {

                peleSelecionada = 0;
            }

            atualizarPreview();

            return true;
        }

        // =====================================================
        // OLHOS <
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 170,
                        top + 84,
                        14,
                        12
                )
        ) {

            olhosSelecionados--;

            if (olhosSelecionados < 0) {

                olhosSelecionados =
                        TOTAL_OLHOS - 1;
            }

            atualizarPreview();

            return true;
        }

        // =====================================================
        // OLHOS >
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 290,
                        top + 84,
                        14,
                        12
                )
        ) {

            olhosSelecionados++;

            if (
                    olhosSelecionados >=
                            TOTAL_OLHOS
            ) {

                olhosSelecionados = 0;
            }

            atualizarPreview();

            return true;
        }

        // =====================================================
        // CABELO <
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 170,
                        top + 124,
                        14,
                        12
                )
        ) {

            cabeloSelecionado--;

            if (cabeloSelecionado < 1) {

                cabeloSelecionado =
                        TOTAL_CABELOS;
            }

            atualizarPreview();

            return true;
        }

        // =====================================================
        // CABELO >
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 290,
                        top + 124,
                        14,
                        12
                )
        ) {

            cabeloSelecionado++;

            if (
                    cabeloSelecionado >
                            TOTAL_CABELOS
            ) {

                cabeloSelecionado = 1;
            }

            atualizarPreview();

            return true;
        }

        // =====================================================
        // COR DO CABELO
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 190,
                        top + 149,
                        80,
                        18
                )
        ) {

            Minecraft.getInstance()
                    .setScreen(
                            new HairColorScreen(
                                    this,
                                    cabeloR,
                                    cabeloG,
                                    cabeloB
                            )
                    );

            return true;
        }

        // =====================================================
        // CONFIRMAR
        // =====================================================

        if (
                dentro(
                        mouseX,
                        mouseY,
                        left + 185,
                        bottom - 30,
                        90,
                        18
                )
        ) {

            confirmar();

            return true;
        }

        return super.mouseClicked(
                mouseX,
                mouseY,
                button
        );
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
    // RECEBE COR DA TELA RGB
    // =========================================================

    public void setCorCabeloPreview(
            int r,
            int g,
            int b
    ) {

        cabeloR =
                Math.max(
                        0,
                        Math.min(
                                255,
                                r
                        )
                );

        cabeloG =
                Math.max(
                        0,
                        Math.min(
                                255,
                                g
                        )
                );

        cabeloB =
                Math.max(
                        0,
                        Math.min(
                                255,
                                b
                        )
                );

        atualizarPreview();
    }

    // =========================================================
    // ATUALIZA PREVIEW
    // =========================================================

    private void atualizarPreview() {

        Player player =
                Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        if (stats != null) {

            stats.setCorPele(
                    peleSelecionada + 1
            );

            stats.setCorOlhos(
                    olhosSelecionados + 1
            );

            stats.setCabeloSelecionado(
                    cabeloSelecionado
            );

            stats.setCorCabelo(
                    cabeloR,
                    cabeloG,
                    cabeloB
            );
        }

        if (
                player instanceof
                        AbstractClientPlayer clientPlayer
        ) {

            aplicarPelePreview(
                    clientPlayer
            );
        }
    }

    // =========================================================
    // CONFIRMAR
    // =========================================================

    private void confirmar() {

        Minecraft minecraft =
                Minecraft.getInstance();

        Player player =
                minecraft.player;

        if (player == null) {
            return;
        }

        // =====================================================
        // ENVIA TUDO PARA SERVIDOR
        // =====================================================

        ModNetwork.CHANNEL.sendToServer(
                new ChooseCustomizationPacket(
                        peleSelecionada + 1,
                        olhosSelecionados + 1,
                        cabeloSelecionado,
                        cabeloR,
                        cabeloG,
                        cabeloB
                )
        );

        // =====================================================
        // ATUALIZA CLIENTE
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        if (stats != null) {

            stats.setCorPele(
                    peleSelecionada + 1
            );

            stats.setCorOlhos(
                    olhosSelecionados + 1
            );

            stats.setCabeloSelecionado(
                    cabeloSelecionado
            );

            stats.setCorCabelo(
                    cabeloR,
                    cabeloG,
                    cabeloB
            );

            stats.setCustomizacaoConcluida(
                    true
            );
        }

        // =====================================================
        // CLÃ
        // =====================================================

        minecraft.setScreen(
                new ClanScreen()
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

        // CANTOS

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

        // BAIXO

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
    // ESC
    // =========================================================

    @Override
    public boolean keyPressed(
            int keyCode,
            int scanCode,
            int modifiers
    ) {

        if (keyCode == 256) {
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