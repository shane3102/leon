import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddFormComponent } from './components/add-form/add-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AddQuestionComponent } from './components/add-question/add-question.component';
import { AddDropdownQuestionComponent } from './components/question-types/add-dropdown-question/add-dropdown-question.component';
import { AddLineScaleQuestionComponent } from './components/question-types/add-line-scale-question/add-line-scale-question.component';
import { AddLongAnswerQuestionComponent } from './components/question-types/add-long-answer-question/add-long-answer-question.component';
import { AddMultipleChoiceQuestionComponent } from './components/question-types/add-multiple-choice-question/add-multiple-choice-question.component';
import { AddShortAnswerQuestionComponent } from './components/question-types/add-short-answer-question/add-short-answer-question.component';
import { AddSingleChoiceQuestionComponent } from './components/question-types/add-single-choice-question/add-single-choice-question.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
  declarations: [
    AddFormComponent,
    AddQuestionComponent,
    AddDropdownQuestionComponent,
    AddLineScaleQuestionComponent,
    AddLongAnswerQuestionComponent,
    AddMultipleChoiceQuestionComponent,
    AddShortAnswerQuestionComponent,
    AddSingleChoiceQuestionComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    FontAwesomeModule
  ],
  exports: [
    AddFormComponent
  ]
})
export class AddFormModule { }
