package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

public interface SingleChoiceQuestionRepository extends JpaRepository<SingleChoiceQuestionEntity, Long> {
}
