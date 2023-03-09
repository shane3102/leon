package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.ShortAnswerQuestionAnswerEntity;

import java.util.List;

public interface ShortAnswerQuestionAnswerRepository extends JpaRepository<ShortAnswerQuestionAnswerEntity, Long> {
    List<ShortAnswerQuestionAnswerEntity> findAllByFormCompletedUxLevelAndFormCompletedUiLevel(FormLevelType uxLevel, FormLevelType uiLevel);
}
