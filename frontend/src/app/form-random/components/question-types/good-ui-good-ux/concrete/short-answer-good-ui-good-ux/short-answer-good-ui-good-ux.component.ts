import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/form-random/models/question-response';

@Component({
  selector: 'app-short-answer-good-ui-good-ux',
  templateUrl: './short-answer-good-ui-good-ux.component.html',
  styleUrls: ['../../style/good-ui-good-ux-style.css', './short-answer-good-ui-good-ux.component.css']
})
export class ShortAnswerGoodUiGoodUxComponent implements OnInit {
  
  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() triedSubmiting: Observable<void>;

  private triedSubmittingSubscription: Subscription;

  constructor() { }

   ngOnInit(): void { 
    this.questionFormGroup.controls['answer']?.setValidators([Validators.required]);
    this.triedSubmittingSubscription = this.triedSubmiting.subscribe(() => {
      this.questionFormGroup.controls['answer'].markAsTouched();
    })
  }

  touchedNotFilled(): boolean {
    return (this.questionFormGroup.controls['answer'] && this.questionFormGroup.controls['answer'].touched && this.questionFormGroup.controls['answer'].errors?.['required'])
  }

  touchedValid(): boolean {
    return (this.questionFormGroup.controls['answer'] && this.questionFormGroup.controls['answer'].touched && this.questionFormGroup.controls['answer'].valid)
  }

}
