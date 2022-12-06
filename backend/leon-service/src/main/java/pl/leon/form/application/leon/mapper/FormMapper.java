package pl.leon.form.application.leon.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.request.FormRequest;
import pl.leon.form.application.leon.model.request.questions.OptionRequest;
import pl.leon.form.application.leon.model.request.questions.QuestionRequest;
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
public interface FormMapper {

    @Mappings({
            @Mapping(target = "dropdownQuestions", source = "questions"),
            @Mapping(target = "lineScaleQuestions", source = "questions"),
            @Mapping(target = "longAnswerQuestions", source = "questions"),
            @Mapping(target = "multipleChoiceQuestions", source = "questions"),
            @Mapping(target = "shortAnswerQuestions", source = "questions"),
            @Mapping(target = "singleChoiceQuestions", source = "questions"),

    })
    FormEntity mapToEntity(FormRequest formRequest);

    default List<DropdownQuestionEntity> mapToDropdownQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> DROPDOWN.equals(request.getType()))
                .map(this::mapToDropdownQuestion)
                .collect(Collectors.toList());
    }

    default DropdownQuestionEntity mapToDropdownQuestion(QuestionRequest request) {
        return DropdownQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    default List<LineScaleQuestionEntity> mapToLineScaleQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> LINE_SCALE.equals(request.getType()))
                .map(this::mapToLineScaleQuestion)
                .collect(Collectors.toList());
    }

    default LineScaleQuestionEntity mapToLineScaleQuestion(QuestionRequest request) {
        return LineScaleQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    default List<LongAnswerQuestionEntity> mapToLongAnswerQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> LONG_ANSWER.equals(request.getType()))
                .map(this::mapToLongAnswerQuestion)
                .collect(Collectors.toList());
    }

    default LongAnswerQuestionEntity mapToLongAnswerQuestion(QuestionRequest request) {
        return LongAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .build();
    }

    default List<MultipleChoiceQuestionEntity> mapToMultipleTypeQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> MULTIPLE_CHOICE.equals(request.getType()))
                .map(this::mapToMultipleChoiceQuestion)
                .collect(Collectors.toList());
    }

    default MultipleChoiceQuestionEntity mapToMultipleChoiceQuestion(QuestionRequest request) {
        return MultipleChoiceQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    default List<ShortAnswerQuestionEntity> mapToShortAnswerQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> SHORT_ANSWER.equals(request.getType()))
                .map(this::mapToShortAnswerQuestion)
                .collect(Collectors.toList());
    }

    default ShortAnswerQuestionEntity mapToShortAnswerQuestion(QuestionRequest request) {
        return ShortAnswerQuestionEntity.builder()
                .question(request.getQuestion())
                .build();
    }

    default List<SingleChoiceQuestionEntity> mapToSingleTypeQuestions(List<QuestionRequest> requests) {
        return requests.stream().filter(request -> SINGLE_CHOICE.equals(request.getType()))
                .map(this::mapToSingleChoiceQuestion)
                .collect(Collectors.toList());
    }

    default SingleChoiceQuestionEntity mapToSingleChoiceQuestion(QuestionRequest request) {
        return SingleChoiceQuestionEntity.builder()
                .question(request.getQuestion())
                .options(request.getOptions().stream().map(this::mapToOption).collect(Collectors.toList()))
                .build();
    }

    default OptionEntity mapToOption(OptionRequest optionRequest) {
        return OptionEntity.builder()
                .content(optionRequest.getContent())
                .build();
    }
}
