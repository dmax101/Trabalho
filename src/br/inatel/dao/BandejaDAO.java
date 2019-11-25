/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.dao;

import br.inatel.conection.ConnectionFactory;
import br.inatel.model.Bandeja;
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
public class BandejaDAO {
    public void save(Bandeja bandeja) {
        String sql = "INSERT INTO bandeja(id_bandeja, descricao)" + " VALUES(?,?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setInt(1, bandeja.getId_bandeja());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, bandeja.getDescricao());
            
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
    
    public void removeById(String id_bandeja) {
        String sql = "DELETE FROM bandeja WHERE id_bandeja = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
 
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, id_bandeja);
            
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

    public void update(Bandeja bandeja, int id_bandeja) {
        String sql = "UPDATE bandeja SET id_bandeja = ?, descricao = ? WHERE (id_bandeja = ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setInt(1, bandeja.getId_bandeja());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, bandeja.getDescricao());
            
            pstm.setInt(3, id_bandeja);
            
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
    
    public List<Bandeja> getBandejas() {
        
        String sql = "SELECT * FROM bandeja";
        
        List<Bandeja> bandejas = new ArrayList<>();
        
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
                Bandeja bandeja = new Bandeja();
                
                //Recupera o id do banco e atribui ele ao objeto
                bandeja.setId_bandeja(rset.getInt("id_bandeja"));
                
                //Recupera o nome do banco e atribui ele ao objeto
                bandeja.setDescricao(rset.getString("descricao"));
                
                //Adiciono o medico recuperado, a lista de medicos
                bandejas.add(bandeja);
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
        return bandejas;
    }
}