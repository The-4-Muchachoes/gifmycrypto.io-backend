package com.fourmuchachos.gifmycrypto.User.Entity;

import com.fourmuchachos.gifmycrypto.Crypto.DTO.CryptoDTO;
import com.fourmuchachos.gifmycrypto.Crypto.Entity.Crypto;
import com.fourmuchachos.gifmycrypto.User.DTO.UserRequest;
import lombok.*;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean enabled = true;
    private boolean tokenExpired = false;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "portfolio",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "crypto_id", referencedColumnName = "id"))
    private Set<Crypto> portfolio = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(UserRequest request) {
        this.username = request.getUsername();
        this.password = request.getPassword();
    }

    public User(String username, String password, Set<Role> authority) {
        this.username = username;
        this.password = password;
        this.roles = authority;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void addCryptoToPortfolio(Crypto crypto) {
        portfolio.add(crypto);
    }

    public Set<CryptoDTO> getPortfolioAsDTOs() {
        ModelMapper modelMapper = new ModelMapper();
        return portfolio
                .stream()
                .map(crypto -> modelMapper.map(crypto, CryptoDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
