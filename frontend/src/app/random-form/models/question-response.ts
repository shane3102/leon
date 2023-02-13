import { QuestionType, QuestionTypes } from "src/app/models/question-types";
import { OptionResponse } from "./option-response";

export class QuestionResponse {
    id: number;
    question: string;
    options: OptionResponse[];
    type: string;
}