package pl.leon.form.application.leon.repository.question;

import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;

@Repository
public interface DropdownQuestionRepository extends QuestionRepositoryInterface<DropdownQuestionEntity> {
}
