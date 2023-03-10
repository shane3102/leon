package pl.leon.form.application.leon.repository.question;

import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;

@Repository
public interface MultipleChoiceQuestionRepository extends QuestionRepositoryInterface<MultipleChoiceQuestionEntity> {
}
