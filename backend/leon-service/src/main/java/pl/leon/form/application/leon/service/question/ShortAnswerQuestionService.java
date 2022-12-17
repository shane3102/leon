package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.service.question.interfaces.AddNewAnswerInterface;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

import java.util.ArrayList;

@Slf4j
@Service
@AllArgsConstructor
public class ShortAnswerQuestionService implements QuestionServiceInterface<ShortAnswerQuestionEntity>, AddNewAnswerInterface<ShortAnswerQuestionEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final ShortAnswerQuestionRepository repository;

    @Override
    public void persistNewAnswer(ShortAnswerQuestionEntity question, AnswerEntity answer) {

        if (question.getAnswers() == null) {
            question.setAnswers(new ArrayList<>());
        }

        question.getAnswers().add(answer);
        repository.save(question);
    }
}
