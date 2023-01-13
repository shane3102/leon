import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-button',
  templateUrl: './headerButton.component.html',
  styleUrls: ['./headerButton.component.css']
})
export class ButtonComponent implements OnInit {
  @Input() color: String;
  @Input() textColor: String;
  @Input() text: String;


  constructor() { }

  ngOnInit(): void {
  }

}
