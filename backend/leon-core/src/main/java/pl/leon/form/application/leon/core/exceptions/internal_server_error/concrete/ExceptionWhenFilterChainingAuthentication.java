package pl.leon.form.application.leon.core.exceptions.internal_server_error.concrete;

import pl.leon.form.application.leon.core.exceptions.ExceptionMessages;
import pl.leon.form.application.leon.core.exceptions.internal_server_error.InternalServerErrorException;

public class ExceptionWhenFilterChainingAuthentication extends InternalServerErrorException {
    public ExceptionWhenFilterChainingAuthentication(String message) {
        super(ExceptionMessages.EXCEPTION_WHEN_FILTER_CHAINING_AUTHENTICATION.getMessage() + message);
    }
}
