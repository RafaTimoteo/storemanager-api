package br.com.storemanager.storemanagerapi.models.dto;

public class FornecedorResponse {
    private Long id;
    private String nome;
    private String cnpj;

    // Construtor
    public FornecedorResponse(Long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }
    
    // Construtor vazio
    public FornecedorResponse() {
        
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
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    
}
