import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './auth/guard/auth.guard';
import { SectionPageComponent } from './components/section-page/section-page.component';
import { DepartmentPageComponent } from './components/department-page/department-page.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'department/:id',
    component: DepartmentPageComponent,
  },{
    path: 'department/:id/section/:id',
    component: SectionPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
