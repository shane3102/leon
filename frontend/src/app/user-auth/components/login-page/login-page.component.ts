import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, map, Observable, of, pipe } from 'rxjs';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  isLogging: Observable<boolean> = of(false);
  isCheckingUsername: Observable<boolean> = of(false);
  isLoggingUnsuccesful: Observable<boolean> = of(false);

  loginForm = new FormGroup({
    username: new FormControl('', {
      validators: [Validators.required],
      asyncValidators: [this.usernameExistsValidator()],
      updateOn: 'blur'
    }),
    password: new FormControl('', Validators.required)
  })

  constructor(
    private jwtClient: JwtClientService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
  }

  public login(request: any) {
    if (!this.loginForm.invalid) {
      this.isLogging = of(true);
      this.jwtClient.generateToken(request).subscribe({
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
          this.isLogging = of(false);
          this.isLoggingUnsuccesful = of(true);
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
          return (exists ? null : { usernameDoesNotExist: true })
        }),
        catchError(error => {
          this.isCheckingUsername = of(false);
          return of(null)
        })
      )
    }
  }

  public disableLoginError() {
    this.isLoggingUnsuccesful = of(false);
  }

  get getUsernameControl() {
    return this.loginForm.get('username');
  }

  get getPasswordControl() {
    return this.loginForm.get('password');
  }

}
