package com.github.laertemateus.eshopmusic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Inicia o sistema
 * @author Laerte
 */
public class EShopMusic {

    /**
     * Inicializa a aplicação
     * @param args Argumentos da linha de comando
     * @throws SQLException Erro na consulta SQL da aplicação
     * @throws java.io.IOException Erro de acesso à arquivos
     * @throws java.net.URISyntaxException Erro no nome de arquivos no sistema
     */
    public static void main(String[] args) throws SQLException, IOException, URISyntaxException {
        atualizaBD();
    }

    /**
     * Sincroniza todas as migrações do banco de dados
     */
    private static void atualizaBD() throws SQLException, IOException, URISyntaxException {
        BufferedReader readerPasta = new BufferedReader(new InputStreamReader(EShopMusic.class.getResourceAsStream("sql")));
        BD bd = BD.getInstance();
        
        // Construção da tabela migração se necessário
        bd.query("CREATE TABLE IF NOT EXISTS migracoes(arquivo TEXT UNIQUE)");
        
        while(readerPasta.ready()) {
            String arquivo = readerPasta.readLine();
            
            if(!bd.query("SELECT 1 FROM migracoes WHERE arquivo = ?", arquivo).next()) {
                StringBuilder builder = new StringBuilder();
                BufferedReader arquivoReader = new BufferedReader(new InputStreamReader(EShopMusic.class.getResourceAsStream("sql/"+arquivo)));
                
                while(arquivoReader.ready()) {
                    builder.append(arquivoReader.readLine());
                }
                
                // Executa o conteúdo do arquivo SQL e adiciona nas migrações
                bd.query(builder.toString());
                bd.query("INSERT INTO migracoes VALUES(?)", arquivo);
            }
        }
        
    }
}
