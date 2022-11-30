package pl.leon.form.application.leon.core.exceptions.bad_request.concrete;

import pl.leon.form.application.leon.core.exceptions.bad_request.BadRequestException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.TOO_MANY_QUESTIONS_TO_GENERATE;

public class TooManyQuestionsToGenerate extends BadRequestException {
    public TooManyQuestionsToGenerate() {
        super(TOO_MANY_QUESTIONS_TO_GENERATE.getMessage());
    }
}
