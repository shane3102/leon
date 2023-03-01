package pl.leon.form.application.leon.model.request.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormUiUxRankingRequest {
    private List<FormUiUxRequest> formsInOrder;
    private String commentOnForms;
}
