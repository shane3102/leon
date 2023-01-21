import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './user-auth/components/login-page/login-page.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { RegisterPageComponent } from './user-auth/components/register-page/register-page.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'main-page', component: MainPageComponent },
  { path: 'siema', component: MainPageComponent },
  { path: 'login-page', component: LoginPageComponent },
  { path: 'register-page', component: RegisterPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
