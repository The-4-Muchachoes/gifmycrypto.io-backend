package com.fourmuchachos.gifmycrypto.Portfolio.Controller;

import com.fourmuchachos.gifmycrypto.Crypto.DTO.CryptoDTO;
import com.fourmuchachos.gifmycrypto.Portfolio.Service.PortfolioService;
import com.fourmuchachos.gifmycrypto.User.Entity.User;
import com.fourmuchachos.gifmycrypto.User.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final UserService userService;

    public PortfolioController(PortfolioService portfolioService, UserService userService) {
        this.portfolioService = portfolioService;
        this.userService = userService;
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Add Crypto to Portfolio")
    private ResponseEntity<Set<CryptoDTO>> addCryptoToPortfolio(
            @RequestHeader("Authorization") String token,
            @RequestBody CryptoDTO crypto) {

        User user = userService.getAuthenticatedUser(token);

        return new ResponseEntity<>(
                portfolioService.addCryptoToPortfolio(user, crypto),
                HttpStatus.CREATED
        );
    }
}
