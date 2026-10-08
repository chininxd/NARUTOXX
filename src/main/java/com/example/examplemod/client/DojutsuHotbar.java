package com.example.examplemod.client;

import com.example.examplemod.network.DojutsuStatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class DojutsuHotbar {

    private static final int SLOT_SIZE = 16;
    private static final int ESPACO = 2;

    private static int slotSelecionado = 0;

    // =========================================================
    // HOTBAR
    // =========================================================

    private static final ResourceLocation HOTEBAR =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/hotebar.png"
            );

    private static final ResourceLocation HOTEBAR_HOVER =
            new ResourceLocation(
                    "examplemod",
                    "textures/ui/buttons/hotebar_hover.png"
            );

    // =========================================================
    // SHARINGAN
    // =========================================================

    private static final ResourceLocation SHARINGAN_1_TOMOE =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/doujutsu/sharingan_1_tomoe.png"
            );

    private static final ResourceLocation SHARINGAN_2_TOMOE =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/doujutsu/sharingan_2_tomoe.png"
            );

    private static final ResourceLocation SHARINGAN_3_TOMOE =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/doujutsu/sharingan_3_tomoe.png"
            );

    // =========================================================
    // MANGEKYOU
    // =========================================================

    private static final ResourceLocation MANGEKYOU =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/doujutsu/mangekyou_sharingan.png"
            );

    // =========================================================
    // BYAKUGAN NORMAL
    // =========================================================

    private static final ResourceLocation BYAKUGAN =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/doujutsu/byakugan.png"
            );

    // =========================================================
    // BYAKUGAN DESPERTADO
    // =========================================================

    private static final ResourceLocation BYAKUGAN_DESPERTADO =
            new ResourceLocation(
                    "examplemod",
                    "textures/gui/doujutsu/byakugan_despertado.png"
            );

    // =========================================================
    // RENDER
    // =========================================================

    @SubscribeEvent
    public static void render(RenderGuiOverlayEvent.Post event) {

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.options.hideGui) {
            return;
        }

        GuiGraphics gui =
                event.getGuiGraphics();

        int largura =
                mc.getWindow().getGuiScaledWidth();

        int altura =
                mc.getWindow().getGuiScaledHeight();

        int x =
                largura - SLOT_SIZE - 10;

        int y =
                (altura / 2)
                        - ((SLOT_SIZE * 4 + ESPACO * 3) / 2);

        int tomoe =
                DojutsuStatePacket.getClientSharinganTomoe();

        boolean mangekyou =
                DojutsuStatePacket.isClientMangekyouDesbloqueado();

        boolean byakugan =
                DojutsuStatePacket.isClientByakuganDesbloqueado();

        boolean byakuganDespertado =
                DojutsuStatePacket.isClientByakuganDespertado();

        // =====================================================
        // DESENHA OS 4 SLOTS
        // =====================================================

        for (int i = 0; i < 4; i++) {

            int slotY =
                    y + i * (SLOT_SIZE + ESPACO);

            boolean selecionado =
                    ModKeyMappings.DOJUTSU_KEY.isDown()
                            && slotSelecionado == i;

            ResourceLocation textura =
                    selecionado
                            ? HOTEBAR_HOVER
                            : HOTEBAR;

            // =================================================
            // FUNDO DO SLOT
            // =================================================

            gui.blit(
                    textura,
                    x,
                    slotY,
                    0,
                    0,
                    SLOT_SIZE,
                    SLOT_SIZE,
                    SLOT_SIZE,
                    SLOT_SIZE
            );

            // =================================================
            // SLOT 1 - BYAKUGAN NORMAL
            // =================================================

            if (
                    i == 0
                            && byakugan
            ) {

                gui.blit(
                        BYAKUGAN,
                        x,
                        slotY,
                        0,
                        0,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE
                );
            }

            // =================================================
            // SLOT 2 - BYAKUGAN DESPERTADO
            // =================================================

            if (
                    i == 1
                            && byakuganDespertado
            ) {

                gui.blit(
                        BYAKUGAN_DESPERTADO,
                        x,
                        slotY,
                        0,
                        0,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE
                );
            }

            // =================================================
            // SLOT 1 - 1 TOMOE
            // =================================================

            else if (
                    i == 0
                            && tomoe >= 1
            ) {

                gui.blit(
                        SHARINGAN_1_TOMOE,
                        x,
                        slotY,
                        0,
                        0,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE
                );
            }

            // =================================================
            // SLOT 2 - 2 TOMOE
            // =================================================

            if (
                    i == 1
                            && tomoe >= 2
            ) {

                gui.blit(
                        SHARINGAN_2_TOMOE,
                        x,
                        slotY,
                        0,
                        0,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE
                );
            }

            // =================================================
            // SLOT 3 - 3 TOMOE
            // =================================================

            if (
                    i == 2
                            && tomoe >= 3
            ) {

                gui.blit(
                        SHARINGAN_3_TOMOE,
                        x,
                        slotY,
                        0,
                        0,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE
                );
            }

            // =================================================
            // SLOT 4 - MANGEKYOU
            // =================================================

            if (
                    i == 3
                            && mangekyou
            ) {

                gui.blit(
                        MANGEKYOU,
                        x,
                        slotY,
                        0,
                        0,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE,
                        SLOT_SIZE
                );
            }
        }
    }

    // =========================================================
    // MOUSE SCROLL
    // =========================================================

    @SubscribeEvent
    public static void mouseScroll(
            InputEvent.MouseScrollingEvent event
    ) {

        if (!ModKeyMappings.DOJUTSU_KEY.isDown()) {
            return;
        }

        Minecraft mc =
                Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }

        double scroll =
                event.getScrollDelta();

        if (scroll > 0) {

            slotSelecionado--;

            if (slotSelecionado < 0) {
                slotSelecionado = 3;
            }

        } else if (scroll < 0) {

            slotSelecionado++;

            if (slotSelecionado > 3) {
                slotSelecionado = 0;
            }
        }

        event.setCanceled(true);
    }

    // =========================================================
    // GET SLOT
    // =========================================================

    public static int getSlotSelecionado() {
        return slotSelecionado;
    }
}