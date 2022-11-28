package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;

@Slf4j
@Service
@AllArgsConstructor
public class MultipleChoiceQuestionService implements QuestionServiceInterface<MultipleChoiceQuestionEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final MultipleChoiceQuestionRepository repository;
}
