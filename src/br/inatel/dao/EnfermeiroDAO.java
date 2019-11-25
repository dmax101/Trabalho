/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.dao;

import br.inatel.conection.ConnectionFactory;
import br.inatel.model.Enfermeiro;
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
public class EnfermeiroDAO {
    public void save(Enfermeiro enfermeiro) {
        String sql = "INSERT INTO enfermeiro(cre, nome, telefone)" + " VALUES(?,?,?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, enfermeiro.getCre());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, enfermeiro.getNome());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setString(3, enfermeiro.getTelefone());

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
    
    public void removeById(String cre) {
        String sql = "DELETE FROM enfermeiro WHERE cre = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
 
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, cre);
            
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

    public void update(Enfermeiro enfermeiro, String cre) {
        String sql = "UPDATE enfermeiro SET cre = ?, nome = ?, telefone = ? WHERE (cre = ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, enfermeiro.getCre());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, enfermeiro.getNome());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setString(3, enfermeiro.getTelefone());
            
            pstm.setString(4, cre);
            
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
    
    public List<Enfermeiro> getEnfermeiros() {
        
        String sql = "SELECT * FROM enfermeiro";
        
        List<Enfermeiro> enfermeiros = new ArrayList<>();
        
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
                Enfermeiro enfermeiro = new Enfermeiro();
                
                //Recupera o id do banco e atribui ele ao objeto
                enfermeiro.setCre(rset.getString("cre"));
                
                //Recupera o nome do banco e atribui ele ao objeto
                enfermeiro.setNome(rset.getString("nome"));
                
                //Recupera a telefone do banco e atribui ele ao objeto
                enfermeiro.setTelefone(rset.getString("telefone"));
                
                //Adiciono o medico recuperado, a lista de medicos
                enfermeiros.add(enfermeiro);
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
        return enfermeiros;
    }
}