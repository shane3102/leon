package pl.leon.form.application.leon.model.response.questions.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum QuestionType {

    DROPDOWN("DROPDOWN", DropdownQuestionEntity.class),
    LINE_SCALE("LINE_SCALE", LineScaleQuestionEntity.class),
    LONG_ANSWER("LONG_ANSWER", LongAnswerQuestionEntity.class),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE", MultipleChoiceQuestionEntity.class),
    SHORT_ANSWER("SHORT_ANSWER", ShortAnswerQuestionEntity.class),
    SINGLE_CHOICE("SINGLE_CHOICE", SingleChoiceQuestionEntity.class);

    private final String name;
    private final Class<?> entityClass;

    public static QuestionType getTypeByEntity(Class<?> entity){
        return Arrays.stream(QuestionType.values())
                .filter(questionType -> Objects.equals(questionType.getEntityClass(), entity))
                .findFirst()
                .orElseThrow(/*TODO customowy exception*/);
    }
}
