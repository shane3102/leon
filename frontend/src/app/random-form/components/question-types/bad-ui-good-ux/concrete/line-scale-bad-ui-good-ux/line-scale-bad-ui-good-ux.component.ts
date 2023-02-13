import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-line-scale-bad-ui-good-ux',
  templateUrl: './line-scale-bad-ui-good-ux.component.html',
  styleUrls: ['./line-scale-bad-ui-good-ux.component.css']
})
export class LineScaleBadUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
