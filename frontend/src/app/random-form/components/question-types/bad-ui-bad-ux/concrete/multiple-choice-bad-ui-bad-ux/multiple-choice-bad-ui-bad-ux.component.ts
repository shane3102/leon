import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-multiple-choice-bad-ui-bad-ux',
  templateUrl: './multiple-choice-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css','./multiple-choice-bad-ui-bad-ux.component.css']
})
export class MultipleChoiceBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup

  constructor() { }

  ngOnInit(): void {
  }

}
