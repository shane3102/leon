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
import { QuestionGoodUiBadUxComponent } from './components/question-types/good-ui-bad-ux/question-good-ui-bad-ux/question-good-ui-bad-ux.component';
import { DropdownGoodUiBadUxComponent } from './components/question-types/good-ui-bad-ux/concrete/dropdown-good-ui-bad-ux/dropdown-good-ui-bad-ux.component';
import { LineScaleGoodUiBadUxComponent } from './components/question-types/good-ui-bad-ux/concrete/line-scale-good-ui-bad-ux/line-scale-good-ui-bad-ux.component';
import { LongAnswerGoodUiBadUxComponent } from './components/question-types/good-ui-bad-ux/concrete/long-answer-good-ui-bad-ux/long-answer-good-ui-bad-ux.component';
import { MultipleChoiceGoodUiBadUxComponent } from './components/question-types/good-ui-bad-ux/concrete/multiple-choice-good-ui-bad-ux/multiple-choice-good-ui-bad-ux.component';
import { ShortAnswerGoodUiBadUxComponent } from './components/question-types/good-ui-bad-ux/concrete/short-answer-good-ui-bad-ux/short-answer-good-ui-bad-ux.component';
import { SingleChoiceGoodUiBadUxComponent } from './components/question-types/good-ui-bad-ux/concrete/single-choice-good-ui-bad-ux/single-choice-good-ui-bad-ux.component';
import { MatSelectModule } from '@angular/material/select'
import { MatRadioModule } from '@angular/material/radio'
import { MatCheckboxModule } from '@angular/material/checkbox'
import { MatInputModule } from '@angular/material/input'
import { MatButtonModule } from '@angular/material/button';
import { QuestionGoodUiGoodUxComponent } from './components/question-types/good-ui-good-ux/question-good-ui-good-ux/question-good-ui-good-ux.component';
import { DropdownGoodUiGoodUxComponent } from './components/question-types/good-ui-good-ux/concrete/dropdown-good-ui-good-ux/dropdown-good-ui-good-ux.component';
import { LineScaleGoodUiGoodUxComponent } from './components/question-types/good-ui-good-ux/concrete/line-scale-good-ui-good-ux/line-scale-good-ui-good-ux.component';
import { LongAnswerGoodUiGoodUxComponent } from './components/question-types/good-ui-good-ux/concrete/long-answer-good-ui-good-ux/long-answer-good-ui-good-ux.component';
import { MultipleChoiceGoodUiGoodUxComponent } from './components/question-types/good-ui-good-ux/concrete/multiple-choice-good-ui-good-ux/multiple-choice-good-ui-good-ux.component';
import { ShortAnswerGoodUiGoodUxComponent } from './components/question-types/good-ui-good-ux/concrete/short-answer-good-ui-good-ux/short-answer-good-ui-good-ux.component';
import { SingleChoiceGoodUiGoodUxComponent } from './components/question-types/good-ui-good-ux/concrete/single-choice-good-ui-good-ux/single-choice-good-ui-good-ux.component';
import { AutosizeModule } from 'ngx-autosize';

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
    QuestionGoodUiBadUxComponent,
    DropdownGoodUiBadUxComponent,
    LineScaleGoodUiBadUxComponent,
    LongAnswerGoodUiBadUxComponent,
    MultipleChoiceGoodUiBadUxComponent,
    ShortAnswerGoodUiBadUxComponent,
    SingleChoiceGoodUiBadUxComponent,
    QuestionGoodUiGoodUxComponent,
    DropdownGoodUiGoodUxComponent,
    LineScaleGoodUiGoodUxComponent,
    LongAnswerGoodUiGoodUxComponent,
    MultipleChoiceGoodUiGoodUxComponent,
    ShortAnswerGoodUiGoodUxComponent,
    SingleChoiceGoodUiGoodUxComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    MatSelectModule,
    MatRadioModule,
    MatCheckboxModule,
    MatInputModule,
    MatRadioModule,
    MatButtonModule,
    AutosizeModule
  ],
  exports: [
    FillRandomFormComponent
  ]
})
export class RandomFormModule { }
