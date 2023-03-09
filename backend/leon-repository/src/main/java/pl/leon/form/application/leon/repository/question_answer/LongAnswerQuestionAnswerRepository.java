package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;

import java.util.List;

public interface LongAnswerQuestionAnswerRepository extends JpaRepository<LongAnswerQuestionAnswerEntity, Long> {
    List<LongAnswerQuestionAnswerRepository> findAllByFormCompletedUxLevelAndFormCompletedUiLevel(FormLevelType uxLevel, FormLevelType uiLevel);
}
