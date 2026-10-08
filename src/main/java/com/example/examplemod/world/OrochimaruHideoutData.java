package com.example.examplemod.world;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class OrochimaruHideoutData extends SavedData {

    private static final String DATA_NAME =
            "narutox_orochimaru_hideout";

    private boolean gerado = false;

    private int x = 0;
    private int y = 0;
    private int z = 0;


    public static OrochimaruHideoutData get(ServerLevel level) {

        return level.getDataStorage().computeIfAbsent(
                OrochimaruHideoutData::load,
                OrochimaruHideoutData::new,
                DATA_NAME
        );
    }


    public static OrochimaruHideoutData load(CompoundTag tag) {

        OrochimaruHideoutData data =
                new OrochimaruHideoutData();

        data.gerado =
                tag.getBoolean("Gerado");

        data.x =
                tag.getInt("X");

        data.y =
                tag.getInt("Y");

        data.z =
                tag.getInt("Z");

        return data;
    }


    @Override
    public CompoundTag save(CompoundTag tag) {

        tag.putBoolean(
                "Gerado",
                gerado
        );

        tag.putInt(
                "X",
                x
        );

        tag.putInt(
                "Y",
                y
        );

        tag.putInt(
                "Z",
                z
        );

        return tag;
    }


    public void definirPosicao(BlockPos pos) {

        this.x =
                pos.getX();

        this.y =
                pos.getY();

        this.z =
                pos.getZ();

        this.gerado =
                true;

        setDirty();
    }


    public boolean isGerado() {

        return gerado;
    }


    public BlockPos getPosicao() {

        return new BlockPos(
                x,
                y,
                z
        );
    }
}