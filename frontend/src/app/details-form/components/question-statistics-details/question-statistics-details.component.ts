import { Component, Input, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { QuestionStatisticsResponse } from '../../models/question-statistics-response';
import { faCaretDown, faCaretUp } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-question-statistics-details',
  templateUrl: './question-statistics-details.component.html',
  styleUrls: ['./question-statistics-details.component.css']
})
export class QuestionStatisticsDetailsComponent implements OnInit {

  @Input() questionStatistics: QuestionStatisticsResponse;

  openQuestionTypes: string[] = ["DROPDOWN", "LINE_SCALE", "MULTIPLE_CHOICE", "SINGLE_CHOICE"]

  expand: Observable<boolean> = of(false)
  faCaretDown = faCaretDown
  faCaretUp = faCaretUp

  constructor() { }

  ngOnInit(): void {
  }

  changeExpand(){
    this.expand.subscribe(ex => {
      this.expand = of(!ex)
    })
  }

}
