package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.service.question.interfaces.AddNewAnswerInterface;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

@Slf4j
@Service
@AllArgsConstructor
public class LongAnswerQuestionService implements QuestionServiceInterface<LongAnswerQuestionEntity>, AddNewAnswerInterface<LongAnswerQuestionEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final LongAnswerQuestionRepository repository;

    @Override
    public void persistNewAnswer(LongAnswerQuestionEntity question, AnswerEntity answer) {
        question.getAnswers().add(answer);
        repository.save(question);
    }
}
