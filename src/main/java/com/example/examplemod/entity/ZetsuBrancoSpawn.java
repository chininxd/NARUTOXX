package com.example.examplemod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;

import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod",
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ZetsuBrancoSpawn {

    @SubscribeEvent
    public static void registerSpawnRules(
            SpawnPlacementRegisterEvent event
    ) {

        System.out.println(
                "[NarutoX] Registrando spawn natural do Zetsu Branco!"
        );

        event.register(
                ModEntities.ZETSU_BRANCO,

                SpawnPlacements.Type.ON_GROUND,

                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,

                ZetsuBrancoSpawn::canSpawn,

                SpawnPlacementRegisterEvent.Operation.REPLACE
        );
    }

    private static boolean canSpawn(
            EntityType<ZetsuBrancoEntity> entityType,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random
    ) {

        return level.getBlockState(pos.below())
                .isValidSpawn(
                        level,
                        pos.below(),
                        entityType
                );
    }
}