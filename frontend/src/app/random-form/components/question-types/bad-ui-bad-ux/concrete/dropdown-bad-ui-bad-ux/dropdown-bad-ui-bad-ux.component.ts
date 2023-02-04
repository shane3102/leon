import { Component, Input, OnInit } from '@angular/core';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-dropdown-bad-ui-bad-ux',
  templateUrl: './dropdown-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './dropdown-bad-ui-bad-ux.component.css']
})
export class DropdownBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
