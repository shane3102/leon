import { Component, OnInit } from '@angular/core';
import { CsvService } from 'src/app/services/csv-service';
import { faDownload } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-form-random-download-csv',
  templateUrl: './form-random-download-csv.component.html',
  styleUrls: ['./form-random-download-csv.component.css']
})
export class FormRandomDownloadCsvComponent implements OnInit {

  faDownload = faDownload;

  constructor(private csvService: CsvService) { }

  ngOnInit(): void {
  }


  getFormStatistics() {
    this.csvService.getFormCompletedCsvReport();
  }

  getQuestionsStatistics() {
    this.csvService.getAnsweredQuestionsCsvReport();
  }

}
