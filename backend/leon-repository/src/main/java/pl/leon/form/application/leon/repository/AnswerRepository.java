package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
