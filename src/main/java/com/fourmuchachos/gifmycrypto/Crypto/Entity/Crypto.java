package com.fourmuchachos.gifmycrypto.Crypto.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fourmuchachos.gifmycrypto.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Crypto {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String symbol;

    @ManyToMany(mappedBy = "portfolio")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crypto crypto = (Crypto) o;
        return Objects.equals(id, crypto.id) && Objects.equals(name, crypto.name) && Objects.equals(symbol, crypto.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol);
    }
}
