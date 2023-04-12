import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ModuleRoutingModule } from './module-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt';
import {MatInputModule} from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from '../components/login/login.component';
import { SignupComponent } from '../components/signup/signup.component';


@NgModule({
  declarations: [LoginComponent, SignupComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    JwtModule,
    MatInputModule,
    MatIconModule,
    ModuleRoutingModule,
    MatButtonModule,
    ReactiveFormsModule
  ]
})
export class ModuleModule { }
