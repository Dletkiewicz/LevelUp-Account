package pl.levelup.account.infrastructure.security;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.levelup.account.domain.model.User;
import pl.levelup.account.domain.port.JwtProviderSpiPort;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class JwtProvider implements JwtProviderSpiPort {

    private final SecretKey key;
    private final int accessMinutes;
    private final int refreshDays;

    public JwtProvider(JwtProperties props) {
        this.key = buildKey(props.getSecret());
        this.accessMinutes = props.getAccessMinutes();
        this.refreshDays = props.getRefreshDays();
    }

    @Override
    public String generateAccessToken(User user) {
        return buildToken(user, Instant.now().plus(accessMinutes, ChronoUnit.MINUTES));
    }

    @Override
    public String generateRefreshToken(User user) {
        return buildToken(user, Instant.now().plus(refreshDays, ChronoUnit.DAYS));
    }

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        return claims(token).getSubject();
    }

    @Override
    public String getRole(String token) {
        return claims(token).get("role", String.class);
    }

    // ---- helpers ----
    private String buildToken(User user, Instant expInstant) {
        return Jwts.builder()
                .claims(claims -> {
                    claims.subject(user.getUsername());
                    claims.add("role", user.getRole().name());
                })
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(expInstant))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    private Claims claims(String token) {
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey buildKey(String secret) {
        try {
            byte[] raw = Decoders.BASE64.decode(secret);
            return Keys.hmacShaKeyFor(raw);
        } catch (IllegalArgumentException base64Failed) {
            return Keys.hmacShaKeyFor(secret.getBytes());
        }
    }
}