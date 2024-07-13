package br.com.storemanager.storemanagerapi.models.dto;

public class FornecedorRequest {
    private String nome;
    private String cnpj;
    
    // Construtor
    public FornecedorRequest(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }
    
    // Construtor vazio
    public FornecedorRequest() {

    }

    // Getters e setters
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
