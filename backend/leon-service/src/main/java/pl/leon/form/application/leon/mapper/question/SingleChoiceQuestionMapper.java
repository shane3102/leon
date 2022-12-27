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
public abstract class SingleChoiceQuestionMapper implements QuestionMapper<SingleChoiceQuestionEntity> {
    @Override
    @Mapping(target="type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(SingleChoiceQuestionEntity questionEntity);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "key.id"),
            @Mapping(target = "chosenOptions", source = "value"),
            @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(org.hibernate.Hibernate.unproxy(singleChoiceAnswer.getKey()).getClass()))")
    })
    public abstract QuestionAnswering mapToAnsweringByOption(Map.Entry<SingleChoiceQuestionEntity, OptionEntity> singleChoiceAnswer);

    @Override
    public abstract QuestionAnswering mapToAnsweringByAnswer(Map.Entry<SingleChoiceQuestionEntity, AnswerEntity> singleChoiceAnswer);

    @Override
    public QuestionAnswering mapToAnswering(Map.Entry<SingleChoiceQuestionEntity, Object> singleChoiceAnswer){
        return mapToAnsweringByOption(new AbstractMap.SimpleEntry<>(singleChoiceAnswer.getKey(), (OptionEntity) singleChoiceAnswer.getValue()));
    }
}
