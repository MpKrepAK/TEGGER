import {Component} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {EGender} from "../../../domain/enums/eGender";
import {HttpClient} from "@angular/common/http";
import {AppDataService} from "../../../services/app-data.service";
import {ServerMessageDTO} from "../../../domain/dto/serverMessageDTO";
import {UserDTO} from "../../../domain/dto/userDTO";
import {AuthDTO} from "../../../domain/dto/authDTO";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css', '../../../../styles/auth.css']
})
export class SignUpComponent {

  user : UserDTO= {
    firstName: "", gender: EGender.MALE, id: 0, lastName: "", login: "", password: '', permissions: [], phoneNumber: ""

  }
  constructor(private appData : AppDataService, private http : HttpClient) {


  }

  form : FormGroup= new FormGroup({
    login : new FormControl('', [
      Validators.required,
      Validators.email,
      Validators.maxLength(255),
      Validators.minLength(3)
    ]),
    password : new FormControl('', [
      Validators.required,
      Validators.maxLength(255),
      Validators.minLength(6)
    ]),
    firstName : new FormControl('', [
      Validators.required,
      Validators.maxLength(255),
      Validators.minLength(2)
    ]),
    lastName : new FormControl('', [
      Validators.required,
      Validators.maxLength(255),
      Validators.minLength(2)
    ]),
    phoneNumber : new FormControl('', [
      Validators.required
    ]),
    confirmPassword: new FormControl('', [
      Validators.required
    ]),
      gender: new FormControl('', Validators.required)
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

  signUp() {
    console.log(this.form.get('gender')?.value);
    this.user.firstName = this.form.get('firstName')?.value;
    this.user.lastName = this.form.get('lastName')?.value;
    this.user.login = this.form.get('login')?.value;
    this.user.password = this.form.get('password')?.value;
    this.user.phoneNumber = this.form.get('phoneNumber')?.value+'';
    this.user.gender = this.form.get('gender')?.value;

    this.http.post<ServerMessageDTO>(this.appData.serverUrl + "/login/signup", <AuthDTO>{login: this.user.login, password: this.user.password}).subscribe(x=>{
      if (x.statusCode===200){
        this.http.post<UserDTO>(this.appData.serverUrl + "/users/register", this.user).subscribe(x=>{
          this.appData.user = x;
          console.log(x);
        });
      }
    });
    console.log("qwe")
  }

  protected readonly EGender = EGender;
}
