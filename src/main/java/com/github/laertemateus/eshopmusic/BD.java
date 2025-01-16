package com.github.laertemateus.eshopmusic;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton para controle do banco de dados
 * @author Laerte M. Rodrigues
 */
public class BD {

    private static BD INSTANCE;
    
    /**
     * Retorna a instância do modelo de banco de dados
     * @return Instância da classe BD
     * @throws SQLException Erro na conexão com o banco de dados
     */
    public static BD getInstance() throws SQLException {
        if(INSTANCE == null) {
            INSTANCE = new BD();
        }
        
        return INSTANCE;
    }
    
    private final Connection conexao;
    
    private BD() throws SQLException {
        conexao = DriverManager.getConnection("jdbc:sqlite:"+Paths.get(System.getProperty("user.home"),".eshopmusic.sqlite"));
    }
    
    /**
     * Executa uma consulta SQL no banco
     * @param sql Comando SQL
     * @param params Lista de parâmetros da consulta
     * @return Instância do ResultSet da consulta
     * @throws java.sql.SQLException Erro na sintaxe ou conexão com o banco de dados
     */
    public ResultSet query(String sql, Object ... params) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        for(int i=0;i<params.length;i++){
            stmt.setObject(i+1, params[i]);
        }
        
        stmt.execute();
        
        return stmt.getResultSet();
    }
    
    /**
     * Devolve o último ID registrado no Banco
     * @return ID
     * @throws SQLException Erro na conexão ou consulta
     */
    public int getLastId() throws SQLException {
        return query("SELECT last_insert_rowid()").getInt(1);
    }
}
