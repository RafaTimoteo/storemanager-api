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

import br.com.storemanager.storemanagerapi.models.User;
import br.com.storemanager.storemanagerapi.models.dto.UserRequest;
import br.com.storemanager.storemanagerapi.models.dto.UserResponse;
import br.com.storemanager.storemanagerapi.services.UserService;
import br.com.storemanager.storemanagerapi.utils.UserMapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    // Retorna um obj User pelo ID
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) throws Exception {
        User user = this.userService.findUserById(id);

        UserResponse response = UserMapper.toResponse(user);
        
        return ResponseEntity.ok().body(response);
    }

    // Cria um registro user no banco a partir do obj recebido pelo post
    @PostMapping
    @Validated()
    public ResponseEntity<Void> salvarUser(@Valid @RequestBody UserRequest request) throws Exception {
        User user = UserMapper.toUser(request);
        this.userService.salvarUser(user);

        // URI do registro recém-criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // Atualiza um user ja existente no banco de dados (senha)
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Validated()
    public ResponseEntity<Void> atualizarUser(@Valid @RequestBody UserRequest request, @PathVariable Long id) throws Exception {
        User user = UserMapper.toUser(request);
        user.setId(id);
        this.userService.atualizarUser(user);

        return ResponseEntity.noContent().build();
    }

    // Deleta um user do banco de dados
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> apagarUser(@PathVariable Long id) throws Exception {
        this.userService.apagarUser(id);

        return ResponseEntity.noContent().build();
    }
}
