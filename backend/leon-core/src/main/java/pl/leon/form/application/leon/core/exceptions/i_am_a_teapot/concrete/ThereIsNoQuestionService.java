package pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.concrete;

import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.IAmATeapotException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.THERE_IS_NO_QUESTION_SERVICE;

public class ThereIsNoQuestionService extends IAmATeapotException {
    public ThereIsNoQuestionService() {
        super(THERE_IS_NO_QUESTION_SERVICE.getMessage());
    }
}
