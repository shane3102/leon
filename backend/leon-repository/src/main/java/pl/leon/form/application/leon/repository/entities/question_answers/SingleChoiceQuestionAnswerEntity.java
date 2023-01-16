package pl.leon.form.application.leon.repository.entities.question_answers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.Duration;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleChoiceQuestionAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private SingleChoiceQuestionEntity question;

    @ManyToOne(cascade = CascadeType.MERGE)
    private OptionEntity option;

    private Duration durationToAnswer;

    @OneToOne(cascade = CascadeType.MERGE)
    private FormCompletedEntity formCompleted;
}