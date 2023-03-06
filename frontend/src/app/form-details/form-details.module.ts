import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFormsComponent } from './components/list-forms/list-forms.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormStatisticsDetailsComponent } from './components/form-statistics-details/form-statistics-details.component';
import { QuestionStatisticsDetailsComponent } from './components/question-statistics-details/question-statistics-details.component';
import { ClosedQuestionDetailsComponent } from './components/closed-question-details/closed-question-details.component';
import { OpenQuestionDetailsComponent } from './components/open-question-details/open-question-details.component';
import { FormRandomModule } from '../form-random/form-random.module';
import { FormFillSingleComponent } from './components/form-fill-single/form-fill-single.component';



@NgModule({
  declarations: [
    ListFormsComponent,
    FormStatisticsDetailsComponent,
    QuestionStatisticsDetailsComponent,
    ClosedQuestionDetailsComponent,
    OpenQuestionDetailsComponent,
    FormFillSingleComponent
  ],
  imports: [
    CommonModule,
    FontAwesomeModule,
    NgxPaginationModule,
    FormRandomModule
  ],
  exports: [
    ListFormsComponent,
    FormStatisticsDetailsComponent,
    FormFillSingleComponent
  ]
})
export class FormDetailsModule { }
