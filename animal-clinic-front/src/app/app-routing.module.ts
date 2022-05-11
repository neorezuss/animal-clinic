import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { MedicalServicesComponent } from "./medical-services/medical-services.component";
import { NotFoundComponent } from "./not-found/not-found.component";
import { ContactsComponent } from "./contacts/contacts.component";
import { AboutUsComponent } from "./about-us/about-us.component";
import { RegisterComponent } from "./register/register.component";
import { LoginComponent } from "./login/login.component";
import { ProfileComponent } from "./profile/profile.component";
import {UserProfileComponent} from "./profile/user-profile/user-profile.component";
import {MakeAppointmentsComponent} from "./profile/make-appointments/make-appointments.component";
import {AppointmentsHistoryComponent} from "./profile/appointments-history/appointments-history.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'medical-services', component: MedicalServicesComponent },
  { path: 'not-found', component: NotFoundComponent },
  { path: 'contacts', component: ContactsComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent, children: [
      { path: 'user-profile', component: UserProfileComponent },
      { path: 'make-appointments', component: MakeAppointmentsComponent },
      { path: 'appointments-history', component: AppointmentsHistoryComponent },
    ] },
  { path: '', component: HomeComponent },
  { path: '**', redirectTo: 'not-found' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
