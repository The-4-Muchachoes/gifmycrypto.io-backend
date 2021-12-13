package com.fourmuchachos.gifmycrypto.Portfolio.Service;

import com.fourmuchachos.gifmycrypto.Crypto.DTO.CryptoDTO;
import com.fourmuchachos.gifmycrypto.User.Entity.User;

import java.util.Set;

public interface PortfolioService {

    Set<CryptoDTO> addCryptoToPortfolio(User user, CryptoDTO dto);
    Set<CryptoDTO> removeCryptoFromPortfolio(User user, String cryptoId);
}
