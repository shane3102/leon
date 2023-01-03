package pl.leon.form.application.leon.service.question.interfaces;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.TooManyQuestionsToGenerate;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.QuestionRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public interface QuestionServiceInterface<T> {

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

    default List<QuestionResponse> getQuestionsWithMinimumCount(Short count) {
        checkIfEnoughQuestionsToGenerate(count);

        return getRepository().findByFormDisableQuestionsForRandomFormsFalseOrderByCountAnswersAsc().stream().limit(count).map(entity -> {
                    if (entity instanceof HibernateProxy) {
                        return (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
                    }
                    return entity;
                })
                .map(getQuestionMapperManager()::mapToResponse)
                .collect(Collectors.toList());
    }

    default void checkIfEnoughQuestionsToGenerate(Short count) {
        long allQuestions = getRepository().count();

        if (allQuestions < count) {
            throw new TooManyQuestionsToGenerate();
        }
    }
}
