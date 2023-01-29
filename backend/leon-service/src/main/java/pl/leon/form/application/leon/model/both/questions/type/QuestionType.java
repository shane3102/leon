package pl.leon.form.application.leon.model.both.questions.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LineScaleQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.ShortAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.SingleChoiceQuestionAnswerEntity;
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
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum QuestionType {

    DROPDOWN("DROPDOWN", DropdownQuestionEntity.class, DropdownQuestionAnswerEntity.class),
    LINE_SCALE("LINE_SCALE", LineScaleQuestionEntity.class, LineScaleQuestionAnswerEntity.class),
    LONG_ANSWER("LONG_ANSWER", LongAnswerQuestionEntity.class, LongAnswerQuestionAnswerEntity.class),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE", MultipleChoiceQuestionEntity.class, MultipleChoiceQuestionAnswerEntity.class),
    SHORT_ANSWER("SHORT_ANSWER", ShortAnswerQuestionEntity.class, ShortAnswerQuestionAnswerEntity.class),
    SINGLE_CHOICE("SINGLE_CHOICE", SingleChoiceQuestionEntity.class, SingleChoiceQuestionAnswerEntity.class);

    private final String name;
    private final Class<?> questionType;
    private final Class<?> answeringType;

    public static QuestionType getTypeByQuestionType(Class<?> entity) {
        return Arrays.stream(QuestionType.values())
                .filter(questionType -> Objects.equals(questionType.getQuestionType(), entity))
                .findFirst()
                .orElseThrow(/*TODO customowy exception*/);
    }

    public static QuestionType getTypeByAnsweringType(Class<?> entity) {
        return Arrays.stream(QuestionType.values())
                .filter(questionType -> Objects.equals(questionType.getAnsweringType(), entity))
                .findFirst()
                .orElseThrow(/*TODO customowy exception*/);
    }

//    @JsonCreator
//    public static QuestionType create(@JsonFormat String name) {
//        return valueOf(name);
//    }
}
