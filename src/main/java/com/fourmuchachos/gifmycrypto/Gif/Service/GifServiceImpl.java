package com.fourmuchachos.gifmycrypto.Gif.Service;

import com.fourmuchachos.gifmycrypto.Gif.Entity.Gif;
import com.fourmuchachos.gifmycrypto.Gif.Repo.GifRepo;
import org.springframework.stereotype.Service;

@Service
public class GifServiceImpl implements GifService{
    GifRepo gifRepo;
    @Override
    public Gif getGifByPerformance(int performance) {
        return gifRepo.findByPerformance(performance);
    }
}
