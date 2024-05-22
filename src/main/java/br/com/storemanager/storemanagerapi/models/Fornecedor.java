package br.com.storemanager.storemanagerapi.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Size;


@Entity
@Table(name = Fornecedor.TABLE_NAME)
public class Fornecedor {

    public static final String TABLE_NAME = "fornecedor";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @Id
    private Long id;

    @Column(name = "nome_empresa", length = 60, unique = false)
    @Size(min = 5, max = 60)
    private String nome;

    @Column(name = "cnpj", length = 18, unique = true)
    @Size(max = 18)
    private String cnpj;

    @OneToMany
    private List<Produto> produtos;

    //Construtor vazio
    public Fornecedor() {
    }

    //Construtor
    public Fornecedor(Long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
