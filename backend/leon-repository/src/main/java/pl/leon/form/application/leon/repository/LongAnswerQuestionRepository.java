package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;

public interface LongAnswerQuestionRepository extends JpaRepository<LongAnswerQuestionEntity, Long> {
}
