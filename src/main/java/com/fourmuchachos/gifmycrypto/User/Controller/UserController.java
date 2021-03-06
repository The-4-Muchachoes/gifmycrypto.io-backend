package com.fourmuchachos.gifmycrypto.User.Controller;


import com.fourmuchachos.gifmycrypto.Config.Security.TokenProvider;
import com.fourmuchachos.gifmycrypto.User.DTO.UserRequest;
import com.fourmuchachos.gifmycrypto.User.DTO.UserResponse;
import com.fourmuchachos.gifmycrypto.User.Entity.User;
import com.fourmuchachos.gifmycrypto.User.Service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Api(tags = "Authentication")
@RestController
@RequestMapping(path = "api/public", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();

    public UserController(UserService userService, AuthenticationManager authenticationManager,
                          TokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/signup")
    private ResponseEntity<UserResponse> signup(@RequestBody UserRequest request){
        User user = new User(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.addUser(user);

        return login(request);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid UserRequest request) {

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );

            String token = tokenProvider.generateAccessToken(authenticate);
            UserResponse user = modelMapper.map(authenticate.getPrincipal(), UserResponse.class);
            user.setAccessToken(token);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(user);

        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login credential don't match any existing users");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
