package pl.leon.form.application.leon.model.response.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormResponse {
    private Long id;
    private LocalDate dateTo;
    private boolean disableQuestionsAfterDateTo;
    private List<QuestionResponse> questions;
}
