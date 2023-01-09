package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.both.FormCompleted;
import pl.leon.form.application.leon.repository.FormCompletedRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.service.question.DropdownQuestionService;
import pl.leon.form.application.leon.service.question.LineScaleQuestionService;
import pl.leon.form.application.leon.service.question.LongAnswerQuestionService;
import pl.leon.form.application.leon.service.question.MultipleChoiceQuestionService;
import pl.leon.form.application.leon.service.question.ShortAnswerQuestionService;
import pl.leon.form.application.leon.service.question.SingleChoiceQuestionService;
import pl.leon.form.application.leon.validation.ValidationService;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FormCompletedService {

    private final FormMapper formMapper;

    private final FormCompletedRepository formCompletedRepository;

    private final DropdownQuestionService dropdownQuestionService;
    private final LineScaleQuestionService lineScaleQuestionService;
    private final LongAnswerQuestionService longAnswerQuestionService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final ShortAnswerQuestionService shortAnswerQuestionService;
    private final SingleChoiceQuestionService singleChoiceQuestionService;

    private final ValidationService<FormCompletedEntity> validator;

    public FormCompleted submitCompletedForm(FormCompleted formCompleted) {
        log.info("submitCompletedForm({})", formCompleted);
        FormCompletedEntity formCompletedEntity = formMapper.mapCompletedRequestToCompletedEntity(formCompleted);

        validator.validate(formCompletedEntity);

        formCompletedEntity.getAnsweredDropdownQuestions().forEach(dropdownQuestionService::incrementOption);
        formCompletedEntity.getAnsweredLineScaleQuestions().forEach(lineScaleQuestionService::incrementOption);
        formCompletedEntity.getAnsweredSingleChoiceQuestions().forEach(singleChoiceQuestionService::incrementOption);
        formCompletedEntity.getAnsweredMultipleChoiceQuestions().forEach(multipleChoiceQuestionService::incrementOption);

        formCompletedEntity.setAnsweredLongAnswerQuestions(
                formCompletedEntity.getAnsweredLongAnswerQuestions().stream().map(longAnswerQuestionService::persistNewAnswer).collect(Collectors.toList())
        );

        formCompletedEntity.setAnsweredShortAnswerQuestions(
                formCompletedEntity.getAnsweredShortAnswerQuestions().stream().map(shortAnswerQuestionService::persistNewAnswer).collect(Collectors.toList())
        );

        FormCompleted resultFormCompleted = formMapper.mapToCompleted(formCompletedRepository.save(formCompletedEntity));

        log.info("submitCompletedForm({}) = {}", formCompleted, resultFormCompleted);
        return resultFormCompleted;
    }


}
