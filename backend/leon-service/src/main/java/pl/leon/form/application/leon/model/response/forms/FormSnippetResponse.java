package pl.leon.form.application.leon.model.response.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormSnippetResponse {
    private Long id;
    private String title;
    private String author;
    private boolean disabled;
    private LocalDate dateTo;
    private LocalDate dateAdded;
    private boolean resultsAvailableForEveryone;
}
