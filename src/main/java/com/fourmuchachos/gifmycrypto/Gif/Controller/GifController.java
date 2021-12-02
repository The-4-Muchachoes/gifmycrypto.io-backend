package com.fourmuchachos.gifmycrypto.Gif.Controller;


import com.fourmuchachos.gifmycrypto.Gif.DTO.GifDTO;
import com.fourmuchachos.gifmycrypto.Gif.Service.GifService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/public/gif", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Gifs")
public class GifController {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    GifService gifService;

    @GetMapping(path = "/{performance}")
    GifDTO getRandomGifByPerformance(@PathVariable int performance) {
        return modelMapper.map(gifService.getRandomGifByPerformance(performance), GifDTO.class);
    }


}
