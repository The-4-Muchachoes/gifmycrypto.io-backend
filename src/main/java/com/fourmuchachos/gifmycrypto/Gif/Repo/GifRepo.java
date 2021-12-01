package com.fourmuchachos.gifmycrypto.Gif.Repo;

import com.fourmuchachos.gifmycrypto.Gif.Entity.Gif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GifRepo  extends JpaRepository<Gif, String> {


    Gif findByPerformance(int performance);
}
