package pl.leon.form.application.leon.service.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.ChosenOptionWasNotFoundInAvailableOptions;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.OptionsEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MultipleChoiceQuestionService implements QuestionServiceInterface<MultipleChoiceQuestionEntity> {
    @Getter(AccessLevel.PUBLIC)
    private final QuestionMapperManager questionMapperManager;
    @Getter(AccessLevel.PUBLIC)
    private final MultipleChoiceQuestionRepository repository;

    public void incrementEachOption(MultipleChoiceQuestionEntity question, OptionsEntity options) {
        log.info("incrementEachOption()");

        List<Long> beforeCountList = new ArrayList<>();
        List<Long> resultCountList = new ArrayList<>();

        options.getChosenOptions().forEach(option -> {
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
