package pl.levelup.account.domain;

import pl.levelup.account.domain.exception.AuthException;
import pl.levelup.account.domain.exception.model.Message;

public class InvalidUsernameOrPasswordException extends AuthException {
    public InvalidUsernameOrPasswordException() {
        super(Message.INVALID_USERNAME_PASSWORD.getValue());
    }
}
