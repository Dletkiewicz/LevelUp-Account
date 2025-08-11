package pl.levelup.account.application.mapper;

import org.mapstruct.Mapper;
import pl.levelup.account.application.dto.AuthResponseDto;
import pl.levelup.account.application.dto.LoginRequestDto;
import pl.levelup.account.application.dto.RegisterRequestDto;
import pl.levelup.account.domain.model.AuthResponse;
import pl.levelup.account.domain.model.LoginRequest;
import pl.levelup.account.domain.model.RegisterRequest;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthResponse map(AuthResponseDto authResponseDto);

    AuthResponseDto map(AuthResponse authResponse);

    RegisterRequest map(RegisterRequestDto registerRequestDto);

    LoginRequest map(LoginRequestDto loginRequestDto);
}
