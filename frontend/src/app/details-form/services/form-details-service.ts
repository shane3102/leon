import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Page } from "ngx-pagination";
import { Observable } from "rxjs";
import { FormSnippetResponse } from "../models/form-snippet-response";

@Injectable({
    providedIn: 'root'
})
export class FormDetailsService {
    readonly PATH: string = "api/form"

    constructor(private http: HttpClient) { }

    public listForms(page: number, size: number, sortColumn: string, sortDirection: string): Observable<any> {

        let params = new HttpParams();
        params = params.set('page', page);
        params = params.set('size', size);
        if(sortDirection!='NONE'){
            params = params.set('sort', sortColumn + ',' + sortDirection)
        }

        return this.http.get<any>(this.PATH + "/list", { params: params });
    }
}