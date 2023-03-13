import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormStatisticsResponse } from '../../models/form-statistics-response';
import { FormDetailsService } from '../../services/form-details-service';
import { faDownload } from '@fortawesome/free-solid-svg-icons'
import { CsvService } from 'src/app/services/csv-service';

@Component({
  selector: 'app-form-statistics-details',
  templateUrl: './form-statistics-details.component.html',
  styleUrls: ['./form-statistics-details.component.css']
})
export class FormStatisticsDetailsComponent implements OnInit {

  private id: number;
  formDetails: FormStatisticsResponse;

  faDownload = faDownload;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formDetailsService: FormDetailsService,
    private csvService: CsvService) {
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

  downloadReportsAllAnswers() {
    this.csvService.getCompletedFormResultsByIdOfAllForms(this.id);
    // this.csvService.getFormCompletedCsvReport();
    // this.csvService.getAnsweredQuestionsCsvReport();
  }
  
  downloadReportsRandomFormsAnswers(){
    this.csvService.getCompletedFormResultsByIdOfRandomForms(this.id);
  }
  
  downloadReportsOnlyThisFormAnswers(){
    this.csvService.getCompletedFormResultsById(this.id);
  }
}
