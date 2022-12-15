package pl.leon.form.application.leon.mapper.question;

import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;

import java.util.Map;

@Component
public interface QuestionMapper<T> {
    QuestionResponse mapToResponse(T entity);

    QuestionAnswering mapToAnsweringByOption(Map.Entry<T, OptionEntity> tAnswerEntityEntry);

    QuestionAnswering mapToAnsweringByAnswer(Map.Entry<T, AnswerEntity> tAnswerEntityEntry);

    QuestionAnswering mapToAnswering(Map.Entry<T, Object> tObjectEntry);
}
