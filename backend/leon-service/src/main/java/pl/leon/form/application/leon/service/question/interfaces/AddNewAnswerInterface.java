package pl.leon.form.application.leon.service.question.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;

public interface AddNewAnswerInterface<T> {

    void persistNewAnswer(T question, AnswerEntity answer);
}
