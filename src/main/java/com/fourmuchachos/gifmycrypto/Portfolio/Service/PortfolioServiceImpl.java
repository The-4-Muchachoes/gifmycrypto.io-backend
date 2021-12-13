package com.fourmuchachos.gifmycrypto.Portfolio.Service;

import com.fourmuchachos.gifmycrypto.Crypto.DTO.CryptoDTO;
import com.fourmuchachos.gifmycrypto.Crypto.Entity.Crypto;
import com.fourmuchachos.gifmycrypto.Crypto.Repo.CryptoRepo;
import com.fourmuchachos.gifmycrypto.User.Entity.User;
import com.fourmuchachos.gifmycrypto.User.Repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final CryptoRepo cryptoRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    public PortfolioServiceImpl(CryptoRepo cryptoRepo, UserRepo userRepo) {
        this.cryptoRepo = cryptoRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Set<CryptoDTO> addCryptoToPortfolio(User user, CryptoDTO dto) {

        Crypto crypto;

        try {
            crypto = cryptoRepo.findById(dto.getId())
                    .orElseGet(() -> cryptoRepo.save(modelMapper.map(dto, Crypto.class)));

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!user.getPortfolio().contains(crypto)) {
            user.addCryptoToPortfolio(crypto);
            user = userRepo.save(user);
        }

        return user.getPortfolioAsDTOs();

    }

    @Override
    public Set<CryptoDTO> removeCryptoFromPortfolio(User user, String cryptoId) {
        user.getPortfolio().removeIf(crypto -> crypto.getId().equals(cryptoId));
        return userRepo.save(user).getPortfolioAsDTOs();
    }
}
