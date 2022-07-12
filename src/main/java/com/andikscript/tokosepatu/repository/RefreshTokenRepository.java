package com.andikscript.tokosepatu.repository;

import com.andikscript.tokosepatu.model.RefreshToken;
import com.andikscript.tokosepatu.model.User;
import com.andikscript.tokosepatu.payload.TokenRefreshRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Override
    Optional<RefreshToken> findById(Integer id);
    Optional<RefreshToken> findByToken(String token);

    User findByToken(TokenRefreshRequest id);
}
