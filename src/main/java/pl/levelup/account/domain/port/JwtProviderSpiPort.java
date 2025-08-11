package pl.levelup.account.domain.port;

import pl.levelup.account.domain.model.User;

public interface JwtProviderSpiPort {
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
}
