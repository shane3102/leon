import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListFormsComponent } from './components/list-forms/list-forms.component';



@NgModule({
  declarations: [
    ListFormsComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ListFormsComponent
  ]
})
export class DetailsFormModule { }
