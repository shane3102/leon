package pl.leon.form.application.leon.repository.question_answer;

import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;

@Repository
public interface DropdownQuestionAnswerRepository extends QuestionAnswerRepositoryInterface<DropdownQuestionAnswerEntity> {
}
