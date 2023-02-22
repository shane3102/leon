import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { QuestionTypes } from 'src/app/models/question-types';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-question-good-ui-bad-ux',
  templateUrl: './question-good-ui-bad-ux.component.html',
  styleUrls: ['./question-good-ui-bad-ux.component.css']
})
export class QuestionGoodUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() wholeForm: FormGroup;
  @Input() resetFormSubject: Subject<void>;
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

}
