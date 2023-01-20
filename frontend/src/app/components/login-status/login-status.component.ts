import { Component, OnInit } from '@angular/core';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  constructor(private jwtService: JwtClientService, private userService: UserService) { }

  ngOnInit(): void {
  }

  public isLogged(): boolean {
    return localStorage.getItem('token') !== null
  }

  public logout() {
    localStorage.clear();
  }

  public getUsername(): string | null {
    return "Zalogowano jako: " + localStorage.getItem('username')
  }

}
