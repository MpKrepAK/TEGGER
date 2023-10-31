import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppDataService {
  locale : string = 'ru';
  serverUrl : string = "http://localhost:8080/api"
  constructor() { }
}
