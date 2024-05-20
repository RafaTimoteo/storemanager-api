package br.com.storemanager.storemanagerapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.storemanager.storemanagerapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("select e from User e where e.email = :email")
    public Optional<User> findByEmail(String email);

    @Query("select e from User e where e.username = :username")
    public Optional<User> findByUsername(String username);
}
