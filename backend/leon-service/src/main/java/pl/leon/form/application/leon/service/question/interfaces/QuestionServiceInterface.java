package pl.leon.form.application.leon.service.question.interfaces;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.TooManyQuestionsToGenerate;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface QuestionServiceInterface<T> {

    JpaRepository<T, Long> getRepository();

    QuestionMapperManager getQuestionMapperManager();

    default List<QuestionResponse> getRandomQuestions(Short count) {
        long allQuestions = getRepository().count();

        if (allQuestions < count) {
            throw new TooManyQuestionsToGenerate();
        }

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
}
