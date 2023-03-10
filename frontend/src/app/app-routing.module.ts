import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './user-auth/components/login-page/login-page.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { RegisterPageComponent } from './user-auth/components/register-page/register-page.component';
import { AddFormComponent } from './form-add/components/add-form/add-form.component';
import { AuthGuard } from './authGuard/auth.service';
import { FillRandomFormComponent } from './form-random/components/fill-random-form/fill-random-form.component';
import { ListFormsComponent } from './form-details/components/list-forms/list-forms.component';
import { FormStatisticsDetailsComponent } from './form-details/components/form-statistics-details/form-statistics-details.component';
import { FormFillSingleComponent } from './form-details/components/form-fill-single/form-fill-single.component';
import { FormRandomStatisticsComponent } from './form-random-statistics/components/form-random-statistics/form-random-statistics.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'main-page', component: MainPageComponent },
  { path: 'login-page', component: LoginPageComponent },
  { path: 'register-page', component: RegisterPageComponent },
  { path: 'add-form', component: AddFormComponent, canActivate: [AuthGuard] },
  { path: 'random-form', component: FillRandomFormComponent },
  { path: 'list', component: ListFormsComponent },
  { path: 'list/:username', component: ListFormsComponent, canActivate: [AuthGuard] },
  { path: 'form-details/:id', component: FormStatisticsDetailsComponent },
  { path: 'form/:id', component: FormFillSingleComponent },
  { path: 'random-form-statistics', component: FormRandomStatisticsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
