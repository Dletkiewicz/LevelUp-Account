package pl.levelup.account.infrastructure.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import pl.levelup.account.domain.model.User;
import pl.levelup.account.domain.port.JwtProviderSpiPort;

@Component
public class JwtProvider implements JwtProviderSpiPort {

    private static final long ACCESS_EXP = 15 * 60 * 1000;
    private static long REFRESH_EXP = 7 * 24 * 60 * 60 * 1000;

    @Override
    public String generateAccessToken(User user) {
        return "";
    }

    @Override
    public String generateRefreshToken(User user) {
        return "";
    }

}
