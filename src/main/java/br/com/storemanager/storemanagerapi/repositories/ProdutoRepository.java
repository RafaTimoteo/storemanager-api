package br.com.storemanager.storemanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.storemanager.storemanagerapi.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
