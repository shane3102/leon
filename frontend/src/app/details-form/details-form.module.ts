import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFormsComponent } from './components/list-forms/list-forms.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormStatisticsDetailsComponent } from './components/form-statistics-details/form-statistics-details.component';
import { QuestionStatisticsDetailsComponent } from './components/question-statistics-details/question-statistics-details.component';



@NgModule({
  declarations: [
    ListFormsComponent,
    FormStatisticsDetailsComponent,
    QuestionStatisticsDetailsComponent
  ],
  imports: [
    CommonModule,
    FontAwesomeModule,
    NgxPaginationModule
  ],
  exports: [
    ListFormsComponent,
    FormStatisticsDetailsComponent
  ]
})
export class DetailsFormModule { }
