package pl.leon.form.application.leon.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LineScaleQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.ShortAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.SingleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.validation.form_completed.FormCompletedValidation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FormCompletedValidation
@Table(name = "COMPLETED_FORM")
public class FormCompletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private FormEntity completedForm;

    @Enumerated(EnumType.STRING)
    private FormLevelType uxLevel;

    @Enumerated(EnumType.STRING)
    private FormLevelType uiLevel;

    private Long completeDurationInMilliseconds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<DropdownQuestionAnswerEntity> answeredDropdownQuestions;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<LineScaleQuestionAnswerEntity> answeredLineScaleQuestions;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<LongAnswerQuestionAnswerEntity> answeredLongAnswerQuestions;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<MultipleChoiceQuestionAnswerEntity> answeredMultipleChoiceQuestions;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ShortAnswerQuestionAnswerEntity> answeredShortAnswerQuestions;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<SingleChoiceQuestionAnswerEntity> answeredSingleChoiceQuestions;
}
