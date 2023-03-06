import { OptionResponse } from "../form-random/models/option-response";

export class QuestionResponse {
    id: number;
    question: string;
    options: OptionResponse[];
    type: string;
}