package pl.leon.form.application.leon.model.response.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.response.questions.type.QuestionType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String question;
    private List<AnswerResponse> options;
//    private String answer;
    private QuestionType type;
}
