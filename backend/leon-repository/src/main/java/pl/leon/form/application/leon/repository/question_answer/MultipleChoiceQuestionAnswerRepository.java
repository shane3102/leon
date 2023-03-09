package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;

import java.util.List;

public interface MultipleChoiceQuestionAnswerRepository extends JpaRepository<MultipleChoiceQuestionAnswerEntity, Long> {
    List<MultipleChoiceQuestionAnswerEntity> findAllByFormCompletedUxLevelAndFormCompletedUiLevel(FormLevelType uxLevel, FormLevelType uiLevel);
}
