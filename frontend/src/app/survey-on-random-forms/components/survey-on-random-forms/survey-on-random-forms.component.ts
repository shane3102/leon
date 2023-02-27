import { Component, Input, OnInit } from '@angular/core';
import { FormUiUxRequest } from '../../../models/form-ui-ux-request';

@Component({
  selector: 'app-survey-on-random-forms',
  templateUrl: './survey-on-random-forms.component.html',
  styleUrls: ['./survey-on-random-forms.component.css']
})
export class SurveyOnRandomFormsComponent implements OnInit {

  @Input() forms: FormUiUxRequest[] = [
    { uiLevel:'BAD', uxLevel:'BAD', formNumber: 1 }, {uiLevel:'BAD', uxLevel:'GOOD', formNumber: 2}, {uiLevel:'GOOD', uxLevel:'BAD', formNumber: 3}, {uiLevel:'GOOD', uxLevel:'GOOD', formNumber: 4}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
