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
public class Enfermeiro extends Contatos {
    private String cre;
    private String nome;
    private String telefone;

    public Enfermeiro() {
    }

    public Enfermeiro(String cre, String nome, String telefone) {
        this.cre = cre;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getCre() {
        return cre;
    }

    public void setCre(String cre) {
        this.cre = cre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
     
}
