import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UntilDestroy } from "@ngneat/until-destroy";
import { untilDestroyed } from "@ngneat/until-destroy/lib/until-destroyed";
import { Observable } from "rxjs";

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

}