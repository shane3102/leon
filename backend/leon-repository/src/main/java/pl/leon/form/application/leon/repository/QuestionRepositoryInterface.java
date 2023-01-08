package pl.leon.form.application.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.List;

@NoRepositoryBean
public interface QuestionRepositoryInterface<T> extends JpaRepository<T, Long> {
    List<T> findByDisabledFalseOrderByCountAnswersAsc();

    List<T> findByDisabledFalseAndFormDateTo(LocalDate dateTo);
}
