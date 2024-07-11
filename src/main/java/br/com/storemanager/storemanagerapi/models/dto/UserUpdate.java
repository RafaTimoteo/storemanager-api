package br.com.storemanager.storemanagerapi.models.dto;

public class UserUpdate {

    private String senha;

    // Construtor
    public UserUpdate(String senha) {
        this.senha = senha;
    }

    // Getters e setters
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}

