import { Component, Input, OnInit } from '@angular/core';
import { faCaretDown, faList, faListAlt, faListOl, faListUl } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-main-page-button',
  templateUrl: './main-page-button.component.html',
  styleUrls: ['./main-page-button.component.css']
})
export class MainPageButtonComponent implements OnInit {
  @Input() text: string;
  @Input() description: string;
  @Input() iconClass: string;

  downIcon = faCaretDown;
  listIcon = faList;
  listAltIcon = faListAlt;
  listOlIcon = faListOl;
  listUlIcon = faListUl;

  constructor() { }

  ngOnInit(): void {
  }

}
