import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthDTO} from "../../../domain/dto/authDTO";
import {AppDataService} from "../../../services/app-data.service";
import {ServerMessageDTO} from "../../../domain/dto/serverMessageDTO";
import {UserDTO} from "../../../domain/dto/userDTO";
import {HttpClient} from "@angular/common/http";

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

  constructor(public appDataService : AppDataService, private appData : AppDataService, private http : HttpClient) {
  }
  signIn() {
    this.signInDTO.login = this.form.get('login')?.value;
    this.signInDTO.password = this.form.get('password')?.value;

    this.http.post<ServerMessageDTO>(this.appData.serverUrl + "/login/signin", <AuthDTO>{login: this.signInDTO.login, password: this.signInDTO.password}).subscribe(x=>{
      if (x.statusCode===200){
        this.http.post<UserDTO>(this.appData.serverUrl + "/users/login", this.signInDTO).subscribe(x=>{
          this.appData.user = x;
          console.log(x);
        });
      }
    });
    console.log("qwe")
  }
}
