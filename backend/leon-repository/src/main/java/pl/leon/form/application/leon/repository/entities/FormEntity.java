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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FORM")
public class FormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateTo;

    private boolean disableQuestionsAfterDateTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String subject;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private List<DropdownQuestionEntity> dropdownQuestions;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private List<LineScaleQuestionEntity> lineScaleQuestions;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private List<LongAnswerQuestionEntity> longAnswerQuestions;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private List<MultipleChoiceQuestionEntity> multipleChoiceQuestions;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private List<ShortAnswerQuestionEntity> shortAnswerQuestions;

    @OneToMany(mappedBy = "form", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private List<SingleChoiceQuestionEntity> singleChoiceQuestions;
}
