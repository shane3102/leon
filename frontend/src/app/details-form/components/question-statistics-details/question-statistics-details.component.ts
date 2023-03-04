import { Component, Input, OnInit } from '@angular/core';
import { QuestionResponse } from 'src/app/random-form/models/question-response';
import { QuestionStatisticsResponse } from '../../models/question-statistics-response';

@Component({
  selector: 'app-question-statistics-details',
  templateUrl: './question-statistics-details.component.html',
  styleUrls: ['./question-statistics-details.component.css']
})
export class QuestionStatisticsDetailsComponent implements OnInit {

  @Input() questionStatistics: QuestionStatisticsResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
