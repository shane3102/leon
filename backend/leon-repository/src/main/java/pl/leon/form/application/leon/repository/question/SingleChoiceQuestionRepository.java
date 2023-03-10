package pl.leon.form.application.leon.repository.question;

import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

@Repository
public interface SingleChoiceQuestionRepository extends QuestionRepositoryInterface<SingleChoiceQuestionEntity> {
}
