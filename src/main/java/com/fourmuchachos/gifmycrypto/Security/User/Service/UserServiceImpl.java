package com.fourmuchachos.gifmycrypto.Security.User.Service;

import com.fourmuchachos.gifmycrypto.Security.User.Entity.User;
import com.fourmuchachos.gifmycrypto.Security.User.Repo.UserRepo;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }
}
