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

    private FormRepository formRepository;

    @Override
    public void initialize(FormCompletedValidation constraintAnnotation) {
        this.formRepository = (FormRepository) ValidationContextProvider.getBean(FormRepository.class);
    }

    @Override
    public boolean isValid(FormCompletedEntity formCompletedEntity, ConstraintValidatorContext context) {

        if (formCompletedEntity.getCompletedForm() == null) {
            return true;
        }

        FormEntity formInDatabase = formRepository.getById(formCompletedEntity.getCompletedForm().getId());

        boolean result = true;
//                doCompletedFormHasSameDropdownQuestionsAsFormInDatabase(formInDatabase, formCompletedEntity, context) &&
//                        doCompletedFormHasSameLineScaleQuestionsAsFormInDatabase(formInDatabase, formCompletedEntity, context) &&
//                        doCompletedFormHasSameLongAnswerQuestionsAsFormInDatabase(formInDatabase, formCompletedEntity, context) &&
//                        doCompletedFormHasSameMultipleChoiceQuestionsAsFormInDatabase(formInDatabase, formCompletedEntity, context) &&
//                        doCompletedFormHasSameShortAnswerQuestionsAsFormInDatabase(formInDatabase, formCompletedEntity, context) &&
//                        doCompletedFormHasSameSingleChoiceQuestionsAsFormInDatabase(formInDatabase, formCompletedEntity, context);

        return result;

    }

    public boolean doCompletedFormHasSameDropdownQuestionsAsFormInDatabase(FormEntity formInDatabase, FormCompletedEntity formCompleted, ConstraintValidatorContext context) {
        boolean result =
                formInDatabase.getDropdownQuestions().size() == formCompleted.getAnsweredDropdownQuestions().size() &&
                        formCompleted
                                .getAnsweredDropdownQuestions()
                                .keySet().stream()
                                .allMatch(dropdownQuestion -> formInDatabase.getDropdownQuestions().stream()
                                        .anyMatch(persistedDropDownQuestion -> Objects.equals(dropdownQuestion.getId(), persistedDropDownQuestion.getId())));


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
                formInDatabase.getDropdownQuestions().size() == formCompleted.getAnsweredDropdownQuestions().size() &&
                        formCompleted
                                .getAnsweredLineScaleQuestions()
                                .keySet().stream()
                                .allMatch(lineScaleQuestion -> formInDatabase.getLineScaleQuestions().stream()
                                        .anyMatch(persistedLineScaleQuestion -> Objects.equals(lineScaleQuestion.getId(), persistedLineScaleQuestion.getId())));

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
                                .getAnsweredLongAnswerQuestions()
                                .keySet().stream()
                                .allMatch(longAnswerQuestion -> formInDatabase.getLongAnswerQuestions().stream()
                                        .anyMatch(persistedLongAnswerQuestion -> Objects.equals(longAnswerQuestion.getId(), persistedLongAnswerQuestion.getId())));

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
                                .getAnsweredMultipleChoiceQuestions()
                                .keySet().stream()
                                .allMatch(multipleChoiceQuestion -> formInDatabase.getMultipleChoiceQuestions().stream()
                                        .anyMatch(persistedMultipleChoiceQuestion -> Objects.equals(multipleChoiceQuestion.getId(), persistedMultipleChoiceQuestion.getId())));

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
                                .getAnsweredShortAnswerQuestions()
                                .keySet().stream()
                                .allMatch(shortAnswerQuestion -> formInDatabase.getShortAnswerQuestions().stream()
                                        .anyMatch(persistedShortAnswerQuestion -> Objects.equals(shortAnswerQuestion.getId(), persistedShortAnswerQuestion.getId())));

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
                                .getAnsweredSingleChoiceQuestions()
                                .keySet().stream()
                                .allMatch(singleChoiceQuestion -> formInDatabase.getSingleChoiceQuestions().stream()
                                        .anyMatch(persistedSingleChoiceQuestion -> Objects.equals(singleChoiceQuestion.getId(), persistedSingleChoiceQuestion.getId())));

        if (!result) {
            context.buildConstraintViolationWithTemplate(
                    "There is at least one SINGLE_CHOICE question that " +
                            "belongs to database form but doesn't belong to completed form or " +
                            "belongs to completed form but does not belong to database form");
        }
        return result;
    }
}