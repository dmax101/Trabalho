/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.dao;

import br.inatel.conection.ConnectionFactory;
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
public class MedicoDAO {
    public void save(Medico medico) {
        String sql = "INSERT INTO medico(crm, nome, telefone)" + " VALUES(?,?,?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, medico.getCrm());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, medico.getNome());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setString(3, medico.getTelefone());

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
    
    public void removeById(String crm) {
        String sql = "DELETE FROM medico WHERE crm = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
 
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, crm);
            
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

    public void update(Medico medico, String crm) {
        String sql = "UPDATE medico SET crm = ?, nome = ?, telefone = ? WHERE (crm = ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, medico.getCrm());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, medico.getNome());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setString(3, medico.getTelefone());
            
            pstm.setString(4, crm);
            
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
    
    public List<Medico> getMedicos() {
        
        String sql = "SELECT * FROM medico";
        
        List<Medico> medicos = new ArrayList<>();
        
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
                Medico medico = new Medico();
                
                //Recupera o id do banco e atribui ele ao objeto
                medico.setCrm(rset.getString("crm"));
                
                //Recupera o nome do banco e atribui ele ao objeto
                medico.setNome(rset.getString("nome"));
                
                //Recupera a telefone do banco e atribui ele ao objeto
                medico.setTelefone(rset.getString("telefone"));
                
                //Adiciono o medico recuperado, a lista de medicos
                medicos.add(medico);
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
        return medicos;
    }
}