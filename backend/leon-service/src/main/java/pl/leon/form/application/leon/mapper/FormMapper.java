package pl.leon.form.application.leon.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.model.request.FormRequest;
import pl.leon.form.application.leon.model.request.questions.OptionRequest;
import pl.leon.form.application.leon.model.request.questions.QuestionRequest;
import pl.leon.form.application.leon.model.response.FormResponse;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.List;
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
    protected QuestionMapperManager questionMapperManager;

    @Mappings({
            @Mapping(target = "dropdownQuestions", source = "questions"),
            @Mapping(target = "lineScaleQuestions", source = "questions"),
            @Mapping(target = "longAnswerQuestions", source = "questions"),
            @Mapping(target = "multipleChoiceQuestions", source = "questions"),
            @Mapping(target = "shortAnswerQuestions", source = "questions"),
            @Mapping(target = "singleChoiceQuestions", source = "questions"),

    })
    public abstract FormEntity mapToEntity(FormRequest formRequest);

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

    protected List<DropdownQuestionEntity> mapToDropdownQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> DROPDOWN.equals(request.getType()))
                .map(this::mapToDropdownQuestion)
                .collect(Collectors.toList());
    }

    protected DropdownQuestionEntity mapToDropdownQuestion(QuestionRequest request) {
        return DropdownQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    protected List<LineScaleQuestionEntity> mapToLineScaleQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> LINE_SCALE.equals(request.getType()))
                .map(this::mapToLineScaleQuestion)
                .collect(Collectors.toList());
    }

    protected LineScaleQuestionEntity mapToLineScaleQuestion(QuestionRequest request) {
        return LineScaleQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    protected List<LongAnswerQuestionEntity> mapToLongAnswerQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> LONG_ANSWER.equals(request.getType()))
                .map(this::mapToLongAnswerQuestion)
                .collect(Collectors.toList());
    }

    protected LongAnswerQuestionEntity mapToLongAnswerQuestion(QuestionRequest request) {
        return LongAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .build();
    }

    protected List<MultipleChoiceQuestionEntity> mapToMultipleTypeQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> MULTIPLE_CHOICE.equals(request.getType()))
                .map(this::mapToMultipleChoiceQuestion)
                .collect(Collectors.toList());
    }

    protected MultipleChoiceQuestionEntity mapToMultipleChoiceQuestion(QuestionRequest request) {
        return MultipleChoiceQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    protected List<ShortAnswerQuestionEntity> mapToShortAnswerQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> SHORT_ANSWER.equals(request.getType()))
                .map(this::mapToShortAnswerQuestion)
                .collect(Collectors.toList());
    }

    protected ShortAnswerQuestionEntity mapToShortAnswerQuestion(QuestionRequest request) {
        return ShortAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .build();
    }

    protected List<SingleChoiceQuestionEntity> mapToSingleTypeQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> SINGLE_CHOICE.equals(request.getType()))
                .map(this::mapToSingleChoiceQuestion)
                .collect(Collectors.toList());
    }

    protected SingleChoiceQuestionEntity mapToSingleChoiceQuestion(QuestionRequest request) {
        return SingleChoiceQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    protected OptionEntity mapToOption(OptionRequest optionRequest) {
        return OptionEntity.builder()
                .content(optionRequest.getContent())
                .build();
    }
}
