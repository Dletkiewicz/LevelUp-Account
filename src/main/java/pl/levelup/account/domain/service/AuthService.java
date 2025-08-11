package pl.levelup.account.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.levelup.account.domain.InvalidUsernameOrPasswordException;
import pl.levelup.account.domain.model.AuthResponse;
import pl.levelup.account.domain.model.LoginRequest;
import pl.levelup.account.domain.model.RegisterRequest;
import pl.levelup.account.domain.model.User;
import pl.levelup.account.domain.port.AuthApiPort;
import pl.levelup.account.domain.port.JwtProviderSpiPort;
import pl.levelup.account.domain.port.PasswordEncoderSpiPort;
import pl.levelup.account.domain.port.UserRepositorySpiPort;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthApiPort {

    private final UserRepositorySpiPort userRepositorySpiPort;
    private final PasswordEncoderSpiPort passwordEncoderSpiPort;
    private final JwtProviderSpiPort jwtProviderSpiPort;
    private final RequestValidationService requestValidationService;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        requestValidationService.validate(registerRequest);

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .passwordHash(passwordEncoderSpiPort.encode(registerRequest.getPassword()))
                .build();

        User savedUser = userRepositorySpiPort.save(user);

        return new AuthResponse(jwtProviderSpiPort.generateAccessToken(savedUser), jwtProviderSpiPort.generateRefreshToken(savedUser));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepositorySpiPort.findByUsername(loginRequest.getUsername())
                .orElseThrow(InvalidUsernameOrPasswordException::new);

        if (!passwordEncoderSpiPort.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new InvalidUsernameOrPasswordException();
        }

        return new AuthResponse(jwtProviderSpiPort.generateAccessToken(user), jwtProviderSpiPort.generateRefreshToken(user));
    }
}
