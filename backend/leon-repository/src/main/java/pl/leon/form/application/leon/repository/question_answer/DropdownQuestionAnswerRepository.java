package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;

import java.util.List;

public interface DropdownQuestionAnswerRepository extends JpaRepository<DropdownQuestionAnswerEntity, Long> {
    List<DropdownQuestionAnswerEntity> findAllByFormCompletedUxLevelAndFormCompletedUiLevel(FormLevelType uxLevel, FormLevelType uiLevel);
}
