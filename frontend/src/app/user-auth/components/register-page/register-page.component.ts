import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { untilDestroyed } from '@ngneat/until-destroy';
import { catchError, map, Observable, of } from 'rxjs';
import { RegistrationForm } from 'src/app/models/registration-form';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { UserService } from 'src/app/services/user.service';
import { passwordsEqual } from 'src/app/validators/registration.validation';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  isRegistering: Observable<boolean> = of(false);
  isCheckingUsername: Observable<boolean> = of(false);

  registerForm = this.formBuilder.group(
    {
      username: new FormControl('', {
        validators: [Validators.required],
        asyncValidators: [this.usernameExistsValidator()],
        updateOn: 'blur'
      }),
      password: new FormControl('', Validators.required),
      passwordConfirmation: new FormControl('', [Validators.required])
    },
    { validators: passwordsEqual('password', 'passwordConfirmation') }
  )

  constructor(
    private jwtClient: JwtClientService,
    private userService: UserService,
    private router: Router,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  public register(request: any) {
    if (!this.registerForm.invalid) {
      this.isRegistering = of(true);
      this.userService.registerUser(request).subscribe({
        
        next: () => {
          this.jwtClient.generateToken({'username': request.username, 'password': request.password}).subscribe({
            next: (res) => {
              if (res.token !== undefined) {
                localStorage.setItem('token', res.token);
                this.userService.getCurrentLoggedUser().subscribe(
                  res =>{
                    localStorage.setItem('username', res.name)
                    this.router.navigateByUrl('/main-page');
                  }
                );
              }
            },
            error: error => {
              this.isRegistering = of(false);
            }
          });
        },
        error: error => {
          this.isRegistering = of(false);
        }
      });
    }
  }

  usernameExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      this.isCheckingUsername = of(true);
      return this.userService.existsUserByUsername(control.value).pipe(
        map(exists => {
          this.isCheckingUsername = of(false);
          return (exists ? { usernameExist: true } : null)
        }),
        catchError(error => {
          this.isCheckingUsername = of(false);
          return of(null)
        })
      )
    }
  }

  passwordsEqualValidator(passwordControlName: string, passwordConfirmationControlName: string): ValidatorFn {
    return (controls: AbstractControl): ValidationErrors | null => {
      const passwordControl = controls.get(passwordControlName);
      const passwordConfirmationControl = controls.get(passwordConfirmationControlName);

      return passwordControl?.value === passwordConfirmationControl?.value ? { passwordsNotEqual: true } : { passwordsNotEqual: true };
    }
  }

  get getUsernameControl() {
    return this.registerForm.get('username');
  }

  get getPasswordControl() {
    return this.registerForm.get('password');
  }

  get getPasswordConfirmationControl() {
    return this.registerForm.get('passwordConfirmation');
  }

}