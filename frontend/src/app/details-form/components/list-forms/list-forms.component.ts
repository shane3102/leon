import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons'
import { Observable, of } from 'rxjs';
import { FormSnippetResponse } from '../../models/form-snippet-response';
import { FormDetailsService } from '../../services/form-details-service';

enum SortDirection {
  DESC = "DESC", ASC = "ASC", NONE = "NONE"
}

@Component({
  selector: 'app-list-forms',
  templateUrl: './list-forms.component.html',
  styleUrls: ['./list-forms.component.css']
})
export class ListFormsComponent implements OnInit {

  isDataLoaded: Observable<boolean> = of(false);

  totalItems: number;
  pageNumber: number = 0;
  itemsPerPage: number = 10;
  sortedColumnName: string = 'dateAdded'
  sortDirection: string = SortDirection.DESC
  displayedFormSnippets: FormSnippetResponse[];

  sortIcon = faSort;
  sortIconDesc = faSortDown;
  sortIconAsc = faSortUp;

  constructor(private formDetailsService: FormDetailsService, private router: Router) { }

  ngOnInit(): void {
    this.getPaginatedForms();
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

    this.getPaginatedForms()
  }

  getPaginatedForms() {
    this.isDataLoaded = of(false);

    this.formDetailsService.listForms(this.pageNumber, this.itemsPerPage, this.sortedColumnName, this.sortDirection).subscribe({
      next: (response) => {
        this.displayedFormSnippets = response.content;
        this.totalItems = response.totalElements;
        this.isDataLoaded = of(true);
      }
    })

  }

  changeAmountOfItemsPerPage(newItemsPerPage: number) {
    this.itemsPerPage = newItemsPerPage
    this.getPaginatedForms()
  }

  pageChange(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.getPaginatedForms()
  }

  checkStatistics(formId: number) {
    this.router.navigateByUrl("/form-details/" + formId)
  }

}
