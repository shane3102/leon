package pl.leon.form.application.leon.mapper.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LineScaleQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Mapper(componentModel = "spring")
public abstract class LineScaleQuestionMapper implements QuestionMapper<LineScaleQuestionEntity, LineScaleQuestionAnswerEntity> {
    @Override
    @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByQuestionType(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(LineScaleQuestionEntity questionEntity);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "question.id"),
            @Mapping(target = "chosenOptions", source = "option"),
            @Mapping(target = "type", expression = "java(pl.leon.form.application.leon.model.both.questions.type.QuestionType.getTypeByAnsweringType(answeringEntity.getClass()))")
    })
    public abstract QuestionAnswering mapToAnswering(LineScaleQuestionAnswerEntity answeringEntity);
}
