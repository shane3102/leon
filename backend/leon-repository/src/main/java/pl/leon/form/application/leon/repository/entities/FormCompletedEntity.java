package pl.leon.form.application.leon.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMPLETED_FORM")
public class FormCompletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private FormEntity completedForm;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "dropdown_option_mapping",
            joinColumns = {@JoinColumn(name = "completed_form_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "dropdown_id")
    private Map<DropdownQuestionEntity, OptionEntity> answeredDropdownQuestions;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "line_scale_option_mapping",
            joinColumns = {@JoinColumn(name = "completed_form_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "line_scale_id")
    private Map<LineScaleQuestionEntity, OptionEntity> answeredLineScaleQuestions;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "long_answer_option_mapping",
            joinColumns = {@JoinColumn(name = "completed_form_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "long_answer_id")
    private Map<LongAnswerQuestionEntity, AnswerEntity> answeredLongAnswerQuestions;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "multiple_choice_option_mapping",
            joinColumns = {@JoinColumn(name = "completed_form_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "multiple_choice_id")
    private Map<MultipleChoiceQuestionEntity, OptionEntity> answeredMultipleChoiceQuestions;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "short_answer_option_mapping",
            joinColumns = {@JoinColumn(name = "completed_form_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "short_answer_id")
    private Map<ShortAnswerQuestionEntity, AnswerEntity> answeredShortAnswerQuestions;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "single_choice_option_mapping",
            joinColumns = {@JoinColumn(name = "completed_form_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "single_choice_id")
    private Map<SingleChoiceQuestionEntity, OptionEntity> answeredSingleChoiceQuestions;
}