/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.model;

import java.sql.Date;

/**
 *
 * @author DMAX(dvrib)
 */
public class Esterilizacao {
    private int id_esterilizacao;
    private Date data;
    private String enfermeiro_cre;
    private int bandeja_id;

    public int getId_esterilizacao() {
        return id_esterilizacao;
    }

    public void setId_esterilizacao(int id_esterilizacao) {
        this.id_esterilizacao = id_esterilizacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEnfermeiro_cre() {
        return enfermeiro_cre;
    }

    public void setEnfermeiro_cre(String enfermeiro_cre) {
        this.enfermeiro_cre = enfermeiro_cre;
    }

    public int getBandeja_id() {
        return bandeja_id;
    }

    public void setBandeja_id(int bandeja_id) {
        this.bandeja_id = bandeja_id;
    }

    public Esterilizacao(int id_esterilizacao, Date data, String enfermeiro_cre, int bandeja_id) {
        this.id_esterilizacao = id_esterilizacao;
        this.data = data;
        this.enfermeiro_cre = enfermeiro_cre;
        this.bandeja_id = bandeja_id;
    }

    public Esterilizacao() {
    }
}
