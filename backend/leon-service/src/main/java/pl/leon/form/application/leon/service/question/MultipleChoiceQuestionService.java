package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.ChosenOptionWasNotFoundInAvailableOptions;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.service.question.interfaces.IncrementCountForOptionInterface;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MultipleChoiceQuestionService implements QuestionServiceInterface<MultipleChoiceQuestionEntity>, IncrementCountForOptionInterface<MultipleChoiceQuestionAnswerEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final MultipleChoiceQuestionRepository repository;

    @Override
    public void incrementOption(MultipleChoiceQuestionAnswerEntity answer) {
        log.info("incrementEachOption()");

        MultipleChoiceQuestionEntity question = answer.getQuestion();
        List<OptionEntity> options = answer.getOptions();

        question.setCountAnswers(question.getCountAnswers() + 1);

        List<Long> beforeCountList = new ArrayList<>();
        List<Long> resultCountList = new ArrayList<>();

        options.forEach(option -> {
            Long beforeCount = option.getCount();

            Long resultCount = question.getOptions().stream().filter(checkedOption -> checkedOption.equals(option))
                    .limit(1)
                    .peek(chosenOption -> chosenOption.setCount(chosenOption.getCount() + 1))
                    .findFirst()
                    .orElseThrow(ChosenOptionWasNotFoundInAvailableOptions::new).getCount();

            repository.save(question);

            beforeCountList.add(beforeCount);
            resultCountList.add(resultCount);
        });
        log.info("incrementEachOption() -> before increment list = {}, after increment list = {}", beforeCountList, resultCountList);
    }
}
