package pl.leon.form.application.leon.mapper.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.AbstractMap;
import java.util.Map;

@Component
@Mapper(componentModel = "spring")
public abstract class LineScaleQuestionMapper implements QuestionMapper<LineScaleQuestionEntity> {
    @Override
    @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(LineScaleQuestionEntity questionEntity);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "key.id"),
            @Mapping(target = "chosenOption.id", source = "value.id"),
            @Mapping(target = "chosenOption.content", source = "value.content"),
            @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(lineScaleAnswer.getKey().getClass()))")
    })
    public abstract QuestionAnswering mapToAnsweringByOption(Map.Entry<LineScaleQuestionEntity, OptionEntity> lineScaleAnswer);

    @Override
    public abstract QuestionAnswering mapToAnsweringByAnswer(Map.Entry<LineScaleQuestionEntity, AnswerEntity> lineScaleAnswer);

    @Override
    public QuestionAnswering mapToAnswering(Map.Entry<LineScaleQuestionEntity, Object> singleChoiceAnswer){
        return mapToAnsweringByOption(new AbstractMap.SimpleEntry<>(singleChoiceAnswer.getKey(), (OptionEntity) singleChoiceAnswer.getValue()));
    }
}
