package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.concrete.NewlyAddedAnswerWasNotAttachedToQuestion;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.question.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.service.question.interfaces.AddNewAnswerInterface;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class LongAnswerQuestionService implements QuestionServiceInterface<LongAnswerQuestionEntity>, AddNewAnswerInterface<LongAnswerQuestionAnswerEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final LongAnswerQuestionRepository repository;

    @Override
    public LongAnswerQuestionAnswerEntity persistNewAnswer(LongAnswerQuestionAnswerEntity answering) {

        log.info("persistNewAnswer({})", answering);

        LongAnswerQuestionEntity question = answering.getQuestion();
        AnswerEntity answer = answering.getAnswer();

        if (question.getAnswers() == null) {
            question.setAnswers(new ArrayList<>());
        }

        question.setCountAnswers((question.getCountAnswers() == null ? 0 : question.getCountAnswers()) + 1);

        question.getAnswers().add(answer);
        LongAnswerQuestionEntity savedLongAnswer = repository.save(question);

        AnswerEntity persistedAnswer = savedLongAnswer.getAnswers().stream()
                .filter(answerPersisted -> Objects.equals(answer.getContent(), answerPersisted.getContent()))
                .findFirst().orElseThrow(NewlyAddedAnswerWasNotAttachedToQuestion::new);

        answering.setAnswer(persistedAnswer);

        log.info("persistNewAnswer({}) = {}", answering, answering);
        return answering;
    }
}
