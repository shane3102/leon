package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.QuestionAnswerMethodsInterface;

import java.util.List;

@NoRepositoryBean
public interface QuestionAnswerRepositoryInterface<T extends QuestionAnswerMethodsInterface> extends JpaRepository<T, Long> {
    List<T> findAllByFormCompletedUxLevelAndFormCompletedUiLevelAndFormCompletedCompletedFormNull(FormLevelType uxLevel, FormLevelType uiLevel);

    List<T> findAllByFormCompletedCompletedFormNull();
}
