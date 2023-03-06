import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-short-answer-good-ui-bad-ux',
  templateUrl: './short-answer-good-ui-bad-ux.component.html',
  styleUrls: ['../../style/good-ui-bad-ux-style.css','./short-answer-good-ui-bad-ux.component.css']
})
export class ShortAnswerGoodUiBadUxComponent implements OnInit {
  
  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() resetFormSubject: Observable<void>;

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
      this.questionFormGroup.setControl('answer', new FormControl(""))
      this.questionFormGroup.setControl('id', new FormControl(this.id))
      this.questionFormGroup.setControl('type', new FormControl(this.type))
    }, 0);
  }

}
