package pl.leon.form.application.leon.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.model.request.forms.FormCompletedRequest;
import pl.leon.form.application.leon.model.request.forms.FormCreateRequest;
import pl.leon.form.application.leon.model.request.questions.OptionRequest;
import pl.leon.form.application.leon.model.request.questions.QuestionAnsweringRequest;
import pl.leon.form.application.leon.model.request.questions.QuestionCreateRequest;
import pl.leon.form.application.leon.model.response.FormResponse;
import pl.leon.form.application.leon.model.response.FormSnippetResponse;
import pl.leon.form.application.leon.repository.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.OptionRepository;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;
import pl.leon.form.application.leon.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Mappings({
            @Mapping(target = "dropdownQuestions", source = "questions"),
            @Mapping(target = "lineScaleQuestions", source = "questions"),
            @Mapping(target = "longAnswerQuestions", source = "questions"),
            @Mapping(target = "multipleChoiceQuestions", source = "questions"),
            @Mapping(target = "shortAnswerQuestions", source = "questions"),
            @Mapping(target = "singleChoiceQuestions", source = "questions"),
            @Mapping(target = "user", expression = "java(" +
                    "(pl.leon.form.application.leon.repository.entities.UserEntity) " +
                    "userService.loadUserByUsername(((org.springframework.security.core.userdetails.User) " +
                    "org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()))")
    })
    public abstract FormEntity mapCreateRequestToEntity(FormCreateRequest formCreateRequest);

    @Mappings({
            @Mapping(target = "answeredDropdownQuestions", source = "answers"),
            @Mapping(target = "answeredLineScaleQuestions", source = "answers"),
            @Mapping(target = "answeredLongAnswerQuestions", source = "answers"),
            @Mapping(target = "answeredMultipleChoiceQuestions", source = "answers"),
            @Mapping(target = "answeredShortAnswerQuestions", source = "answers"),
            @Mapping(target = "answeredSingleChoiceQuestions", source = "answers"),
            @Mapping(target = "user", expression = "java(" +
                    "(pl.leon.form.application.leon.repository.entities.UserEntity) " +
                    "userService.loadUserByUsername(((org.springframework.security.core.userdetails.User) " +
                    "org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()))")
    })
    public abstract FormCompletedEntity mapCompletedRequestToCompletedEntity(FormCompletedRequest formCompletedRequested);

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
            @Mapping(target = "author", source = "user.username")
    })
    public abstract FormSnippetResponse mapToSnippetResponse(FormEntity formEntity);

    public abstract List<FormSnippetResponse> mapToSnippetResponses(List<FormEntity> formEntities);

    protected List<DropdownQuestionEntity> mapToDropdownQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> DROPDOWN.equals(request.getType()))
                .map(this::mapToDropdownQuestion)
                .collect(Collectors.toList());
    }

    protected DropdownQuestionEntity mapToDropdownQuestion(QuestionCreateRequest request) {
        return DropdownQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    protected Map<DropdownQuestionEntity, OptionEntity> mapDropdownAnswerToMapWithQuestionAndAnswer(List<QuestionAnsweringRequest> requests) {
        return requests.stream()
                .filter(answer -> DROPDOWN.equals(answer.getType()))
                .map(answer -> Map.of(dropdownQuestionRepository.getById(answer.getId()), optionRepository.getById(answer.getChosenOption().getId())))
                .reduce((resultMap, currentMap) -> {
                    resultMap.putAll(currentMap);
                    return resultMap;
                }).orElse(Collections.emptyMap());
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
                .build();
    }

    protected Map<LineScaleQuestionEntity, OptionEntity> mapLineScaleAnswerToMapWithQuestionAndAnswer(List<QuestionAnsweringRequest> requests) {
        return requests.stream()
                .filter(answer -> LINE_SCALE.equals(answer.getType()))
                .map(answer -> Map.of(lineScaleQuestionRepository.getById(answer.getId()), optionRepository.getById(answer.getChosenOption().getId())))
                .reduce((resultMap, currentMap) -> {
                    resultMap.putAll(currentMap);
                    return resultMap;
                }).orElse(Collections.emptyMap());
    }

    protected List<LongAnswerQuestionEntity> mapToLongAnswerQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> LONG_ANSWER.equals(request.getType()))
                .map(this::mapToLongAnswerQuestion)
                .collect(Collectors.toList());
    }

    protected LongAnswerQuestionEntity mapToLongAnswerQuestion(QuestionCreateRequest request) {
        return LongAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .build();
    }

    protected Map<LongAnswerQuestionEntity, AnswerEntity> mapLongAnswerAnswerToMapWithQuestionAndAnswer(List<QuestionAnsweringRequest> requests) {
        return requests.stream()
                .filter(answer -> LONG_ANSWER.equals(answer.getType()))
                .map(answer -> {
                    LongAnswerQuestionEntity longAnswerEntity = longAnswerQuestionRepository.getById(answer.getId());
                    return Map.of(longAnswerEntity, AnswerEntity.builder().longAnswerQuestionEntity(longAnswerEntity).content(answer.getAnswer()).build());
                })
                .reduce((resultMap, currentMap) -> {
                    resultMap.putAll(currentMap);
                    return resultMap;
                }).orElse(Collections.emptyMap());
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
                .build();
    }

    protected Map<MultipleChoiceQuestionEntity, OptionEntity> mapMultipleChoiceAnswerToMapWithQuestionAndAnswer(List<QuestionAnsweringRequest> requests) {
        return requests.stream()
                .filter(answer -> MULTIPLE_CHOICE.equals(answer.getType()))
                .map(answer -> Map.of(multipleChoiceQuestionRepository.getById(answer.getId()), optionRepository.getById(answer.getChosenOption().getId())))
                .reduce((resultMap, currentMap) -> {
                    resultMap.putAll(currentMap);
                    return resultMap;
                }).orElse(Collections.emptyMap());
    }

    protected List<ShortAnswerQuestionEntity> mapToShortAnswerQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> SHORT_ANSWER.equals(request.getType()))
                .map(this::mapToShortAnswerQuestion)
                .collect(Collectors.toList());
    }

    protected ShortAnswerQuestionEntity mapToShortAnswerQuestion(QuestionCreateRequest request) {
        return ShortAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .build();
    }

    protected Map<ShortAnswerQuestionEntity, AnswerEntity> mapShortAnswerAnswerToMapWithQuestionAndAnswer(List<QuestionAnsweringRequest> requests) {
        return requests.stream()
                .filter(answer -> SHORT_ANSWER.equals(answer.getType()))
                .map(answer -> {
                    ShortAnswerQuestionEntity shortAnswerEntity = shortAnswerQuestionRepository.getById(answer.getId());
                    return Map.of(shortAnswerEntity, AnswerEntity.builder().shortAnswerQuestionEntity(shortAnswerEntity).content(answer.getAnswer()).build());
                })
                .reduce((resultMap, currentMap) -> {
                    resultMap.putAll(currentMap);
                    return resultMap;
                }).orElse(Collections.emptyMap());
    }

    protected List<SingleChoiceQuestionEntity> mapToSingleTypeQuestions(List<QuestionCreateRequest> requests) {
        return requests.stream().filter(request -> SINGLE_CHOICE.equals(request.getType()))
                .map(this::mapToSingleChoiceQuestion)
                .collect(Collectors.toList());
    }

    protected SingleChoiceQuestionEntity mapToSingleChoiceQuestion(QuestionCreateRequest request) {
        return SingleChoiceQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    protected Map<SingleChoiceQuestionEntity, OptionEntity> mapSingleChoiceAnswerToMapWithQuestionAndAnswer(List<QuestionAnsweringRequest> requests) {
        return requests.stream()
                .filter(answer -> SINGLE_CHOICE.equals(answer.getType()))
                .map(answer -> Map.of(singleChoiceQuestionRepository.getById(answer.getId()), optionRepository.getById(answer.getChosenOption().getId())))
                .reduce((resultMap, currentMap) -> {
                    resultMap.putAll(currentMap);
                    return resultMap;
                }).orElse(Collections.emptyMap());
    }

    protected OptionEntity mapToOption(OptionRequest optionRequest) {
        return OptionEntity.builder()
                .content(optionRequest.getContent())
                .build();
    }
}
