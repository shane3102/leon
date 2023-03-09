import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';
import { QuestionResponse } from 'src/app/models/question-response';
import { QuestionTypes } from 'src/app/models/question-types';

@Component({
  selector: 'app-question-bad-ui-bad-ux',
  templateUrl: './question-bad-ui-bad-ux.component.html',
  styleUrls: ['./question-bad-ui-bad-ux.component.css']
})
export class QuestionBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() wholeForm: FormGroup
  @Input() resetFormSubject: Subject<void>;
  @Input() formResultChanged: Subject<FormChangeSubject> = new Subject<FormChangeSubject>();

  @Output() passFormChangeFurther = new EventEmitter<FormChanged>()

  questionFormGroup: FormGroup;

  questionTypes = new QuestionTypes();

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup = new FormGroup({
      'id': new FormControl(this.question.id),
      'chosenOptions': new FormArray([]),
      'answer': new FormControl(),
      'type': new FormControl(this.question.type),
      'durationToAnswerInMilliseconds': new FormControl(0)
    });
    (this.wholeForm.get('answers') as FormArray).push(this.questionFormGroup)
  }

  passFormChangeFurtherMethod(formChanged: FormChanged) {
    this.passFormChangeFurther.emit(formChanged);
  }

}
