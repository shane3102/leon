import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class FormService {
    readonly PATH: string = "api/form"

    constructor(private http: HttpClient) { }

    public addNewForm(addFormForm: any) {
        return this.http.post((this.PATH + "/add"), addFormForm);
    }
}