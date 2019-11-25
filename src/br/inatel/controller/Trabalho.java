/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.controller;

import br.inatel.dao.EscalaDAO;
import br.inatel.dao.PacienteDAO;
import br.inatel.model.Escala;
import br.inatel.model.Paciente;
import br.inatel.view.PacienteView;

/**
 *
 * @author DMAX(dvrib)
 */
public class Trabalho {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        PacienteView tela = new PacienteView();
        tela.setTitle("Paciente");
        tela.setVisible(true);
        
    }

}
