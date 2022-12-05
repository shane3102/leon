package pl.leon.form.application.leon.core.exceptions.bad_request.concrete;

import pl.leon.form.application.leon.core.exceptions.bad_request.BadRequestException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.COULD_NOT_PARSE_LOGIN_REQUEST_ATTEMPT;

public class CouldNotParseLoginRequestAttempt extends BadRequestException {
    public CouldNotParseLoginRequestAttempt() {
        super(COULD_NOT_PARSE_LOGIN_REQUEST_ATTEMPT.getMessage());
    }
}
