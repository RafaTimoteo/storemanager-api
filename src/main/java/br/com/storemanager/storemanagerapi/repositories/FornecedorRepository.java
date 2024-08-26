package br.com.storemanager.storemanagerapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.storemanager.storemanagerapi.models.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query("select t from Fornecedor t")
    public List<Fornecedor> getAllFornecedores();
}
