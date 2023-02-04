import { Component, Input, OnInit } from '@angular/core';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-long-answer-bad-ui-bad-ux',
  templateUrl: './long-answer-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css','./long-answer-bad-ui-bad-ux.component.css']
})
export class LongAnswerBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
