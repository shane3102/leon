package pl.leon.form.application.leon.model.response.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.core.enums.FormLevelType;
import pl.leon.form.application.leon.model.response.questions.QuestionUiUxStatisticsResponse;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormUiUxTypeRandomTimeStatisticsResponse {
    private FormLevelType uiLevel;
    private FormLevelType uxLevel;
    private List<QuestionUiUxStatisticsResponse> averageTimeToAnswerPerQuestionType;
    private long answerCount;
}
