import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFormsComponent } from './components/list-forms/list-forms.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';



@NgModule({
  declarations: [
    ListFormsComponent
  ],
  imports: [
    CommonModule,
    FontAwesomeModule
  ],
  exports: [
    ListFormsComponent
  ]
})
export class DetailsFormModule { }
