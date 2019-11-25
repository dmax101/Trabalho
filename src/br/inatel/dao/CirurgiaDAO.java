/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.dao;

import br.inatel.conection.ConnectionFactory;
import br.inatel.model.Bandeja;
import br.inatel.model.Cirurgia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DMAX(dvrib)
 */
public class CirurgiaDAO {
    public void save(Cirurgia cirurgia) {
        String sql = "INSERT INTO cirurgia(id_cirurgia, data, bandeja_id, paciente_cpf, medico_crm, enfermeiro_cre)" + " VALUES(?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, cirurgia.getId_cirurgia());
            pstm.setDate(2, cirurgia.getData());
            pstm.setInt(3, cirurgia.getBandeja_id());
            pstm.setString(4, cirurgia.getPaciente_cpf());
            pstm.setString(5, cirurgia.getMedico_crm());
            pstm.setString(6, cirurgia.getEnfermeiro_cre());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstm != null) {
                    pstm.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

    }
    
    public void removeById(String id_cirurgia) {
        String sql = "DELETE FROM cirurgia WHERE id_cirurgia = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
 
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, id_cirurgia);
            
            pstm.execute();
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(pstm != null) {
                    pstm.close();
                }
 
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Cirurgia cirurgia, int id_cirurgia) {
        String sql = "UPDATE cirurgia SET id_cirurgia = ?, data = ?, bandeja_id = ?, paciente_cpf = ?, medico_crm = ?, enfermeiro_cre = ? WHERE (id_cirurgia = ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, cirurgia.getId_cirurgia());
            pstm.setDate(2, cirurgia.getData());
            pstm.setInt(3, cirurgia.getBandeja_id());
            pstm.setString(4, cirurgia.getPaciente_cpf());
            pstm.setString(5, cirurgia.getMedico_crm());
            pstm.setString(6, cirurgia.getEnfermeiro_cre());
            
            pstm.setInt(7, id_cirurgia);
            
            //Executa a sql para inserção dos dados
            pstm.execute();
        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Fecha as conexões
            try {
                if(pstm != null) {
                    pstm.close();
                }
                
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Cirurgia> getCirurgias() {
        
        String sql = "SELECT * FROM cirurgia";
        
        List<Cirurgia> cirurgias = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while(rset.next()) {
                Cirurgia cirurgia = new Cirurgia();
                
                cirurgia.setId_cirurgia(rset.getInt("id_cirurgia"));
                cirurgia.setData(rset.getDate("data"));
                cirurgia.setBandeja_id(rset.getInt("bandeja_id"));
                cirurgia.setPaciente_cpf(rset.getString("paciente_cpf"));
                cirurgia.setMedico_crm(rset.getString("medico_crm"));
                cirurgia.setEnfermeiro_cre(rset.getString("enfermeiro_cre"));
                
                cirurgias.add(cirurgia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(rset != null) {
                    rset.close();
                }
                
                if(pstm != null) {
                    pstm.close();
                }
                
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return cirurgias;
    }
}
