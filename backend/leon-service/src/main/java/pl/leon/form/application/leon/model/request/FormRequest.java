package pl.leon.form.application.leon.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.request.questions.QuestionRequest;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormRequest {
    private Long id;
    private LocalDate dateTo;
    private boolean disableQuestionsAfterDateTo;
    private List<QuestionRequest> questions;
}
