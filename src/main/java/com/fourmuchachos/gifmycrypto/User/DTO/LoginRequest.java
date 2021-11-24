package com.fourmuchachos.gifmycrypto.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    String username;
    String password;
}
