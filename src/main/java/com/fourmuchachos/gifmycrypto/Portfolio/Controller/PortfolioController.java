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

    @GetMapping
    @ApiOperation(value = "Gets the authenticated user's portfolio")
    private ResponseEntity<Set<CryptoDTO>> getPortfolio(@RequestHeader("Authorization") String token) {
        User user = userService.getAuthenticatedUser(token);
        return new ResponseEntity<>(
                user.getPortfolioAsDTOs(),
                HttpStatus.OK
        );
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

    @PutMapping(path = "/remove/{id}")
    @ApiOperation(value = "Remove Crypto from Portfolio")
    private ResponseEntity<Set<CryptoDTO>> removeCryptoFromPortfolio(
            @RequestHeader("Authorization") String token,
            @PathVariable(name = "id") String cryptoId) {

        User user = userService.getAuthenticatedUser(token);

        return new ResponseEntity<>(
                portfolioService.removeCryptoFromPortfolio(user, cryptoId),
                HttpStatus.OK
        );
    }
}
