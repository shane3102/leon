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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class LongAnswerQuestionService implements QuestionServiceInterface<LongAnswerQuestionEntity>, AddNewAnswerInterface<LongAnswerQuestionEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final LongAnswerQuestionRepository repository;

    @Override
    public Map.Entry<Object, AnswerEntity> persistNewAnswer(LongAnswerQuestionEntity question, AnswerEntity answer) {

        if (question.getAnswers() == null) {
            question.setAnswers(new ArrayList<>());
        }

        question.getAnswers().add(answer);
        LongAnswerQuestionEntity savedLongAnswer = repository.save(question);

        AnswerEntity persistedAnswer = savedLongAnswer.getAnswers().stream()
                .filter(answerPersisted -> Objects.equals(answer.getContent(), answerPersisted.getContent()))
                .findFirst().orElseThrow(/*TODO custom exception*/);

        return new AbstractMap.SimpleEntry<>(savedLongAnswer, persistedAnswer);
    }
}
