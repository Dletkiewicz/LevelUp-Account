package pl.levelup.account.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.levelup.account.domain.exception.EmailAlreadyTakenException;
import pl.levelup.account.domain.exception.UsernameAlreadyTakenException;
import pl.levelup.account.domain.model.RegisterRequest;
import pl.levelup.account.domain.port.UserRepositorySpiPort;

@Service
@RequiredArgsConstructor
public class RequestValidationService {

    private final UserRepositorySpiPort userRepositorySpiPort;

    public void validate(RegisterRequest registerRequest) {
        if (userRepositorySpiPort.existsByUsername(registerRequest.getUsername())) {
            throw new UsernameAlreadyTakenException(registerRequest.getUsername());
        }
        if (userRepositorySpiPort.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyTakenException(registerRequest.getEmail());
        }
    }
}
