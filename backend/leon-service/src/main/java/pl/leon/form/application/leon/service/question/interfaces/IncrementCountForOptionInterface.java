package pl.leon.form.application.leon.service.question.interfaces;

import pl.leon.form.application.leon.repository.entities.OptionEntity;

public interface IncrementCountForOptionInterface<T> {
    void incrementOption(T question);
}
