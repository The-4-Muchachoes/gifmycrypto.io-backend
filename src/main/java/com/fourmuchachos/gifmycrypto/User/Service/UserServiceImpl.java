package com.fourmuchachos.gifmycrypto.User.Service;

import com.fourmuchachos.gifmycrypto.Config.Security.TokenProvider;
import com.fourmuchachos.gifmycrypto.Crypto.DTO.CryptoDTO;
import com.fourmuchachos.gifmycrypto.User.Entity.Role;
import com.fourmuchachos.gifmycrypto.User.Entity.User;
import com.fourmuchachos.gifmycrypto.User.Repo.RoleRepo;
import com.fourmuchachos.gifmycrypto.User.Repo.UserRepo;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final TokenProvider tokenProvider;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, TokenProvider tokenProvider) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public User addUser(User user) {
        if (userRepo.existsByUsername(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.FOUND, "Username already taken");
        user.addRole(roleRepo.findByName(Role.USER));
        return userRepo.save(user);
    }

    @Override
    public User getAuthenticatedUser(String jwtToken) {
        String bearer = "Bearer ";
        if (jwtToken.startsWith(bearer))
            jwtToken = jwtToken.replace(bearer, "");

        String username = tokenProvider.getUsernameFromToken(jwtToken);
        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepo.findByUsername(username).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

//            if (user == null) {
//                return new org.springframework.security.core.userdetails.User(
//                        " ", " ", true, true, true, true,
//                        getAuthorities(null));
//            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), user.isEnabled(), true, true,
                    true, getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            User user) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

//        if (user == null) {
//            Role role = roleRepo.findByName(Role.USER);
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//
//        } else

        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

        return authorities;
    }
}
