/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.dao;

import br.inatel.conection.ConnectionFactory;
import br.inatel.model.Escala;
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
public class EscalaDAO {
    public void save(Escala escala) {
        String sql = "INSERT INTO enfermeiro_has_cirurgia(enfermeiro_cre, cirurgia_id)" + " VALUES(?,?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, escala.getEnfermeiro_cre());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setInt(2, escala.getCirurgia_id());

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
    
    public void removeById(String enfermeiro_cre, int cirurgia_id) {
        String sql = "DELETE FROM enfermeiro_has_cirurgia WHERE enfermeiro_cre = ? and cirurgia_id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
 
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, enfermeiro_cre);
            pstm.setInt(2, cirurgia_id);
            
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
    
    public void update(Escala escala, String enfermeiro_cre, int cirurgia_id) {
        String sql = "UPDATE enfermeiro_has_cirurgia SET enfermeiro_cre = ?, cirurgia_id = ? WHERE (enfermeiro_cre = ? and cirurgia_id = ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, escala.getEnfermeiro_cre());
            pstm.setInt(2, escala.getCirurgia_id());
            
            pstm.setString(3, enfermeiro_cre);
            pstm.setInt(4, cirurgia_id);
            
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
    
    public List<Escala> getEscalas() {
        
        String sql = "SELECT * FROM enfermeiro_has_cirurgia";
        
        List<Escala> escalas = new ArrayList<>();
        
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
                Escala escala = new Escala();
                
                //Recupera o id do banco e atribui ele ao objeto
                escala.setEnfermeiro_cre(rset.getString("enfermeiro_cre"));
                escala.setCirurgia_id(rset.getInt("cirurgia_id"));
                
                escalas.add(escala);
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
        return escalas;
    }
}
