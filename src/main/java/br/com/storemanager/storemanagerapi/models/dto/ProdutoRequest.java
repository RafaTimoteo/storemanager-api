package br.com.storemanager.storemanagerapi.models.dto;

import java.time.LocalDate;

public class ProdutoRequest {
    private String nome;
    private Long user;
    private Long fornecedor;
    private LocalDate validade;
    private String descricao;

    // Construtor
    public ProdutoRequest(String nome, Long user, Long fornecedor, LocalDate validade, String descricao) {
        this.nome = nome;
        this.user = user;
        this.fornecedor = fornecedor;
        this.validade = validade;
        this.descricao = descricao;
    }
    
    // Construtor vazio
    public ProdutoRequest() {
   
    }
    
    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Long fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
