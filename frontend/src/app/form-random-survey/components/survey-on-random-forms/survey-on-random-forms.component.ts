import { CdkDragDrop, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
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

  @Input() forms: FormUiUxRequest[];

  triedSubmitingWithoutRankingForms: Observable<boolean> = of(false);
  commentOnForms: string = "";
  formsLength: void[];
  responseFormUiUxOrder: Map<number, FormUiUxRequest> = new Map();

  constructor(private formSurveyService: FormSurveyService, private router: Router) { }

  ngOnInit(): void {
    this.formsLength = this.forms.map(() => { });
  }

  onDropBack(event: CdkDragDrop<FormUiUxRequest[]>) {
    transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex)
    this.forms.sort((e1, e2) => e1.formNumber - e2.formNumber)

  }

  onPlaced(emmitedForm: EmmittForm) {
    this.responseFormUiUxOrder.set(emmitedForm.place, emmitedForm.form);
  }

  submit() {

    let orderOfForms = Array.from(new Map([...this.responseFormUiUxOrder].sort((a, b) => a[0] - b[0]))).map(el => el[1]);

    if (orderOfForms.length < 4 || this.forms.length > 0) {
      this.triedSubmitingWithoutRankingForms = of(true);
      setTimeout(() => { this.triedSubmitingWithoutRankingForms = of(false) }, 2000)
    } else {
      let formSurveyRequest: FormSurveyRequest = {
        'formsInOrder': orderOfForms,
        'commentOnForms': this.commentOnForms
      }

      this.formSurveyService.submitFormSurvey(formSurveyRequest).subscribe({
        next: () => { this.router.navigateByUrl('main-page'); }
      })
    }
  }

}
