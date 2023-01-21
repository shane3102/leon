import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterPageComponent} from './components/register-page/register-page.component';



@NgModule({
  declarations: [
    LoginPageComponent,
    RegisterPageComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    LoginPageComponent,
    RegisterPageComponent,
  ]
})
export class UserAuthModule { }
