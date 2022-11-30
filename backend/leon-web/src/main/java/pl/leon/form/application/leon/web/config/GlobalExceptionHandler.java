package pl.leon.form.application.leon.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.leon.form.application.leon.core.exceptions.ExceptionMessage;
import pl.leon.form.application.leon.core.exceptions.bad_request.BadRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public final ExceptionMessage handleBadRequest(BadRequestException badRequestException){
        return new ExceptionMessage(badRequestException.getMessage());
    }
}
