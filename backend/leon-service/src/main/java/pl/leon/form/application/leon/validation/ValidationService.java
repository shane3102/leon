package pl.leon.form.application.leon.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Collection;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService<M> {

    private final Validator validator;

    public void validate(Collection<M> models) {
        log.debug("validate({})", models);
        models.forEach(this::validate);
        log.debug("validate({}) -> validation successful", models);
    }

    public void validate(M model) {
        log.info("validate({})", model == null ? null : model.getClass());
        validate(model, Default.class);
        log.info("validate({})", model == null ? null : model.getClass());
    }

    public void validate(M model, Class<?>... groups) {
        log.debug("validate({}, {})", model, groups);
        Set<ConstraintViolation<M>> violations = validator.validate(model, groups);

        if (!violations.isEmpty()) {
            String message = buildConstraintViolationMessage(violations);
            throw new ConstraintViolationException("Following constraints were violated: " + message, violations);
        }
        log.debug("validate({}, {}) -> validation successful", model, groups);
    }

    private String buildConstraintViolationMessage(Set<ConstraintViolation<M>> violations) {
        log.debug("buildConstraintViolationMessage({})", violations);
        StringBuilder messageBuilder = new StringBuilder();
        violations.stream()
                .map(ConstraintViolation::getMessage)
                .map(message -> String.format("%s; ", message))
                .forEach(messageBuilder::append);
        String message = messageBuilder.toString();
        log.debug("buildConstraintViolationMessage({}) = {}", violations, message);
        return message;
    }
}
