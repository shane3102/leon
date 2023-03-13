package pl.leon.form.application.leon.repository.entities.questions;

import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;

import java.util.List;

public interface QuestionMethodsInterface {
    Long getId();

    void setDisabled(boolean disabled);

    FormEntity getForm();

    List<OptionEntity> getOptions();

    Long getCountAnswers();

    String getQuestion();

    default String getQuestionTypeName() {
        if (DropdownQuestionEntity.class.equals(this.getClass())) {
            return "Lista rozwijana";
        } else if (LineScaleQuestionEntity.class.equals(this.getClass())) {
            return "Skala liniowa";
        } else if (LongAnswerQuestionEntity.class.equals(this.getClass())) {
            return "Długa odpowiedź";
        } else if (MultipleChoiceQuestionEntity.class.equals(this.getClass())) {
            return "Wielokrotny wybór";
        } else if (ShortAnswerQuestionEntity.class.equals(this.getClass())) {
            return "Krótka odpowiedź";
        } else if (SingleChoiceQuestionEntity.class.equals(this.getClass())) {
            return "Jednokrotny wybór";
        }
        return "";
    }
}
