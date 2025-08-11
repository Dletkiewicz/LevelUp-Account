package pl.levelup.account.domain.port;

public interface PasswordEncoderSpiPort {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);

}
