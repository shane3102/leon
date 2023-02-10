import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-short-answer-bad-ui-bad-ux',
  templateUrl: './short-answer-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './short-answer-bad-ui-bad-ux.component.css']
})
export class ShortAnswerBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  private id: number;
  private type: string;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.controls['answer']?.setValidators([Validators.required]);

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;
  }

  onReset() {
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id))
      this.questionFormGroup.setControl('type', new FormControl(this.type))
    }, 0);
  }

}
