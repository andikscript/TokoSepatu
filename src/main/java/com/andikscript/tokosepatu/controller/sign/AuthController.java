package com.andikscript.tokosepatu.controller.sign;

import com.andikscript.tokosepatu.exception.TokenRefreshException;
import com.andikscript.tokosepatu.model.RefreshToken;
import com.andikscript.tokosepatu.model.User;
import com.andikscript.tokosepatu.payload.TokenRefreshResponse;
import com.andikscript.tokosepatu.payload.UsernamePasswordRequest;
import com.andikscript.tokosepatu.payload.JwtResponse;
import com.andikscript.tokosepatu.payload.TokenRefreshRequest;
import com.andikscript.tokosepatu.repository.RefreshTokenRepository;
import com.andikscript.tokosepatu.repository.RolesRepository;
import com.andikscript.tokosepatu.repository.UserRepository;
import com.andikscript.tokosepatu.security.jwt.JwtUtils;
import com.andikscript.tokosepatu.security.service.RefreshTokenService;
import com.andikscript.tokosepatu.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @PostMapping(value = "/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody UsernamePasswordRequest usernamePasswordRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernamePasswordRequest.getUsername(), usernamePasswordRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),
                userDetails.getUsername(),
                userDetails.getEmail(), roles));
    }

    @PostMapping(value = "/refreshtoken", consumes = "application/json")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(user -> {
                    User user1 = user.getIdUser();
                    String token = jwtUtils.generateTokenFromUsername(user1.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database"));
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity addUser(@RequestBody User user) {
        if (user.getUsername().equals(null) || user.getPassword().equals(null) ||
                user.getUsername().equals("") || user.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
