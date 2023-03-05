package pl.leon.form.application.leon.repository.entities.questions;

import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;

import java.util.List;

public interface QuestionMethodsInterface {
    void setDisabled(boolean disabled);

    FormEntity getForm();

    List<OptionEntity> getOptions();

    Long getCountAnswers();
}
