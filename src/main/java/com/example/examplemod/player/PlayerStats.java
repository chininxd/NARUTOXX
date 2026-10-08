package com.example.examplemod.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class PlayerStats {

    // =========================================================
    // STATUS
    // =========================================================

    private int nivel = 1;
    private int pontos = 0;

    private int forca = 1;
    private int defesa = 1;
    private int chakara = 1;
    private int mente = 1;

    private int chakraAtual = 1;
    private int upgradesTotais = 0;

    private String cla = "Nenhum";
    private String linhagem = "Nenhuma";
    private String alinhamento = "Neutro";


    // =========================================================
    // RYO
    // =========================================================

    private int ryo = 0;


    // =========================================================
    // PERSONALIZAÇÃO
    // =========================================================

    private int corPele = 1;
    private int corOlhos = 1;
    private boolean customizacaoConcluida = false;


    // =========================================================
    // CABELO
    // =========================================================

    private int cabeloSelecionado = 1;

    private int cabeloR = 255;
    private int cabeloG = 255;
    private int cabeloB = 255;


    // =========================================================
    // SHARINGAN
    // =========================================================

    private int sharinganTomoe = 0;
    private boolean sharinganAtivo = false;
    private int sharinganTomoeAtivo = 0;


    // =========================================================
    // MANGEKYOU
    // =========================================================

    private boolean mangekyouDesbloqueado = false;
    private boolean mangekyouAtivo = false;

    /*
     * Mangekyō Sharingan Eterno.
     *
     * false = Mangekyō normal
     * true  = Mangekyō Eterno
     */
    private boolean mangekyouEternoDesbloqueado = false;


    // =========================================================
    // TIPO DO MANGEKYOU
    //
    // 0 = Nenhum
    // 1 = Kakashi
    // 2 = Sasuke
    // 3 = Madara
    // =========================================================

    private int mangekyouTipo = 0;


    // =========================================================
    // SUSANOO
    //
    // TIPO:
    //
    // 0 = Nenhum
    // 1 = Kakashi
    // 2 = Sasuke
    // 3 = Madara
    //
    // ESTÁGIO:
    //
    // 0 = Nenhum
    // 1 = Costelas
    // 2 = Estágio 2
    // 3 = Estágio 3
    // 4 = Blindado / Armado
    // 5 = Susanoo Perfeito
    //
    // Estágios 1 até 4:
    // Mangekyō normal.
    //
    // Estágio 5:
    // exige Mangekyō Eterno.
    // =========================================================

    private int susanooTipo = 0;
    private int susanooEstagio = 0;
    private boolean susanooAtivo = false;


    // =========================================================
    // BYAKUGAN NORMAL
    // =========================================================

    private boolean byakuganDesbloqueado = false;
    private boolean byakuganAtivo = false;


    // =========================================================
    // BYAKUGAN DESPERTADO
    // =========================================================

    private boolean byakuganDespertado = false;
    private boolean byakuganDespertadoAtivo = false;


    // =========================================================
    // GETTERS STATUS
    // =========================================================

    public int getNivel() {
        return nivel;
    }

    public int getPontos() {
        return pontos;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getChakara() {
        return chakara;
    }

    public int getMente() {
        return mente;
    }

    public int getChakraAtual() {
        return chakraAtual;
    }

    public int getUpgradesTotais() {
        return upgradesTotais;
    }

    public String getCla() {
        return cla;
    }

    public String getLinhagem() {
        return linhagem;
    }

    public String getAlinhamento() {
        return alinhamento;
    }


    // =========================================================
    // RYO
    // =========================================================

    public int getRyo() {
        return ryo;
    }


    // =========================================================
    // PERSONALIZAÇÃO GETTERS
    // =========================================================

    public int getCorPele() {
        return corPele;
    }

    public int getCorOlhos() {
        return corOlhos;
    }

    public boolean isCustomizacaoConcluida() {
        return customizacaoConcluida;
    }


    // =========================================================
    // CABELO GETTERS
    // =========================================================

    public int getCabeloSelecionado() {
        return cabeloSelecionado;
    }

    public int getCabeloR() {
        return cabeloR;
    }

    public int getCabeloG() {
        return cabeloG;
    }

    public int getCabeloB() {
        return cabeloB;
    }


    // =========================================================
    // DOJUTSU GETTERS
    // =========================================================

    public int getSharinganTomoe() {
        return sharinganTomoe;
    }

    public boolean isSharinganAtivo() {
        return sharinganAtivo;
    }

    public int getSharinganTomoeAtivo() {
        return sharinganTomoeAtivo;
    }

    public boolean isMangekyouDesbloqueado() {
        return mangekyouDesbloqueado;
    }

    public boolean isMangekyouAtivo() {
        return mangekyouAtivo;
    }

    public int getMangekyouTipo() {
        return mangekyouTipo;
    }

    public boolean isMangekyouEternoDesbloqueado() {
        return mangekyouEternoDesbloqueado;
    }


    // =========================================================
    // SUSANOO GETTERS
    // =========================================================

    public int getSusanooTipo() {
        return susanooTipo;
    }

    public int getSusanooEstagio() {
        return susanooEstagio;
    }

    public boolean isSusanooAtivo() {
        return susanooAtivo;
    }

    public boolean isSusanooDesbloqueado() {

        return mangekyouDesbloqueado
                && mangekyouTipo >= 1
                && mangekyouTipo <= 3
                && susanooTipo == mangekyouTipo
                && susanooEstagio >= 1;
    }

    public boolean podeUsarSusanoo() {
        return isSusanooDesbloqueado();
    }


    // =========================================================
    // ESTÁGIO MÁXIMO DO SUSANOO
    // =========================================================

    public int getSusanooEstagioMaximo() {

        if (!mangekyouDesbloqueado) {
            return 0;
        }

        if (mangekyouEternoDesbloqueado) {
            return 5;
        }

        return 4;
    }


    // =========================================================
    // PODE DESBLOQUEAR ESTÁGIO
    // =========================================================

    public boolean podeDesbloquearSusanooEstagio(int estagio) {

        if (!mangekyouDesbloqueado) {
            return false;
        }

        if (mangekyouTipo < 1 || mangekyouTipo > 3) {
            return false;
        }

        if (estagio < 2 || estagio > 5) {
            return false;
        }

        /*
         * Obrigatoriamente precisa comprar em ordem.
         *
         * 1 -> 2
         * 2 -> 3
         * 3 -> 4
         * 4 -> 5
         */
        if (estagio != susanooEstagio + 1) {
            return false;
        }

        /*
         * Susanoo Perfeito exige EMS.
         */
        if (
                estagio == 5
                        && !mangekyouEternoDesbloqueado
        ) {
            return false;
        }

        return true;
    }


    // =========================================================
    // BYAKUGAN GETTERS
    // =========================================================

    public boolean isByakuganDesbloqueado() {
        return byakuganDesbloqueado;
    }

    public boolean isByakuganAtivo() {
        return byakuganAtivo;
    }

    public boolean isByakuganDespertado() {
        return byakuganDespertado;
    }

    public boolean isByakuganDespertadoAtivo() {
        return byakuganDespertadoAtivo;
    }


    // =========================================================
    // STATUS SETTERS
    // =========================================================

    public void setNivel(int nivel) {
        this.nivel = Math.max(1, nivel);
    }

    public void setPontos(int pontos) {
        this.pontos = Math.max(0, pontos);
    }

    public void setForca(int forca) {
        this.forca = Math.max(1, forca);
    }

    public void setDefesa(int defesa) {

        this.defesa = Math.max(1, defesa);

        if (chakraAtual > chakara) {
            chakraAtual = chakara;
        }
    }

    public void setChakara(int chakara) {

        this.chakara = Math.max(1, chakara);

        if (chakraAtual > this.chakara) {
            chakraAtual = this.chakara;
        }
    }

    public void setMente(int mente) {
        this.mente = Math.max(1, mente);
    }

    public void setChakraAtual(int chakraAtual) {

        this.chakraAtual =
                Math.max(
                        0,
                        Math.min(
                                chakraAtual,
                                chakara
                        )
                );
    }

    public void setUpgradesTotais(int upgradesTotais) {
        this.upgradesTotais = Math.max(0, upgradesTotais);
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public void setLinhagem(String linhagem) {
        this.linhagem = linhagem;
    }

    public void setAlinhamento(String alinhamento) {
        this.alinhamento = alinhamento;
    }


    // =========================================================
    // RYO
    // =========================================================

    public void setRyo(int ryo) {
        this.ryo = Math.max(0, ryo);
    }

    public void adicionarRyo(int quantidade) {

        if (quantidade <= 0) {
            return;
        }

        ryo += quantidade;
    }

    public boolean gastarRyo(int quantidade) {

        if (quantidade <= 0) {
            return true;
        }

        if (ryo < quantidade) {
            return false;
        }

        ryo -= quantidade;

        return true;
    }


    // =========================================================
    // PERSONALIZAÇÃO
    // =========================================================

    public void setCorPele(int corPele) {

        this.corPele =
                Math.max(
                        1,
                        Math.min(
                                5,
                                corPele
                        )
                );
    }

    public void setCorOlhos(int corOlhos) {

        this.corOlhos =
                Math.max(
                        1,
                        Math.min(
                                6,
                                corOlhos
                        )
                );
    }

    public void setCustomizacaoConcluida(boolean concluida) {
        this.customizacaoConcluida = concluida;
    }


    // =========================================================
    // CABELO
    // =========================================================

    public void setCabeloSelecionado(int cabeloSelecionado) {

        this.cabeloSelecionado =
                Math.max(
                        1,
                        Math.min(
                                3,
                                cabeloSelecionado
                        )
                );
    }

    public void setCabeloR(int cabeloR) {

        this.cabeloR =
                Math.max(
                        0,
                        Math.min(
                                255,
                                cabeloR
                        )
                );
    }

    public void setCabeloG(int cabeloG) {

        this.cabeloG =
                Math.max(
                        0,
                        Math.min(
                                255,
                                cabeloG
                        )
                );
    }

    public void setCabeloB(int cabeloB) {

        this.cabeloB =
                Math.max(
                        0,
                        Math.min(
                                255,
                                cabeloB
                        )
                );
    }

    public void setCorCabelo(
            int r,
            int g,
            int b
    ) {

        setCabeloR(r);
        setCabeloG(g);
        setCabeloB(b);
    }


    // =========================================================
    // BYAKUGAN NORMAL
    // =========================================================

    public void setByakuganDesbloqueado(
            boolean desbloqueado
    ) {

        if (
                desbloqueado
                        && !"Hyuga".equalsIgnoreCase(cla)
        ) {
            return;
        }

        this.byakuganDesbloqueado =
                desbloqueado;

        if (!desbloqueado) {
            this.byakuganAtivo = false;
        }
    }


    public void setByakuganAtivo(
            boolean ativo
    ) {

        if (
                ativo
                        && !byakuganDesbloqueado
        ) {

            this.byakuganAtivo = false;
            return;
        }

        if (
                ativo
                        && !"Hyuga".equalsIgnoreCase(cla)
        ) {

            this.byakuganAtivo = false;
            return;
        }

        if (ativo) {

            this.byakuganDespertadoAtivo = false;

            this.sharinganAtivo = false;
            this.sharinganTomoeAtivo = 0;

            this.mangekyouAtivo = false;
        }

        this.byakuganAtivo = ativo;
    }


    // =========================================================
    // BYAKUGAN DESPERTADO
    // =========================================================

    public void setByakuganDespertado(
            boolean desbloqueado
    ) {

        if (
                desbloqueado
                        && !"Hyuga".equalsIgnoreCase(cla)
        ) {
            return;
        }

        this.byakuganDespertado =
                desbloqueado;

        if (!desbloqueado) {
            this.byakuganDespertadoAtivo = false;
        }
    }


    public void setByakuganDespertadoAtivo(
            boolean ativo
    ) {

        if (
                ativo
                        && !byakuganDespertado
        ) {

            this.byakuganDespertadoAtivo = false;
            return;
        }

        if (
                ativo
                        && !"Hyuga".equalsIgnoreCase(cla)
        ) {

            this.byakuganDespertadoAtivo = false;
            return;
        }

        if (ativo) {

            this.byakuganAtivo = false;

            this.sharinganAtivo = false;
            this.sharinganTomoeAtivo = 0;

            this.mangekyouAtivo = false;
        }

        this.byakuganDespertadoAtivo =
                ativo;
    }


    // =========================================================
    // SHARINGAN
    // =========================================================

    public void setSharinganTomoe(
            int tomoe
    ) {

        tomoe =
                Math.max(
                        0,
                        Math.min(
                                3,
                                tomoe
                        )
                );

        this.sharinganTomoe =
                tomoe;


        // =====================================================
        // SEM SHARINGAN
        // =====================================================

        if (tomoe <= 0) {

            this.sharinganAtivo = false;
            this.sharinganTomoeAtivo = 0;

            this.mangekyouDesbloqueado = false;
            this.mangekyouAtivo = false;
            this.mangekyouEternoDesbloqueado = false;
            this.mangekyouTipo = 0;

            limparSusanoo();

            return;
        }


        // =====================================================
        // MENOS DE 3 TOMOES
        // =====================================================

        if (tomoe < 3) {

            this.mangekyouDesbloqueado = false;
            this.mangekyouAtivo = false;
            this.mangekyouEternoDesbloqueado = false;
            this.mangekyouTipo = 0;

            limparSusanoo();
        }


        if (sharinganTomoeAtivo > tomoe) {
            sharinganTomoeAtivo = tomoe;
        }

        if (sharinganTomoeAtivo <= 0) {
            sharinganAtivo = false;
        }
    }


    public void setSharinganAtivo(
            boolean ativo
    ) {

        if (
                ativo
                        && sharinganTomoe < 1
        ) {

            this.sharinganAtivo = false;
            this.sharinganTomoeAtivo = 0;

            return;
        }

        if (ativo) {

            this.mangekyouAtivo = false;

            this.byakuganAtivo = false;
            this.byakuganDespertadoAtivo = false;
        }

        this.sharinganAtivo =
                ativo;

        if (!ativo) {

            this.sharinganTomoeAtivo = 0;

        } else if (sharinganTomoeAtivo <= 0) {

            this.sharinganTomoeAtivo = 1;
        }
    }


    public void setSharinganTomoeAtivo(
            int tomoe
    ) {

        tomoe =
                Math.max(
                        0,
                        Math.min(
                                3,
                                tomoe
                        )
                );

        if (tomoe > sharinganTomoe) {
            return;
        }

        this.sharinganTomoeAtivo =
                tomoe;

        if (tomoe <= 0) {

            this.sharinganAtivo = false;

        } else {

            this.sharinganAtivo = true;

            this.mangekyouAtivo = false;

            this.byakuganAtivo = false;
            this.byakuganDespertadoAtivo = false;
        }
    }


    // =========================================================
    // MANGEKYOU
    // =========================================================

    public void setMangekyouDesbloqueado(
            boolean desbloqueado
    ) {

        if (
                desbloqueado
                        && sharinganTomoe < 3
        ) {
            return;
        }

        this.mangekyouDesbloqueado =
                desbloqueado;


        // =====================================================
        // PERDEU O MANGEKYOU
        // =====================================================

        if (!desbloqueado) {

            this.mangekyouAtivo = false;

            this.mangekyouEternoDesbloqueado =
                    false;

            this.mangekyouTipo = 0;

            limparSusanoo();

            return;
        }


        atualizarSusanooPeloMangekyou();
    }


    public void setMangekyouAtivo(
            boolean ativo
    ) {

        if (
                ativo
                        && !mangekyouDesbloqueado
        ) {

            this.mangekyouAtivo = false;
            return;
        }

        if (ativo) {

            this.sharinganAtivo = false;
            this.sharinganTomoeAtivo = 0;

            this.byakuganAtivo = false;
            this.byakuganDespertadoAtivo = false;
        }

        this.mangekyouAtivo =
                ativo;
    }


    // =========================================================
    // MANGEKYOU ETERNO
    // =========================================================

    public void setMangekyouEternoDesbloqueado(
            boolean desbloqueado
    ) {

        /*
         * Não pode ter EMS sem possuir Mangekyō normal.
         */
        if (
                desbloqueado
                        && !mangekyouDesbloqueado
        ) {
            return;
        }

        this.mangekyouEternoDesbloqueado =
                desbloqueado;


        /*
         * Caso algum sistema remova o EMS,
         * o Estágio 5 deixa de ser válido.
         *
         * O jogador volta para o Estágio 4.
         */
        if (
                !desbloqueado
                        && susanooEstagio > 4
        ) {

            susanooEstagio = 4;
        }
    }


    // =========================================================
    // TIPO DO MANGEKYOU
    // =========================================================

    public void setMangekyouTipo(
            int tipo
    ) {

        this.mangekyouTipo =
                Math.max(
                        0,
                        Math.min(
                                3,
                                tipo
                        )
                );

        atualizarSusanooPeloMangekyou();
    }


    // =========================================================
    // SUSANOO TIPO
    // =========================================================

    public void setSusanooTipo(
            int tipo
    ) {

        tipo =
                Math.max(
                        0,
                        Math.min(
                                3,
                                tipo
                        )
                );

        if (!mangekyouDesbloqueado) {

            limparSusanoo();
            return;
        }

        /*
         * O tipo do Susanoo nunca pode ser diferente
         * do tipo do Mangekyō.
         */
        if (tipo != mangekyouTipo) {

            limparSusanoo();
            return;
        }

        this.susanooTipo =
                tipo;
    }


    // =========================================================
    // SUSANOO ESTÁGIO
    // =========================================================

    public void setSusanooEstagio(
            int estagio
    ) {

        if (
                !mangekyouDesbloqueado
                        || mangekyouTipo < 1
                        || mangekyouTipo > 3
        ) {

            limparSusanoo();
            return;
        }


        estagio =
                Math.max(
                        1,
                        Math.min(
                                5,
                                estagio
                        )
                );


        /*
         * Susanoo Perfeito exige EMS.
         */
        if (
                estagio == 5
                        && !mangekyouEternoDesbloqueado
        ) {

            return;
        }


        this.susanooTipo =
                mangekyouTipo;

        this.susanooEstagio =
                estagio;
    }


    // =========================================================
    // SUSANOO ATIVO
    // =========================================================

    public void setSusanooAtivo(
            boolean ativo
    ) {

        /*
         * Desativar sempre é permitido.
         */
        if (!ativo) {

            this.susanooAtivo = false;
            return;
        }


        if (!mangekyouDesbloqueado) {

            this.susanooAtivo = false;
            return;
        }


        if (
                mangekyouTipo < 1
                        || mangekyouTipo > 3
        ) {

            this.susanooAtivo = false;
            return;
        }


        if (susanooTipo != mangekyouTipo) {

            this.susanooAtivo = false;
            return;
        }


        if (susanooEstagio <= 0) {

            this.susanooAtivo = false;
            return;
        }


        /*
         * Segurança extra.
         *
         * Estágio 5 sem EMS nunca pode ser ativado.
         */
        if (
                susanooEstagio == 5
                        && !mangekyouEternoDesbloqueado
        ) {

            this.susanooAtivo = false;
            return;
        }


        this.susanooAtivo = true;
    }


    // =========================================================
    // TOGGLE SUSANOO
    // =========================================================

    public void toggleSusanoo() {

        setSusanooAtivo(
                !susanooAtivo
        );
    }


    // =========================================================
    // DESBLOQUEAR ESTÁGIO 2
    // =========================================================

    public void desbloquearSusanooEstagio2() {

        desbloquearSusanooEstagio(
                2
        );
    }


    // =========================================================
    // DESBLOQUEAR ESTÁGIO 3
    // =========================================================

    public void desbloquearSusanooEstagio3() {

        desbloquearSusanooEstagio(
                3
        );
    }


    // =========================================================
    // DESBLOQUEAR ESTÁGIO 4
    // =========================================================

    public void desbloquearSusanooEstagio4() {

        desbloquearSusanooEstagio(
                4
        );
    }


    // =========================================================
    // DESBLOQUEAR ESTÁGIO 5
    // =========================================================

    public void desbloquearSusanooEstagio5() {

        desbloquearSusanooEstagio(
                5
        );
    }


    // =========================================================
    // DESBLOQUEAR SUSANOO INTERNO
    // =========================================================

    private void desbloquearSusanooEstagio(
            int estagio
    ) {

        if (
                !podeDesbloquearSusanooEstagio(
                        estagio
                )
        ) {
            return;
        }

        this.susanooTipo =
                mangekyouTipo;

        this.susanooEstagio =
                estagio;
    }


    // =========================================================
    // ATUALIZAR SUSANOO PELO MANGEKYOU
    // =========================================================

    private void atualizarSusanooPeloMangekyou() {

        /*
         * Precisa ter Mangekyō desbloqueado
         * e um tipo válido.
         */
        if (
                mangekyouDesbloqueado
                        && mangekyouTipo >= 1
                        && mangekyouTipo <= 3
        ) {

            /*
             * Susanoo sempre acompanha o tipo do Mangekyō.
             */
            susanooTipo =
                    mangekyouTipo;


            /*
             * Ao conseguir Mangekyō:
             *
             * libera automaticamente
             * Susanoo Estágio 1.
             */
            if (susanooEstagio <= 0) {

                susanooEstagio = 1;
            }

            return;
        }


        limparSusanoo();
    }


    // =========================================================
    // LIMPAR SUSANOO
    // =========================================================

    private void limparSusanoo() {

        susanooTipo = 0;
        susanooEstagio = 0;
        susanooAtivo = false;
    }


    // =========================================================
    // CHAKRA
    // =========================================================

    public boolean gastarChakra(
            int quantidade
    ) {

        if (quantidade <= 0) {
            return true;
        }

        if (chakraAtual < quantidade) {
            return false;
        }

        chakraAtual -= quantidade;

        return true;
    }


    public void recuperarChakra(
            int quantidade
    ) {

        if (quantidade <= 0) {
            return;
        }

        chakraAtual =
                Math.min(
                        chakara,
                        chakraAtual + quantidade
                );
    }


    public void recuperarTodoChakra() {

        chakraAtual =
                chakara;
    }


    // =========================================================
    // UPGRADES
    // =========================================================

    public int getCustoProximoUpgrade() {

        return getCustoUpgrades(
                upgradesTotais + 1
        );
    }


    public int getCustoUpgrades(
            int quantidade
    ) {

        if (quantidade <= 0) {
            return 0;
        }

        return quantidade * 10;
    }


    public void adicionarUpgrades(
            int quantidade
    ) {

        if (quantidade <= 0) {
            return;
        }

        upgradesTotais += quantidade;
    }


    // =========================================================
    // SAVE
    // =========================================================

    public void save(
            Player player
    ) {

        if (player == null) {
            return;
        }

        CompoundTag data =
                player.getPersistentData();


        // =====================================================
        // STATUS
        // =====================================================

        data.putInt(
                "examplemod_nivel",
                nivel
        );

        data.putInt(
                "examplemod_pontos",
                pontos
        );

        data.putInt(
                "examplemod_forca",
                forca
        );

        data.putInt(
                "examplemod_defesa",
                defesa
        );

        data.putInt(
                "examplemod_chakara",
                chakara
        );

        data.putInt(
                "examplemod_mente",
                mente
        );

        data.putInt(
                "examplemod_chakra_atual",
                chakraAtual
        );

        data.putInt(
                "examplemod_upgrades_totais",
                upgradesTotais
        );


        // =====================================================
        // RYO
        // =====================================================

        data.putInt(
                "examplemod_ryo",
                ryo
        );


        // =====================================================
        // CLÃ / LINHAGEM / ALINHAMENTO
        // =====================================================

        data.putString(
                "examplemod_cla",
                cla
        );

        data.putString(
                "examplemod_linhagem",
                linhagem
        );

        data.putString(
                "examplemod_alinhamento",
                alinhamento
        );


        // =====================================================
        // PERSONALIZAÇÃO
        // =====================================================

        data.putInt(
                "examplemod_cor_pele",
                corPele
        );

        data.putInt(
                "examplemod_cor_olhos",
                corOlhos
        );

        data.putBoolean(
                "examplemod_customizacao_concluida",
                customizacaoConcluida
        );


        // =====================================================
        // CABELO
        // =====================================================

        data.putInt(
                "examplemod_cabelo_selecionado",
                cabeloSelecionado
        );

        data.putInt(
                "examplemod_cabelo_r",
                cabeloR
        );

        data.putInt(
                "examplemod_cabelo_g",
                cabeloG
        );

        data.putInt(
                "examplemod_cabelo_b",
                cabeloB
        );


        // =====================================================
        // SHARINGAN
        // =====================================================

        data.putInt(
                "examplemod_sharingan_tomoe",
                sharinganTomoe
        );

        data.putBoolean(
                "examplemod_sharingan_ativo",
                sharinganAtivo
        );

        data.putInt(
                "examplemod_sharingan_tomoe_ativo",
                sharinganTomoeAtivo
        );


        // =====================================================
        // MANGEKYOU
        // =====================================================

        data.putBoolean(
                "examplemod_mangekyou_desbloqueado",
                mangekyouDesbloqueado
        );

        data.putBoolean(
                "examplemod_mangekyou_ativo",
                mangekyouAtivo
        );

        data.putBoolean(
                "examplemod_mangekyou_eterno_desbloqueado",
                mangekyouEternoDesbloqueado
        );

        data.putInt(
                "examplemod_mangekyou_tipo",
                mangekyouTipo
        );


        // =====================================================
        // SUSANOO
        // =====================================================

        data.putInt(
                "examplemod_susanoo_tipo",
                susanooTipo
        );

        data.putInt(
                "examplemod_susanoo_estagio",
                susanooEstagio
        );

        data.putBoolean(
                "examplemod_susanoo_ativo",
                susanooAtivo
        );


        // =====================================================
        // BYAKUGAN
        // =====================================================

        data.putBoolean(
                "examplemod_byakugan_desbloqueado",
                byakuganDesbloqueado
        );

        data.putBoolean(
                "examplemod_byakugan_ativo",
                byakuganAtivo
        );

        data.putBoolean(
                "examplemod_byakugan_despertado",
                byakuganDespertado
        );

        data.putBoolean(
                "examplemod_byakugan_despertado_ativo",
                byakuganDespertadoAtivo
        );
    }


    // =========================================================
    // LOAD
    // =========================================================

    public void load(
            Player player
    ) {

        if (player == null) {
            return;
        }

        CompoundTag data =
                player.getPersistentData();


        // =====================================================
        // STATUS
        // =====================================================

        nivel =
                Math.max(
                        1,
                        data.getInt(
                                "examplemod_nivel"
                        )
                );

        pontos =
                Math.max(
                        0,
                        data.getInt(
                                "examplemod_pontos"
                        )
                );

        forca =
                Math.max(
                        1,
                        data.getInt(
                                "examplemod_forca"
                        )
                );

        defesa =
                Math.max(
                        1,
                        data.getInt(
                                "examplemod_defesa"
                        )
                );

        chakara =
                Math.max(
                        1,
                        data.getInt(
                                "examplemod_chakara"
                        )
                );

        mente =
                Math.max(
                        1,
                        data.getInt(
                                "examplemod_mente"
                        )
                );

        chakraAtual =
                Math.max(
                        0,
                        data.getInt(
                                "examplemod_chakra_atual"
                        )
                );

        if (chakraAtual > chakara) {
            chakraAtual = chakara;
        }

        upgradesTotais =
                Math.max(
                        0,
                        data.getInt(
                                "examplemod_upgrades_totais"
                        )
                );


        // =====================================================
        // RYO
        // =====================================================

        ryo =
                Math.max(
                        0,
                        data.getInt(
                                "examplemod_ryo"
                        )
                );


        // =====================================================
        // CLÃ / LINHAGEM / ALINHAMENTO
        // =====================================================

        cla =
                data.getString(
                        "examplemod_cla"
                );

        linhagem =
                data.getString(
                        "examplemod_linhagem"
                );

        alinhamento =
                data.getString(
                        "examplemod_alinhamento"
                );

        if (
                cla == null
                        || cla.isEmpty()
        ) {

            cla = "Nenhum";
        }

        if (
                linhagem == null
                        || linhagem.isEmpty()
        ) {

            linhagem = "Nenhuma";
        }

        if (
                alinhamento == null
                        || alinhamento.isEmpty()
        ) {

            alinhamento = "Neutro";
        }


        // =====================================================
        // PERSONALIZAÇÃO
        // =====================================================

        corPele =
                Math.max(
                        1,
                        Math.min(
                                5,
                                data.getInt(
                                        "examplemod_cor_pele"
                                )
                        )
                );

        corOlhos =
                Math.max(
                        1,
                        Math.min(
                                6,
                                data.getInt(
                                        "examplemod_cor_olhos"
                                )
                        )
                );

        customizacaoConcluida =
                data.getBoolean(
                        "examplemod_customizacao_concluida"
                );


        // =====================================================
        // CABELO
        // =====================================================

        if (
                data.contains(
                        "examplemod_cabelo_selecionado"
                )
        ) {

            cabeloSelecionado =
                    Math.max(
                            1,
                            Math.min(
                                    3,
                                    data.getInt(
                                            "examplemod_cabelo_selecionado"
                                    )
                            )
                    );

        } else {

            cabeloSelecionado = 1;
        }


        if (
                data.contains(
                        "examplemod_cabelo_r"
                )
        ) {

            cabeloR =
                    Math.max(
                            0,
                            Math.min(
                                    255,
                                    data.getInt(
                                            "examplemod_cabelo_r"
                                    )
                            )
                    );

        } else {

            cabeloR = 255;
        }


        if (
                data.contains(
                        "examplemod_cabelo_g"
                )
        ) {

            cabeloG =
                    Math.max(
                            0,
                            Math.min(
                                    255,
                                    data.getInt(
                                            "examplemod_cabelo_g"
                                    )
                            )
                    );

        } else {

            cabeloG = 255;
        }


        if (
                data.contains(
                        "examplemod_cabelo_b"
                )
        ) {

            cabeloB =
                    Math.max(
                            0,
                            Math.min(
                                    255,
                                    data.getInt(
                                            "examplemod_cabelo_b"
                                    )
                            )
                    );

        } else {

            cabeloB = 255;
        }


        // =====================================================
        // SHARINGAN
        // =====================================================

        sharinganTomoe =
                Math.max(
                        0,
                        Math.min(
                                3,
                                data.getInt(
                                        "examplemod_sharingan_tomoe"
                                )
                        )
                );

        sharinganAtivo =
                data.getBoolean(
                        "examplemod_sharingan_ativo"
                );

        sharinganTomoeAtivo =
                Math.max(
                        0,
                        Math.min(
                                3,
                                data.getInt(
                                        "examplemod_sharingan_tomoe_ativo"
                                )
                        )
                );


        // =====================================================
        // MANGEKYOU
        // =====================================================

        mangekyouDesbloqueado =
                data.getBoolean(
                        "examplemod_mangekyou_desbloqueado"
                );

        mangekyouAtivo =
                data.getBoolean(
                        "examplemod_mangekyou_ativo"
                );


        if (
                data.contains(
                        "examplemod_mangekyou_eterno_desbloqueado"
                )
        ) {

            mangekyouEternoDesbloqueado =
                    data.getBoolean(
                            "examplemod_mangekyou_eterno_desbloqueado"
                    );

        } else {

            mangekyouEternoDesbloqueado =
                    false;
        }


        if (
                data.contains(
                        "examplemod_mangekyou_tipo"
                )
        ) {

            mangekyouTipo =
                    Math.max(
                            0,
                            Math.min(
                                    3,
                                    data.getInt(
                                            "examplemod_mangekyou_tipo"
                                    )
                            )
                    );

        } else {

            mangekyouTipo = 0;
        }


        // =====================================================
        // SUSANOO
        // =====================================================

        if (
                data.contains(
                        "examplemod_susanoo_tipo"
                )
        ) {

            susanooTipo =
                    Math.max(
                            0,
                            Math.min(
                                    3,
                                    data.getInt(
                                            "examplemod_susanoo_tipo"
                                    )
                            )
                    );

        } else {

            susanooTipo = 0;
        }


        if (
                data.contains(
                        "examplemod_susanoo_estagio"
                )
        ) {

            susanooEstagio =
                    Math.max(
                            0,
                            Math.min(
                                    5,
                                    data.getInt(
                                            "examplemod_susanoo_estagio"
                                    )
                            )
                    );

        } else {

            susanooEstagio = 0;
        }


        if (
                data.contains(
                        "examplemod_susanoo_ativo"
                )
        ) {

            susanooAtivo =
                    data.getBoolean(
                            "examplemod_susanoo_ativo"
                    );

        } else {

            susanooAtivo = false;
        }


        // =====================================================
        // BYAKUGAN
        // =====================================================

        byakuganDesbloqueado =
                data.getBoolean(
                        "examplemod_byakugan_desbloqueado"
                );

        byakuganAtivo =
                data.getBoolean(
                        "examplemod_byakugan_ativo"
                );

        byakuganDespertado =
                data.getBoolean(
                        "examplemod_byakugan_despertado"
                );

        byakuganDespertadoAtivo =
                data.getBoolean(
                        "examplemod_byakugan_despertado_ativo"
                );


        // =====================================================
        // CORREÇÕES SHARINGAN
        // =====================================================

        if (sharinganTomoe <= 0) {

            sharinganAtivo = false;
            sharinganTomoeAtivo = 0;

            mangekyouDesbloqueado = false;
            mangekyouAtivo = false;
            mangekyouEternoDesbloqueado = false;
            mangekyouTipo = 0;

            limparSusanoo();
        }


        if (sharinganTomoe < 3) {

            mangekyouDesbloqueado = false;
            mangekyouAtivo = false;
            mangekyouEternoDesbloqueado = false;
            mangekyouTipo = 0;

            limparSusanoo();
        }


        if (
                sharinganTomoeAtivo
                        > sharinganTomoe
        ) {

            sharinganTomoeAtivo =
                    sharinganTomoe;
        }


        if (sharinganTomoeAtivo <= 0) {
            sharinganAtivo = false;
        }


        // =====================================================
        // CORREÇÕES MANGEKYOU
        // =====================================================

        if (!mangekyouDesbloqueado) {

            mangekyouAtivo = false;
            mangekyouEternoDesbloqueado = false;
            mangekyouTipo = 0;

            limparSusanoo();
        }


        if (
                mangekyouAtivo
                        && !mangekyouDesbloqueado
        ) {

            mangekyouAtivo = false;
        }


        if (mangekyouAtivo) {

            sharinganAtivo = false;
            sharinganTomoeAtivo = 0;
        }


        // =====================================================
        // CORREÇÕES SUSANOO
        // =====================================================

        if (
                mangekyouDesbloqueado
                        && mangekyouTipo >= 1
                        && mangekyouTipo <= 3
        ) {

            /*
             * Susanoo sempre acompanha
             * o tipo do Mangekyō.
             */
            susanooTipo =
                    mangekyouTipo;


            /*
             * Compatibilidade com saves antigos.
             *
             * Se já possuía Mangekyō antes
             * do sistema de Susanoo,
             * ganha Estágio 1.
             */
            if (susanooEstagio <= 0) {

                susanooEstagio = 1;
            }

        } else {

            limparSusanoo();
        }


        /*
         * Mangekyō normal não pode permanecer
         * com Estágio 5 salvo.
         */
        if (
                susanooEstagio > 4
                        && !mangekyouEternoDesbloqueado
        ) {

            susanooEstagio = 4;
        }


        /*
         * Segurança geral.
         */
        if (
                susanooEstagio < 1
                        || susanooEstagio > 5
        ) {

            susanooAtivo = false;
        }


        if (
                susanooTipo
                        != mangekyouTipo
        ) {

            susanooAtivo = false;

            susanooTipo =
                    mangekyouTipo;
        }


        if (
                susanooEstagio == 5
                        && !mangekyouEternoDesbloqueado
        ) {

            susanooAtivo = false;
            susanooEstagio = 4;
        }


        // =====================================================
        // CORREÇÕES BYAKUGAN
        // =====================================================

        if (
                !"Hyuga".equalsIgnoreCase(
                        cla
                )
        ) {

            byakuganDesbloqueado = false;
            byakuganAtivo = false;

            byakuganDespertado = false;
            byakuganDespertadoAtivo = false;
        }


        if (!byakuganDesbloqueado) {
            byakuganAtivo = false;
        }


        if (!byakuganDespertado) {
            byakuganDespertadoAtivo = false;
        }


        // =====================================================
        // NÃO DEIXAR DOJUTSUS ATIVOS JUNTOS
        //
        // O SUSANOO NÃO ENTRA NESSA REGRA.
        // =====================================================

        if (byakuganDespertadoAtivo) {

            byakuganAtivo = false;

            sharinganAtivo = false;
            sharinganTomoeAtivo = 0;

            mangekyouAtivo = false;
        }


        if (byakuganAtivo) {

            byakuganDespertadoAtivo = false;

            sharinganAtivo = false;
            sharinganTomoeAtivo = 0;

            mangekyouAtivo = false;
        }


        if (mangekyouAtivo) {

            sharinganAtivo = false;
            sharinganTomoeAtivo = 0;

            byakuganAtivo = false;
            byakuganDespertadoAtivo = false;
        }


        if (sharinganAtivo) {

            byakuganAtivo = false;
            byakuganDespertadoAtivo = false;

            mangekyouAtivo = false;
        }
    }
}