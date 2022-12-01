package pl.leon.form.application.leon.mapper.question.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.question.DropdownQuestionMapper;
import pl.leon.form.application.leon.mapper.question.LineScaleQuestionMapper;
import pl.leon.form.application.leon.mapper.question.LongAnswerQuestionMapper;
import pl.leon.form.application.leon.mapper.question.MultipleChoiceQuestionMapper;
import pl.leon.form.application.leon.mapper.question.QuestionMapper;
import pl.leon.form.application.leon.mapper.question.ShortAnswerQuestionMapper;
import pl.leon.form.application.leon.mapper.question.SingleChoiceQuestionMapper;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings({"unchecked"})
public class QuestionMapperManager {
    private Map<Class<?>, QuestionMapper> mappers;

    private final DropdownQuestionMapper dropdownQuestionMapper;
    private final LineScaleQuestionMapper lineScaleQuestionMapper;
    private final LongAnswerQuestionMapper longAnswerQuestionMapper;
    private final MultipleChoiceQuestionMapper multipleChoiceQuestionMapper;
    private final ShortAnswerQuestionMapper shortAnswerQuestionMapper;
    private final SingleChoiceQuestionMapper singleChoiceQuestionMapper;

    public QuestionResponse mapToResponse(Object questionEntity) {
        log.info("Entity type: {}", questionEntity.getClass());
        QuestionResponse response = mappers.get(questionEntity.getClass()).mapToResponse(questionEntity);
        log.info("Response: {}", response);
        return response;
    }

    @PostConstruct
    private void postConstruct() {
        mappers.put(DropdownQuestionEntity.class, dropdownQuestionMapper);
        mappers.put(LineScaleQuestionEntity.class, lineScaleQuestionMapper);
        mappers.put(LongAnswerQuestionEntity.class, longAnswerQuestionMapper);
        mappers.put(MultipleChoiceQuestionEntity.class, multipleChoiceQuestionMapper);
        mappers.put(ShortAnswerQuestionEntity.class, shortAnswerQuestionMapper);
        mappers.put(SingleChoiceQuestionEntity.class, singleChoiceQuestionMapper);
    }
}
