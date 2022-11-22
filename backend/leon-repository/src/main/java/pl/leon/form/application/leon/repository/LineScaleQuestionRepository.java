package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;

public interface LineScaleQuestionRepository extends JpaRepository<LineScaleQuestionEntity,Long> {
}
