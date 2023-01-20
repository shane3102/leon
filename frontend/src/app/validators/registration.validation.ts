import { ValidatorFn, FormGroup, ValidationErrors, AbstractControl } from '@angular/forms';

export function passwordsEqual(password: string, passwordConfirmation: string): ValidatorFn {
    return (form: AbstractControl): ValidationErrors | null => {
        const passwordValue = form.get(password)?.value;
        const passwordConfirmationValue = form.get(passwordConfirmation)?.value;

        return passwordValue != passwordConfirmationValue ? { passwordsNotEqual: true } : null;
    };
}