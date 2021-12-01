package com.fourmuchachos.gifmycrypto.Gif.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer performance;

    private String description;

    @Column
    String gifURL;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gif gif = (Gif) o;
        return id == gif.id && performance == gif.performance && Objects.equals(gifURL, gif.gifURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, performance, gifURL);
    }
}
