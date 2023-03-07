import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, Subscription } from 'rxjs';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-long-answer-bad-ui-bad-ux',
  templateUrl: './long-answer-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './long-answer-bad-ui-bad-ux.component.css']
})
export class LongAnswerBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() resetFormSubject: Observable<void>;
  @Input() formResultChanged: Observable<FormChangeSubject> = new Observable<FormChangeSubject>();

  private resetFormSubscription: Subscription;

  private id: number;
  private type: string;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.controls['answer']?.setValidators([Validators.required]);

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;

    this.resetFormSubscription = this.resetFormSubject.subscribe(() => {
      this.onReset();
    })
  }

  onReset() {
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id))
      this.questionFormGroup.setControl('type', new FormControl(this.type))
    }, 0);
  }
}
