import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormToCompleteResponse } from 'src/app/models/form-to-complete-response';
import { FormResponse } from '../../models/form-response';
import { FormDetailsService } from '../../services/form-details-service';

@Component({
  selector: 'app-form-fill-single',
  templateUrl: './form-fill-single.component.html',
  styleUrls: ['./form-fill-single.component.css']
})
export class FormFillSingleComponent implements OnInit {

  formId: number;
  formResponse: FormResponse;
  formToComplete: FormToCompleteResponse;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formDetailsService: FormDetailsService) {
    this.route.params.subscribe(
      params => {
        this.formId = params['id'];
        if (this.formId == undefined) {
          this.router.navigateByUrl('/main-page');
        }
        this.formDetailsService.getConcreteForm(this.formId).subscribe({
          next: res => {
            this.formResponse = res;
            this.formToComplete = new FormToCompleteResponse()
            this.formToComplete.questions = this.formResponse.questions;
          }
        })
      }
    )
  }

  ngOnInit(): void {
  }

  goToMainPage(){
    this.router.navigateByUrl('/main-page');
  }

}
