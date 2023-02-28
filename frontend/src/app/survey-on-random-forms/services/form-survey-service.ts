import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { FormSurveyRequest } from "../model/form-survey-request";

@Injectable({
    providedIn: 'root'
})
export class FormSurveyService {

    private readonly PATH: string = '/api/form-survey'

    constructor(private http: HttpClient) { }

    submitFormSurvey(request: FormSurveyRequest) {
        return this.http.post(this.PATH, request)
    }
}