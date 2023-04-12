import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { LoginDto } from '../../interface/login-dto.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  hide = true;
  loginForm: FormGroup;
  constructor(private fb: FormBuilder, private auth: AuthService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern(
            '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$'
          ),
        ],
      ],
    });
  }

  ngOnInit(): void {}

  getErrorsControl(name: string, error: string) {
    return this.loginForm.get(name)?.errors![error];
  }

  getFormControl(name: string) {
    return this.loginForm.get(name);
  }

  onSubmit() {
    if(this.loginForm.valid){
      console.log('submit', this.loginForm.value);
      let user: LoginDto = {
        username : this.loginForm.value.username!,
        password : this.loginForm.value.password!
      }
      this.auth.login(user).subscribe(data => console.log(data));
    }
  }
}
