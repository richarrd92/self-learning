import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SelectCompanyComponent } from './select-company/select-company.component';
import { AnnouncementsComponent } from './announcements/announcements.component';
import { Teams } from './teams/teams';
import { Projects } from './projects/projects';
import { UsersComponent } from './users/users.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'select-company', component: SelectCompanyComponent },
  { path: 'company', component: SelectCompanyComponent },
  { path: 'announcements', component: AnnouncementsComponent },
  { path: 'teams', component: Teams },
  { path: 'projects', component: Projects },
  { path: 'users', component: UsersComponent },
  { path: '**', redirectTo: '' }
];
