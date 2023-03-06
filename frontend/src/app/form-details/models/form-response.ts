import { QuestionResponse } from "src/app/models/question-response";

export class FormResponse {
    id: number;
    title: string;
    subject: string;
    questions: QuestionResponse[]
}