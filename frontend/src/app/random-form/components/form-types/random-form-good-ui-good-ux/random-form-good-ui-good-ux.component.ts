import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-random-form-good-ui-good-ux',
  templateUrl: './random-form-good-ui-good-ux.component.html',
  styleUrls: ['./random-form-good-ui-good-ux.component.css']
})
export class RandomFormGoodUiGoodUxComponent implements OnInit {

  @Output() formSentEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

}
