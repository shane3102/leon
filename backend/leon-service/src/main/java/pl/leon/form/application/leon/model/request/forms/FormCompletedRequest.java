package pl.leon.form.application.leon.model.request.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.request.questions.QuestionAnsweringRequest;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormCompletedRequest {
    private Long completedFormId;
    private List<QuestionAnsweringRequest> answers;
}
