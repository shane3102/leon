import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { Observable, of } from 'rxjs';
import { LoginAttempt } from 'src/app/models/login-attempt';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  isLogging: Observable<boolean> = of(false);
  isLoggingUnsuccesful: Observable<boolean> = of(false);

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })

  constructor(
    private jwtClient: JwtClientService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
  }

  public login(request: any) {
    this.isLogging = of(true);
    let loginSuccesful: boolean = this.jwtClient.login(request);
    console.log(loginSuccesful);
    setTimeout(() => {
      console.log("siema1");
      this.isLogging = of(false);
      console.log("siem2");
      if (loginSuccesful) {
        console.log("siema3");
        this.router.navigateByUrl('/main-page');
      }
    }, 0);
    console.log(loginSuccesful);
  }

  get getUsernameControl() {
    return this.loginForm.get('username');
  }

  get getPasswordControl() {
    return this.loginForm.get('password');
  }

}
