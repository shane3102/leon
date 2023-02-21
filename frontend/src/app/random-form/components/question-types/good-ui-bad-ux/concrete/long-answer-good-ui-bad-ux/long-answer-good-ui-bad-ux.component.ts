import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-long-answer-good-ui-bad-ux',
  templateUrl: './long-answer-good-ui-bad-ux.component.html',
  styleUrls: ['../../style/good-ui-bad-ux-style.css', './long-answer-good-ui-bad-ux.component.css']
})
export class LongAnswerGoodUiBadUxComponent implements OnInit {

  
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
      this.questionFormGroup.setControl('id', new FormControl(this.id))
      this.questionFormGroup.setControl('type', new FormControl(this.type))
    }, 0);
  }

}
