package pl.leon.form.application.leon.core.exceptions.unauthorized.concrete;

import pl.leon.form.application.leon.core.exceptions.unauthorized.UnauthorizedException;

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.STATISTICS_NOT_AVAILABLE_TO_USER;

public class StatisticsNotAvailableToUser extends UnauthorizedException {
    public StatisticsNotAvailableToUser() {
        super(STATISTICS_NOT_AVAILABLE_TO_USER.getMessage());
    }
}
