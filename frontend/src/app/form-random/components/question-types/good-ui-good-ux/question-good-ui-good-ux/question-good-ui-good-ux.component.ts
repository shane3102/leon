import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { QuestionResponse } from 'src/app/form-random/models/question-response';
import { QuestionTypes } from 'src/app/models/question-types';

@Component({
  selector: 'app-question-good-ui-good-ux',
  templateUrl: './question-good-ui-good-ux.component.html',
  styleUrls: ['./question-good-ui-good-ux.component.css']
})
export class QuestionGoodUiGoodUxComponent implements OnInit {
  @Input() question: QuestionResponse;
  @Input() wholeForm: FormGroup;
  @Input() triedSubmiting: Subject<void>;

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
