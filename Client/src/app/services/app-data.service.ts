import { Injectable } from '@angular/core';
import {UserDTO} from "../domain/dto/userDTO";

@Injectable({
  providedIn: 'root'
})
export class AppDataService {
  locale : string = 'ru';
  serverUrl : string = "http://localhost:8080/api"
  user! : UserDTO;
  constructor() { }
}
