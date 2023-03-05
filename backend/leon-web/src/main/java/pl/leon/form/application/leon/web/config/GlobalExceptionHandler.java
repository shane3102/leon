package pl.leon.form.application.leon.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.leon.form.application.leon.core.exceptions.ExceptionMessage;
import pl.leon.form.application.leon.core.exceptions.bad_request.BadRequestException;
import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.IAmATeapotException;
import pl.leon.form.application.leon.core.exceptions.internal_server_error.InternalServerErrorException;
import pl.leon.form.application.leon.core.exceptions.not_found.NotFoundException;
import pl.leon.form.application.leon.core.exceptions.unauthorized.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public final ResponseEntity<?> handleBadRequest(BadRequestException badRequestException) {
        return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler({IAmATeapotException.class})
    public final ResponseEntity<?> handleIAmATeapot(IAmATeapotException iAmATeapotException) {
        return new ResponseEntity<>(iAmATeapotException.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerErrorException.class})
    public final ResponseEntity<?> handleNetworkAuthenticationRequired(InternalServerErrorException internalServerError) {
        return new ResponseEntity<>(internalServerError.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<?> handleNotFound(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public final ResponseEntity<?> handleUnauthorized(UnauthorizedException unauthorizedException) {
        return new ResponseEntity<>(unauthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
