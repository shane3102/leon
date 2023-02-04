import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-random-form-bad-ui-good-ux',
  templateUrl: './random-form-bad-ui-good-ux.component.html',
  styleUrls: ['./random-form-bad-ui-good-ux.component.css']
})
export class RandomFormBadUiGoodUxComponent implements OnInit {

  @Output() formSentEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

}
