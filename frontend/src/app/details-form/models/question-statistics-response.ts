import { QuestionType } from "src/app/models/question-types";
import { AnswerResponse } from "./answer-response";
import { OptionStatisticsResponse } from "./option-statistics-response";

export class QuestionStatisticsResponse{
    id: number;
    question: string;
    options: OptionStatisticsResponse[];
    questionType: QuestionType;
    countAnswers: number;
    answers: AnswerResponse[];
}