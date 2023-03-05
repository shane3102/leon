import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { FormToCompleteResponse } from "../models/form-to-complete-response";
import { FormRandomModule } from "../form-random.module";

@Injectable({
    providedIn: 'root'
})
export class RandomFormService {
    readonly PATH: string = "api/form"

    constructor(private http: HttpClient) { }

    public getRandomForm(questionCount: number): Observable<FormToCompleteResponse> {
        let params = new HttpParams();
        params = params.set('question-count', questionCount);

        return this.http.get<FormToCompleteResponse>((this.PATH + "/get-random-form"), { params: params });
    }

    public getEachRandomForm(formCount: number, questionCount: number): Observable<FormToCompleteResponse[]> {
        let params = new HttpParams();
        params = params.set('question-count', questionCount);
        params = params.append('form-count', formCount);

        return this.http.get<FormToCompleteResponse[]>((this.PATH + "/get-each-random-form"), { params: params });
    }

    public submitRandomForm(completedRandomForm: any) {
        return this.http.post(this.PATH + "/submit", completedRandomForm)
    }

}