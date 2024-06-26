package pl.leon.form.application.leon.repository.entities.question_answers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.Duration;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultipleChoiceQuestionAnswerEntity implements QuestionAnswerMethodsInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private MultipleChoiceQuestionEntity question;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<OptionEntity> options;

    private Long durationToAnswerInMilliseconds;

    @ManyToOne
    @JoinColumn(name = "form_completed_id", referencedColumnName = "id")
    private FormCompletedEntity formCompleted;

    @Override
    public String getOptionCount() {
        return String.valueOf(options.size());
    }

    @Override
    public String getAnswersAsText() {
        StringBuilder sb = new StringBuilder();

        options.forEach(option -> {
            sb.append(option.getContent()).append(',');
        });

        String result = sb.toString();
        result = result.substring(0, result.length() - 1);

        return result;
    }
}
