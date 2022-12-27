package pl.leon.form.application.leon.mapper.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.OptionsEntity;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class MultipleChoiceQuestionMapper implements QuestionMapper<MultipleChoiceQuestionEntity> {
    @Override
    @Mapping(target="type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(MultipleChoiceQuestionEntity questionEntity);

    @Mappings({
            @Mapping(target = "id", source = "key.id"),
            @Mapping(target = "chosenOptions", source = "value"),
            @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByEntity(org.hibernate.Hibernate.unproxy(multipleChoiceAnswer.getKey()).getClass()))")
    })
    public abstract QuestionAnswering mapToAnsweringByOptions(Map.Entry<MultipleChoiceQuestionEntity, OptionsEntity> multipleChoiceAnswer);

    @Override
    public abstract QuestionAnswering mapToAnsweringByAnswer(Map.Entry<MultipleChoiceQuestionEntity, AnswerEntity> multipleChoiceAnswer);

    @Override
    public QuestionAnswering mapToAnswering(Map.Entry<MultipleChoiceQuestionEntity, Object> multipleChoiceAnswer){
        return mapToAnsweringByOptions(new AbstractMap.SimpleEntry<>(multipleChoiceAnswer.getKey(), (OptionsEntity) multipleChoiceAnswer.getValue()));
    }
}
