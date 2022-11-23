package pl.leon.form.application.leon.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;

@Component
@Mapper(componentModel = "spring")
public abstract class LineScaleQuestionMapper implements QuestionMapper<LineScaleQuestionEntity> {
    @Override
    public abstract QuestionResponse mapToResponse(LineScaleQuestionEntity lineScaleQuestionEntity);
}
