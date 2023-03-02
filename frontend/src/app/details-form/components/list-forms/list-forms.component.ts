import { Component, OnInit } from '@angular/core';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons'

enum SortDirection {
  DESC = "DESC", ASC = "ASC", NONE = "NONE"
}

@Component({
  selector: 'app-list-forms',
  templateUrl: './list-forms.component.html',
  styleUrls: ['./list-forms.component.css']
})
export class ListFormsComponent implements OnInit {

  sortedColumnName: string = 'dateAdded'
  sortDirection: string = SortDirection.DESC

  sortIcon = faSort;
  sortIconDesc = faSortDown;
  sortIconAsc = faSortUp;

  constructor() { }

  ngOnInit(): void {
  }

  sort(column: string) {

    if (this.sortedColumnName == column) {
      switch (this.sortDirection) {
        case SortDirection.DESC:
          this.sortDirection = SortDirection.ASC;
          break;
        case SortDirection.ASC:
          this.sortDirection = SortDirection.NONE
          break;
        case SortDirection.NONE:
          this.sortDirection = SortDirection.DESC;
          break;
      }
    }
    this.sortedColumnName = column;

    console.log(this.sortedColumnName + " " + this.sortDirection)

  }

}
