import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { QuestionTypes } from 'src/app/models/question-types';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-question-bad-ui-bad-ux',
  templateUrl: './question-bad-ui-bad-ux.component.html',
  styleUrls: ['./question-bad-ui-bad-ux.component.css']
})
export class QuestionBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() formGroup: FormGroup

  questionTypes = new QuestionTypes();

  constructor() { }

  ngOnInit(): void {
  }

}
