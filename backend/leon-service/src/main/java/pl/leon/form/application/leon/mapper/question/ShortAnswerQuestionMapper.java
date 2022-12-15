package pl.leon.form.application.leon.mapper.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.AbstractMap;
import java.util.Map;

@Component
@Mapper(componentModel = "spring")
public abstract class ShortAnswerQuestionMapper implements QuestionMapper<ShortAnswerQuestionEntity> {
    @Override
    @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(ShortAnswerQuestionEntity questionEntity);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "key.id"),
            @Mapping(target = "answer", source = "value.content"),
            @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(shortAnswer.getKey().getClass()))")
    })
    public abstract QuestionAnswering mapToAnsweringByAnswer(Map.Entry<ShortAnswerQuestionEntity, AnswerEntity> shortAnswer);

    @Override
    public abstract QuestionAnswering mapToAnsweringByOption(Map.Entry<ShortAnswerQuestionEntity, OptionEntity> shortAnswer);

    @Override
    public QuestionAnswering mapToAnswering(Map.Entry<ShortAnswerQuestionEntity, Object> singleChoiceAnswer){
        return mapToAnsweringByAnswer(new AbstractMap.SimpleEntry<>(singleChoiceAnswer.getKey(), (AnswerEntity) singleChoiceAnswer.getValue()));
    }
}
