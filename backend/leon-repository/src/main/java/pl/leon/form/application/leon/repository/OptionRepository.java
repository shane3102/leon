package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.leon.form.application.leon.repository.entities.OptionEntity;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
}
