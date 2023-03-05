import { Component, Input, OnInit } from '@angular/core';
import { QuestionStatisticsResponse } from '../../models/question-statistics-response';

@Component({
  selector: 'app-open-question-details',
  templateUrl: './open-question-details.component.html',
  styleUrls: ['./open-question-details.component.css']
})
export class OpenQuestionDetailsComponent implements OnInit {

  @Input() questionStatistics: QuestionStatisticsResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
