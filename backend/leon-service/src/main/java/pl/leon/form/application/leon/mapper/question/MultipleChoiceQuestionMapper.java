package pl.leon.form.application.leon.mapper.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;

@Component
@Mapper(componentModel = "spring")
public abstract class MultipleChoiceQuestionMapper implements QuestionMapper<MultipleChoiceQuestionEntity, MultipleChoiceQuestionAnswerEntity> {
    @Override
    @Mapping(target="type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByQuestionType(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(MultipleChoiceQuestionEntity questionEntity);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "question.id"),
            @Mapping(target = "chosenOptions", source = "options"),
            @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByAnsweringType(answeringEntity.getClass()))")
    })
    public abstract QuestionAnswering mapToAnswering(MultipleChoiceQuestionAnswerEntity answeringEntity);
}
