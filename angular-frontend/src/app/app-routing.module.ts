import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './auth/guard/auth.guard';
import { SectionPageComponent } from './components/section-page/section-page.component';
import { DepartmentPageComponent } from './components/department-page/department-page.component';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { StructPageComponent } from './components/struct-page/struct-page.component';
import { TeamPageComponent } from './components/team-page/team-page.component';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard],
  },{
    path: 'profile',
    component: ProfilePageComponent,
    canActivate: [AuthGuard],
  },{
    path: 'employees',
    component: EmployeePageComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'structure/:key',
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: StructPageComponent,
      },{
        path: 'department/:id',
        component: DepartmentPageComponent,
      },{
        path: 'section/:id',
        component: SectionPageComponent,
      },{
        path: 'team/:id',
        component: TeamPageComponent,
      }
    ],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
