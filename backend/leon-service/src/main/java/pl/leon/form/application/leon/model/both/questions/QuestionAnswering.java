package pl.leon.form.application.leon.model.both.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.type.QuestionType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswering {
    private Long id;
    private Option chosenOption;
    private String answer;
    private QuestionType type;
}
