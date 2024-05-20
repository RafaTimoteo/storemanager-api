package br.com.storemanager.storemanagerapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.storemanager.storemanagerapi.Exceptions.UsernameExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.CriptoExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.DeleteExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.EmailExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.IdNullExistsException;
import br.com.storemanager.storemanagerapi.Exceptions.LoginExistsException;

import br.com.storemanager.storemanagerapi.models.User;

import br.com.storemanager.storemanagerapi.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Retorna usuario pelo ID
    public User findUserById(Long id) throws IdNullExistsException {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new IdNullExistsException("Usuário não encontrado pelo ID"));

    }

    // Retorna o usuario a ser logado
    public User loginUser(String username, String senha) throws LoginExistsException {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);

        User user = optionalUser.orElseThrow(() -> new LoginExistsException("Username incorreto!"));

        if (!passwordEncoder.matches(senha, user.getSenha())) {
            throw new LoginExistsException("Senha incorreta!");
        }

        return user;
    }

    // Cria um novo usuário no banco de dados
    @Transactional
    public User salvarUser(User user) throws UsernameExistsException, EmailExistsException, CriptoExistsException {
        user.setId(null);

        //Validações
        validarUsername(user.getUsername());
        validarEmail(user.getEmail());
        criptografarSenha(user);

        userRepository.save(user);
        return user;
    }

    // Atualiza a senha de usuario ja existente
    @Transactional
    public User atualizarUser(User user) throws IdNullExistsException {
        User newUser = findUserById(user.getId());

        newUser.setSenha(user.getSenha());
        return userRepository.save(newUser);

    }

    public void apagarUser(Long id) throws IdNullExistsException, DeleteExistsException {
        User user = findUserById(id);

        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw new DeleteExistsException("Não é possível excluir pois há entidades relacionadas");
        }

    }
    
    // Verifica se o username já está em uso
    private void validarUsername(String username) throws UsernameExistsException {
       Optional<User> optionalUser = this.userRepository.findByUsername(username);

       if (optionalUser.isPresent()) {
        throw new UsernameExistsException("O username " + username + " já está em uso!");
       }
    }

    // Verifica se o email ja está em uso
    private void validarEmail(String email) throws EmailExistsException {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new EmailExistsException("O email " + email + " já esta em uso em outra conta!");
        }
    }

    // Criptografa a senha a ser salva no banco de dados
    private void criptografarSenha(User user) throws CriptoExistsException {
        try {
            String cripto = this.passwordEncoder.encode(user.getSenha());
            user.setSenha(cripto);
        } catch (Exception e) {
            throw new CriptoExistsException("Erro de criptografia da senha!");
        }
    }
}