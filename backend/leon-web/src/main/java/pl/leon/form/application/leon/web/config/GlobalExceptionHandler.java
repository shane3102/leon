package pl.leon.form.application.leon.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.leon.form.application.leon.core.exceptions.ExceptionMessage;
import pl.leon.form.application.leon.core.exceptions.bad_request.BadRequestException;
import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.IAmATeapotException;
import pl.leon.form.application.leon.core.exceptions.internal_server_error.InternalServerErrorException;
import pl.leon.form.application.leon.core.exceptions.not_found.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public final ExceptionMessage handleBadRequest(BadRequestException badRequestException) {
        return new ExceptionMessage(badRequestException.getMessage());
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler({IAmATeapotException.class})
    public final ExceptionMessage handleIAmATeapot(IAmATeapotException iAmATeapotException) {
        return new ExceptionMessage(iAmATeapotException.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerErrorException.class})
    public final ExceptionMessage handleNetworkAuthenticationRequired(InternalServerErrorException internalServerError) {
        return new ExceptionMessage(internalServerError.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public final ExceptionMessage handleNotFound(NotFoundException notFoundException) {
        return new ExceptionMessage(notFoundException.getMessage());
    }
}
