import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { FormSnippetResponse } from "../models/form-snippet-response";

@Injectable({
    providedIn: 'root'
})
export class AddNewFormService {
    readonly PATH: string = "api/form"

    constructor(private http: HttpClient) { }

    public listForms() : Observable<FormSnippetResponse[]> {
        return this.http.get<FormSnippetResponse[]>(this.PATH + "/list");
    }
}