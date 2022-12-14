package pl.leon.form.application.leon.model.request.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.both.questions.type.QuestionType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnsweringRequest {
    private Long id;
    private OptionRequest chosenOption;
    private String answer;
    private QuestionType type;
}
