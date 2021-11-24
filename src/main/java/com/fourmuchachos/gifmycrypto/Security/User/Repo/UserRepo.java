package com.fourmuchachos.gifmycrypto.Security.User.Repo;

import com.muchachos.cinemaxx.Security.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}