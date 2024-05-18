package br.com.storemanager.storemanagerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.storemanager.storemanagerapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
