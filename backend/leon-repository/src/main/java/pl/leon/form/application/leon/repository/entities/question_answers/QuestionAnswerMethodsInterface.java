package pl.leon.form.application.leon.repository.entities.question_answers;

import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.questions.QuestionMethodsInterface;

public interface QuestionAnswerMethodsInterface {
    Long getDurationToAnswerInMilliseconds();

    QuestionMethodsInterface getQuestion();

    FormCompletedEntity getFormCompleted();

    default String getOptionCount() {
        return String.valueOf(1L);
    }

    default String getTextAnswer() {
        return "Nie dotyczy";
    }
}
