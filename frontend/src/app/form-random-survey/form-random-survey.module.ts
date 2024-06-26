import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SurveyOnRandomFormsComponent } from './components/survey-on-random-forms/survey-on-random-forms.component';
import { FormPanelRepresentationComponent } from './components/form-panel-representation/form-panel-representation.component';
import { FormAddModule } from '../form-add/form-add.module';
import { ReactiveFormsModule } from '@angular/forms';
import { DragDropModule } from "@angular/cdk/drag-drop";
import { FormPlaceComponent } from './components/form-place/form-place.component'
import { AutosizeModule } from 'ngx-autosize';

@NgModule({
  declarations: [
    SurveyOnRandomFormsComponent,
    FormPanelRepresentationComponent,
    FormPlaceComponent
  ],
  imports: [
    CommonModule,
    FormAddModule,
    ReactiveFormsModule,
    DragDropModule,
    AutosizeModule
  ],
  exports:[
    SurveyOnRandomFormsComponent
  ]
})
export class FormRandomSurveyModule { }
