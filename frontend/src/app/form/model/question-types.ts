export class QuestionTypes {
    public DROPDOWN: QuestionType = { sent: "DROPDOWN", name: "Lista rozwijalna" };
    public LINE_SCALE: QuestionType = { sent: "LINE_SCALE", name: "Skala liniowa" };
    public LONG_ANSWER: QuestionType = { sent: "LONG_ANSWER", name: "Długa odpowiedź" };
    public MULTIPLE_CHOICE: QuestionType = { sent: "MULTIPLE_CHOICE", name: "Wielokrotny wybór" };
    public SHORT_ANSWER: QuestionType = { sent: "SHORT_ANSWER", name: "Krótka odpowiedź" };
    public SINGLE_CHOICE: QuestionType = { sent: "SINGLE_CHOICE", name: "Jednokrotny wybór" };

    public types: QuestionType[] = [
        this.DROPDOWN, 
        this.LINE_SCALE, 
        this.LONG_ANSWER, 
        this.MULTIPLE_CHOICE, 
        this.SHORT_ANSWER, 
        this.SINGLE_CHOICE
    ];
}

class QuestionType{
    sent: string;
    name: string;
}