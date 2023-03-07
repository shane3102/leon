package pl.leon.form.application.leon.model.both;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormCompleted {
    private Long completedFormId;
    private List<QuestionAnswering> answers;
    private FormLevelType uxLevel;
    private FormLevelType uiLevel;
    private Long completeDurationInMilliseconds;
}
