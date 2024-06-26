package pl.leon.form.application.leon.repository.entities.question_answers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Duration;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DROPDOWN_QUESTION_ANSWER")
public class DropdownQuestionAnswerEntity implements QuestionAnswerMethodsInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private DropdownQuestionEntity question;

    @ManyToOne(cascade = CascadeType.MERGE)
    private OptionEntity option;

    private Long durationToAnswerInMilliseconds;

    @ManyToOne
    @JoinColumn(name = "form_completed_id", referencedColumnName = "id")
    private FormCompletedEntity formCompleted;

    @Override
    public String getAnswersAsText() {
        return option.getContent();
    }
}
