package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;

public interface ShortAnswerQuestionRepository extends JpaRepository<ShortAnswerQuestionEntity,Long> {
}
