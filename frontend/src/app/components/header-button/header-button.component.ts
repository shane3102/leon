import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-button',
  templateUrl: './header-button.component.html',
  styleUrls: ['./header-button.component.css']
})
export class ButtonComponent implements OnInit {
  @Input() color: String;
  @Input() textColor: String;
  @Input() text: String;


  constructor() { }

  ngOnInit(): void {
  }

  public logout() {
    localStorage.clear();
  }

}
