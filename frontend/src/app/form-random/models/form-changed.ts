export class FormChanged {
    id: number;
    userFilled: number;

    constructor(id: number, userFilled: number) {
        this.id = id;
        this.userFilled = userFilled;
    }
}