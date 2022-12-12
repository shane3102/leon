package pl.leon.form.application.leon.core.exceptions.internal_server_error;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
