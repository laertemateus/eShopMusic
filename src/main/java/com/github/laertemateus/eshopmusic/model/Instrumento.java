package com.github.laertemateus.eshopmusic.model;

import java.math.BigDecimal;

/**
 * Entidade instrumento
 * @author Laerte M. Rodrigues
 */
public class Instrumento extends BaseModel {
    private String nome;
    private char condicao;
    private char tipo;
    private String marca;
    private String modelo;
    private int anoFabricao;
    private String cor;
    private String material;
    private String descricao;
    private BigDecimal preco;
    private boolean ativo;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getCondicao() {
        return condicao;
    }

    public void setCondicao(char condicao) {
        this.condicao = condicao;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricao() {
        return anoFabricao;
    }

    public void setAnoFabricao(int anoFabricao) {
        this.anoFabricao = anoFabricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
