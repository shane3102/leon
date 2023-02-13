import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-multiple-choice-bad-ui-good-ux',
  templateUrl: './multiple-choice-bad-ui-good-ux.component.html',
  styleUrls: ['../../style/bad-ui-good-ux-style.css','../../style/bad-ui-good-ux-style.css','./multiple-choice-bad-ui-good-ux.component.css']
})
export class MultipleChoiceBadUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
