package pl.leon.form.application.leon.service.question.interfaces;

import pl.leon.form.application.leon.repository.entities.AnswerEntity;

import java.util.Map;

public interface AddNewAnswerInterface<T> {

    T persistNewAnswer(T question);
}
