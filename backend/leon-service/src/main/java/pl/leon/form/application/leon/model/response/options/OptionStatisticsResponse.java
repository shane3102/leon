package pl.leon.form.application.leon.model.response.options;

import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionStatisticsResponse {
    private Long id;
    private String content;
    private Long count;
    @Percentage
    private double percentageOfAnswers;
}
