import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { ButtonComponent } from './components/header-button/header-button.component';
import { MainPageButtonComponent } from './components/main-page-button/main-page-button.component';
import { FooterComponent } from './components/footer/footer.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { UserAuthModule } from './user-auth/user-auth.module';
import { LoginStatusComponent } from './components/login-status/login-status.component';
import { FormModule } from './form/form.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainPageComponent,
    MainPageButtonComponent,
    FooterComponent,
    ButtonComponent,
    LoginStatusComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FontAwesomeModule,
    UserAuthModule,
    FormModule
  ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true },
    JwtHelperService,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
