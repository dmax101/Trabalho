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
public class Bandeja {
    private int id_bandeja;
    private String descricao;

    public Bandeja() {
    }

    public Bandeja(int id_bandeja, String descricao) {
        this.id_bandeja = id_bandeja;
        this.descricao = descricao;
    }

    public int getId_bandeja() {
        return id_bandeja;
    }

    public void setId_bandeja(int id_bandeja) {
        this.id_bandeja = id_bandeja;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
