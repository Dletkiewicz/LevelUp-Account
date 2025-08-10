package pl.levelup.account.domain.exception.model;

import lombok.Getter;

@Getter
public enum Message {
    USERNAME_TAKEN ("Username %s is taken"),
    INVALID_USERNAME_PASSWORD ("Invalid username or password"),
    EMAIL_TAKEN ("Email %s is taken");

    private final String value;

    Message(String value) {
        this.value = value;
    }
}
