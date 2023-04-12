import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  constructor(private fb: FormBuilder, private auth: AuthService) {}

  hide = true;
  signupForm!: FormGroup;

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      username: this.fb.control('', [Validators.required]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      password: this.fb.control(
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern(
            '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$'
          ),
        ],
      ),
    });
  }

  getErrorsControl(name: string, error: string) {
    return this.signupForm.get(name)?.errors![error];
  }

  getFormControl(name: string) {
    return this.signupForm.get(name);
  }

  onSubmit() {
    if(this.signupForm.valid){
    }
  }

}
