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
import br.com.storemanager.storemanagerapi.models.User.CreateUser;
import br.com.storemanager.storemanagerapi.models.User.UpdateUser;
import br.com.storemanager.storemanagerapi.services.UserService;
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
    public ResponseEntity<User> findById(@PathVariable Long id) throws Exception {
        User user = this.userService.findUserById(id);
        
        return ResponseEntity.ok().body(user);
    }

    // Cria um registro user no banco a partir do obj recebido pelo post
    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> salvarUser(@Valid @RequestBody User user) throws Exception {
        this.userService.salvarUser(user);

        // URI do registro recém-criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // Atualiza um user ja existente no banco de dados (senha)
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> atualizarUser(@Valid @RequestBody User user, @PathVariable Long id) throws Exception {
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
