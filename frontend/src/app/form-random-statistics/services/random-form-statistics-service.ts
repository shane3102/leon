import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { FormUiUxRandomCompletingStatisticsResponse } from "../models/form-ui-ux-random-completing-statistics-response";
import { FormUiUxTypeRandomTimeStatisticsResponse } from "../models/form-ui-ux-type-random-time-statistics-response";

@Injectable({
    providedIn: 'root'
})
export class RandomFormStatisticsService {
    readonly PATH: string = "/api/form/statistics"

    constructor(private http: HttpClient) { }

    public getFormUiUxRandomCompletingStatisticsResponse(): Observable<FormUiUxRandomCompletingStatisticsResponse[]> {
        return this.http.get<FormUiUxRandomCompletingStatisticsResponse[]>(this.PATH + "/random-forms/rankings");
    }

    public getFillingRandomFormTimes(): Observable<FormUiUxTypeRandomTimeStatisticsResponse[]> {
        return this.http.get<FormUiUxTypeRandomTimeStatisticsResponse[]>(this.PATH + "/random-forms/times-to-answer");
    }

}