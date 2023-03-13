import { DatePipe } from "@angular/common";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class CsvService {

    readonly PATH: string = "api/csv";

    constructor(private http: HttpClient, private datePipe: DatePipe) { }

    getFormCompletedCsvReport() {
        this.http.get<any>(this.PATH + "/completed-forms", { responseType: 'blob' as 'json' }).subscribe({
            next: (response: any) => {
                const report = document.createElement('a');
                const objectUrl = URL.createObjectURL(response);
                report.href = objectUrl;
                let currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
                report.download = 'losowo-wypełnianie-formularze-' + currentDate + '.csv';
                report.click();
                URL.revokeObjectURL(objectUrl);
            },
            error: (error: HttpErrorResponse) => {
                console.log("Could not get reports " + error.message);
            }
        })
    }

    getAnsweredQuestionsCsvReport() {
        this.http.get<any>(this.PATH + "/answered-questions", { responseType: 'blob' as 'json' }).subscribe({
            next: (response: any) => {
                const report = document.createElement('a');
                const objectUrl = URL.createObjectURL(response);
                report.href = objectUrl;
                let currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
                report.download = 'losowo-wypełnianie-pytania-formularzowe-' + currentDate + '.csv';
                report.click();
                URL.revokeObjectURL(objectUrl);
            },
            error: (error: HttpErrorResponse) => {
                console.log("Could not get reports " + error.message);
            }
        })
    }

    getCompletedFormResultsById(id: number) {
        this.http.get<any>(this.PATH + "/completed-form-results/" + id, { responseType: 'blob' as 'json' }).subscribe({
            next: (response: any) => {
                const report = document.createElement('a');
                const objectUrl = URL.createObjectURL(response);
                report.href = objectUrl;
                let currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
                report.download = 'wyniki-ankiety-' + currentDate + '.csv';
                report.click();
                URL.revokeObjectURL(objectUrl);
            },
            error: (error: HttpErrorResponse) => {
                console.log("Could not get reports " + error.message);
            }
        })
    }

    getCompletedFormResultsByIdOfRandomForms(id: number) {
        this.http.get<any>(this.PATH + "/completed-form-results-random-forms/" + id, { responseType: 'blob' as 'json' }).subscribe({
            next: (response: any) => {
                const report = document.createElement('a');
                const objectUrl = URL.createObjectURL(response);
                report.href = objectUrl;
                let currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
                report.download = 'wyniki-ankiety-' + currentDate + '.csv';
                report.click();
                URL.revokeObjectURL(objectUrl);
            },
            error: (error: HttpErrorResponse) => {
                console.log("Could not get reports " + error.message);
            }
        })
    }

    getCompletedFormResultsByIdOfAllForms(id: number) {
        this.http.get<any>(this.PATH + "/completed-form-results-all/" + id, { responseType: 'blob' as 'json' }).subscribe({
            next: (response: any) => {
                const report = document.createElement('a');
                const objectUrl = URL.createObjectURL(response);
                report.href = objectUrl;
                let currentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
                report.download = 'wyniki-ankiety-' + currentDate + '.csv';
                report.click();
                URL.revokeObjectURL(objectUrl);
            },
            error: (error: HttpErrorResponse) => {
                console.log("Could not get reports " + error.message);
            }
        })
    }

}