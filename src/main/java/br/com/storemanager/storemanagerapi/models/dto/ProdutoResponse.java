package br.com.storemanager.storemanagerapi.models.dto;

import java.time.LocalDate;

public class ProdutoResponse {
    private Long id;
    private String nome;
    private String user;
    private String fornecedor;
    private LocalDate validade;
    private String descricao;

    // Construtor
    public ProdutoResponse(Long id, String nome, String user, String fornecedor, LocalDate validade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.user = user;
        this.fornecedor = fornecedor;
        this.validade = validade;
        this.descricao = descricao;
    }
    
    // Construtor vazio
    public ProdutoResponse() {

    }

    // Getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(String fornecedor) {
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
