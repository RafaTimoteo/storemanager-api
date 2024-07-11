package br.com.storemanager.storemanagerapi.utils;

import br.com.storemanager.storemanagerapi.models.User;
import br.com.storemanager.storemanagerapi.models.dto.UserRequest;
import br.com.storemanager.storemanagerapi.models.dto.UserResponse;

public class UserMapper {
    
    public static User toUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setNomeCompleto(request.getNomeCompleto());
        user.setEmail(request.getEmail());
        user.setSenha(request.getSenha());

        return user;
    }

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setNomeCompleto(user.getNomeCompleto());
        response.setEmail(user.getEmail());

        return response;
    }
}
                    