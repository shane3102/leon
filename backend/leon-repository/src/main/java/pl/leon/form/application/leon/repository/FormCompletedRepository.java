package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;

public interface FormCompletedRepository extends JpaRepository<FormCompletedEntity, Long> {
}
