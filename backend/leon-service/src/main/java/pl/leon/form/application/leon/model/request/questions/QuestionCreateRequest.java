package pl.leon.form.application.leon.model.request.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.type.QuestionType;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateRequest {
    private Long id;
    private String question;
    private List<Option> options;
    private QuestionType type;
}
