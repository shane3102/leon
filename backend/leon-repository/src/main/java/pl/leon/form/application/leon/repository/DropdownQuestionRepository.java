package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;

@Repository
public interface DropdownQuestionRepository extends QuestionRepositoryInterface<DropdownQuestionEntity> {
}
