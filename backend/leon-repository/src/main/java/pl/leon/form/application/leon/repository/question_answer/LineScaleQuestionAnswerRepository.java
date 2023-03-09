package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.LineScaleQuestionAnswerEntity;

import java.util.List;

public interface LineScaleQuestionAnswerRepository extends JpaRepository<LineScaleQuestionAnswerEntity, Long> {
    List<LineScaleQuestionAnswerEntity> findAllByFormCompletedUxLevelAndFormCompletedUiLevel(FormLevelType uxLevel, FormLevelType uiLevel);
}
