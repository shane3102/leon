import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-single-choice-bad-ui-good-ux',
  templateUrl: './single-choice-bad-ui-good-ux.component.html',
  styleUrls: ['./single-choice-bad-ui-good-ux.component.css']
})
export class SingleChoiceBadUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
