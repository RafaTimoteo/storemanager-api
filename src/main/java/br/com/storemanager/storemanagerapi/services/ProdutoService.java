package br.com.storemanager.storemanagerapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.storemanager.storemanagerapi.Exceptions.DeleteExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.IdNullExistsException;
import br.com.storemanager.storemanagerapi.models.Fornecedor;
import br.com.storemanager.storemanagerapi.models.Produto;
import br.com.storemanager.storemanagerapi.models.User;
import br.com.storemanager.storemanagerapi.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FornecedorService fornecedorService;

    // Retorna o produto pelo ID
    public Produto findProdutoById(Long id) throws IdNullExistsException {
        Optional<Produto> obj = this.produtoRepository.findById(id);

        return obj.orElseThrow(() -> new IdNullExistsException("Produto não encontrado pelo ID!"));
    }

    // Cria um novo produto no banco de dados
    @Transactional
    public Produto salvarProduto(Produto produto) throws IdNullExistsException {
        User user = this.userService.findUserById(produto.getUser().getId());
        Fornecedor fornecedor = this.fornecedorService.findFornecedorById(produto.getFornecedor().getId());

        produto.setId(null);

        produto.setUser(user);
        produto.setFornecedor(fornecedor);

        this.produtoRepository.save(produto);

        return produto;
    }

    // Atualiza nome, validade e descrição de um produto
    @Transactional
    public Produto atualizarProduto(Produto produto) throws IdNullExistsException {
        Produto newProduto = this.findProdutoById(produto.getId());

        newProduto.setNome(produto.getNome());
        newProduto.setValidade(produto.getValidade());
        newProduto.setDescricao(produto.getDescricao());

        return this.produtoRepository.save(newProduto);
    }

    // Apaga um produto do banco de dados a partir do ID
    @Transactional
    public void apagarProduto(Long id) throws IdNullExistsException, DeleteExistsException {
        Produto produto = this.findProdutoById(id);

        try {
            this.produtoRepository.delete(produto);
        } catch (Exception e) {
            throw new DeleteExistsException("Não é possível excluir pois há entidades relacionadas");
        }
    }

}
