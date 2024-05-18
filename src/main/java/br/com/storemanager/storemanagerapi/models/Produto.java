package br.com.storemanager.storemanagerapi.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Size;


@Entity
@Table(name = Produto.TABLE_NAME)
public class Produto {

    public static final String TABLE_NAME = "produto";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome_produto", length = 60)
    @Size(min = 3, max = 60)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(name = "validade")
    private LocalDate validade;

    @Column(name = "descricao_produto", length = 250)
    @Size(max = 250)
    private String descricao;

    //Construtor vazio
    public Produto() {
    }

    //Construtor
    public Produto(Long id, @Size(min = 3, max = 60) String nome, User user, Fornecedor fornecedor, LocalDate validade,
            @Size(max = 250) String descricao) {
        this.id = id;
        this.nome = nome;
        this.user = user;
        this.fornecedor = fornecedor;
        this.validade = validade;
        this.descricao = descricao;
    }

    //Getters and Setters
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
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
