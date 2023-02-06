import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-line-scale-bad-ui-bad-ux',
  templateUrl: './line-scale-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css','./line-scale-bad-ui-bad-ux.component.css']
})
export class LineScaleBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup

  constructor() { }

  ngOnInit(): void {
  }

}
