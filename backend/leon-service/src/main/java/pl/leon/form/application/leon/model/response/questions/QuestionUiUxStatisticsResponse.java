package pl.leon.form.application.leon.model.response.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.both.questions.type.QuestionType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUiUxStatisticsResponse {
    private QuestionType questionType;
    private double averageTimeToAnswerSingleQuestion;
    private long questionCount;
}
