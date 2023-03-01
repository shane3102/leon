import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './user-auth/components/login-page/login-page.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { RegisterPageComponent } from './user-auth/components/register-page/register-page.component';
import { AddFormComponent } from './add-form/components/add-form/add-form.component';
import { AuthGuard } from './authGuard/auth.service';
import { FillRandomFormComponent } from './random-form/components/fill-random-form/fill-random-form.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'main-page', component: MainPageComponent },
  { path: 'login-page', component: LoginPageComponent },
  { path: 'register-page', component: RegisterPageComponent },
  { path: 'add-form', component: AddFormComponent, canActivate: [AuthGuard]},
  { path: 'random-form', component: FillRandomFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
