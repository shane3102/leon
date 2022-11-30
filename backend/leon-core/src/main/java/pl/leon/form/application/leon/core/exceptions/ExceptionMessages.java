package pl.leon.form.application.leon.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {
    TOO_MANY_QUESTIONS_TO_GENERATE("Too many questions were requested: ");

    private final String message;
}
