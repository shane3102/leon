import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-dropdown-bad-ui-good-ux',
  templateUrl: './dropdown-bad-ui-good-ux.component.html',
  styleUrls: ['./dropdown-bad-ui-good-ux.component.css']
})
export class DropdownBadUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
