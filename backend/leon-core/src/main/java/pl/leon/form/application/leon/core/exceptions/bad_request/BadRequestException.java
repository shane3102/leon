package pl.leon.form.application.leon.core.exceptions.bad_request;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
