package pl.levelup.account.domain.exception;

import pl.levelup.account.domain.exception.model.Message;

public class EmailAlreadyTakenException extends AuthException {
    public EmailAlreadyTakenException(String email) {
        super(String.format(Message.EMAIL_TAKEN.getValue(), email));
    }
}
