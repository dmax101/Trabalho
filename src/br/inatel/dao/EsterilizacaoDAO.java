/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.dao;

import br.inatel.conection.ConnectionFactory;
import br.inatel.model.Esterilizacao;
import br.inatel.model.Medico;
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
public class EsterilizacaoDAO {
    public void save(Esterilizacao esterilizacao) {
        String sql = "INSERT INTO esterilizacao(id_esterilizacao, data, enfermeiro_cre, bandeja_id)" + " VALUES(?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, esterilizacao.getId_esterilizacao());
            pstm.setDate(2, esterilizacao.getData());
            pstm.setString(3, esterilizacao.getEnfermeiro_cre());
            pstm.setInt(4, esterilizacao.getBandeja_id());

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
    
    public void removeById(int id_esterilizacao) {
        String sql = "DELETE FROM esterilizacao WHERE id_esterilizacao = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
 
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, id_esterilizacao);
            
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
    
    public void update(Esterilizacao esterilizacao, int id_esterilizacao) {
        String sql = "UPDATE esterilizacao SET id_esterilizacao = ?, data = ?, enfermeiro_cre = ?, bandeja_id = ? WHERE (id_esterilizacao = ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, esterilizacao.getId_esterilizacao());
            pstm.setDate(2, esterilizacao.getData());
            pstm.setString(3, esterilizacao.getEnfermeiro_cre());
            pstm.setInt(4, esterilizacao.getBandeja_id());
            
            pstm.setInt(5, id_esterilizacao);
            
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
    
    public List<Esterilizacao> getEsterilizacoes() {
        
        String sql = "SELECT * FROM esterilizacao";
        
        List<Esterilizacao> esterilizacoes = new ArrayList<>();
        
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
                Esterilizacao esterilizacao = new Esterilizacao();
                
                esterilizacao.setId_esterilizacao(rset.getInt("id_esterilizacao"));
                esterilizacao.setData(rset.getDate("data"));
                esterilizacao.setEnfermeiro_cre(rset.getString("enfermeiro_cre"));
                esterilizacao.setBandeja_id(rset.getInt("bandeja_id"));
                
                //Adiciono o medico recuperado, a lista de medicos
                esterilizacoes.add(esterilizacao);
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
        return esterilizacoes;
    }
}
