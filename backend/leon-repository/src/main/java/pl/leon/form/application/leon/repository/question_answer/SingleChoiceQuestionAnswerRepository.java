package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.question_answers.SingleChoiceQuestionAnswerEntity;

@Repository
public interface SingleChoiceQuestionAnswerRepository extends QuestionAnswerRepositoryInterface<SingleChoiceQuestionAnswerEntity> {
}
