package pl.leon.form.application.leon.model.both.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.type.QuestionType;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswering {
    private Long id;
    private Set<Option> chosenOptions;
    private String answer;
    private QuestionType type;
}
