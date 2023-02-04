import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-random-form-good-ui-bad-ux',
  templateUrl: './random-form-good-ui-bad-ux.component.html',
  styleUrls: ['./random-form-good-ui-bad-ux.component.css']
})
export class RandomFormGoodUiBadUxComponent implements OnInit {

  @Output() formSentEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

}
