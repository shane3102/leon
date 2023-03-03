package pl.leon.form.application.leon.model.response.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.both.questions.type.QuestionType;
import pl.leon.form.application.leon.model.response.options.OptionStatisticsResponse;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticsResponse {
    private Long id;
    private String question;
    private List<OptionStatisticsResponse> options;
    private QuestionType type;
    private Long countAnswers;
}
