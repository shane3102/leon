package pl.leon.form.application.leon.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.model.both.forms.FormCompleted;
import pl.leon.form.application.leon.model.request.forms.FormCreateRequest;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.both.forms.FormUiUxRanking;
import pl.leon.form.application.leon.model.request.questions.QuestionCreateRequest;
import pl.leon.form.application.leon.model.response.forms.FormResponse;
import pl.leon.form.application.leon.model.response.forms.FormSnippetResponse;
import pl.leon.form.application.leon.model.response.forms.FormStatisticsResponse;
import pl.leon.form.application.leon.model.response.forms.FormUiUxRandomCompletingStatisticsResponse;
import pl.leon.form.application.leon.repository.question.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.question.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.question.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.question.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.OptionRepository;
import pl.leon.form.application.leon.repository.question.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.question.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.FormUiUxEntity;
import pl.leon.form.application.leon.repository.entities.FormUiUxRankingEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.UserEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LineScaleQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.ShortAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.SingleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;
import pl.leon.form.application.leon.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.leon.form.application.leon.core.enums.FormLevelType.BAD;
import static pl.leon.form.application.leon.core.enums.FormLevelType.GOOD;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.DROPDOWN;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LINE_SCALE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LONG_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.MULTIPLE_CHOICE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SHORT_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SINGLE_CHOICE;

@Component
@Mapper(componentModel = "spring")
public abstract class FormMapper {

    @Autowired
    protected UserService userService;

    @Autowired
    protected FormRepository formRepository;

    @Autowired
    protected DropdownQuestionRepository dropdownQuestionRepository;
    @Autowired
    protected LineScaleQuestionRepository lineScaleQuestionRepository;
    @Autowired
    protected LongAnswerQuestionRepository longAnswerQuestionRepository;
    @Autowired
    protected MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    @Autowired
    protected ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    @Autowired
    protected SingleChoiceQuestionRepository singleChoiceQuestionRepository;
    @Autowired
    protected OptionRepository optionRepository;

    @Autowired
    protected QuestionMapperManager questionMapperManager;

    public abstract FormUiUxRankingEntity mapUiUxRankingDtoToEntity(FormUiUxRanking request);

    public abstract FormUiUxRanking mapUiUxRankingEntityToDto(FormUiUxRankingEntity request);

    public List<FormUiUxRandomCompletingStatisticsResponse> mapRankingsToResponseWithCounts(List<FormUiUxRankingEntity> rankings) {
        FormUiUxRandomCompletingStatisticsResponse badUiBadUx = new FormUiUxRandomCompletingStatisticsResponse(BAD, BAD);
        FormUiUxRandomCompletingStatisticsResponse badUiGoodUx = new FormUiUxRandomCompletingStatisticsResponse(BAD, GOOD);
        FormUiUxRandomCompletingStatisticsResponse goodUiBadUx = new FormUiUxRandomCompletingStatisticsResponse(GOOD, BAD);
        FormUiUxRandomCompletingStatisticsResponse goodUiGoodUx = new FormUiUxRandomCompletingStatisticsResponse(GOOD, GOOD);

        for (FormUiUxRankingEntity ranking : rankings) {
            for (int i = 0; i < ranking.getFormsInOrder().size(); i++) {
                FormUiUxEntity record = ranking.getFormsInOrder().get(i);

                if (BAD.equals(record.getUiLevel()) && BAD.equals(record.getUxLevel())) {
                    badUiBadUx.incrementPlaceByIndex(i);
                } else if (BAD.equals(record.getUiLevel()) && GOOD.equals(record.getUxLevel())) {
                    badUiGoodUx.incrementPlaceByIndex(i);
                } else if (GOOD.equals(record.getUiLevel()) && BAD.equals(record.getUxLevel())) {
                    goodUiBadUx.incrementPlaceByIndex(i);
                } else if (GOOD.equals(record.getUiLevel()) && GOOD.equals(record.getUxLevel())) {
                    goodUiGoodUx.incrementPlaceByIndex(i);
                }
            }
        }

        return List.of(goodUiGoodUx, badUiGoodUx, goodUiBadUx, badUiBadUx);
    }

    public abstract FormUiUxRanking mapUiUxEntityToResponse(FormUiUxRankingEntity entity);

    @Mappings({
            @Mapping(target = "dropdownQuestions", source = "questions"),
            @Mapping(target = "lineScaleQuestions", source = "questions"),
            @Mapping(target = "longAnswerQuestions", source = "questions"),
            @Mapping(target = "multipleChoiceQuestions", source = "questions"),
            @Mapping(target = "shortAnswerQuestions", source = "questions"),
            @Mapping(target = "singleChoiceQuestions", source = "questions"),
            @Mapping(target = "disabledFormRandomFormGenerating", source = "disableQuestionsFromRandomGeneratedForms"),
            @Mapping(target = "user", expression = "java(" +
                    "(pl.leon.form.application.leon.repository.entities.UserEntity) " +
                    "userService.loadUserByUsername((java.lang.String)org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal()))")
    })
    public abstract FormEntity mapCreateRequestToEntity(FormCreateRequest formCreateRequest);

    @Mappings({
            @Mapping(target = "completedForm", source = "completedFormId"),
            @Mapping(target = "answeredDropdownQuestions", source = "answers"),
            @Mapping(target = "answeredLineScaleQuestions", source = "answers"),
            @Mapping(target = "answeredLongAnswerQuestions", source = "answers"),
            @Mapping(target = "answeredMultipleChoiceQuestions", source = "answers"),
            @Mapping(target = "answeredShortAnswerQuestions", source = "answers"),
            @Mapping(target = "answeredSingleChoiceQuestions", source = "answers")
    })
    public abstract FormCompletedEntity mapCompletedRequestToCompletedEntity(FormCompleted formCompletedRequested);

    @AfterMapping
    public void setCurrentlyLoggedUser(@MappingTarget FormCompletedEntity.FormCompletedEntityBuilder formCompletedEntity) {


        formCompletedEntity.user(
                SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")
                        ?
                        null
                        :
                        (UserEntity) userService.loadUserByUsername(((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
    }

    @Mappings({
            @Mapping(target = "questions", expression = "java(questionMapperManager.mapToResponses(" +
                    "formEntity.getDropdownQuestions(), " +
                    "formEntity.getLineScaleQuestions(), " +
                    "formEntity.getLongAnswerQuestions(), " +
                    "formEntity.getMultipleChoiceQuestions(), " +
                    "formEntity.getShortAnswerQuestions(), " +
                    "formEntity.getSingleChoiceQuestions()" +
                    "))")
    })
    public abstract FormResponse mapToResponse(FormEntity formEntity);

    @Mappings({
            @Mapping(target = "questions", expression = "java(questionMapperManager.mapToStatisticsResponses(" +
                    "formEntity.getDropdownQuestions(), " +
                    "formEntity.getLineScaleQuestions(), " +
                    "formEntity.getLongAnswerQuestions(), " +
                    "formEntity.getMultipleChoiceQuestions(), " +
                    "formEntity.getShortAnswerQuestions(), " +
                    "formEntity.getSingleChoiceQuestions()" +
                    "))")
    })
    public abstract FormStatisticsResponse mapToStatisticsResponse(FormEntity formEntity);

    @Mappings({
            @Mapping(target = "answers", expression = "java(questionMapperManager.mapToAnswering(" +
                    "formCompletedEntity.getAnsweredDropdownQuestions(), " +
                    "formCompletedEntity.getAnsweredLineScaleQuestions(), " +
                    "formCompletedEntity.getAnsweredLongAnswerQuestions(), " +
                    "formCompletedEntity.getAnsweredMultipleChoiceQuestions(), " +
                    "formCompletedEntity.getAnsweredShortAnswerQuestions(), " +
                    "formCompletedEntity.getAnsweredSingleChoiceQuestions()" +
                    "))")
    })
    public abstract FormCompleted mapToCompleted(FormCompletedEntity formCompletedEntity);

    @Mappings({
            @Mapping(target = "author", source = "user.username")
    })
    public abstract FormSnippetResponse mapToSnippetResponse(FormEntity formEntity);

    protected FormEntity mapIdToForm(Long formId) {
        return formId == null ? null : formRepository.getById(formId);
    }

    protected List<DropdownQuestionEntity> mapToDropdownQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> DROPDOWN.equals(request.getType()))
                .map(this::mapToDropdownQuestion)
                .collect(Collectors.toList());
    }

    protected DropdownQuestionEntity mapToDropdownQuestion(QuestionCreateRequest request) {
        return DropdownQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .disabledFormRandomFormGenerating(request.isDisabledFormRandomFormGenerating())
                .countAnswers(0L)
                .build();
    }

    protected List<DropdownQuestionAnswerEntity> mapDropdownAnswerToDropdownQuestionAnswer(List<QuestionAnswering> requests) {
        return requests.stream()
                .filter(answer -> DROPDOWN.equals(answer.getType()))
                .map(answer ->
                        DropdownQuestionAnswerEntity.builder()
                                .durationToAnswerInMilliseconds(answer.getDurationToAnswerInMilliseconds())
                                .question(dropdownQuestionRepository.getById(answer.getId()))
                                .option(optionRepository.getById(answer.getChosenOptions().stream().findFirst().orElse(new Option()).getId()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    protected List<LineScaleQuestionEntity> mapToLineScaleQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> LINE_SCALE.equals(request.getType()))
                .map(this::mapToLineScaleQuestion)
                .collect(Collectors.toList());
    }

    protected LineScaleQuestionEntity mapToLineScaleQuestion(QuestionCreateRequest request) {
        return LineScaleQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .disabledFormRandomFormGenerating(request.isDisabledFormRandomFormGenerating())
                .countAnswers(0L)
                .build();
    }

    protected List<LineScaleQuestionAnswerEntity> mapLineScaleAnswerToLineScaleQuestionAnswer(List<QuestionAnswering> requests) {
        return requests.stream()
                .filter(answer -> LINE_SCALE.equals(answer.getType()))
                .map(answer ->
                        LineScaleQuestionAnswerEntity.builder()
                                .durationToAnswerInMilliseconds(answer.getDurationToAnswerInMilliseconds())
                                .question(lineScaleQuestionRepository.getById(answer.getId()))
                                .option(optionRepository.getById(answer.getChosenOptions().stream().findFirst().orElse(new Option()).getId()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    protected List<LongAnswerQuestionEntity> mapToLongAnswerQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> LONG_ANSWER.equals(request.getType()))
                .map(this::mapToLongAnswerQuestion)
                .collect(Collectors.toList());
    }

    protected LongAnswerQuestionEntity mapToLongAnswerQuestion(QuestionCreateRequest request) {
        return LongAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .disabledFormRandomFormGenerating(request.isDisabledFormRandomFormGenerating())
                .countAnswers(0L)
                .build();
    }

    protected List<LongAnswerQuestionAnswerEntity> mapLongAnswerAnswerToLongAnswerQuestionAnswer(List<QuestionAnswering> requests) {
        return requests.stream()
                .filter(answer -> LONG_ANSWER.equals(answer.getType()))
                .map(answer ->
                        LongAnswerQuestionAnswerEntity.builder()
                                .durationToAnswerInMilliseconds(answer.getDurationToAnswerInMilliseconds())
                                .question(longAnswerQuestionRepository.getById(answer.getId()))
                                .answer(AnswerEntity.builder().content(answer.getAnswer()).build())
                                .build()
                )
                .collect(Collectors.toList());
    }

    protected List<MultipleChoiceQuestionEntity> mapToMultipleTypeQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> MULTIPLE_CHOICE.equals(request.getType()))
                .map(this::mapToMultipleChoiceQuestion)
                .collect(Collectors.toList());
    }

    protected MultipleChoiceQuestionEntity mapToMultipleChoiceQuestion(QuestionCreateRequest request) {
        return MultipleChoiceQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .disabledFormRandomFormGenerating(request.isDisabledFormRandomFormGenerating())
                .countAnswers(0L)
                .build();
    }

    protected List<MultipleChoiceQuestionAnswerEntity> mapMultipleChoiceAnswerToMultipleChoiceQuestionAnswer(List<QuestionAnswering> requests) {
        return requests.stream()
                .filter(answer -> MULTIPLE_CHOICE.equals(answer.getType()))
                .map(answer ->
                        MultipleChoiceQuestionAnswerEntity.builder()
                                .durationToAnswerInMilliseconds(answer.getDurationToAnswerInMilliseconds())
                                .question(multipleChoiceQuestionRepository.getById(answer.getId()))
                                .options(mapToMultipleOptions(answer.getChosenOptions()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    protected List<OptionEntity> mapToMultipleOptions(Set<Option> options) {
        return options.stream().map(option ->
                optionRepository.getById(option.getId())).collect(Collectors.toList());
    }

    protected List<ShortAnswerQuestionEntity> mapToShortAnswerQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> SHORT_ANSWER.equals(request.getType()))
                .map(this::mapToShortAnswerQuestion)
                .collect(Collectors.toList());
    }

    protected ShortAnswerQuestionEntity mapToShortAnswerQuestion(QuestionCreateRequest request) {
        return ShortAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .disabledFormRandomFormGenerating(request.isDisabledFormRandomFormGenerating())
                .countAnswers(0L)
                .build();
    }

    protected List<ShortAnswerQuestionAnswerEntity> mapShortAnswerAnswerToShortAnswerQuestionAnswer(List<QuestionAnswering> requests) {
        return requests.stream()
                .filter(answer -> SHORT_ANSWER.equals(answer.getType()))
                .map(answer ->
                        ShortAnswerQuestionAnswerEntity.builder()
                                .durationToAnswerInMilliseconds(answer.getDurationToAnswerInMilliseconds())
                                .question(shortAnswerQuestionRepository.getById(answer.getId()))
                                .answer(AnswerEntity.builder().content(answer.getAnswer()).build())
                                .build()
                )
                .collect(Collectors.toList());
    }

    protected List<SingleChoiceQuestionEntity> mapToSingleChoiceQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> SINGLE_CHOICE.equals(request.getType()))
                .map(this::mapToSingleChoiceQuestion)
                .collect(Collectors.toList());
    }

    protected SingleChoiceQuestionEntity mapToSingleChoiceQuestion(QuestionCreateRequest request) {
        return SingleChoiceQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .disabledFormRandomFormGenerating(request.isDisabledFormRandomFormGenerating())
                .countAnswers(0L)
                .build();
    }

    protected List<SingleChoiceQuestionAnswerEntity> mapSingleChoiceAnswerToSingleChoiceQuestionAnswer(List<QuestionAnswering> requests) {
        return requests.stream()
                .filter(answer -> SINGLE_CHOICE.equals(answer.getType()))
                .map(answer ->
                        SingleChoiceQuestionAnswerEntity.builder()
                                .durationToAnswerInMilliseconds(answer.getDurationToAnswerInMilliseconds())
                                .question(singleChoiceQuestionRepository.getById(answer.getId()))
                                .option(optionRepository.getById(answer.getChosenOptions().stream().findFirst().orElse(new Option()).getId()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    protected OptionEntity mapToOption(Option option) {
        return OptionEntity.builder()
                .content(option.getContent())
                .build();
    }
}
