package pl.leon.form.application.leon.repository.entities.questions;

import lombok.Builder;
import lombok.Data;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "LONG_ANSWER_QUESTIONS")
public class LongAnswerQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(
            mappedBy = "longAnswerQuestionEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AnswerEntity> answers;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private FormEntity form;
}
