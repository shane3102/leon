package pl.leon.form.application.leon.model.response.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormSnippetResponse {
    private Long id;
    private String subject;
    private String author;
}
