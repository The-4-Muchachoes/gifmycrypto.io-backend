package com.fourmuchachos.gifmycrypto.Gif.Service;

import com.fourmuchachos.gifmycrypto.Gif.Entity.Gif;



public interface GifService {
    Gif getRandomGifByPerformance(int performance);
}
