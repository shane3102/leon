import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { QuestionTypes } from 'src/app/models/question-types';
import { QuestionResponse } from 'src/app/models/question-response';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';

@Component({
  selector: 'app-question-bad-ui-good-ux',
  templateUrl: './question-bad-ui-good-ux.component.html',
  styleUrls: ['./question-bad-ui-good-ux.component.css']
})
export class QuestionBadUiGoodUxComponent implements OnInit {
  @Input() question: QuestionResponse;
  @Input() wholeForm: FormGroup;
  @Input() triedSubmiting: Subject<void>;
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
      'durationToAnswer': new FormControl(null)//TODO liczenie tego
    });
    (this.wholeForm.get('answers') as FormArray).push(this.questionFormGroup)
  }

  passFormChangeFurtherMethod(formChanged: FormChanged) {
    this.passFormChangeFurther.emit(formChanged);
  }
}
