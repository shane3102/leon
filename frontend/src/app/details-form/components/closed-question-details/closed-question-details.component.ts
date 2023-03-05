import { Component, Input, OnInit } from '@angular/core';
import { QuestionStatisticsResponse } from '../../models/question-statistics-response';

@Component({
  selector: 'app-closed-question-details',
  templateUrl: './closed-question-details.component.html',
  styleUrls: ['./closed-question-details.component.css']
})
export class ClosedQuestionDetailsComponent implements OnInit {

  @Input() questionStatistics: QuestionStatisticsResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
