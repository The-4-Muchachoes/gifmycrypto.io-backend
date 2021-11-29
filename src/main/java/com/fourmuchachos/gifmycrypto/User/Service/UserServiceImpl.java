package com.fourmuchachos.gifmycrypto.User.Service;

import com.fourmuchachos.gifmycrypto.User.Entity.Role;
import com.fourmuchachos.gifmycrypto.User.Entity.User;
import com.fourmuchachos.gifmycrypto.User.Repo.RoleRepo;
import com.fourmuchachos.gifmycrypto.User.Repo.UserRepo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public User addUser(User user) {
        user.addRole(roleRepo.findByName(Role.USER));
        return userRepo.save(user);
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
