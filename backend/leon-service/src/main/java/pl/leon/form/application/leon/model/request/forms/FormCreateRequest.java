package pl.leon.form.application.leon.model.request.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.request.questions.QuestionCreateRequest;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormCreateRequest {
    private Long id;
    private LocalDate dateTo;
    private boolean disableQuestionsAfterDateTo;
    private List<QuestionCreateRequest> questions;
}
