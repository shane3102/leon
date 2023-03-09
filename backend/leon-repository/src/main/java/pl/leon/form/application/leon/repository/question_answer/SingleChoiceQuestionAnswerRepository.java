package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.SingleChoiceQuestionAnswerEntity;

import java.util.List;

public interface SingleChoiceQuestionAnswerRepository extends JpaRepository<SingleChoiceQuestionAnswerEntity, Long> {
    List<SingleChoiceQuestionAnswerEntity> findAllByFormCompletedUxLevelAndFormCompletedUiLevel(FormLevelType uxLevel, FormLevelType uiLevel);
}
