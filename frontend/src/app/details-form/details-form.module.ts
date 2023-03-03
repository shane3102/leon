import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFormsComponent } from './components/list-forms/list-forms.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [
    ListFormsComponent
  ],
  imports: [
    CommonModule,
    FontAwesomeModule,
    NgxPaginationModule
  ],
  exports: [
    ListFormsComponent
  ]
})
export class DetailsFormModule { }
