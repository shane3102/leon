import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-short-answer-bad-ui-good-ux',
  templateUrl: './short-answer-bad-ui-good-ux.component.html',
  styleUrls: ['../../style/bad-ui-good-ux-style.css', './short-answer-bad-ui-good-ux.component.css']
})
export class ShortAnswerBadUiGoodUxComponent implements OnInit {

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
