package pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.concrete;

import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.IAmATeapotException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.OTHER_EXCEPTION_WHILE_EXTRACTING_TOKEN;

public class OtherExceptionWhileExtractingToken extends IAmATeapotException {
    public OtherExceptionWhileExtractingToken(String message) {
        super(OTHER_EXCEPTION_WHILE_EXTRACTING_TOKEN.getMessage() + message);
    }
}
