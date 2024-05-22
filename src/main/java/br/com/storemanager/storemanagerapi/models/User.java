package br.com.storemanager.storemanagerapi.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @Id
    private Long id;

    @Column(name = "user_name", length = 25, unique = true)
    @Size(groups = CreateUser.class, min = 5, max = 25)
    @NotBlank(groups = CreateUser.class)
    private String username;

    @Column(name = "nome_completo", length = 60, unique = false)
    @Size(groups = CreateUser.class, min = 5, max = 60)
    @NotBlank
    private String nomeCompleto;

    @Column(name = "email")
    @Email
    @NotEmpty(groups = CreateUser.class)
    private String email;

    @Column(name = "senha")
    @NotBlank(groups = {CreateUser.class, UpdateUser.class})
    private String senha;
    
    @OneToMany()
    private List<Produto> produtos;
    
    //Construtor vazio
    public User() {
    }

    //Construtor
    public User(Long id, @Size(min = 5, max = 25) String username, @Size(min = 5, max = 60) String nomeCompleto,
            @Email String email, String senha) {
        this.id = id;
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
    }

    //Getters and Setters
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
