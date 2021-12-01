package com.fourmuchachos.gifmycrypto.Gif.Repo;

import com.fourmuchachos.gifmycrypto.Gif.Entity.Gif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GifRepo extends JpaRepository<Gif, Integer> {


    List<Gif> findAllByPerformance(int performance);
}
