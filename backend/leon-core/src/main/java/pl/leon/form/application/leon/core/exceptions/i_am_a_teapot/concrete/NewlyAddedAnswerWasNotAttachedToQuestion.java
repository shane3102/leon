package pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.concrete;

import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.IAmATeapotException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.NEWLY_ADDED_ANSWER_WAS_NOT_ATTACHED_TO_QUESTION;

public class NewlyAddedAnswerWasNotAttachedToQuestion extends IAmATeapotException {
    public NewlyAddedAnswerWasNotAttachedToQuestion() {
        super(NEWLY_ADDED_ANSWER_WAS_NOT_ATTACHED_TO_QUESTION.getMessage());
    }
}
