package pl.levelup.account.domain.exception;

import pl.levelup.account.domain.exception.model.Message;

public class UsernameAlreadyTakenException extends AuthException {
    public UsernameAlreadyTakenException(String username) {
        super(String.format(Message.USERNAME_TAKEN.getValue(), username));
    }
}
