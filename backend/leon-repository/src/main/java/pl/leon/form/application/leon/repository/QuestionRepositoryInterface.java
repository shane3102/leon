package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface QuestionRepositoryInterface<T> extends JpaRepository<T, Long> {
    List<T> findByDisabledFalseOrderByCountAnswersAsc();
}
