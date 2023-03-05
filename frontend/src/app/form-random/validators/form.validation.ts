import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function maxOneOptionChosen(): ValidatorFn {
    return (c: AbstractControl): ValidationErrors | null => {
        if (c.value.length <= 1) {
            return null;
        }

        return { 'maxOneOptionChosen': { valid: false } };
    }
}