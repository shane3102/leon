import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { JwtClientService } from "../services/jwt-client.service";

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private jwtClientService: JwtClientService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        if (this.jwtClientService.isLogged() == false) {
            this.router.navigateByUrl('main-page');
            return false;
        }
        return true;
    }
}