package com.fourmuchachos.gifmycrypto.Config;

import com.fourmuchachos.gifmycrypto.User.Entity.Role;
import com.fourmuchachos.gifmycrypto.User.Repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSetup implements CommandLineRunner {

    private final RoleRepo roleRepo;

    public DataSetup(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Role user = new Role(Role.USER);
        Role admin = new Role(Role.ADMIN);

        roleRepo.save(user);
        roleRepo.save(admin);

    }
}
