package pl.leon.form.application.leon.model.both.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.model.both.forms.FormUiUx;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormUiUxRanking {
    private List<FormUiUx> formsInOrder;
    private String commentOnForms;
}
