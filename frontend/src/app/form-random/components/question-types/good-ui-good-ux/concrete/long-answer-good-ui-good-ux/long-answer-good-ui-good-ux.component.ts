import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-long-answer-good-ui-good-ux',
  templateUrl: './long-answer-good-ui-good-ux.component.html',
  styleUrls: ['../../style/good-ui-good-ux-style.css', './long-answer-good-ui-good-ux.component.css']
})
export class LongAnswerGoodUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() triedSubmiting: Observable<void>;

  private triedSubmittingSubscription: Subscription;

  focus: boolean;

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
