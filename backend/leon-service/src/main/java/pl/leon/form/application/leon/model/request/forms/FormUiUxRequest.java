package pl.leon.form.application.leon.model.request.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.core.enums.FormLevelType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormUiUxRequest {
    private FormLevelType uxLevel;
    private FormLevelType uiLevel;
}
