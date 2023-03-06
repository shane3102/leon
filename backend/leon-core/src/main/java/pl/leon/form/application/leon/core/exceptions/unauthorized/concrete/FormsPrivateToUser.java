package pl.leon.form.application.leon.core.exceptions.unauthorized.concrete;

import pl.leon.form.application.leon.core.exceptions.unauthorized.UnauthorizedException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.FORMS_PRIVATE_TO_USER;

public class FormsPrivateToUser extends UnauthorizedException {
    public FormsPrivateToUser() {
        super(FORMS_PRIVATE_TO_USER.getMessage());
    }
}
