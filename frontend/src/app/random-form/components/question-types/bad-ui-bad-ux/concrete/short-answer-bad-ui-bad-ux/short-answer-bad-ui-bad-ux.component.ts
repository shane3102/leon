import { Component, Input, OnInit } from '@angular/core';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-short-answer-bad-ui-bad-ux',
  templateUrl: './short-answer-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css','./short-answer-bad-ui-bad-ux.component.css']
})
export class ShortAnswerBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
