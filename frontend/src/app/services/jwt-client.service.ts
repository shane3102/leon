import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Observable, pipe } from 'rxjs';
import { JwtToken } from '../models/jwt-token';
import { LoginAttempt } from '../models/login-attempt';

@UntilDestroy()
@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  constructor(private http: HttpClient) { }

  public generateToken(request: LoginAttempt): Observable<JwtToken> {
    return this.http.post<JwtToken>(
      "/login",
      request,
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    );
  }

  public isLogged(): boolean {
    return localStorage.getItem('token') !== null;
  }
}
