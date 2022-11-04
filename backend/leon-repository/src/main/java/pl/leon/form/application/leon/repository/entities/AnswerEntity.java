package pl.leon.form.application.leon.repository.entities;

import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWERS")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "short_answer_id")
    private ShortAnswerQuestionEntity shortAnswerQuestionEntity;

    @ManyToOne
    @JoinColumn(name = "long_answer_id")
    private LongAnswerQuestionEntity longAnswerQuestionEntity;
}
