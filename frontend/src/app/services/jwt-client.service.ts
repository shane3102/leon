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

  private generateToken(request: LoginAttempt): Observable<JwtToken> {
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

  public login(request: LoginAttempt): boolean {
    this.generateToken(request)
      .pipe(untilDestroyed(this))
      .subscribe(
        res => {
          if (res.token !== undefined) {
            localStorage.setItem('token', res.token)
          }
        },
        error => {
          console.log("siema")
        },
      );

    return this.isLogged()
  }

  public isLogged(): boolean {
    return localStorage.getItem('token') !== null;
  }
}
