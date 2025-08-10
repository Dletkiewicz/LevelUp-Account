package pl.levelup.account.domain.port;

import pl.levelup.account.domain.model.AuthResponse;
import pl.levelup.account.domain.model.LoginRequest;
import pl.levelup.account.domain.model.RegisterRequest;

public interface AuthApiPort {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
