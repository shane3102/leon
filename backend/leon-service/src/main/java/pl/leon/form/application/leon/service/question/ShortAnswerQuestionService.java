package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;

@Slf4j
@Service
@AllArgsConstructor
public class ShortAnswerQuestionService implements QuestionServiceInterface<ShortAnswerQuestionEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final ShortAnswerQuestionRepository repository;
}
