package pl.leon.form.application.leon.repository.question;

import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;

@Repository
public interface LongAnswerQuestionRepository extends QuestionRepositoryInterface<LongAnswerQuestionEntity> {
}
