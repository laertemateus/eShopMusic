package com.github.laertemateus.eshopmusic.dao;

import com.github.laertemateus.eshopmusic.BD;
import com.github.laertemateus.eshopmusic.model.BaseModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Modelo Base das classes de Acesso à Dados
 * @author Laerte M. Rodrigues
 * @param <T> Classe manipulada pelo DAO
 */
public abstract class BaseDAO<T extends BaseModel> {

    /**
     * Salva no banco de dados (inserindo ou atualizando) um registro do modelo
     * @param model Instância a ser salva
     * @throws SQLException Erro na consulta SQL
     */
    public void salvar(T model) throws SQLException {
        if(model.getId() == 0) {
            inserir(model);
            model.setId(BD.getInstance().getLastId());
        }else{
            atualizar(model);
        }
    }
    
    /**
     * Método para inserir no banco de dados a instância do modelo
     * @param model Instância do modelo
     * @throws java.sql.SQLException Erro na consulta SQL
     */
    protected abstract void inserir(T model) throws SQLException;
    
    /**
     * Método para atualizar no banco de dados a instância do modelo
     * @param model Instância do modelo
     * @throws java.sql.SQLException Erro na consulta SQL
     */
    protected abstract void atualizar(T model) throws SQLException;
    
    /**
     * Converte o registro do banco para o modelo
     * @param rs ResultSet da consulta
     * @return Instância construída
     * @throws SQLException Erro no acesso aos registros da resposta SQL
     */
    protected abstract T fetch(ResultSet rs) throws SQLException;
    
    /**
     * Método de exclusão de um registro no banco de dados
     * @param model Modelo a ser excluído
     * @throws Exception Erro no processo de exclusão
     */
    public abstract void excluir(T model) throws Exception;
    
    /**
     * Recupera a lista completa de entidades existentes no banco de dados
     * @return Lista com todos os modelos existentes
     * @throws SQLException Erro na consulta ou conexão
     */
    public abstract List<T> getAll() throws SQLException;
}
