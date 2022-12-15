package pl.leon.form.application.leon.mapper.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.AbstractMap;
import java.util.Map;

@Component
@Mapper(componentModel = "spring")
public abstract class LongAnswerQuestionMapper implements QuestionMapper<LongAnswerQuestionEntity> {
    @Override
    @Mapping(target="type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(LongAnswerQuestionEntity questionEntity);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "key.id"),
            @Mapping(target = "answer", source = "value.content"),
            @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(longAnswer.getKey().getClass()))")
    })
    public abstract QuestionAnswering mapToAnsweringByAnswer(Map.Entry<LongAnswerQuestionEntity, AnswerEntity> longAnswer);

    @Override
    public abstract QuestionAnswering mapToAnsweringByOption(Map.Entry<LongAnswerQuestionEntity, OptionEntity> longAnswer);

    @Override
    public QuestionAnswering mapToAnswering(Map.Entry<LongAnswerQuestionEntity, Object> singleChoiceAnswer){
        return mapToAnsweringByAnswer(new AbstractMap.SimpleEntry<>(singleChoiceAnswer.getKey(), (AnswerEntity) singleChoiceAnswer.getValue()));
    }
}
