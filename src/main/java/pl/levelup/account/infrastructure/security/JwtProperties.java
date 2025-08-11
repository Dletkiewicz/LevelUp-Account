package pl.levelup.account.infrastructure.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Getter
public class JwtProperties {
    private String secret;
    private int accessMinutes;
    private int refreshDays;
}
