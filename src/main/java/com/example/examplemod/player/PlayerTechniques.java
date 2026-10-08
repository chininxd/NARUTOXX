package com.example.examplemod.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerTechniques {

    // =========================================================
    // TÉCNICAS FIXAS DO MENU X
    //
    // SLOT 0 = MARCA DA MALDIÇÃO
    // SLOT 1 = SUSANOO
    //
    // AS OUTRAS TÉCNICAS COMEÇAM NO SLOT 2.
    // =========================================================

    public static final String TECNICA_MARCA_DA_MALDICAO =
            "Marca da Maldição";

    public static final String TECNICA_MARCA_DA_MALDICAO_SEM_ACENTO =
            "Marca da Maldicao";

    public static final String TECNICA_SUSANOO =
            "Susanoo";


    // =========================================================
    // TÉCNICA
    // =========================================================

    public static class Tecnica {

        private String nome;
        private boolean ativada;

        public Tecnica(
                String nome,
                boolean ativada
        ) {

            this.nome = nome;
            this.ativada = ativada;
        }

        public String getNome() {
            return nome;
        }

        public boolean isAtivada() {
            return ativada;
        }

        public void setAtivada(
                boolean ativada
        ) {

            this.ativada = ativada;
        }

        public void toggle() {

            this.ativada =
                    !this.ativada;
        }
    }


    // =========================================================
    // LISTA DE TÉCNICAS
    // =========================================================

    private final List<Tecnica> tecnicas =
            new ArrayList<>();


    // =========================================================
    // ADICIONAR TÉCNICA
    // =========================================================

    public boolean adicionarTecnica(
            String nome
    ) {

        if (
                nome == null
                        || nome.isEmpty()
        ) {
            return false;
        }

        if (
                possuiTecnica(
                        nome
                )
        ) {
            return false;
        }

        tecnicas.add(
                new Tecnica(
                        nome,
                        false
                )
        );

        return true;
    }


    // =========================================================
    // VERIFICAR SE POSSUI
    // =========================================================

    public boolean possuiTecnica(
            String nome
    ) {

        if (
                nome == null
                        || nome.isEmpty()
        ) {
            return false;
        }

        for (
                Tecnica tecnica : tecnicas
        ) {

            if (
                    tecnica != null
                            && tecnica.getNome() != null
                            && tecnica.getNome()
                            .equalsIgnoreCase(
                                    nome
                            )
            ) {

                return true;
            }
        }

        return false;
    }


    // =========================================================
    // PEGAR TÉCNICA PELO NOME
    // =========================================================

    public Tecnica getTecnica(
            String nome
    ) {

        if (
                nome == null
                        || nome.isEmpty()
        ) {
            return null;
        }

        for (
                Tecnica tecnica : tecnicas
        ) {

            if (
                    tecnica != null
                            && tecnica.getNome() != null
                            && tecnica.getNome()
                            .equalsIgnoreCase(
                                    nome
                            )
            ) {

                return tecnica;
            }
        }

        return null;
    }


    // =========================================================
    // PEGAR TÉCNICA PELO SLOT
    //
    // IMPORTANTE:
    //
    // SLOT 0 = MARCA
    // SLOT 1 = SUSANOO
    //
    // ISSO GARANTE QUE A ORDEM EM QUE O PLAYER APRENDEU
    // AS TÉCNICAS NÃO MUDE O SLOT DELAS NO MENU X.
    // =========================================================

    public Tecnica getTecnicaDoSlot(
            int slot
    ) {

        if (
                slot < 0
                        || slot >= 8
        ) {

            return null;
        }


        // =====================================================
        // SLOT 0 = MARCA DA MALDIÇÃO
        // =====================================================

        if (
                slot == 0
        ) {

            Tecnica marca =
                    getTecnica(
                            TECNICA_MARCA_DA_MALDICAO
                    );

            if (
                    marca == null
            ) {

                marca =
                        getTecnica(
                                TECNICA_MARCA_DA_MALDICAO_SEM_ACENTO
                        );
            }

            return marca;
        }


        // =====================================================
        // SLOT 1 = SUSANOO
        // =====================================================

        if (
                slot == 1
        ) {

            return getTecnica(
                    TECNICA_SUSANOO
            );
        }


        // =====================================================
        // OUTRAS TÉCNICAS
        //
        // SLOT 2 ATÉ 7.
        // =====================================================

        int indiceProcurado =
                slot - 2;

        int indiceAtual =
                0;


        for (
                Tecnica tecnica : tecnicas
        ) {

            if (
                    tecnica == null
                            || isTecnicaFixa(
                            tecnica.getNome()
                    )
            ) {

                continue;
            }


            if (
                    indiceAtual
                            == indiceProcurado
            ) {

                return tecnica;
            }

            indiceAtual++;
        }


        return null;
    }


    // =========================================================
    // NOME DA TÉCNICA DO SLOT
    // =========================================================

    public String getNomeDoSlot(
            int slot
    ) {

        Tecnica tecnica =
                getTecnicaDoSlot(
                        slot
                );

        if (
                tecnica == null
        ) {

            return null;
        }

        return tecnica.getNome();
    }


    // =========================================================
    // ESTADO DA TÉCNICA DO SLOT
    // =========================================================

    public boolean isTecnicaAtivada(
            int slot
    ) {

        Tecnica tecnica =
                getTecnicaDoSlot(
                        slot
                );

        if (
                tecnica == null
        ) {

            return false;
        }

        return tecnica.isAtivada();
    }


    // =========================================================
    // ATIVAR / DESATIVAR
    // =========================================================

    public boolean setTecnicaAtivada(
            int slot,
            boolean ativada
    ) {

        Tecnica tecnica =
                getTecnicaDoSlot(
                        slot
                );

        if (
                tecnica == null
        ) {

            return false;
        }

        tecnica.setAtivada(
                ativada
        );

        return true;
    }


    // =========================================================
    // TOGGLE
    // =========================================================

    public boolean toggleTecnica(
            int slot
    ) {

        Tecnica tecnica =
                getTecnicaDoSlot(
                        slot
                );

        if (
                tecnica == null
        ) {

            return false;
        }

        tecnica.toggle();

        return true;
    }


    // =========================================================
    // QUANTIDADE
    // =========================================================

    public int getQuantidadeTecnicas() {

        return tecnicas.size();
    }


    // =========================================================
    // PRIMEIRO SLOT LIVRE
    // =========================================================

    public int getPrimeiroSlotLivre() {

        for (
                int i = 0;
                i < 8;
                i++
        ) {

            if (
                    getTecnicaDoSlot(
                            i
                    ) == null
            ) {

                return i;
            }
        }

        return -1;
    }


    // =========================================================
    // SLOTS CHEIOS
    // =========================================================

    public boolean slotsCheios() {

        return tecnicas.size()
                >= 8;
    }


    // =========================================================
    // PEGAR TODAS
    // =========================================================

    public List<Tecnica> getTecnicas() {

        return new ArrayList<>(
                tecnicas
        );
    }


    // =========================================================
    // REMOVER
    // =========================================================

    public boolean removerTecnica(
            String nome
    ) {

        if (
                nome == null
                        || nome.isEmpty()
        ) {

            return false;
        }


        for (
                int i = 0;
                i < tecnicas.size();
                i++
        ) {

            Tecnica tecnica =
                    tecnicas.get(
                            i
                    );

            if (
                    tecnica != null
                            && tecnica.getNome() != null
                            && tecnica.getNome()
                            .equalsIgnoreCase(
                                    nome
                            )
            ) {

                tecnicas.remove(
                        i
                );

                return true;
            }
        }

        return false;
    }


    // =========================================================
    // LIMPAR
    // =========================================================

    public void limparTecnicas() {

        tecnicas.clear();
    }


    // =========================================================
    // TÉCNICA FIXA?
    // =========================================================

    private boolean isTecnicaFixa(
            String nome
    ) {

        if (
                nome == null
                        || nome.isEmpty()
        ) {

            return false;
        }


        return nome.equalsIgnoreCase(
                TECNICA_MARCA_DA_MALDICAO
        )
                || nome.equalsIgnoreCase(
                TECNICA_MARCA_DA_MALDICAO_SEM_ACENTO
        )
                || nome.equalsIgnoreCase(
                TECNICA_SUSANOO
        );
    }


    // =========================================================
    // GARANTIR SUSANOO REGISTRADO NO X
    // =========================================================

    public boolean garantirSusanooRegistrado() {

        if (
                possuiTecnica(
                        TECNICA_SUSANOO
                )
        ) {

            return false;
        }


        return adicionarTecnica(
                TECNICA_SUSANOO
        );
    }


    // =========================================================
    // SAVE
    // =========================================================

    public void save(
            Player player
    ) {

        if (
                player == null
        ) {

            return;
        }


        CompoundTag data =
                player.getPersistentData();


        ListTag lista =
                new ListTag();


        for (
                Tecnica tecnica : tecnicas
        ) {

            if (
                    tecnica == null
                            || tecnica.getNome() == null
                            || tecnica.getNome().isEmpty()
            ) {

                continue;
            }


            CompoundTag tecnicaTag =
                    new CompoundTag();


            tecnicaTag.putString(
                    "nome",
                    tecnica.getNome()
            );


            tecnicaTag.putBoolean(
                    "ativada",
                    tecnica.isAtivada()
            );


            lista.add(
                    tecnicaTag
            );
        }


        data.put(
                "examplemod_tecnicas",
                lista
        );
    }


    // =========================================================
    // LOAD
    // =========================================================

    public void load(
            Player player
    ) {

        if (
                player == null
        ) {

            return;
        }


        CompoundTag data =
                player.getPersistentData();


        tecnicas.clear();


        // =====================================================
        // SAVE SEM LISTA DE TÉCNICAS
        // =====================================================

        if (
                !data.contains(
                        "examplemod_tecnicas"
                )
        ) {

            /*
             * Compatibilidade:
             *
             * se o save já tinha Mangekyō + Susanoo,
             * registra Susanoo no X.
             */
            boolean possuiSusanooNosStats =
                    data.getBoolean(
                            "examplemod_mangekyou_desbloqueado"
                    )
                            && data.getInt(
                            "examplemod_susanoo_estagio"
                    ) >= 1;


            if (
                    possuiSusanooNosStats
            ) {

                boolean adicionou =
                        garantirSusanooRegistrado();


                if (
                        adicionou
                ) {

                    save(
                            player
                    );
                }
            }

            return;
        }


        // =====================================================
        // CARREGAR LISTA
        // =====================================================

        ListTag lista =
                data.getList(
                        "examplemod_tecnicas",
                        10
                );


        for (
                int i = 0;
                i < lista.size();
                i++
        ) {

            CompoundTag tecnicaTag =
                    lista.getCompound(
                            i
                    );


            String nome =
                    tecnicaTag.getString(
                            "nome"
                    );


            boolean ativada =
                    tecnicaTag.getBoolean(
                            "ativada"
                    );


            if (
                    nome != null
                            && !nome.isEmpty()
                            && !possuiTecnica(
                            nome
                    )
            ) {

                tecnicas.add(
                        new Tecnica(
                                nome,
                                ativada
                        )
                );
            }
        }


        // =====================================================
        // COMPATIBILIDADE COM SAVE ANTIGO
        //
        // JÁ POSSUÍA SUSANOO NOS STATS,
        // MAS NÃO ESTAVA REGISTRADO NAS TÉCNICAS.
        // =====================================================

        boolean possuiSusanooNosStats =
                data.getBoolean(
                        "examplemod_mangekyou_desbloqueado"
                )
                        && data.getInt(
                        "examplemod_susanoo_estagio"
                ) >= 1;


        if (
                possuiSusanooNosStats
        ) {

            boolean adicionou =
                    garantirSusanooRegistrado();


            if (
                    adicionou
            ) {

                save(
                        player
                );
            }
        }
    }
}