package com.andikscript.tokosepatu.security.service;

import com.andikscript.tokosepatu.exception.TokenRefreshException;
import com.andikscript.tokosepatu.model.RefreshToken;
import com.andikscript.tokosepatu.repository.RefreshTokenRepository;
import com.andikscript.tokosepatu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${TokoSepatu.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDuration;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Integer userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setIdUser(userRepository.findById(userId).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiredDate(Instant.now().plusMillis(refreshTokenDuration));
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refresh) {
        if (refresh.getExpiredDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refresh);
            throw new TokenRefreshException(refresh.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return refresh;
    }
}
