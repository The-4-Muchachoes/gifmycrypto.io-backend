package com.fourmuchachos.gifmycrypto.User.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fourmuchachos.gifmycrypto.Crypto.Entity.Crypto;
import com.fourmuchachos.gifmycrypto.User.Entity.Role;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String id;
    private String username;
    private Set<Role> roles;
    private Set<Crypto> portfolio;
}