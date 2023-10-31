import { Component } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css', '../../../../styles/auth.css']
})
export class SignUpComponent {
  form : FormGroup= new FormGroup({
    login : new FormControl('f', [
      Validators.required,
      Validators.email,
      Validators.maxLength(255),
      Validators.minLength(3)
    ]),
    password : new FormControl('qwe', [
      Validators.required,
      Validators.maxLength(255),
      Validators.minLength(6)
    ]),
    confirmPassword: new FormControl('', [
      Validators.required
    ])
  }, {
    validators: this.passwordMatchValidator
  });

  passwordMatchValidator(control: AbstractControl) {
    const password = control.get('password')?.value;
    const confirmPassword = control?.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      control.get('confirmPassword')?.setErrors({ passwordMatch: true });
    } else {
      control.get('confirmPassword')?.setErrors(null);
    }

    return null;
  }

  signIn() {
    // this.signInDTO.login = this.form.get('login')?.value;
    // this.signInDTO.password = this.form.get('password')?.value;
    // console.log(this.signInDTO);
    console.log("qwe")
  }
}
