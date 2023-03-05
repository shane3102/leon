import { AnswerResponse } from "./answer-response";
import { OptionStatisticsResponse } from "./option-statistics-response";

export class QuestionStatisticsResponse{
    id: number;
    question: string;
    options: OptionStatisticsResponse[];
    type: string;
    countAnswers: number;
    answers: AnswerResponse[];
}