import { QuestionStatisticsResponse } from "./question-statistics-response";

export class FormStatisticsResponse {
    id: number;
    title: string;
    subject: string;
    dateTo: Date;
    dateAdded: Date;
    disabled: boolean;
    resultsAvailableForEveryone: boolean;
    disabledFormRandomFormGenerating: boolean;
    questions: QuestionStatisticsResponse[];
}