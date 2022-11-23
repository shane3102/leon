package pl.leon.form.application.leon.mapper;

import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;

@Component
public interface QuestionMapper<T> {
    QuestionResponse mapToResponse(T entity);
}
