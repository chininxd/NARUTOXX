package com.example.examplemod.client;

import com.example.examplemod.network.ChooseClanPacket;
import com.example.examplemod.network.ModNetwork;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class ClanScreen extends Screen {

    // =========================================================
    // TEXTURAS
    // =========================================================

    private static final ResourceLocation MENU_BACKGROUND =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/backgrounds/menu_background.png"
            );

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
    // CLÃS
    // =========================================================

    private final String[] clas = {
            "Uchiha",
            "Hyuga"
    };

    private final String[] descricoes = {
            "Clã conhecido pelo Sharingan.",
            "Clã conhecido pelo Byakugan."
    };

    // Força, Defesa, Chakara, Velocidade
    private final int[][] atributos = {
            {15, 12, 65, 4},
            {25, 7, 98, 5}
    };

    private int claSelecionado = 0;

    // =========================================================
    // TAMANHO
    // =========================================================

    private final int panelWidth = 300;
    private final int panelHeight = 150;

    private int left;
    private int top;
    private int right;
    private int bottom;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public ClanScreen() {
        super(Component.literal("Escolha seu Clã"));
    }

    // =========================================================
    // INIT
    // =========================================================

    @Override
    protected void init() {
        super.init();

        left = (this.width - panelWidth) / 2;
        top = (this.height - panelHeight) / 2;

        right = left + panelWidth;
        bottom = top + panelHeight;
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

        Player player = Minecraft.getInstance().player;

        if (player == null) {
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
                Component.literal("ESCOLHA SEU CLÃ"),
                left + panelWidth / 2,
                top + 9,
                0xFFFFFFFF
        );

        // =====================================================
        // NOME DO CLÃ
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(clas[claSelecionado]),
                left + panelWidth / 2,
                top + 35,
                0xFFFFFFFF
        );

        // =====================================================
        // DESCRIÇÃO
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal(descricoes[claSelecionado]),
                left + panelWidth / 2,
                top + 51,
                0xFFFFFFFF
        );

        // =====================================================
        // ATRIBUTOS
        // =====================================================

        int[] atributosCla =
                atributos[claSelecionado];

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Força: " + atributosCla[0]
                ),
                left + 30,
                top + 70,
                0xFFFFFFFF
        );

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Defesa: " + atributosCla[1]
                ),
                left + 30,
                top + 84,
                0xFFFFFFFF
        );

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Chakara: " + atributosCla[2]
                ),
                left + 160,
                top + 70,
                0xFFFFFFFF
        );

        guiGraphics.drawString(
                this.font,
                Component.literal(
                        "Velocidade: " + atributosCla[3]
                ),
                left + 160,
                top + 84,
                0xFFFFFFFF
        );

        // =====================================================
        // BOTÃO ESQUERDA
        // =====================================================

        int previousX =
                left + 110;

        int previousY =
                bottom - 117;

        boolean previousHover =
                mouseX >= previousX
                        && mouseX <= previousX + 24
                        && mouseY >= previousY
                        && mouseY <= previousY + 22;

        guiGraphics.blit(
                previousHover
                        ? PREVIOUS_BUTTON_HOVER
                        : PREVIOUS_BUTTON,
                previousX,
                previousY,
                0,
                0,
                14,
                12,
                14,
                12
        );

        // =====================================================
        // ESCOLHER
        // =====================================================

        guiGraphics.drawCenteredString(
                this.font,
                Component.literal("ESCOLHER"),
                left + panelWidth / 2,
                bottom - 25,
                0xFFFFFFFF
        );

        // =====================================================
        // BOTÃO DIREITA
        // =====================================================

        int nextX =
                right - 130;

        int nextY =
                bottom - 117;

        boolean nextHover =
                mouseX >= nextX
                        && mouseX <= nextX + 24
                        && mouseY >= nextY
                        && mouseY <= nextY + 22;

        guiGraphics.blit(
                nextHover
                        ? NEXT_BUTTON_HOVER
                        : NEXT_BUTTON,
                nextX,
                nextY,
                0,
                0,
                14,
                12,
                14,
                12
        );

        super.render(
                guiGraphics,
                mouseX,
                mouseY,
                partialTick
        );
    }

    // =========================================================
    // NINE SLICE
    // MESMO SISTEMA DO DOJUTSUUPGRADESCReen
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
        // FUNDO
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
        // ESQUERDA
        // =====================================================

        int previousX =
                left + 110;

        int previousY =
                bottom - 117;

        if (
                mouseX >= previousX
                        && mouseX <= previousX + 24
                        && mouseY >= previousY
                        && mouseY <= previousY + 22
        ) {

            claSelecionado--;

            if (claSelecionado < 0) {
                claSelecionado =
                        clas.length - 1;
            }

            return true;
        }

        // =====================================================
        // DIREITA
        // =====================================================

        int nextX =
                right - 130;

        int nextY =
                bottom - 117;

        if (
                mouseX >= nextX
                        && mouseX <= nextX + 24
                        && mouseY >= nextY
                        && mouseY <= nextY + 22
        ) {

            claSelecionado++;

            if (claSelecionado >= clas.length) {
                claSelecionado = 0;
            }

            return true;
        }

        // =====================================================
        // ESCOLHER
        // =====================================================

        int escolherX =
                left + 105;

        int escolherY =
                bottom - 30;

        int escolherWidth = 90;
        int escolherHeight = 20;

        if (
                mouseX >= escolherX
                        && mouseX <= escolherX + escolherWidth
                        && mouseY >= escolherY
                        && mouseY <= escolherY + escolherHeight
        ) {

            confirmarClan();

            return true;
        }

        return super.mouseClicked(
                mouseX,
                mouseY,
                button
        );
    }

    // =========================================================
    // CONFIRMAR CLÃ
    // =========================================================

    private void confirmarClan() {

        Player player =
                Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        PlayerStats stats =
                PlayerStatsManager.get(player);

        if (!stats.getCla().equals("Nenhum")) {
            return;
        }

        ModNetwork.CHANNEL.sendToServer(
                new ChooseClanPacket(claSelecionado)
        );

        this.onClose();
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