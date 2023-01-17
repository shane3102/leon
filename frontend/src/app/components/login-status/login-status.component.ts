import { Component, OnInit } from '@angular/core';
import { JwtClientService } from 'src/app/services/jwt-client.service';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  constructor(private jwtService: JwtClientService) { }

  ngOnInit(): void {
  }

  public isLogged(): boolean {
    return localStorage.getItem('token') !== null
  }

  public logout() {
    localStorage.clear();
  }

}
