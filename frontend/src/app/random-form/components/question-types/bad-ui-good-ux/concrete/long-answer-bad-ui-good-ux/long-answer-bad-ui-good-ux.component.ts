import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-long-answer-bad-ui-good-ux',
  templateUrl: './long-answer-bad-ui-good-ux.component.html',
  styleUrls: ['../../style/bad-ui-good-ux-style.css','./long-answer-bad-ui-good-ux.component.css']
})
export class LongAnswerBadUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.controls['answer']?.setValidators([Validators.required]);
  }

}
