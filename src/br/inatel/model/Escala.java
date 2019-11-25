/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.model;

/**
 *
 * @author DMAX(dvrib)
 */
public class Escala {
    private String enfermeiro_cre;
    private int cirurgia_id;

    public Escala() {
    }

    public Escala(String enfermeiro_cre, int cirurgia_id) {
        this.enfermeiro_cre = enfermeiro_cre;
        this.cirurgia_id = cirurgia_id;
    }

    public String getEnfermeiro_cre() {
        return enfermeiro_cre;
    }

    public void setEnfermeiro_cre(String enfermeiro_cre) {
        this.enfermeiro_cre = enfermeiro_cre;
    }

    public int getCirurgia_id() {
        return cirurgia_id;
    }

    public void setCirurgia_id(int cirurgia_id) {
        this.cirurgia_id = cirurgia_id;
    }
    
}
