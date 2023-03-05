package pl.leon.form.application.leon.model.response.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.response.questions.QuestionStatisticsResponse;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormStatisticsResponse {
    private Long id;
    private String title;
    private String subject;
    private LocalDate dateTo;
    private LocalDate dateAdded;
    private boolean disabled;
    private boolean resultsAvailableForEveryone;
    private List<QuestionStatisticsResponse> questions;
}
