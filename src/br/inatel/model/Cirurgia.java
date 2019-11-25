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
public class Cirurgia {
    private int id_cirurgia;
    private Date data;
    private int bandeja_id;
    private String paciente_cpf;
    private String medico_crm;
    private String enfermeiro_cre;

    public int getId_cirurgia() {
        return id_cirurgia;
    }

    public void setId_cirurgia(int id_cirurgia) {
        this.id_cirurgia = id_cirurgia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getBandeja_id() {
        return bandeja_id;
    }

    public void setBandeja_id(int bandeja_id) {
        this.bandeja_id = bandeja_id;
    }

    public String getPaciente_cpf() {
        return paciente_cpf;
    }

    public void setPaciente_cpf(String paciente_cpf) {
        this.paciente_cpf = paciente_cpf;
    }

    public String getMedico_crm() {
        return medico_crm;
    }

    public void setMedico_crm(String medico_crm) {
        this.medico_crm = medico_crm;
    }

    public String getEnfermeiro_cre() {
        return enfermeiro_cre;
    }

    public void setEnfermeiro_cre(String enfermeiro_cre) {
        this.enfermeiro_cre = enfermeiro_cre;
    }

    public Cirurgia(int id_cirurgia, Date data, int bandeja_id, String paciente_cpf, String medico_crm, String enfermeiro_cre) {
        this.id_cirurgia = id_cirurgia;
        this.data = data;
        this.bandeja_id = bandeja_id;
        this.paciente_cpf = paciente_cpf;
        this.medico_crm = medico_crm;
        this.enfermeiro_cre = enfermeiro_cre;
    }

    public Cirurgia() {
    }
}
