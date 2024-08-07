package br.com.storemanager.storemanagerapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.com.storemanager.storemanagerapi.models.Fornecedor;
import br.com.storemanager.storemanagerapi.services.FornecedorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fornecedor")
@Validated
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    // Retorna um obj Fornecedor pelo ID
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Fornecedor> findFornecedorById(@PathVariable Long id) throws Exception {
        Fornecedor obj = this.fornecedorService.findFornecedorById(id);
        
        return ResponseEntity.ok().body(obj);
    }

    // Cria um registro Fornecedor no banco de dados a partir do obj recebido pelo post
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> salvarFornecedor(@Valid @RequestBody Fornecedor obj) throws Exception {
        this.fornecedorService.salvarFornecedor(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // Atualiza um Fornecedor ja existente no banco de dados
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> atualizarFornecedor(@Valid @RequestBody Fornecedor obj, @PathVariable Long id) throws Exception {
        obj.setId(id);
        this.fornecedorService.atualizarFornecedor(obj);

        return ResponseEntity.noContent().build();
    }

    // Deleta um Fornecedor do banco de dados
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> apagarFornecedor(@PathVariable Long id) throws Exception {
        this.fornecedorService.apagarFornecedor(id);

        return ResponseEntity.noContent().build();
    }

}
