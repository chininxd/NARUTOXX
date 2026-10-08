package com.example.examplemod.client;

import com.example.examplemod.player.PlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class DamageIndicator {

    private static final Map<UUID, DamageInfo> DANOS =
            new ConcurrentHashMap<>();

    private static class DamageInfo {

        final double x;
        final double y;
        final double z;
        final float dano;
        final long tempo;

        DamageInfo(
                LivingEntity entidade,
                float dano
        ) {

            this.x = entidade.getX();
            this.y = entidade.getY()
                    + entidade.getBbHeight()
                    + 0.8D;
            this.z = entidade.getZ();

            this.dano = dano;
            this.tempo = System.currentTimeMillis();
        }
    }

    /*
     * Renderiza o HUD.
     */
    @SubscribeEvent
    public static void renderDamageHud(
            RenderGuiOverlayEvent.Post event
    ) {

        renderDamage(event.getGuiGraphics());
    }

    /*
     * Recebe o dano.
     */
    public static void addDamage(
            UUID alvo,
            float dano
    ) {

        Minecraft minecraft =
                Minecraft.getInstance();

        if (minecraft.level == null) {
            System.out.println(
                    ">>> ERRO: CLIENTE SEM LEVEL"
            );
            return;
        }

        if (minecraft.player == null) {
            return;
        }

        LivingEntity entidadeEncontrada = null;

        /*
         * Procura a entidade pelo UUID.
         */
        for (LivingEntity entidade :
                minecraft.level.getEntitiesOfClass(
                        LivingEntity.class,
                        minecraft.player
                                .getBoundingBox()
                                .inflate(64.0D)
                )) {

            if (entidade.getUUID().equals(alvo)) {

                entidadeEncontrada = entidade;
                break;
            }
        }

        if (entidadeEncontrada == null) {

            System.out.println(
                    ">>> NÃO ENCONTROU ENTIDADE: "
                            + alvo
            );

            return;
        }

        System.out.println(
                ">>> ENTIDADE ENCONTRADA: "
                        + entidadeEncontrada
                        .getName()
                        .getString()
                        + " | DANO: "
                        + dano
        );

        DANOS.put(
                alvo,
                new DamageInfo(
                        entidadeEncontrada,
                        dano
                )
        );
    }

    /*
     * Renderiza os números.
     */
    private static void renderDamage(
            GuiGraphics guiGraphics
    ) {

        Minecraft minecraft =
                Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        long agora =
                System.currentTimeMillis();

        for (Map.Entry<UUID, DamageInfo> entry :
                DANOS.entrySet()) {

            DamageInfo info =
                    entry.getValue();

            /*
             * Fica na tela por 500 ms.
             */
            if (agora - info.tempo > 500) {

                DANOS.remove(
                        entry.getKey()
                );

                continue;
            }

            double distancia =
                    Math.sqrt(
                            Math.pow(
                                    minecraft.player.getX()
                                            - info.x,
                                    2
                            )
                                    +
                                    Math.pow(
                                            minecraft.player.getY()
                                                    - info.y,
                                            2
                                    )
                                    +
                                    Math.pow(
                                            minecraft.player.getZ()
                                                    - info.z,
                                            2
                                    )
                    );

            /*
             * Não mostra se estiver muito longe.
             */
            if (distancia > 64.0D) {
                continue;
            }

            /*
             * Converte posição do mundo
             * para posição da tela.
             */
            double[] tela =
                    worldToScreen(
                            minecraft,
                            info.x,
                            info.y,
                            info.z
                    );

            if (tela == null) {
                continue;
            }

            String texto =
                    "-" + formatarDano(info.dano);

            int largura =
                    minecraft.font.width(texto);

            int screenX =
                    (int) tela[0]
                            - largura / 2;

            int screenY =
                    (int) tela[1];

            /*
             * Sombra.
             */
            guiGraphics.drawString(
                    minecraft.font,
                    texto,
                    screenX + 1,
                    screenY + 1,
                    0x000000,
                    false
            );

            /*
             * Dano.
             */
            guiGraphics.drawString(
                    minecraft.font,
                    texto,
                    screenX,
                    screenY,
                    0xFFFFFF,
                    false
            );
        }
    }

    /*
     * Converte coordenadas do mundo
     * para coordenadas da tela.
     */
    private static double[] worldToScreen(
            Minecraft minecraft,
            double x,
            double y,
            double z
    ) {

        var camera =
                minecraft.gameRenderer
                        .getMainCamera();

        var cameraPos =
                camera.getPosition();

        double dx =
                x - cameraPos.x;

        double dy =
                y - cameraPos.y;

        double dz =
                z - cameraPos.z;

        float yaw =
                camera.getYRot();

        float pitch =
                camera.getXRot();

        double yawRad =
                Math.toRadians(yaw);

        double pitchRad =
                Math.toRadians(pitch);

        /*
         * Rotação horizontal.
         */
        double cosYaw =
                Math.cos(yawRad);

        double sinYaw =
                Math.sin(yawRad);

        double x1 =
                dx * cosYaw
                        + dz * sinYaw;

        double z1 =
                -dx * sinYaw
                        + dz * cosYaw;

        /*
         * Rotação vertical.
         */
        double cosPitch =
                Math.cos(pitchRad);

        double sinPitch =
                Math.sin(pitchRad);

        double y1 =
                dy * cosPitch
                        - z1 * sinPitch;

        double z2 =
                dy * sinPitch
                        + z1 * cosPitch;

        /*
         * Está atrás da câmera.
         */
        if (z2 <= 0.05D) {
            return null;
        }

        /*
         * FOV.
         */
        double fov =
                minecraft.options
                        .fov()
                        .get();

        double escala =
                minecraft.getWindow()
                        .getGuiScaledHeight()
                        /
                        (
                                2.0D
                                        *
                                        Math.tan(
                                                Math.toRadians(
                                                        fov / 2.0D
                                                )
                                        )
                        );

        double screenX =
                minecraft.getWindow()
                        .getGuiScaledWidth()
                        / 2.0D
                        +
                        (
                                x1
                                        * escala
                                        / z2
                        );

        double screenY =
                minecraft.getWindow()
                        .getGuiScaledHeight()
                        / 2.0D
                        -
                        (
                                y1
                                        * escala
                                        / z2
                        );

        return new double[]{
                screenX,
                screenY
        };
    }

    private static String formatarDano(
            float dano
    ) {

        if (dano == (int) dano) {

            return String.valueOf(
                    (int) dano
            );
        }

        return String.format(
                java.util.Locale.US,
                "%.1f",
                dano
        );
    }
}