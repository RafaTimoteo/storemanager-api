package br.com.storemanager.storemanagerapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.storemanager.storemanagerapi.Exceptions.AuthorizationException;
import br.com.storemanager.storemanagerapi.Exceptions.DeleteExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.IdNullExistsException;
import br.com.storemanager.storemanagerapi.models.Fornecedor;
import br.com.storemanager.storemanagerapi.models.Produto;
import br.com.storemanager.storemanagerapi.models.User;
import br.com.storemanager.storemanagerapi.models.enums.ProfileEnum;
import br.com.storemanager.storemanagerapi.repositories.ProdutoRepository;
import br.com.storemanager.storemanagerapi.security.UserSpringSecurity;
import br.com.storemanager.storemanagerapi.utils.SecurityUtil;
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

    // Retorna lista de produtos pelo user associado
    public List<Produto> findAllByUserId(Long userId) {
        List<Produto> produtos = produtoRepository.findAllByUserId(userId);

        return produtos;
    }

    // Retorna lista de produtos pelo fornecedor associado
    public List<Produto> findAllByFornecedorId(Long fornecedorId) {
        List<Produto> produtos = produtoRepository.findAllByFornecedorId(fornecedorId);

        return produtos;
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
        
        UserSpringSecurity userSpringSecurity = SecurityUtil.authenticated();
        if (!produto.getUser().getId().equals(userSpringSecurity.getId()) && !userSpringSecurity.hasRole(ProfileEnum.ADMIN)) {
            throw new AuthorizationException("Acesso negado! produto criado por outro user");
        }
        
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
