import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { FormAddModule } from "../form-add.module";

@Injectable({
    providedIn: 'root'
})
export class AddNewFormService {
    readonly PATH: string = "api/form"

    constructor(private http: HttpClient) { }

    public addNewForm(addFormForm: any) {
        return this.http.post((this.PATH + "/add"), addFormForm);
    }
}