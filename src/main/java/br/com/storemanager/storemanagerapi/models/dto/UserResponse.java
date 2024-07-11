package br.com.storemanager.storemanagerapi.models.dto;

public class UserResponse {

    private Long  id;
    private String username;
    private String nomeCompleto;
    private String email;

    // Construtor
    public UserResponse(Long id, String username, String nomeCompleto, String email) {
        this.id = id;
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
    }
    
    // Construtor vazio
    public UserResponse() {
        
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
