import {EGender} from "../enums/eGender";

export interface UserDTO{
  id : number,
  firstName : string,
  lastName : string,
  phoneNumber : string,
  gender : EGender,
  login : string,
    password : string,
  permissions : string[]
}
