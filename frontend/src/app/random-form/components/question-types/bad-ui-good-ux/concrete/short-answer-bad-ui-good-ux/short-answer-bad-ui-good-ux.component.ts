import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-short-answer-bad-ui-good-ux',
  templateUrl: './short-answer-bad-ui-good-ux.component.html',
  styleUrls: ['./short-answer-bad-ui-good-ux.component.css']
})
export class ShortAnswerBadUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
