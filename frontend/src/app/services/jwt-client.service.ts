import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Observable, pipe } from 'rxjs';
import { JwtToken } from '../models/jwt-token';
import { LoginAttempt } from '../models/login-attempt';

@UntilDestroy()
@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService) { }

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
    const token = localStorage.getItem('token');
    if(token != null)
      return !this.jwtHelper.isTokenExpired(token);
    else
      return false;
  }
}
