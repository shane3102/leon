import { QuestionUiUxStatisticsResponse } from "./question-ui-ux-statistics-response";

export class FormUiUxTypeRandomTimeStatisticsResponse {
    uiLevel: string;
    uxLevel: string;
    averageTimeToAnswerPerQuestionType: QuestionUiUxStatisticsResponse[];
    averageTimeToFillForm: number;
    answerCount: number;
}