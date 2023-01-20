import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UntilDestroy } from "@ngneat/until-destroy";
import { untilDestroyed } from "@ngneat/until-destroy/lib/until-destroyed";
import { Observable } from "rxjs";
import { Principal } from "../models/principal";
import { RegistrationForm } from "../models/registration-form";

@UntilDestroy()
@Injectable({
    providedIn: 'root'
})
export class UserService {

    readonly PATH: string = "api/user";

    constructor(private http: HttpClient) { }

    public existsUserByUsername(username: string): Observable<boolean> {

        let params = new HttpParams();
        params = params.set('username', username);


        return this.http.get<boolean>((this.PATH + "/exists"), { params: params });
    }

    public registerUser(registrationForm: RegistrationForm) {
        return this.http.post((this.PATH + "/register"), registrationForm);
    }

    public getCurrentLoggedUser(): Observable<Principal> {
        return this.http.get<Principal>(this.PATH);
    }

}