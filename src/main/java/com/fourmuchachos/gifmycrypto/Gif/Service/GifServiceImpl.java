package com.fourmuchachos.gifmycrypto.Gif.Service;

import com.fourmuchachos.gifmycrypto.Gif.Entity.Gif;
import com.fourmuchachos.gifmycrypto.Gif.Repo.GifRepo;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@Service
public class GifServiceImpl implements GifService{

    private final GifRepo gifRepo;

    public GifServiceImpl(GifRepo gifRepo) {
        this.gifRepo = gifRepo;
    }

    @Override
    public Gif getRandomGifByPerformance(int performance) {
        Random r = new Random();

        List<Gif> gifs = gifRepo.findAllByPerformance(performance);

        if (gifs.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return gifs.get(r.nextInt(gifs.size()));
    }
}
