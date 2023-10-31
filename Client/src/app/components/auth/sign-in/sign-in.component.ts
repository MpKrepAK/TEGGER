import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthDTO} from "../../../domain/dto/authDTO";
import {AppDataService} from "../../../services/app-data.service";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css', '../../../../styles/auth.css']
})
export class SignInComponent {
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
    ])
  });

  signInDTO : AuthDTO = {login : '', password : ''};

  constructor(public appDataService : AppDataService) {
  }
  signIn() {
    this.signInDTO.login = this.form.get('login')?.value;
    this.signInDTO.password = this.form.get('password')?.value;
    console.log(this.signInDTO);
  }
}
