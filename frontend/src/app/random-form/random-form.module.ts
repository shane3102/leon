import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FillRandomFormComponent } from './components/fill-random-form/fill-random-form.component';
import { RandomFormBadUiBadUxComponent } from './components/form-types/random-form-bad-ui-bad-ux/random-form-bad-ui-bad-ux.component';
import { RandomFormBadUiGoodUxComponent } from './components/form-types/random-form-bad-ui-good-ux/random-form-bad-ui-good-ux.component';
import { RandomFormGoodUiBadUxComponent } from './components/form-types/random-form-good-ui-bad-ux/random-form-good-ui-bad-ux.component';
import { RandomFormGoodUiGoodUxComponent } from './components/form-types/random-form-good-ui-good-ux/random-form-good-ui-good-ux.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { DropdownBadUiBadUxComponent } from './components/question-types/bad-ui-bad-ux/concrete/dropdown-bad-ui-bad-ux/dropdown-bad-ui-bad-ux.component';
import { LineScaleBadUiBadUxComponent } from './components/question-types/bad-ui-bad-ux/concrete/line-scale-bad-ui-bad-ux/line-scale-bad-ui-bad-ux.component';
import { LongAnswerBadUiBadUxComponent } from './components/question-types/bad-ui-bad-ux/concrete/long-answer-bad-ui-bad-ux/long-answer-bad-ui-bad-ux.component';
import { MultipleChoiceBadUiBadUxComponent } from './components/question-types/bad-ui-bad-ux/concrete/multiple-choice-bad-ui-bad-ux/multiple-choice-bad-ui-bad-ux.component';
import { ShortAnswerBadUiBadUxComponent } from './components/question-types/bad-ui-bad-ux/concrete/short-answer-bad-ui-bad-ux/short-answer-bad-ui-bad-ux.component';
import { SingleChoiceBadUiBadUxComponent } from './components/question-types/bad-ui-bad-ux/concrete/single-choice-bad-ui-bad-ux/single-choice-bad-ui-bad-ux.component';
import { QuestionBadUiBadUxComponent } from './components/question-types/bad-ui-bad-ux/question-bad-ui-bad-ux/question-bad-ui-bad-ux.component';
import { QuestionBadUiGoodUxComponent } from './components/question-types/bad-ui-good-ux/question-bad-ui-good-ux/question-bad-ui-good-ux.component';
import { DropdownBadUiGoodUxComponent } from './components/question-types/bad-ui-good-ux/concrete/dropdown-bad-ui-good-ux/dropdown-bad-ui-good-ux.component';
import { LineScaleBadUiGoodUxComponent } from './components/question-types/bad-ui-good-ux/concrete/line-scale-bad-ui-good-ux/line-scale-bad-ui-good-ux.component';
import { LongAnswerBadUiGoodUxComponent } from './components/question-types/bad-ui-good-ux/concrete/long-answer-bad-ui-good-ux/long-answer-bad-ui-good-ux.component';
import { MultipleChoiceBadUiGoodUxComponent } from './components/question-types/bad-ui-good-ux/concrete/multiple-choice-bad-ui-good-ux/multiple-choice-bad-ui-good-ux.component';
import { ShortAnswerBadUiGoodUxComponent } from './components/question-types/bad-ui-good-ux/concrete/short-answer-bad-ui-good-ux/short-answer-bad-ui-good-ux.component';
import { SingleChoiceBadUiGoodUxComponent } from './components/question-types/bad-ui-good-ux/concrete/single-choice-bad-ui-good-ux/single-choice-bad-ui-good-ux.component';



@NgModule({
  declarations: [
    FillRandomFormComponent,
    RandomFormBadUiBadUxComponent,
    RandomFormBadUiGoodUxComponent,
    RandomFormGoodUiBadUxComponent,
    RandomFormGoodUiGoodUxComponent,
    DropdownBadUiBadUxComponent,
    LineScaleBadUiBadUxComponent,
    LongAnswerBadUiBadUxComponent,
    MultipleChoiceBadUiBadUxComponent,
    ShortAnswerBadUiBadUxComponent,
    SingleChoiceBadUiBadUxComponent,
    QuestionBadUiBadUxComponent,
    QuestionBadUiGoodUxComponent,
    DropdownBadUiGoodUxComponent,
    LineScaleBadUiGoodUxComponent,
    LongAnswerBadUiGoodUxComponent,
    MultipleChoiceBadUiGoodUxComponent,
    ShortAnswerBadUiGoodUxComponent,
    SingleChoiceBadUiGoodUxComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    FontAwesomeModule
  ],
  exports: [
    FillRandomFormComponent
  ]
})
export class RandomFormModule { }
