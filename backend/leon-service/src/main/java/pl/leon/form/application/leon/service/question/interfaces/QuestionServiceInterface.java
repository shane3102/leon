package pl.leon.form.application.leon.service.question.interfaces;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.TooManyQuestionsToGenerate;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.QuestionRepositoryInterface;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.questions.QuestionMethodsInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@SuppressWarnings("unchecked")
public interface QuestionServiceInterface<T extends QuestionMethodsInterface> {

    QuestionRepositoryInterface<T> getRepository();

    QuestionMapperManager getQuestionMapperManager();

    @Deprecated
    default List<QuestionResponse> getRandomQuestions(Short count) {
        checkIfEnoughQuestionsToGenerate(count);

        List<Long> randomQuestionIds = new ArrayList<>();
        while (randomQuestionIds.size() < count) {
            Long randIndex = Double.valueOf(Math.ceil(Math.random() * count)).longValue();
            if (!randomQuestionIds.contains(randIndex)) {
                randomQuestionIds.add(randIndex);
            }
        }

        return randomQuestionIds.stream()
                .map(getRepository()::getById)
                .map(entity -> {
                    if (entity instanceof HibernateProxy) {
                        return (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
                    }
                    return entity;
                })
                .map(getQuestionMapperManager()::mapToResponse)
                .collect(Collectors.toList());
    }

    default Stream<FormEntity> disableQuestions() {
        return getRepository().findByDisabledFalseAndFormDateTo(LocalDate.now()).stream().peek(question -> {
            question.setDisabled(true);
            getRepository().save(question);
        }).map(QuestionMethodsInterface::getForm);
    }

    default List<QuestionResponse> getQuestionsWithMinimumCount(Short count) {
        checkIfEnoughQuestionsToGenerate(count);

        return getRepository().findByDisabledFalseOrderByCountAnswersAsc().stream().limit(count).map(entity -> {
                    if (entity instanceof HibernateProxy) {
                        return (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
                    }
                    return entity;
                })
                .map(getQuestionMapperManager()::mapToResponse)
                .collect(Collectors.toList());
    }

    default List<QuestionResponse> getQuestionsWithMinimumCountWhereIdNotIn(Short count, List<Long> questionIds){
        checkIfEnoughQuestionToGenerateIfIdsNot(count, questionIds);

        return getRepository().findByDisabledFalseAndIdNotInOrderByCountAnswersAsc(questionIds).stream().limit(count).map(entity -> {
                    if (entity instanceof HibernateProxy) {
                        return (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
                    }
                    return entity;
                })
                .map(getQuestionMapperManager()::mapToResponse)
                .collect(Collectors.toList());
    }

    default void checkIfEnoughQuestionToGenerateIfIdsNot(Short count, List<Long> questionIds){
        long availableQuestions = getRepository().countByIdNotIn(questionIds);

        if (availableQuestions < count) {
            throw new TooManyQuestionsToGenerate();
        }
    }

    default void checkIfEnoughQuestionsToGenerate(Short count) {
        long allQuestions = getRepository().count();

        if (allQuestions < count) {
            throw new TooManyQuestionsToGenerate();
        }
    }


}
