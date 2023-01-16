import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtToken } from '../models/jwt-token';
import { LoginAttempt } from '../models/login-attempt';

@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  constructor(private http: HttpClient) { }

  public generateToken(request: LoginAttempt): Observable<JwtToken> {
    return this.http.post<JwtToken>("/login", request);
  }

  public login(request: LoginAttempt) {
    this.generateToken(request)
      .subscribe(
        res => {
          localStorage.setItem('token', res.token)
        }
      )
  }
}
