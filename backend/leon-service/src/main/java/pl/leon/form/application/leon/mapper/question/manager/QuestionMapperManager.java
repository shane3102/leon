package pl.leon.form.application.leon.mapper.question.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.question.DropdownQuestionMapper;
import pl.leon.form.application.leon.mapper.question.LineScaleQuestionMapper;
import pl.leon.form.application.leon.mapper.question.LongAnswerQuestionMapper;
import pl.leon.form.application.leon.mapper.question.MultipleChoiceQuestionMapper;
import pl.leon.form.application.leon.mapper.question.QuestionMapper;
import pl.leon.form.application.leon.mapper.question.ShortAnswerQuestionMapper;
import pl.leon.form.application.leon.mapper.question.SingleChoiceQuestionMapper;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionStatisticsResponse;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LineScaleQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.ShortAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.SingleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.QuestionMethodsInterface;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public QuestionResponse mapToResponse(QuestionMethodsInterface questionEntity) {
        log.info("Entity type: {}", questionEntity.getClass());
        QuestionResponse response = mappers.get(questionEntity.getClass()).mapToResponse(questionEntity);
        log.info("Response: {}", response);
        return response;
    }

    public QuestionStatisticsResponse mapToStatisticsResponse(QuestionMethodsInterface questionEntity) {
        log.info("Entity type: {}", questionEntity.getClass());
        QuestionStatisticsResponse response = mappers.get(questionEntity.getClass()).mapToStatisticsResponse(questionEntity);
        log.info("Response: {}", response);
        return response;
    }

    public QuestionAnswering mapToAnswering(Object questionAnsweringEntity) {
        log.info("Entity type: {}", questionAnsweringEntity.getClass());
        QuestionAnswering response = mappers.get(questionAnsweringEntity.getClass()).mapToAnswering(questionAnsweringEntity);
        log.info("Response: {}", response);
        return response;
    }

    public List<QuestionResponse> mapToResponses(List... questionLists) {
        log.info("mapToResponses({})", questionLists == null ? null : questionLists.length);

        List<QuestionResponse> resultList = new ArrayList<>();

        for (List<QuestionMethodsInterface> concreteQuestionList : Optional.ofNullable(questionLists).orElse(new List[]{})) {
            concreteQuestionList.forEach(
                    question -> resultList.add(mapToResponse(question))
            );
        }

        log.info("mapToResponses({}) = {}", questionLists == null ? null : questionLists.length, resultList);
        return resultList;
    }

    public List<QuestionStatisticsResponse> mapToStatisticsResponses(List... questionLists) {
        log.info("mapToStatisticsResponses({})", questionLists == null ? null : questionLists.length);

        List<QuestionStatisticsResponse> resultList = new ArrayList<>();

        for (List<QuestionMethodsInterface> concreteQuestionList : Optional.ofNullable(questionLists).orElse(new List[]{})) {
            concreteQuestionList.forEach(
                    question -> resultList.add(mapToStatisticsResponse(question))
            );
        }

        log.info("mapToStatisticsResponses({}) = {}", questionLists == null ? null : questionLists.length, resultList);
        return resultList;
    }

    public List<QuestionAnswering> mapToAnswering(List... questionLists) {
        log.info("mapToAnswering({})", questionLists == null ? null : questionLists.length);

        List<QuestionAnswering> resultList = new ArrayList<>();

        for (List<Object> concreteQuestionList : Optional.ofNullable(questionLists).orElse(new List[]{})) {
            concreteQuestionList.forEach(
                    question -> resultList.add(mapToAnswering(question))
            );
        }

        log.info("mapToAnswering({}) = {}", questionLists == null ? null : questionLists.length, resultList);
        return resultList;
    }

    @PostConstruct
    private void postConstruct() {
        mappers.put(DropdownQuestionEntity.class, dropdownQuestionMapper);
        mappers.put(LineScaleQuestionEntity.class, lineScaleQuestionMapper);
        mappers.put(LongAnswerQuestionEntity.class, longAnswerQuestionMapper);
        mappers.put(MultipleChoiceQuestionEntity.class, multipleChoiceQuestionMapper);
        mappers.put(ShortAnswerQuestionEntity.class, shortAnswerQuestionMapper);
        mappers.put(SingleChoiceQuestionEntity.class, singleChoiceQuestionMapper);

        mappers.put(DropdownQuestionAnswerEntity.class, dropdownQuestionMapper);
        mappers.put(LineScaleQuestionAnswerEntity.class, lineScaleQuestionMapper);
        mappers.put(LongAnswerQuestionAnswerEntity.class, longAnswerQuestionMapper);
        mappers.put(MultipleChoiceQuestionAnswerEntity.class, multipleChoiceQuestionMapper);
        mappers.put(ShortAnswerQuestionAnswerEntity.class, shortAnswerQuestionMapper);
        mappers.put(SingleChoiceQuestionAnswerEntity.class, singleChoiceQuestionMapper);
    }
}
