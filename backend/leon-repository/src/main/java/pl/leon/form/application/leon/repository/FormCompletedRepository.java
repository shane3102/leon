package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;

import java.util.List;

@Repository
public interface FormCompletedRepository extends JpaRepository<FormCompletedEntity, Long> {

    List<FormCompletedEntity> findAllByUxLevelAndUiLevel(FormLevelType uxLevel, FormLevelType uiLevel);
}
