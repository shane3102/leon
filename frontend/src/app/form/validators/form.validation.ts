import { AbstractControl, FormControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function minQuestionCount(min: number): ValidatorFn {
    return (c: AbstractControl): ValidationErrors | null => {
        if (c.value.length >= min)
            return null;

        return { 'minQuestionCount': { valid: false } };
    }
}

export function whiteSpaceOfEmpty(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        if ((control.value || '').trim().length === 0) {
            return { 'whiteSpaceOfEmpty': true }
        }
        return null;
    }
}

export function noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { 'whitespace': true };
}