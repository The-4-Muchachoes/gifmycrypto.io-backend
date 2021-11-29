package com.fourmuchachos.gifmycrypto.User.Repo;

import com.fourmuchachos.gifmycrypto.User.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
