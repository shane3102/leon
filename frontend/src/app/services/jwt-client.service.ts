import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, pipe } from 'rxjs';
import { JwtToken } from '../models/jwt-token';
import { LoginAttempt } from '../models/login-attempt';

@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  constructor(private http: HttpClient) { }

  private generateToken(request: LoginAttempt): Observable<any> {
    return this.http.post(
      "/login",
      request,
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    );
  }

  public login(request: LoginAttempt): boolean {
    this.generateToken(request)
      .subscribe(
        res => {
          localStorage.setItem('token', res.token)
        }
      );

    return this.isLogged()
  }

  public isLogged(): boolean {
    return localStorage.getItem('token') !== null;
  }
}
