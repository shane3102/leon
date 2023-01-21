import { Component, OnInit } from '@angular/core';
import { JwtClientService } from 'src/app/services/jwt-client.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private jwtClientService: JwtClientService) { }

  ngOnInit(): void {
  }

  public isLogged(): boolean {
    return this.jwtClientService.isLogged();
  }

}
