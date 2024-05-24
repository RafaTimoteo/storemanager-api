package br.com.storemanager.storemanagerapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.storemanager.storemanagerapi.models.Produto;
import br.com.storemanager.storemanagerapi.services.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findProdutoById(@PathVariable Long id) throws Exception {
        Produto produto = this.produtoService.findProdutoById(id);

        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Produto>> findAllByUserId(@PathVariable Long userId) {
        List<Produto> produtos = this.produtoService.findAllByUserId(userId);

        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<Produto>> findAllByFornecedorId(@PathVariable Long fornecedorId) {
        List<Produto> produtos = this.produtoService.findAllByFornecedorId(fornecedorId);

        return ResponseEntity.ok().body(produtos);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> salvarProduto(@Valid @RequestBody Produto obj) throws Exception {
        this.produtoService.salvarProduto(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> atualizarProduto(@Valid @RequestBody Produto obj, @PathVariable Long id) throws Exception {
        obj.setId(id);
        this.produtoService.atualizarProduto(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarProduto(@PathVariable Long id) throws Exception {
        this.produtoService.apagarProduto(id);

        return ResponseEntity.noContent().build();
    }
}
