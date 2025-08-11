package pl.levelup.account.domain.port;

import pl.levelup.account.domain.model.User;

import java.util.Optional;

public interface UserRepositorySpiPort {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User save(User user);
}
