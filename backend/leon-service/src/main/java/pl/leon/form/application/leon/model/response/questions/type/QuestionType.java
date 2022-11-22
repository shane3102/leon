package pl.leon.form.application.leon.model.response.questions.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum QuestionType {

    DROPDOWN("DROPDOWN"),
    LINE_SCALE("LINE_SCALE"),
    LONG_ANSWER("LONG_ANSWER"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    SHORT_ANSWER("SHORT_ANSWER"),
    SINGLE_CHOICE("SINGLE_CHOICE");

    private final String name;
}
