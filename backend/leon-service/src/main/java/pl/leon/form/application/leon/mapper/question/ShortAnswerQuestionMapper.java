package pl.leon.form.application.leon.mapper.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;

@Component
@Mapper(componentModel = "spring")
public abstract class ShortAnswerQuestionMapper implements QuestionMapper<ShortAnswerQuestionEntity> {
    @Override
    @Mapping(target="type", expression = "java(pl.leon.form.application.leon.model.response.questions.type.QuestionType.getTypeByEntity(questionEntity.getClass()))")
    public abstract QuestionResponse mapToResponse(ShortAnswerQuestionEntity questionEntity);
}
