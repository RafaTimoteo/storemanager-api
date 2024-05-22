package br.com.storemanager.storemanagerapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.storemanager.storemanagerapi.Exceptions.DeleteExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.IdNullExistsException;
import br.com.storemanager.storemanagerapi.models.Fornecedor;
import br.com.storemanager.storemanagerapi.repositories.FornecedorRepository;
import jakarta.transaction.Transactional;

@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Retorna o um fornecedor pelo ID
    public Fornecedor findFornecedorById(Long id) throws IdNullExistsException {
        Optional<Fornecedor> obj = fornecedorRepository.findById(id);

        return obj.orElseThrow(() -> new IdNullExistsException("Fornecedor não encontrado pelo ID"));
    }

    // Cria um novo fornecedor no banco de dados
    @Transactional
    public Fornecedor salvarFornecedor(Fornecedor obj) {
        obj.setId(null);

        fornecedorRepository.save(obj);

        return obj;
    }

    // Atualiza nome e CNPJ de um fornecedor ja existente
    @Transactional
    public Fornecedor atualizarFornecedor(Fornecedor obj) throws IdNullExistsException {
        Fornecedor newObj = findFornecedorById(obj.getId());

        newObj.setNome(obj.getNome());
        newObj.setCnpj(obj.getCnpj());

        fornecedorRepository.save(newObj);

        return newObj;
    }

    // Apaga um fornecedor do banco de dados a partir do ID
    @Transactional
    public void apagarFornecedor(Long id) throws IdNullExistsException, DeleteExistsException {
        Fornecedor obj = findFornecedorById(id);

        try {
            this.fornecedorRepository.delete(obj);
        } catch (Exception e) {
            throw new DeleteExistsException("Não é possível excluir pois há entidades relacionadas");
        }
    }
}