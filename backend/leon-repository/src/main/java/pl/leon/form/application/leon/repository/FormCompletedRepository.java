package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;

@Repository
public interface FormCompletedRepository extends JpaRepository<FormCompletedEntity, Long> {
}
