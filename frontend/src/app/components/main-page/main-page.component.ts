import { Component, OnInit } from '@angular/core';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { faList, faListAlt, faListOl, faListUl, faClipboardCheck, faChartBar, faClipboardList } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  listIcon = faList;
  listAltIcon = faListAlt;
  listOlIcon = faListOl;
  listUlIcon = faListUl;
  faClipboardCheck = faClipboardCheck;
  chart = faChartBar
  faClipboardList = faClipboardList;

  constructor(private jwtClientService: JwtClientService) { }

  ngOnInit(): void {
  }

  public isLogged(): boolean {
    return this.jwtClientService.isLogged();
  }

  public getUsername(): string {
    return localStorage.getItem("username") == null ? "" : localStorage.getItem("username") as string
  }

}
