package com.github.laertemateus.eshopmusic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.swing.plaf.metal.MetalIconFactory;

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
        Path path = Paths.get(EShopMusic.class.getResource("sql").toURI());
        BD bd = BD.getInstance();
        // Construção da tabela migração se necessário
        bd.query("CREATE TABLE IF NOT EXISTS migracoes(arquivo TEXT UNIQUE)");
        
        for(Path p : Files.newDirectoryStream(path)) {
            if(!bd.query("SELECT 1 FROM migracoes WHERE arquivo = ?", p.getFileName()).next()) {
                StringBuilder builder = new StringBuilder();
                FileInputStream fis = new FileInputStream(p.toFile());
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr);
                
                while(reader.ready()) {
                    builder.append(reader.readLine());
                }
                
                // Executa o conteúdo do arquivo SQL e adiciona nas migrações
                bd.query(builder.toString());
                bd.query("INSERT INTO migracoes VALUES(?)", p.getFileName());
            }
        }
        
    }
}
