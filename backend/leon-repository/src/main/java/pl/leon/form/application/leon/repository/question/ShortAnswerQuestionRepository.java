package pl.leon.form.application.leon.repository.question;

import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;

@Repository
public interface ShortAnswerQuestionRepository extends QuestionRepositoryInterface<ShortAnswerQuestionEntity> {
}
