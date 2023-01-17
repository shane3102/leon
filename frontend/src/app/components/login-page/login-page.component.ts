import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import jwtDecode from 'jwt-decode';
import { LoginAttempt } from 'src/app/models/login-attempt';
import { JwtClientService } from 'src/app/services/jwt-client.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })

  constructor(private jwtClient: JwtClientService) { }

  ngOnInit(): void {
  }

  public login(request: any) {
    console.log("SIema");
    let loginSuccesful: boolean = this.jwtClient.login(request);
  }

  get getUsername() {
    return this.loginForm.get('username');
  }

  get getPassword() {
    return this.loginForm.get('password');
  }

}
