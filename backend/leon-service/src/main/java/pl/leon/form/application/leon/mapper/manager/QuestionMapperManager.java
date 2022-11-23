package pl.leon.form.application.leon.mapper.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.DropdownQuestionMapper;
import pl.leon.form.application.leon.mapper.LineScaleQuestionMapper;
import pl.leon.form.application.leon.mapper.LongAnswerQuestionMapper;
import pl.leon.form.application.leon.mapper.MultipleChoiceQuestionMapper;
import pl.leon.form.application.leon.mapper.QuestionMapper;
import pl.leon.form.application.leon.mapper.ShortAnswerQuestionMapper;
import pl.leon.form.application.leon.mapper.SingleChoiceQuestionMapper;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.questions.AbstractQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings({"unchecked"})
public class QuestionMapperManager {
    private Map<Class<? extends AbstractQuestionEntity>, QuestionMapper> mappers;

    private final DropdownQuestionMapper dropdownQuestionMapper;
    private final LineScaleQuestionMapper lineScaleQuestionMapper;
    private final LongAnswerQuestionMapper longAnswerQuestionMapper;
    private final MultipleChoiceQuestionMapper multipleChoiceQuestionMapper;
    private final ShortAnswerQuestionMapper shortAnswerQuestionMapper;
    private final SingleChoiceQuestionMapper singleChoiceQuestionMapper;

    public QuestionResponse mapToResponse(AbstractQuestionEntity questionEntity) {
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
