import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SurveyOnRandomFormsComponent } from './components/survey-on-random-forms/survey-on-random-forms.component';
import { FormPanelRepresentationComponent } from './components/form-panel-representation/form-panel-representation.component';



@NgModule({
  declarations: [
    SurveyOnRandomFormsComponent,
    FormPanelRepresentationComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    SurveyOnRandomFormsComponent
  ]
})
export class SurveyOnRandomFormsModule { }
