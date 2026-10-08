package com.example.examplemod.client;

import com.example.examplemod.network.TechniqueStatePacket;
import com.example.examplemod.player.PlayerStats;
import com.example.examplemod.player.PlayerStatsManager;

import com.mojang.blaze3d.platform.NativeImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.InputStream;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class PlayerSkinHandler {

    // =========================================================
    // TEXTURA DA MARCA DA MALDIÇÃO
    // =========================================================

    private static final ResourceLocation MARCA_DA_MALDICAO =
            new ResourceLocation(
                    "examplemod",
                    "textures/player/marca_maldicao.png"
            );

    // =========================================================
    // ÚLTIMA PELE APLICADA
    // =========================================================

    private static int ultimaPeleAplicada = -1;

    // =========================================================
    // ÚLTIMO ESTADO DA MARCA
    // =========================================================

    private static boolean ultimaMarcaAtiva = false;

    // =========================================================
    // PRIMEIRA ATUALIZAÇÃO
    // =========================================================

    private static boolean primeiraAtualizacao = true;

    // =========================================================
    // TEXTURA DINÂMICA
    // =========================================================

    private static DynamicTexture texturaPele;

    // =========================================================
    // RENDER PLAYER
    // =========================================================

    @SubscribeEvent
    public static void onRenderPlayer(
            RenderPlayerEvent.Pre event
    ) {

        // =====================================================
        // PLAYER
        // =====================================================

        if (!(event.getEntity()
                instanceof AbstractClientPlayer player)) {

            return;
        }

        // =====================================================
        // STATS
        // =====================================================

        PlayerStats stats =
                PlayerStatsManager.get(
                        player
                );

        if (stats == null) {
            return;
        }

        // =====================================================
        // COR DA PELE
        // =====================================================

        int corPele =
                stats.getCorPele();

        // =====================================================
        // LIMITES
        // =====================================================

        if (corPele < 1) {
            corPele = 1;
        }

        if (corPele > CustomizationScreen.PELES.length) {

            corPele =
                    CustomizationScreen.PELES.length;
        }

        // =====================================================
        // ESTADO DA MARCA
        // =====================================================

        boolean marcaAtiva =
                TechniqueStatePacket
                        .isClientMarcaDaMaldicaoAtiva();

        // =====================================================
        // VERIFICA SE PRECISA RECRIAR A SKIN
        // =====================================================

        if (!primeiraAtualizacao
                && ultimaPeleAplicada == corPele
                && ultimaMarcaAtiva == marcaAtiva) {

            return;
        }

        // =====================================================
        // TEXTURA DA PELE
        // =====================================================

        ResourceLocation pele =
                CustomizationScreen.PELES[
                        corPele - 1
                        ];

        // =====================================================
        // TEXTURA ORIGINAL DO PLAYER
        // =====================================================

        ResourceLocation skinOriginal =
                player.getSkinTextureLocation();

        // =====================================================
        // MINECRAFT
        // =====================================================

        Minecraft minecraft =
                Minecraft.getInstance();

        try {

            // =================================================
            // CARREGA A PELE
            // =================================================

            NativeImage imagemPele;

            try (
                    InputStream stream =
                            minecraft
                                    .getResourceManager()
                                    .open(
                                            pele
                                    )
            ) {

                imagemPele =
                        NativeImage.read(
                                stream
                        );
            }

            // =================================================
            // CRIA IMAGEM FINAL
            // =================================================

            NativeImage imagemFinal =
                    new NativeImage(
                            imagemPele.getWidth(),
                            imagemPele.getHeight(),
                            true
                    );

            // =================================================
            // COPIA A PELE
            // =================================================

            for (
                    int y = 0;
                    y < imagemPele.getHeight();
                    y++
            ) {

                for (
                        int x = 0;
                        x < imagemPele.getWidth();
                        x++
                ) {

                    int pixel =
                            imagemPele.getPixelRGBA(
                                    x,
                                    y
                            );

                    imagemFinal.setPixelRGBA(
                            x,
                            y,
                            pixel
                    );
                }
            }

            // =================================================
            // MARCA DA MALDIÇÃO
            // =================================================

            if (marcaAtiva) {

                aplicarMarcaDaMaldicao(
                        minecraft,
                        imagemFinal
                );
            }

            // =================================================
            // FECHA TEXTURA ANTERIOR
            // =================================================

            if (texturaPele != null) {

                texturaPele.close();

                texturaPele = null;
            }

            // =================================================
            // CRIA TEXTURA DINÂMICA
            // =================================================

            texturaPele =
                    new DynamicTexture(
                            imagemFinal
                    );

            // =================================================
            // TEXTURE MANAGER
            // =================================================

            TextureManager textureManager =
                    minecraft.getTextureManager();

            // =================================================
            // SUBSTITUI A SKIN
            // =================================================

            textureManager.register(
                    skinOriginal,
                    texturaPele
            );

            // =================================================
            // FECHA IMAGEM DA PELE
            // =================================================

            imagemPele.close();

            // =================================================
            // CACHE
            // =================================================

            ultimaPeleAplicada =
                    corPele;

            ultimaMarcaAtiva =
                    marcaAtiva;

            primeiraAtualizacao =
                    false;

            // =================================================
            // DEBUG
            // =================================================

            System.out.println(
                    "[NARUTOX] PELE ATUALIZADA"
                            + " | PELE: "
                            + corPele
                            + " | MARCA: "
                            + (
                            marcaAtiva
                                    ? "ATIVA"
                                    : "DESATIVADA"
                    )
            );

        } catch (Exception erro) {

            System.err.println(
                    "[NARUTOX] ERRO AO APLICAR PELE:"
            );

            erro.printStackTrace();
        }
    }

    // =========================================================
    // APLICAR MARCA DA MALDIÇÃO
    // =========================================================

    private static void aplicarMarcaDaMaldicao(
            Minecraft minecraft,
            NativeImage imagemFinal
    ) throws Exception {

        // =====================================================
        // CARREGA A TEXTURA DA MARCA
        // =====================================================

        NativeImage imagemMarca;

        try (
                InputStream stream =
                        minecraft
                                .getResourceManager()
                                .open(
                                        MARCA_DA_MALDICAO
                                )
        ) {

            imagemMarca =
                    NativeImage.read(
                            stream
                    );
        }

        try {

            // =================================================
            // TAMANHO MÁXIMO
            // =====================================================

            int largura =
                    Math.min(
                            imagemFinal.getWidth(),
                            imagemMarca.getWidth()
                    );

            int altura =
                    Math.min(
                            imagemFinal.getHeight(),
                            imagemMarca.getHeight()
                    );

            // =================================================
            // APLICA PIXEL POR PIXEL
            // =================================================

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

                    int pixelMarca =
                            imagemMarca.getPixelRGBA(
                                    x,
                                    y
                            );

                    // =========================================
                    // ALPHA
                    // =========================================

                    int alpha =
                            pixelMarca & 0xFF;

                    // Totalmente transparente.
                    if (alpha == 0) {
                        continue;
                    }

                    // =========================================
                    // PIXEL ORIGINAL
                    // =========================================

                    int pixelBase =
                            imagemFinal.getPixelRGBA(
                                    x,
                                    y
                            );

                    // =========================================
                    // MISTURA
                    // =========================================

                    int pixelFinal =
                            misturarPixels(
                                    pixelBase,
                                    pixelMarca
                            );

                    imagemFinal.setPixelRGBA(
                            x,
                            y,
                            pixelFinal
                    );
                }
            }

        } finally {

            imagemMarca.close();
        }
    }

    // =========================================================
    // MISTURAR PIXELS
    //
    // NativeImage usa ABGR internamente.
    // =========================================================

    private static int misturarPixels(
            int base,
            int sobreposicao
    ) {

        // =====================================================
        // BASE
        // =====================================================

        int baseR =
                base & 0xFF;

        int baseG =
                (base >> 8) & 0xFF;

        int baseB =
                (base >> 16) & 0xFF;

        int baseA =
                (base >> 24) & 0xFF;

        // =====================================================
        // SOBREPOSIÇÃO
        // =====================================================

        int overlayR =
                sobreposicao & 0xFF;

        int overlayG =
                (sobreposicao >> 8) & 0xFF;

        int overlayB =
                (sobreposicao >> 16) & 0xFF;

        int overlayA =
                (sobreposicao >> 24) & 0xFF;

        // =====================================================
        // ALPHA
        // =====================================================

        float alpha =
                overlayA / 255.0F;

        // =====================================================
        // MISTURA RGB
        // =====================================================

        int finalR =
                (int) (
                        overlayR * alpha
                                + baseR * (1.0F - alpha)
                );

        int finalG =
                (int) (
                        overlayG * alpha
                                + baseG * (1.0F - alpha)
                );

        int finalB =
                (int) (
                        overlayB * alpha
                                + baseB * (1.0F - alpha)
                );

        int finalA =
                Math.max(
                        baseA,
                        overlayA
                );

        // =====================================================
        // MONTA ABGR
        // =====================================================

        return (finalA << 24)
                | (finalB << 16)
                | (finalG << 8)
                | finalR;
    }

    // =========================================================
    // CONSTRUTOR PRIVADO
    // =========================================================

    private PlayerSkinHandler() {
    }
}