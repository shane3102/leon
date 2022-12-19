package pl.leon.form.application.leon.core.exceptions.bad_request.concrete;

import pl.leon.form.application.leon.core.exceptions.bad_request.BadRequestException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.CHOSEN_OPTION_WAS_NOT_FOUND_IN_AVAILABLE_OPTIONS;

public class ChosenOptionWasNotFoundInAvailableOptions extends BadRequestException {
    public ChosenOptionWasNotFoundInAvailableOptions() {
        super(CHOSEN_OPTION_WAS_NOT_FOUND_IN_AVAILABLE_OPTIONS.getMessage());
    }
}
