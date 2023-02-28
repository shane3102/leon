import { CdkDragDrop, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { FormUiUxRequest } from '../../../models/form-ui-ux-request';
import { EmmittForm } from '../../model/emitt-form';
import { FormSurveyRequest } from '../../model/form-survey-request';
import { FormSurveyService } from '../../services/form-survey-service';

@Component({
  selector: 'app-survey-on-random-forms',
  templateUrl: './survey-on-random-forms.component.html',
  styleUrls: ['./survey-on-random-forms.component.css']
})
export class SurveyOnRandomFormsComponent implements OnInit {

  @Input() forms: FormUiUxRequest[] = [
    { uiLevel: 'BAD', uxLevel: 'BAD', formNumber: 1 }, { uiLevel: 'BAD', uxLevel: 'GOOD', formNumber: 2 }, { uiLevel: 'GOOD', uxLevel: 'BAD', formNumber: 3 }, { uiLevel: 'GOOD', uxLevel: 'GOOD', formNumber: 4 }
  ];

  commentOnForms: string = "";

  formsLength: void[];

  responseFormUiUxOrder: Map<number, FormUiUxRequest> = new Map();

  constructor(formSurveyService: FormSurveyService) { }

  ngOnInit(): void {
    this.formsLength = this.forms.map(() => { });
  }

  onDropBack(event: CdkDragDrop<FormUiUxRequest[]>) {
    transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex)
    this.forms.sort((e1, e2) => e1.formNumber - e2.formNumber)
  }

  onPlaced(emmitedForm: EmmittForm) {
    this.responseFormUiUxOrder.set(emmitedForm.place, emmitedForm.form);
    console.log(Array.from(new Map([...this.responseFormUiUxOrder].sort((a, b) => a[0] - b[0]))).map(el => el[1]));
  }

  submit() {

    let orderOfForms = Array.from(new Map([...this.responseFormUiUxOrder].sort((a, b) => a[0] - b[0]))).map(el => el[1]);

    let formSurveyRequest: FormSurveyRequest = {
      'formsInOrder': orderOfForms,
      'commentOnForms': this.commentOnForms
    }

    console.log(formSurveyRequest);
  }

}