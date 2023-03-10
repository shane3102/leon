import { Component, Input, OnInit } from '@angular/core';
import { QuestionTypes } from 'src/app/models/question-types';
import { FormUiUxTypeRandomTimeStatisticsResponse } from '../../models/form-ui-ux-type-random-time-statistics-response';

@Component({
  selector: 'app-form-random-times-table',
  templateUrl: './form-random-times-table.component.html',
  styleUrls: ['./form-random-times-table.component.css']
})
export class FormRandomTimesTableComponent implements OnInit {

  @Input() timesStatistics: FormUiUxTypeRandomTimeStatisticsResponse;

  constructor() { }

  ngOnInit(): void {
  }

  getNameBySent(sent: string) {
    switch (sent) {
      case "DROPDOWN":
        return "Lista rozwijalna";
      case "LINE_SCALE":
        return "Skala liniowa";
      case "LONG_ANSWER":
        return "Długa odpowiedź";
      case "MULTIPLE_CHOICE":
        return "Wielokrotny wybór";
      case "SHORT_ANSWER":
        return "Krótka odpowiedź";
      case "SINGLE_CHOICE":
        return "Jednokrotny wybór";
      default:
        break;
    }
    return undefined;
  }

  millisToMinutesAndSeconds(millis: number) {
    var minutes = Math.floor(millis / 60000);
    var seconds = ((millis % 60000) / 1000).toFixed(0);
    return minutes + ":" + (+seconds < 10 ? '0' : '') + seconds;
  }

}
