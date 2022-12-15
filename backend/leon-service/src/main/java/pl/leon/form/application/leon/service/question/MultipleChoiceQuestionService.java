package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.service.question.interfaces.IncrementCountForOptionInterface;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

@Slf4j
@Service
@AllArgsConstructor
public class MultipleChoiceQuestionService implements QuestionServiceInterface<MultipleChoiceQuestionEntity>, IncrementCountForOptionInterface<MultipleChoiceQuestionEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final MultipleChoiceQuestionRepository repository;

    @Override
    public void incrementOption(MultipleChoiceQuestionEntity question, OptionEntity option) {
        log.info("incrementOption({}, {})", question, option);

        Long beforeCount = option.getCount();

        Long resultCount = question.getOptions().stream().filter(checkedOption -> checkedOption.equals(option))
                .limit(1)
                .peek(chosenOption -> chosenOption.setCount(chosenOption.getCount() + 1))
                .findFirst()
                .orElseThrow(/*TODO custom exception*/).getCount();

        repository.save(question);
        log.info("incrementOption({}, {}) -> before increment = {}, after increment = {}", question, option, beforeCount, resultCount);
    }
}
