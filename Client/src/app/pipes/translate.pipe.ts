import { Pipe, PipeTransform } from '@angular/core';
import {Locale} from "../domain/ui/locale";
import {AppDataService} from "../services/app-data.service";

@Pipe({
  name: 'translate'
})
export class TranslatePipe implements PipeTransform {
  locales : Locale[]=[
    {ru:'Вход', en:'Sign In'},
    {ru:'Регистрация', en:'Sign Up'},
    {ru:'У меня есть аккаунт', en:'I have an account'},
    {ru:'Логин', en:'Login'},
    {ru:'Пароль', en:'Password'},
    {ru:'Повторите пароль', en:'Repeat the password'},
    {ru:'Пароли не совпадают', en:'Passwords don\'t match'},
    {ru:'Обязательное поле', en:'Required field'},
    {ru:'Почта не соответствует формату', en:'Mail does not match the format'},
    {ru:'Максимальная длина поля ', en:'The maximum field length is '},
    {ru:'Минимальная длина поля ', en:'The minimum field length is '},
  ]
  transform(value : string): string {
    let obj = this.locales.find(x=>x.ru == value);
    if (obj==null){
      return 'Ошибка локализации'
      // return value;
    }

    // @ts-ignore
    return obj[this.appDataService.locale];
  }
  constructor(private appDataService : AppDataService) {
  }

}
