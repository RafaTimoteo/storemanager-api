package br.com.storemanager.storemanagerapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.storemanager.storemanagerapi.models.User;
import br.com.storemanager.storemanagerapi.repositories.UserRepository;
import br.com.storemanager.storemanagerapi.security.UserSpringSecurity;

@Service
public class UserSpringSecurityImpl implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username não encontrado!"));

        return new UserSpringSecurity(user.getId(), user.getUsername(), user.getSenha(), user.getProfiles());
    }

}
