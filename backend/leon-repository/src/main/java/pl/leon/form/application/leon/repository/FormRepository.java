package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.FormEntity;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, Long> {
}
