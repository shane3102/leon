import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormStatisticsResponse } from '../../models/form-statistics-response';
import { FormDetailsService } from '../../services/form-details-service';

@Component({
  selector: 'app-form-statistics-details',
  templateUrl: './form-statistics-details.component.html',
  styleUrls: ['./form-statistics-details.component.css']
})
export class FormStatisticsDetailsComponent implements OnInit {

  private id: number;
  formDetails: FormStatisticsResponse;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formDetailsService: FormDetailsService) {
    this.route.params.subscribe(
      params => this.id = params['id']
    )
  }

  ngOnInit(): void {
    this.formDetailsService.getFormStatistics(this.id)
    .subscribe({
      error: () => {
        this.router.navigateByUrl('/main-page');
      },
      next: (res) => {
        this.formDetails = res;
      }
    })
  }

}
