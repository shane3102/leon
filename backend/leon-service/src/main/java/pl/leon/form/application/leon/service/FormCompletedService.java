package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.both.forms.FormCompleted;
import pl.leon.form.application.leon.model.both.questions.type.QuestionType;
import pl.leon.form.application.leon.model.response.forms.FormUiUxTypeRandomTimeStatisticsResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionUiUxStatisticsResponse;
import pl.leon.form.application.leon.repository.FormCompletedRepository;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.QuestionAnswerMethodsInterface;
import pl.leon.form.application.leon.repository.question_answer.DropdownQuestionAnswerRepository;
import pl.leon.form.application.leon.repository.question_answer.LineScaleQuestionAnswerRepository;
import pl.leon.form.application.leon.repository.question_answer.LongAnswerQuestionAnswerRepository;
import pl.leon.form.application.leon.repository.question_answer.MultipleChoiceQuestionAnswerRepository;
import pl.leon.form.application.leon.repository.question_answer.QuestionAnswerRepositoryInterface;
import pl.leon.form.application.leon.repository.question_answer.ShortAnswerQuestionAnswerRepository;
import pl.leon.form.application.leon.repository.question_answer.SingleChoiceQuestionAnswerRepository;
import pl.leon.form.application.leon.service.question.DropdownQuestionService;
import pl.leon.form.application.leon.service.question.LineScaleQuestionService;
import pl.leon.form.application.leon.service.question.LongAnswerQuestionService;
import pl.leon.form.application.leon.service.question.MultipleChoiceQuestionService;
import pl.leon.form.application.leon.service.question.ShortAnswerQuestionService;
import pl.leon.form.application.leon.service.question.SingleChoiceQuestionService;
import pl.leon.form.application.leon.validation.ValidationService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static pl.leon.form.application.leon.core.enums.FormLevelType.BAD;
import static pl.leon.form.application.leon.core.enums.FormLevelType.GOOD;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.DROPDOWN;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LINE_SCALE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LONG_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.MULTIPLE_CHOICE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SHORT_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SINGLE_CHOICE;

@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class FormCompletedService {

    private final FormMapper formMapper;

    private final FormCompletedRepository formCompletedRepository;

    private final DropdownQuestionService dropdownQuestionService;
    private final LineScaleQuestionService lineScaleQuestionService;
    private final LongAnswerQuestionService longAnswerQuestionService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final ShortAnswerQuestionService shortAnswerQuestionService;
    private final SingleChoiceQuestionService singleChoiceQuestionService;

    private final DropdownQuestionAnswerRepository dropdownQuestionAnswerRepository;
    private final LineScaleQuestionAnswerRepository lineScaleQuestionAnswerRepository;
    private final LongAnswerQuestionAnswerRepository longAnswerQuestionAnswerRepository;
    private final MultipleChoiceQuestionAnswerRepository multipleChoiceQuestionAnswerRepository;
    private final ShortAnswerQuestionAnswerRepository shortAnswerQuestionAnswerRepository;
    private final SingleChoiceQuestionAnswerRepository singleChoiceQuestionAnswerRepository;

    private final Map<QuestionType, QuestionAnswerRepositoryInterface> typeByRepository;

    private final ValidationService<FormCompletedEntity> validator;

    public FormCompleted submitCompletedForm(FormCompleted formCompleted) {
        log.info("submitCompletedForm({})", formCompleted);

        Long idOfForm = formCompletedRepository.save(new FormCompletedEntity()).getId();

        FormCompletedEntity formCompletedEntity = formMapper.mapCompletedRequestToCompletedEntity(formCompleted);
        formCompletedEntity.setId(idOfForm);

        final FormCompletedEntity finalFormCompleted = formCompletedEntity;

        validator.validate(formCompletedEntity);

        formCompletedEntity.setAnsweredDropdownQuestions(formCompletedEntity.getAnsweredDropdownQuestions().stream().peek(answer -> {
            answer.setFormCompleted(finalFormCompleted);
            dropdownQuestionService.incrementOption(answer);
        }).collect(Collectors.toList()));
        formCompletedEntity.setAnsweredLineScaleQuestions(formCompletedEntity.getAnsweredLineScaleQuestions().stream().peek(answer -> {
            answer.setFormCompleted(finalFormCompleted);
            lineScaleQuestionService.incrementOption(answer);
        }).collect(Collectors.toList()));
        formCompletedEntity.setAnsweredSingleChoiceQuestions(formCompletedEntity.getAnsweredSingleChoiceQuestions().stream().peek(answer -> {
            answer.setFormCompleted(finalFormCompleted);
            singleChoiceQuestionService.incrementOption(answer);
        }).collect(Collectors.toList()));
        formCompletedEntity.setAnsweredMultipleChoiceQuestions(formCompletedEntity.getAnsweredMultipleChoiceQuestions().stream().peek(answer -> {
            answer.setFormCompleted(finalFormCompleted);
            multipleChoiceQuestionService.incrementOption(answer);
        }).collect(Collectors.toList()));

        formCompletedEntity.setAnsweredLongAnswerQuestions(
                formCompletedEntity.getAnsweredLongAnswerQuestions().stream().map(answering -> {
                    answering.setFormCompleted(finalFormCompleted);
                    return longAnswerQuestionService.persistNewAnswer(answering);
                }).collect(Collectors.toList())
        );

        formCompletedEntity.setAnsweredShortAnswerQuestions(
                formCompletedEntity.getAnsweredShortAnswerQuestions().stream().map(answering -> {
                    answering.setFormCompleted(finalFormCompleted);
                    return shortAnswerQuestionService.persistNewAnswer(answering);
                }).collect(Collectors.toList())
        );
        formCompletedEntity.setDateAdded(LocalDate.now());

        FormCompleted resultFormCompleted = formMapper.mapToCompleted(formCompletedRepository.save(formCompletedEntity));

        log.info("submitCompletedForm({}) = {}", formCompleted, resultFormCompleted);
        return resultFormCompleted;
    }

    public List<FormUiUxTypeRandomTimeStatisticsResponse> getRankingsWithAverageTimes() {
        log.debug("getRankingsWithAverageTimes()");
        List<FormUiUxTypeRandomTimeStatisticsResponse> result = new ArrayList<>();
        result.add(getAverageTimesByType(GOOD, GOOD));
        result.add(getAverageTimesByType(GOOD, BAD));
        result.add(getAverageTimesByType(BAD, GOOD));
        result.add(getAverageTimesByType(BAD, BAD));
        log.debug("getRankingsWithAverageTimes() = {}", result);
        return result;
    }

    private FormUiUxTypeRandomTimeStatisticsResponse getAverageTimesByType(FormLevelType uxLevel, FormLevelType uiLevel) {
        log.debug("getAverageTimesByType({}, {})", uxLevel, uiLevel);
        FormUiUxTypeRandomTimeStatisticsResponse result = new FormUiUxTypeRandomTimeStatisticsResponse();
        result.setUiLevel(uiLevel);
        result.setUxLevel(uxLevel);

        Supplier<LongStream> timesToAnswer = () -> formCompletedRepository
                .findAllByUxLevelAndUiLevelAndCompletedFormNull(uxLevel, uiLevel)
                .stream().mapToLong(FormCompletedEntity::getCompleteDurationInMilliseconds);

        result.setAnswerCount(timesToAnswer.get().count());
        result.setAverageTimeToFillForm(timesToAnswer.get().average().orElse(0));
        result.setAverageTimeToAnswerPerQuestionType(new ArrayList<>());

        for (QuestionType type : QuestionType.values()) {
            List<QuestionAnswerMethodsInterface> allAnswersByType = typeByRepository.get(type)
                    .findAllByFormCompletedUxLevelAndFormCompletedUiLevelAndFormCompletedCompletedFormNull(uxLevel, uiLevel);

            Supplier<LongStream> timesToAnswerQuestion = () -> allAnswersByType.stream().mapToLong(QuestionAnswerMethodsInterface::getDurationToAnswerInMilliseconds);

            QuestionUiUxStatisticsResponse questionStatistics = new QuestionUiUxStatisticsResponse();
            questionStatistics.setQuestionType(type);
            questionStatistics.setQuestionCount(timesToAnswerQuestion.get().count());
            questionStatistics.setAverageTimeToAnswerSingleQuestion(timesToAnswerQuestion.get().average().orElse(0));

            result.getAverageTimeToAnswerPerQuestionType().add(questionStatistics);
        }
        log.debug("getAverageTimesByType({}, {}) = {}", uxLevel, uiLevel, result);

        return result;
    }

    @PostConstruct
    void postConstruct() {
        typeByRepository.put(DROPDOWN, dropdownQuestionAnswerRepository);
        typeByRepository.put(LINE_SCALE, lineScaleQuestionAnswerRepository);
        typeByRepository.put(LONG_ANSWER, longAnswerQuestionAnswerRepository);
        typeByRepository.put(MULTIPLE_CHOICE, multipleChoiceQuestionAnswerRepository);
        typeByRepository.put(SHORT_ANSWER, shortAnswerQuestionAnswerRepository);
        typeByRepository.put(SINGLE_CHOICE, singleChoiceQuestionAnswerRepository);
    }

}
