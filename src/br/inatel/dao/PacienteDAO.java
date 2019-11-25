/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.inatel.dao;

import br.inatel.conection.ConnectionFactory;
import br.inatel.model.Paciente;
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
public class PacienteDAO {
    public void save(Paciente paciente) {
        String sql = "INSERT INTO paciente(cpf, nome, idade)" + " VALUES(?,?,?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, paciente.getCpf());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, paciente.getNome());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setInt(3, paciente.getIdade());

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
    
    public void removeById(String cpf) {
        String sql = "DELETE FROM paciente WHERE cpf = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
 
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, cpf);
            
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

    public void update(Paciente paciente, String cpf) {
        String sql = "UPDATE paciente SET cpf = ?, nome = ?, idade = ? WHERE (cpf = ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, paciente.getCpf());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(2, paciente.getNome());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setInt(3, paciente.getIdade());
            
            pstm.setString(4, cpf);
            
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
    
    public List<Paciente> getPacientes() {
        
        String sql = "SELECT * FROM paciente";
        
        List<Paciente> pacientes = new ArrayList<>();
        
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
                Paciente paciente = new Paciente();
                
                //Recupera o id do banco e atribui ele ao objeto
                paciente.setCpf(rset.getString("cpf"));
                
                //Recupera o nome do banco e atribui ele ao objeto
                paciente.setNome(rset.getString("nome"));
                
                //Recupera a telefone do banco e atribui ele ao objeto
                paciente.setIdade(rset.getInt("idade"));
                
                //Adiciono o medico recuperado, a lista de medicos
                pacientes.add(paciente);
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
        return pacientes;
    }
    
    public List<Paciente> getPacientesLoked(String cpf) {
        
        String sql = "SELECT * FROM paciente";
        
        List<Paciente> pacientes = new ArrayList<>();
        
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
                Paciente paciente = new Paciente();
                
                //Recupera o id do banco e atribui ele ao objeto
                paciente.setCpf(rset.getString("cpf"));
                
                //Recupera o nome do banco e atribui ele ao objeto
                paciente.setNome(rset.getString("nome"));
                
                //Recupera a telefone do banco e atribui ele ao objeto
                paciente.setIdade(rset.getInt("idade"));
                
                //Adiciono o medico recuperado, a lista de medicos
                if (rset.getString("cpf").equals(cpf)) {
                    pacientes.add(paciente);
                }
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
        return pacientes;
    }
}