package br.com.storemanager.storemanagerapi.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.storemanager.storemanagerapi.models.Produto;
import br.com.storemanager.storemanagerapi.models.dto.ProdutoRequest;
import br.com.storemanager.storemanagerapi.models.dto.ProdutoResponse;

public class ProdutoMapper {

    public static ProdutoResponse toResponse(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setUser(produto.getUser().getUsername());
        response.setFornecedor(produto.getFornecedor().getNome());
        response.setValidade(produto.getValidade());
        response.setDescricao(produto.getDescricao());

        return response;
    }

    public static Produto toProduto(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setId(null);
        produto.setNome(request.getNome());
        produto.setValidade(request.getValidade());
        produto.setDescricao(request.getDescricao());

        return produto;
    }

    public static List<ProdutoResponse> toResponseList(List<Produto> produtos) {
        List<ProdutoResponse> responses = new ArrayList<>();

        for (Produto i : produtos) {
            ProdutoResponse response = new ProdutoResponse();
            response.setId(i.getId());
            response.setNome(i.getNome());
            response.setUser(i.getUser().getUsername());
            response.setFornecedor(i.getFornecedor().getNome());
            response.setValidade(i.getValidade());
            response.setDescricao(i.getDescricao());

            responses.add(response);
        }

        return responses;
    }
}
