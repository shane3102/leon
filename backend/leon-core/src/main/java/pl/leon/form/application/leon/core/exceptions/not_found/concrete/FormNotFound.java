package pl.leon.form.application.leon.core.exceptions.not_found.concrete;

import pl.leon.form.application.leon.core.exceptions.not_found.NotFoundException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.FORM_NOT_FOUND;

public class FormNotFound extends NotFoundException {
    public FormNotFound() {
        super(FORM_NOT_FOUND.getMessage());
    }
}
