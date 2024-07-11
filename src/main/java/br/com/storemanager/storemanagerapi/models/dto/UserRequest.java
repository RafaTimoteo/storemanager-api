package br.com.storemanager.storemanagerapi.models.dto;

public class UserRequest {
    
    private String username;
    private String nomeCompleto;
    private String email;
    private String senha;

    // Construtor
    public UserRequest(String username, String nomeCompleto, String email, String senha) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
    }

    // Construtor vazio
    public UserRequest() {

    }

    // Getters e setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
