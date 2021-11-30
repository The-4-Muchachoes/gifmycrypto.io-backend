package com.fourmuchachos.gifmycrypto.Crypto.Repo;

import com.fourmuchachos.gifmycrypto.Crypto.Entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoRepo extends JpaRepository<Crypto, String> {

    @Override
    Optional<Crypto> findById(String id);
}
