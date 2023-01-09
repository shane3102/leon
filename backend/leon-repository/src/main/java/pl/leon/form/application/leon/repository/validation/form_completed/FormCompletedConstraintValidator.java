package pl.leon.form.application.leon.repository.validation.form_completed;

import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.validation.ValidationContextProvider;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Service
public class FormCompletedConstraintValidator implements ConstraintValidator<FormCompletedValidation, FormCompletedEntity> {

    @Override
    public boolean isValid(FormCompletedEntity formCompletedEntity, ConstraintValidatorContext context) {

        if (formCompletedEntity.getCompletedForm() == null) {
            return true;
        }

        boolean result = doCompletedFormHasSameDropdownQuestionsAsFormInDatabase(formCompletedEntity.getCompletedForm(), formCompletedEntity, context) &&
                doCompletedFormHasSameLineScaleQuestionsAsFormInDatabase(formCompletedEntity.getCompletedForm(), formCompletedEntity, context) &&
                doCompletedFormHasSameLongAnswerQuestionsAsFormInDatabase(formCompletedEntity.getCompletedForm(), formCompletedEntity, context) &&
                doCompletedFormHasSameMultipleChoiceQuestionsAsFormInDatabase(formCompletedEntity.getCompletedForm(), formCompletedEntity, context) &&
                doCompletedFormHasSameShortAnswerQuestionsAsFormInDatabase(formCompletedEntity.getCompletedForm(), formCompletedEntity, context) &&
                doCompletedFormHasSameSingleChoiceQuestionsAsFormInDatabase(formCompletedEntity.getCompletedForm(), formCompletedEntity, context);

        return result;

    }

    public boolean doCompletedFormHasSameDropdownQuestionsAsFormInDatabase(FormEntity formInDatabase, FormCompletedEntity formCompleted, ConstraintValidatorContext context) {
        boolean result =
                formInDatabase.getDropdownQuestions().size() == formCompleted.getAnsweredDropdownQuestions().size() &&
                        formCompleted
                                .getAnsweredDropdownQuestions().stream()
                                .allMatch(dropdownQuestion -> formInDatabase.getDropdownQuestions().stream()
                                        .anyMatch(persistedDropDownQuestion -> Objects.equals(dropdownQuestion.getQuestion().getId(), persistedDropDownQuestion.getId())));


        if (!result) {
            context.buildConstraintViolationWithTemplate(
                    "There is at least one DROPDOWN question that " +
                            "belongs to database form but doesn't belong to completed form or " +
                            "belongs to completed form but does not belong to database form");
        }
        return result;
    }

    public boolean doCompletedFormHasSameLineScaleQuestionsAsFormInDatabase(FormEntity formInDatabase, FormCompletedEntity formCompleted, ConstraintValidatorContext context) {
        boolean result =
                formInDatabase.getLineScaleQuestions().size() == formCompleted.getAnsweredLineScaleQuestions().size() &&
                        formCompleted
                                .getAnsweredLineScaleQuestions().stream()
                                .allMatch(lineScaleQuestion -> formInDatabase.getLineScaleQuestions().stream()
                                        .anyMatch(persistedLineScaleQuestion -> Objects.equals(lineScaleQuestion.getQuestion().getId(), persistedLineScaleQuestion.getId())));


        if (!result) {
            context.buildConstraintViolationWithTemplate(
                    "There is at least one LINE_SCALE question that " +
                            "belongs to database form but doesn't belong to completed form or " +
                            "belongs to completed form but does not belong to database form");
        }
        return result;
    }

    public boolean doCompletedFormHasSameLongAnswerQuestionsAsFormInDatabase(FormEntity formInDatabase, FormCompletedEntity formCompleted, ConstraintValidatorContext context) {

        boolean result =
                formInDatabase.getLongAnswerQuestions().size() == formCompleted.getAnsweredLongAnswerQuestions().size() &&
                        formCompleted
                                .getAnsweredLongAnswerQuestions().stream()
                                .allMatch(longAnswerQuestion -> formInDatabase.getLongAnswerQuestions().stream()
                                        .anyMatch(persistedLongAnswerQuestion -> Objects.equals(longAnswerQuestion.getQuestion().getId(), persistedLongAnswerQuestion.getId())));


        if (!result) {
            context.buildConstraintViolationWithTemplate(
                    "There is at least one LONG_ANSWER question that " +
                            "belongs to database form but doesn't belong to completed form or " +
                            "belongs to completed form but does not belong to database form");
        }
        return result;
    }

    public boolean doCompletedFormHasSameMultipleChoiceQuestionsAsFormInDatabase(FormEntity formInDatabase, FormCompletedEntity formCompleted, ConstraintValidatorContext context) {
        boolean result =
                formInDatabase.getMultipleChoiceQuestions().size() == formCompleted.getAnsweredMultipleChoiceQuestions().size() &&
                        formCompleted
                                .getAnsweredMultipleChoiceQuestions().stream()
                                .allMatch(multipleChoiceQuestion -> formInDatabase.getMultipleChoiceQuestions().stream()
                                        .anyMatch(persistedMultipleChoiceQuestion -> Objects.equals(multipleChoiceQuestion.getQuestion().getId(), persistedMultipleChoiceQuestion.getId())));


        if (!result) {
            context.buildConstraintViolationWithTemplate(
                    "There is at least one MULTIPLE_CHOICE question that " +
                            "belongs to database form but doesn't belong to completed form or " +
                            "belongs to completed form but does not belong to database form");
        }
        return result;
    }

    public boolean doCompletedFormHasSameShortAnswerQuestionsAsFormInDatabase(FormEntity formInDatabase, FormCompletedEntity formCompleted, ConstraintValidatorContext context) {
        boolean result =
                formInDatabase.getShortAnswerQuestions().size() == formCompleted.getAnsweredShortAnswerQuestions().size() &&
                        formCompleted
                                .getAnsweredShortAnswerQuestions().stream()
                                .allMatch(shortAnswerQuestion -> formInDatabase.getShortAnswerQuestions().stream()
                                        .anyMatch(persistedShortAnswerQuestion -> Objects.equals(shortAnswerQuestion.getQuestion().getId(), persistedShortAnswerQuestion.getId())));


        if (!result) {
            context.buildConstraintViolationWithTemplate(
                    "There is at least one SHORT_ANSWER question that " +
                            "belongs to database form but doesn't belong to completed form or " +
                            "belongs to completed form but does not belong to database form");
        }
        return result;
    }

    public boolean doCompletedFormHasSameSingleChoiceQuestionsAsFormInDatabase(FormEntity formInDatabase, FormCompletedEntity formCompleted, ConstraintValidatorContext context) {
        boolean result =
                formInDatabase.getSingleChoiceQuestions().size() == formCompleted.getAnsweredSingleChoiceQuestions().size() &&
                        formCompleted
                                .getAnsweredSingleChoiceQuestions().stream()
                                .allMatch(singleChoiceQuestion -> formInDatabase.getSingleChoiceQuestions().stream()
                                        .anyMatch(persistedSingleChoiceQuestion -> Objects.equals(singleChoiceQuestion.getQuestion().getId(), persistedSingleChoiceQuestion.getId())));


        if (!result) {
            context.buildConstraintViolationWithTemplate(
                    "There is at least one SINGLE_CHOICE question that " +
                            "belongs to database form but doesn't belong to completed form or " +
                            "belongs to completed form but does not belong to database form");
        }
        return result;
    }
}