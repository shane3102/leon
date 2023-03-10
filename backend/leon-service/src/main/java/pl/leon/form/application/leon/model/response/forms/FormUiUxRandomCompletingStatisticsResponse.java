package pl.leon.form.application.leon.model.response.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.leon.form.application.leon.core.enums.FormLevelType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormUiUxRandomCompletingStatisticsResponse {
    private FormLevelType uiLevel;
    private FormLevelType uxLevel;
    private int firstPlaceCount;
    private int secondPlaceCount;
    private int thirdPlaceCount;
    private int fourthPlaceCount;

    public FormUiUxRandomCompletingStatisticsResponse(FormLevelType uiLevel, FormLevelType uxLevel) {
        this.uiLevel = uiLevel;
        this.uxLevel = uxLevel;
    }

    public void incrementPlaceByIndex(int index) {
        switch (index) {
            case 0:
                firstPlaceCount++;
                break;
            case 1:
                secondPlaceCount++;
                break;
            case 2:
                thirdPlaceCount++;
                break;
            case 3:
                fourthPlaceCount++;
                break;
            default:
                break;
        }
    }
}
