import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {MedicalServicesComponent} from './medical-services/medical-services.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {ContactsComponent} from './contacts/contacts.component';
import {AboutUsComponent} from './about-us/about-us.component';
import {MedicalServiceCardComponent} from './medical-services/medical-service-card/medical-service-card.component';
import {FooterComponent} from './footer/footer.component';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {ProfileComponent} from './profile/profile.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {UserProfileComponent} from './profile/user-profile/user-profile.component';
import {MakeAppointmentsComponent} from './profile/make-appointments/make-appointments.component';
import {AppointmentsHistoryComponent} from './profile/appointments-history/appointments-history.component';
import {AuthInterceptor} from "./interceptors/auth-interceptor";
import {UserInfoComponent} from './profile/user-profile/user-info/user-info.component';
import {PetsInfoComponent} from './profile/user-profile/pets-info/pets-info.component';
import { PetCardComponent } from './profile/user-profile/pets-info/pet-card/pet-card.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MedicalServicesComponent,
    NotFoundComponent,
    ContactsComponent,
    AboutUsComponent,
    MedicalServiceCardComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    ProfileComponent,
    UserProfileComponent,
    MakeAppointmentsComponent,
    AppointmentsHistoryComponent,
    UserInfoComponent,
    PetsInfoComponent,
    PetCardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FormsModule,
    MatDatepickerModule,
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
