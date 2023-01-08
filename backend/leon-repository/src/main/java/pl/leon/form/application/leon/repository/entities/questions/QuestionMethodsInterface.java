package pl.leon.form.application.leon.repository.entities.questions;

import pl.leon.form.application.leon.repository.entities.FormEntity;

public interface QuestionMethodsInterface {
    void setDisabled(boolean disabled);

    FormEntity getForm();
}
