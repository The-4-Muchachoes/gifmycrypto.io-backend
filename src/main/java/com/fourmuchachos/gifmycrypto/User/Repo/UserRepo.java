package com.fourmuchachos.gifmycrypto.User.Repo;

import com.fourmuchachos.gifmycrypto.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
