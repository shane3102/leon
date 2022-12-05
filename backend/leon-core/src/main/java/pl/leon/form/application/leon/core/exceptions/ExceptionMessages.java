package pl.leon.form.application.leon.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {
    // bad request
    TOO_MANY_QUESTIONS_TO_GENERATE("Too many questions were requested"),
    COULD_NOT_PARSE_LOGIN_REQUEST_ATTEMPT("Login request attempt was badly shaped"),

    // i am a teapot
    OTHER_EXCEPTION_WHILE_EXTRACTING_TOKEN("Unexpected exception while extracting access token. Exception message: ");

    private final String message;
}
