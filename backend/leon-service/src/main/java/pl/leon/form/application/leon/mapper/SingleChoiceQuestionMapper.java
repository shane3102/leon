package pl.leon.form.application.leon.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

@Component
@Mapper(componentModel = "spring")
public abstract class SingleChoiceQuestionMapper implements QuestionMapper<SingleChoiceQuestionEntity> {
    @Override
    public abstract QuestionResponse mapToResponse(SingleChoiceQuestionEntity singleChoiceQuestionEntity);
}
