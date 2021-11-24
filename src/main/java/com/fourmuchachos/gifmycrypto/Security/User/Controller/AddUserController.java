package com.fourmuchachos.gifmycrypto.Security.User.Controller;

import com.fourmuchachos.gifmycrypto.Security.User.DTO.LoginRequest;
import com.fourmuchachos.gifmycrypto.Security.User.Entity.User;
import com.fourmuchachos.gifmycrypto.Security.User.Service.UserService;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class AddUserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/api/new_user")
    private User addUser(@RequestBody LoginRequest request){
        User user = new User(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userService.addUser(user);
    }





}
