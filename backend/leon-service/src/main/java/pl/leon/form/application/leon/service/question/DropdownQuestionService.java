package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.ChosenOptionWasNotFoundInAvailableOptions;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.question.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.service.question.interfaces.IncrementCountForOptionInterface;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

@Slf4j
@Service
@AllArgsConstructor
public class DropdownQuestionService implements QuestionServiceInterface<DropdownQuestionEntity>, IncrementCountForOptionInterface<DropdownQuestionAnswerEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final DropdownQuestionRepository repository;

    @Override
    public void incrementOption(DropdownQuestionAnswerEntity answer) {
        log.info("incrementOption()");

        DropdownQuestionEntity question = answer.getQuestion();
        OptionEntity option = answer.getOption();

        question.setCountAnswers((question.getCountAnswers() == null ? 0 : question.getCountAnswers()) + 1);

        Long beforeCount = option.getCount();

        Long resultCount = question.getOptions().stream()
                .filter(checkedOption -> checkedOption.equals(option))
                .limit(1)
                .peek(chosenOption -> chosenOption.setCount((chosenOption.getCount() == null ? 0 : chosenOption.getCount()) + 1))
                .findFirst()
                .orElseThrow(ChosenOptionWasNotFoundInAvailableOptions::new).getCount();

        repository.save(question);
        log.info("incrementOption() -> before increment = {}, after increment = {}", beforeCount, resultCount);
    }
}
