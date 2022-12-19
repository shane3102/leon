package pl.leon.form.application.leon.service.question;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.both.FormCompleted;
import pl.leon.form.application.leon.repository.FormCompletedRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;

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

    public FormCompleted submitCompletedForm(FormCompleted formCompleted) {
        log.info("submitCompletedForm({})", formCompleted);
        FormCompletedEntity formCompletedEntity = formMapper.mapCompletedRequestToCompletedEntity(formCompleted);

        formCompletedEntity.getAnsweredDropdownQuestions().forEach(dropdownQuestionService::incrementOption);
        formCompletedEntity.getAnsweredLineScaleQuestions().forEach(lineScaleQuestionService::incrementOption);
        formCompletedEntity.getAnsweredSingleChoiceQuestions().forEach(singleChoiceQuestionService::incrementOption);
        formCompletedEntity.getAnsweredMultipleChoiceQuestions().forEach(multipleChoiceQuestionService::incrementOption);

        Map<LongAnswerQuestionEntity, AnswerEntity> persistedLongQuestionAnswerMap = formCompletedEntity.getAnsweredLongAnswerQuestions()
                .entrySet()
                .stream()
                .map(questionAnswerKey -> longAnswerQuestionService.persistNewAnswer(questionAnswerKey.getKey(), questionAnswerKey.getValue()))
                .map(questionAnswerKey -> new AbstractMap.SimpleEntry<>((LongAnswerQuestionEntity) questionAnswerKey.getKey(), questionAnswerKey.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        formCompletedEntity.setAnsweredLongAnswerQuestions(persistedLongQuestionAnswerMap);

        Map<ShortAnswerQuestionEntity, AnswerEntity> persistedShortQuestionAnswerMap = formCompletedEntity.getAnsweredShortAnswerQuestions()
                .entrySet().stream()
                .map(questionAnswerKey -> shortAnswerQuestionService.persistNewAnswer(questionAnswerKey.getKey(), questionAnswerKey.getValue()))
                .map(questionAnswerKey -> new AbstractMap.SimpleEntry<>((ShortAnswerQuestionEntity) questionAnswerKey.getKey(), questionAnswerKey.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        formCompletedEntity.setAnsweredShortAnswerQuestions(persistedShortQuestionAnswerMap);

        FormCompleted resultFormCompleted = formMapper.mapToCompleted(formCompletedRepository.save(formCompletedEntity));

        log.info("submitCompletedForm({}) = {}", formCompleted, resultFormCompleted);
        return resultFormCompleted;
    }


}
