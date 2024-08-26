package br.com.storemanager.storemanagerapi.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.storemanager.storemanagerapi.models.Fornecedor;
import br.com.storemanager.storemanagerapi.models.dto.FornecedorRequest;
import br.com.storemanager.storemanagerapi.models.dto.FornecedorResponse;

public class FornecedorMapper {
    public static FornecedorResponse toResponse(Fornecedor obj) {
        FornecedorResponse response = new FornecedorResponse();
        response.setId(obj.getId());
        response.setNome(obj.getNome());
        response.setCnpj(obj.getCnpj());

        return response;
    }

    public static Fornecedor toFornecedor(FornecedorRequest request) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(request.getNome());
        fornecedor.setCnpj(request.getCnpj());

        return fornecedor;
    }

    public static List<FornecedorResponse> toResponseList(List<Fornecedor> fornecedores) {
        List<FornecedorResponse> responses = new ArrayList<>();

        for (Fornecedor fornecedor : fornecedores) {
            FornecedorResponse fornecedorResponse = new FornecedorResponse();

            fornecedorResponse.setId(fornecedor.getId());
            fornecedorResponse.setNome(fornecedor.getNome());
            fornecedorResponse.setCnpj(fornecedor.getCnpj());

            responses.add(fornecedorResponse);
        }

        return responses;
    }
}
