package pl.leon.form.application.leon.core.exceptions.internal_server_error.concrete;

import pl.leon.form.application.leon.core.exceptions.ExceptionMessages;
import pl.leon.form.application.leon.core.exceptions.internal_server_error.InternalServerError;

public class ExceptionWhenFilterChainingAuthentication extends InternalServerError {
    public ExceptionWhenFilterChainingAuthentication(String message) {
        super(ExceptionMessages.EXCEPTION_WHEN_FILTER_CHAINING_AUTHENTICATION.getMessage() + message);
    }
}
