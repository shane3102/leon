export class FormChangeSubject {
    id: number;
    startedFillingQuestion: number;
    finishedFillingQuestion: number;
    startedFillingForm: number;

    constructor(id: number | undefined, userStartedFillingQuestion: number, userFinishedFillingQuestion: number, formStartedFilling: number) {
        if (id != undefined) {
            this.id = id;
        }
        this.startedFillingQuestion = userStartedFillingQuestion;
        this.finishedFillingQuestion = userFinishedFillingQuestion;
        this.startedFillingForm = formStartedFilling;
    }
}