package pl.leon.form.application.leon.repository.validation.form_completed;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FormCompletedConstraintValidator.class})
public @interface FormCompletedValidation {

    String message() default "Completed form is not valid: ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
