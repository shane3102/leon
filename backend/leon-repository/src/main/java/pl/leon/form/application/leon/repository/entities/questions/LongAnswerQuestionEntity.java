package pl.leon.form.application.leon.repository.entities.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LONG_ANSWER_QUESTIONS")
public class LongAnswerQuestionEntity implements QuestionMethodsInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean disabled;

    private boolean disabledFormRandomFormGenerating;

    private String question;

    @OneToMany(
            mappedBy = "longAnswerQuestionEntity",
            cascade = CascadeType.ALL
    )
    private List<AnswerEntity> answers;

    private Long countAnswers;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private FormEntity form;
}
