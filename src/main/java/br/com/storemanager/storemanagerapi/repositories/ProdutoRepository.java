package br.com.storemanager.storemanagerapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.storemanager.storemanagerapi.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select t from Produto t where t.user.id = :id")
    public List<Produto> findAllByUserId(Long id);

    @Query("select t from Produto t where t.fornecedor.id = :id")
    public List<Produto> findAllByFornecedorId(Long id);
}
