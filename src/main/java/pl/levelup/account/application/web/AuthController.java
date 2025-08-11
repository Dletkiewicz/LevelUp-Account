package pl.levelup.account.application.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.levelup.account.application.dto.AuthResponseDto;
import pl.levelup.account.application.dto.LoginRequestDto;
import pl.levelup.account.application.dto.RegisterRequestDto;
import pl.levelup.account.application.mapper.AuthMapper;
import pl.levelup.account.domain.port.AuthApiPort;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthApiPort authApiPort;
    private final AuthMapper authMapper;

    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto payload) {
        return ResponseEntity.ok(authMapper.map(authApiPort.register(authMapper.map(payload))));
    }

    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto payload) {
        return ResponseEntity.ok(authMapper.map(authApiPort.login(authMapper.map(payload))));
    }
}
